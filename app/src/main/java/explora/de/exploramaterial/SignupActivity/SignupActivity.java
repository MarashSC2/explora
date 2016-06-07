package explora.de.exploramaterial.SignupActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import explora.de.exploramaterial.MainActivity.MainActivity;
import explora.de.exploramaterial.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import explora.de.exploramaterial.user.entity.User;
import explora.de.exploramaterial.user.service.UserService;

/**
 * Verwaltet den Signup und verweßt bei Bedarf zurück auf die Loginactivity
 */
public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    public static final String PREFS_LOGIN = "login_prefs";
    private UserService userService;

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        userService=new UserService(getApplicationContext());
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                         finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(userService.signUp(new User(email,password,name))){
                            onSignupSuccess();
                        }else{
                            onSignupFailed();
                        }
                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    public void onSignupSuccess() {
        SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("logged", "logged");
        editor.putString("userName", _emailText.getText().toString());
        editor.commit();

        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        _signupButton.setEnabled(true);

        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Email exists", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
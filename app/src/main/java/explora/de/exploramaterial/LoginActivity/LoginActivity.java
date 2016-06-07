package explora.de.exploramaterial.LoginActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import explora.de.exploramaterial.SignupActivity.SignupActivity;
import explora.de.exploramaterial.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import explora.de.exploramaterial.MainActivity.MainActivity;
import explora.de.exploramaterial.user.entity.User;
import explora.de.exploramaterial.user.service.UserService;

/**
 * Verwaltet den Login und verweißt bei Bedarf auf Main- und Signupactivity
 */
public class LoginActivity extends AppCompatActivity {
    public static final String PREFS_LOGIN = "login_prefs";

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private UserService userService;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        //Speichern des angemeldeten Benutzers im Speicher
        SharedPreferences settings = getSharedPreferences(PREFS_LOGIN,0);
        Log.d(TAG,"OnCreate Login is: "+settings.getString("logged", "").toString());

        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        userService=new UserService(getApplicationContext());
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {

        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(userService.isLoginCorrect(new User(email,password,null))){
                            onLoginSuccess();
                        }else{
                            onLoginFailed();
                        }
                        progressDialog.dismiss();
                    }
                }, 1000);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("logged", "logged");
                editor.putString("userName", _emailText.getText().toString());
                editor.commit();
                this.finish();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREFS_LOGIN,0);
        Log.d(TAG,"On resume login is: "+settings.getString("logged", "").toString());

        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        SharedPreferences settings = getSharedPreferences(PREFS_LOGIN, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("logged", "logged");
        editor.putString("userName", _emailText.getText().toString());
        editor.commit();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

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
package explora.de.exploramaterial.user.service;

import android.content.Context;

import explora.de.exploramaterial.database.DatabaseHelper;
import explora.de.exploramaterial.user.dao.UserDAO;
import explora.de.exploramaterial.user.entity.User;


public class UserService {

    private UserDAO userDao;

    public UserService(Context context){
        userDao = new UserDAO(new DatabaseHelper(context));
    }
    public boolean isLoginCorrect(User user){
        if(user==null)
            return false;
        String password=userDao.findPasswordByEMail(user.getEmail());
        if(password==null||password.equals(user.getPassword()))
            return false;
        return true;
    }
    public boolean signUp(User user) {
        if (user == null) {
            return false;
        }
        boolean x=userDao.save(user);
        return x;
    }
}

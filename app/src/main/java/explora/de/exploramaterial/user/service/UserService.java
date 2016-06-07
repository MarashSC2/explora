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

    /**
     * prüft ob der übergebene User die korrekten Credentials besitzt
     * @param user
     * @return Erfolg
     */
    public boolean isLoginCorrect(User user){
        if(user==null)
            return false;
        String password=userDao.findPasswordByEMail(user.getEmail());
        if(password==null||!password.equals(user.getPassword()))
            return false;
        return true;
    }

    /**
     * fügt einen neuen User hinzu
     * @param user
     * @return Erfolg
     */
    public boolean signUp(User user) {
        if (user == null) {
            return false;
        }
        return userDao.save(user);

    }
}

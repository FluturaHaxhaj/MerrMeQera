package com.fiek.ppmapp.LoginSignup;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBER = "remember";
    //Users session variables
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME ="name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONENO = "phoneNo";
    public static final String KEY_PASSWORD = "password";


    //Remember me variables
    private static final String IS_REMEMBER = "IsRemember";
    public static final String KEY_SESSIONUSERNAME = "username";
    public static final String KEY_SESSIONPASSWORD = "password";

    public SessionManager(Context _context, String sessionName){
        context = _context;
        usersSession = context.getSharedPreferences(sessionName,Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }
    //Users LoginSession
    public void createLoginSession(String name, String username, String email,String phoneNo,String password){

        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_NAME,name);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PHONENO,phoneNo);
        editor.putString(KEY_PASSWORD,password);

        editor.commit();

    }


    public HashMap<String, String> getUsersDetailFromSession(){
        HashMap<String,String> userData = new HashMap<String, String>();
        userData.put(KEY_NAME,usersSession.getString(KEY_NAME,null));
        userData.put(KEY_USERNAME,usersSession.getString(KEY_USERNAME,null));
        userData.put(KEY_EMAIL,usersSession.getString(KEY_EMAIL,null));
        userData.put(KEY_PHONENO,usersSession.getString(KEY_PHONENO,null));
        userData.put(KEY_PASSWORD,usersSession.getString(KEY_PASSWORD,null));

        return  userData;
    }

    public boolean checkLogin(){
        if(usersSession.getBoolean(IS_LOGIN,true)){
            return true;
        }
        else
            return false;
    }

    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }


    //Remember me Session Functions

    public void createRememberSession( String username,String password){

        editor.putBoolean(IS_REMEMBER,true);

        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_SESSIONUSERNAME,username);
        editor.putString(KEY_SESSIONPASSWORD,password);

        editor.commit();

    }

    public HashMap<String, String> getRememberDetailFromSession(){
        HashMap<String,String> userData = new HashMap<String, String>();

        userData.put(KEY_SESSIONUSERNAME,usersSession.getString(KEY_SESSIONUSERNAME,null));
        userData.put(KEY_SESSIONPASSWORD,usersSession.getString(KEY_SESSIONPASSWORD,null));

        return  userData;
    }

    public boolean checkRemember(){
        if(usersSession.getBoolean(IS_REMEMBER,false)){
            return true;
        }
        else
            return false;
    }


}


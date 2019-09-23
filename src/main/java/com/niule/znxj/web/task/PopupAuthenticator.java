package com.niule.znxj.web.task;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by administor on 2017/6/16.
 */
public class PopupAuthenticator extends Authenticator {
    private String username;
    private String password;
    public PopupAuthenticator(String username,String pwd){
        this.username=username;
        this.password=pwd;
    }
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(this.username,this.password);
    }
}

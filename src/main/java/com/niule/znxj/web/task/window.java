package com.niule.znxj.web.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by administor on 2017/5/16.
 */
public class window extends JFrame implements ActionListener,Runnable {

    //设置登陆的用户名和密码
    public window() {
        super("用户登陆界面");
        try {
            // 设置窗体的大小、位置、可见性
            this.setVisible(true);
            this.setSize(410, 300);
            this.addWindowListener(new WindowAdapter() { // 清空内存
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //多线程控制登陆信息框
    public void run(){
        try{
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "正在登陆中....");
            Thread.sleep(3000);
            this.dispose();
        }catch(Exception e){
            System.out.println(e);
        }

    }
    //当点击按钮的时候触发下面的方法
    public void actionPerformed(ActionEvent e) {

    }

}

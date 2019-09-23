package com.niule.znxj.web.controller;

/**
 * Created by administor on 2017/6/28.
 */
public class TestFor {
    public static void main(String[] args) {
        for(int i = 0 ; i < 5 ;i++){
            System.out.println("i="+i);
            for(int j = 0 ; j < 10 ; j ++){
                System.out.println("j="+j);
                if(j ==3){
                    break;
                }
            }
        }
    }
}

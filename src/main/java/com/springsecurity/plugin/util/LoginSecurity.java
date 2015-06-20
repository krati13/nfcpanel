/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

/**
 *
 * @author Shyam
 */
public class LoginSecurity {

    public String ciphertext(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int num1 = (int) (Math.random() * 9);
            int num2 = (int) (Math.random() * 9);
            int num3 = (int) (Math.random() * 9);
            char ch = str.charAt(i);
            int num = (int) (ch);
            String s = String.valueOf(num);
            if (s.length() == 1) {
                s = "000" + s;
            } else if (s.length() == 2) {
                s = "00" + s;
            } else if (s.length() == 3) {
                s = "0" + s;
            }
            sb.append("").append(num1).append(num2).append(num3).append(s);
        }
        return sb.toString();
    }

    public String plaintext(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i = i + 7) {
            String s = str.substring(i + 3, i + 7);
            char ch = (char) (Integer.parseInt(s));
            sb.append(ch);
        }
        return sb.toString();
    }

    public String logincheck(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int num = (int) (ch);
            String s = String.valueOf(num);
            if (s.length() == 1) {
                s = "000" + s;
            } else if (s.length() == 2) {
                s = "00" + s;
            } else if (s.length() == 3) {
                s = "0" + s;
            }
            sb.append("___").append(s);
        }
        return sb.toString();
    }

    // public static void main(String args[]) {
    //    String s=ciphertext("vivek");
    //     System.out.println(s);
    //  System.out.println(logincheck("vivek"));
    //  }
}

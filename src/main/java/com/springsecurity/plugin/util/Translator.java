package com.springsecurity.plugin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Translator {

    public String Encrypt(String str) {
        long k = 2;
        String date = ReturnDate().replace("-", "");
        StringBuilder sbuf = new StringBuilder();
        str = date + str;
        for (int i = 0; i < str.length(); i++, k = i * k) {
            int j = (int) Math.sqrt(i);
            String x = String.valueOf(j);
            x = x.substring(0, 1);
            if (k > 100) {
                k = 1;
            }
            String y = String.valueOf(k);
            y = y.substring(0, 1);
            String xy = "7" + y;
            int yx = Integer.parseInt(xy);
            char c = (char) yx;
            String newStr = str.charAt(i) + x + c;
            sbuf.append(newStr);
        }
        String str1 = new String(sbuf.reverse());
        return str1;
    }

    public String Decrypt(String str) {
        StringBuilder sbuf = new StringBuilder();
        for (int i = 2; i < str.length();) {
            char x = str.charAt(i);
            sbuf.append(x);
            i = i + 3;
        }
        String str1 = new String(sbuf.reverse());
        return str1.substring(8);
    }

    public String SplitDate(String str) {
        String str1 = str.substring(5, 7);
        String date = "";
        switch (Integer.parseInt(str1)) {
            case 1:
                date = str.substring(8, 10) + " Jan, " + str.substring(0, 4);
                break;
            case 2:
                date = str.substring(8, 10) + " Feb, " + str.substring(0, 4);
                break;
            case 3:
                date = str.substring(8, 10) + " Mar, " + str.substring(0, 4);
                break;
            case 4:
                date = str.substring(8, 10) + " Apr, " + str.substring(0, 4);
                break;
            case 5:
                date = str.substring(8, 10) + " May, " + str.substring(0, 4);
                break;
            case 6:
                date = str.substring(8, 10) + " Jun, " + str.substring(0, 4);
                break;
            case 7:
                date = str.substring(8, 10) + " Jul, " + str.substring(0, 4);
                break;
            case 8:
                date = str.substring(8, 10) + " Aug, " + str.substring(0, 4);
                break;
            case 9:
                date = str.substring(8, 10) + " Sep, " + str.substring(0, 4);
                break;
            case 10:
                date = str.substring(8, 10) + " Oct, " + str.substring(0, 4);
                break;
            case 11:
                date = str.substring(8, 10) + " Nov, " + str.substring(0, 4);
                break;
            case 12:
                date = str.substring(8, 10) + " Dec, " + str.substring(0, 4);
                break;
        }
        return date;
    }

    public String SplitDate8(String str) {
        String str1 = str.substring(4, 6);
        String date = "";
        switch (Integer.parseInt(str1)) {
            case 1:
                date = str.substring(6, 8) + " Jan, " + str.substring(0, 4);
                break;
            case 2:
                date = str.substring(6, 8) + " Feb, " + str.substring(0, 4);
                break;
            case 3:
                date = str.substring(6, 8) + " Mar, " + str.substring(0, 4);
                break;
            case 4:
                date = str.substring(6, 8) + " Apr, " + str.substring(0, 4);
                break;
            case 5:
                date = str.substring(6, 8) + " May, " + str.substring(0, 4);
                break;
            case 6:
                date = str.substring(6, 8) + " Jun, " + str.substring(0, 4);
                break;
            case 7:
                date = str.substring(6, 8) + " Jul, " + str.substring(0, 4);
                break;
            case 8:
                date = str.substring(6, 8) + " Aug, " + str.substring(0, 4);
                break;
            case 9:
                date = str.substring(6, 8) + " Sep, " + str.substring(0, 4);
                break;
            case 10:
                date = str.substring(6, 8) + " Oct, " + str.substring(0, 4);
                break;
            case 11:
                date = str.substring(6, 8) + " Nov, " + str.substring(0, 4);
                break;
            case 12:
                date = str.substring(6, 8) + " Dec, " + str.substring(0, 4);
                break;
        }
        return date;
    }

    public String SplitTime(String str) {
        String str1 = str.substring(11, 13);
        int t = Integer.parseInt(str1);
        boolean flag = false;
        if (t > 12 || t == 00) {
            flag = true;
            if (t != 00) {
                t = t - 12;
                str1 = String.valueOf(t);
            } else if (t == 00) {
                flag = false;
                str1 = "12";
            }
        }
        String time = "";
        if (flag) {
            time = str1 + str.substring(13, str.length() - 3) + " PM";
        } else {
            time = str1 + str.substring(13, str.length() - 3) + " AM";
        }
        return time;
    }

    public String SplitTime8(String str) {
        String str1 = str.substring(0, 2);
        int t = Integer.parseInt(str1);
        boolean flag = false;
        if (t > 12 || t == 00) {
            flag = true;
            if (t != 00) {
                t = t - 12;
                str1 = String.valueOf(t);
            } else if (t == 00) {
                flag = false;
                str1 = "12";
            }
        }
        String time = "";
        if (flag) {
            time = str1 + str.substring(2, str.length() - 3) + " PM";
        } else {
            time = str1 + str.substring(2, str.length() - 3) + " AM";
        }
        return time;
    }

    public String ReturnDate() {
        String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return str;
    }

    public String ReturnTime() {
        String str = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return str;
    }

    public String returnDayOfWeek(int Y, int M, int D) {
        String day = "";
        Calendar xmas = new GregorianCalendar(Y, Calendar.DECEMBER, D);
        int dayOfWeek = xmas.get(Calendar.DAY_OF_WEEK);    // 6=Friday

        return day;
    }

}

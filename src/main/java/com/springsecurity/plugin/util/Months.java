/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

/**
 *
 * @author Jeet
 */
public class Months {

    public String Month(int i) {
        String month = "";
        if (i == 1) {
            month = "Jan";
        } else if (i == 2) {
            month = "Feb";
        } else if (i == 3) {
            month = "Mar";
        } else if (i == 4) {
            month = "Apr";
        } else if (i == 5) {
            month = "May";
        } else if (i == 6) {
            month = "Jun";
        } else if (i == 7) {
            month = "Jul";
        } else if (i == 8) {
            month = "Aug";
        } else if (i == 9) {
            month = "Sept";
        } else if (i == 10) {
            month = "Oct";
        } else if (i == 11) {
            month = "Nov";
        } else if (i == 12) {
            month = "Dec";
        }
        return month;
    }
    public String FullMonth(int i) {
        String month = "";
        if (i == 1) {
            month = "January";
        } else if (i == 2) {
            month = "February";
        } else if (i == 3) {
            month = "March";
        } else if (i == 4) {
            month = "April";
        } else if (i == 5) {
            month = "May";
        } else if (i == 6) {
            month = "June";
        } else if (i == 7) {
            month = "July";
        } else if (i == 8) {
            month = "August";
        } else if (i == 9) {
            month = "September";
        } else if (i == 10) {
            month = "October";
        } else if (i == 11) {
            month = "November";
        } else if (i == 12) {
            month = "December";
        }
        return month;
    }
    public String days(int i){
        String day="";
        if (i == 0) {
            day = "Monday";
        } else if (i == 1) {
            day = "Tuesday";
        } else if (i == 2) {
            day = "Wednesday";
        } else if (i == 3) {
            day = "Thursday";
        } else if (i == 4) {
            day = "Friday";
        } else if (i == 5) {
            day = "Saturday";
        } else if (i == 6) {
            day = "Sunday";
        }
        return day;
    }
}

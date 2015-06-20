/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;


/**
 *
 * @author Jeet
 */
public class SubtractTime {
    public String timeDiffer(String dateC,String dateD){
        //2012-05-19 02:17:42
        //2012-05-19 12:17:42
        String rTime="";
        int hh1=Integer.parseInt(dateC.substring(11, 13));
        int hh2=Integer.parseInt(dateD.substring(11, 13));

        int mm1=Integer.parseInt(dateC.substring(14, 16));
        int mm2=Integer.parseInt(dateD.substring(14, 16));
        
        int ss1=Integer.parseInt(dateC.substring(17, 19));
        int ss2=Integer.parseInt(dateD.substring(17, 19));

        if(hh1==hh2 && mm1==mm2){
            rTime="about "+String.valueOf(ss1-ss2)+" seconds ago";
        }else if(hh1==hh2){
            rTime="about "+String.valueOf(mm1-mm2)+" minute ago";
        }else{
            rTime="about "+String.valueOf(hh1-hh2)+" hour ago";
        }
        
        return rTime;
    }
    
}

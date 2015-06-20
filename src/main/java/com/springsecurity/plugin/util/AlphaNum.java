/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jeet
 */
public class AlphaNum {

    public String replaceSpecials(String query, String replaceWith) {
    	if(query==null){
    		query="";
    	}
        query = removeDuplicates(removeExtraWhiteSpace(query).toLowerCase().replaceAll("[^A-Za-z0-9]", replaceWith));
        if(query.endsWith("-")){
            return query.substring(0,query.length()-1);
        }
        return query;
    }

    public String removeExtraWhiteSpace(String str) {
        str = str.replaceAll("\\s+", " ");
        return str;
    }
    
    public String keepAlphabets(String str,String replaceWith) {
        str = str.replaceAll("[^A-Za-z0-9]", replaceWith);
        return str;
    }

    public String removeDuplicates(String str) {
        String regex = "([-])\\1+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            str = matcher.replaceFirst(matcher.start() > 0 ? matcher.group(1)
                    .toLowerCase() : matcher.group(1));
            matcher.reset(str);
        }
        str = str.replaceAll("\\s+", " ");
        return str;
    }
}

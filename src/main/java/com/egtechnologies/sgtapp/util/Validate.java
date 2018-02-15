/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jonathan
 */
public class Validate implements Serializable {
    
    private static final Pattern emailPattern = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z]{2,9}.)+([a-zA-Z]{2,9}.)+([a-zA-Z]{2,3}))$");
    private static final Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");
    
    public static boolean isEmail(String email) {
        Matcher mat;
        mat = emailPattern.matcher(email);
        return mat.find();
    }
    
    public static boolean isPhone(String phone) {
        Matcher mat;
        mat = phonePattern.matcher(phone);
        return mat.find();
    }
}

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
    private static final Pattern phonePattern = Pattern.compile("^\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
                                                                "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
                                                                "4[987654310]|3[9643210]|2[70]|7|1)\n" +
                                                                "\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*(\\d{1,2})$");
    
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

package com.xiaowudr.atmsite.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TTASP {
    public static String ttasp_md5(String val) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < val.length() + 1; i++) {
            if (i % 2 == 1) {
                str.append(val.charAt(i - 1));
                if (str.length() == 16) {
                    break;
                }
            }
        }
        return str.toString();
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String ttasp_Password(String password) {
        // Convert to lowercase and then generate the MD5 hash
        String md5Hashed = md5(password.toLowerCase());
        return ttasp_md5(md5Hashed);
    }

    public static void main(String[] args) {
        String password = "xs198762"; // The input string
        String result = ttasp_Password(password);
        System.out.println("Generated ttasp_Password: " + result);
    }
}

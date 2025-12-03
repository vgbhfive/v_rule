package com.vgbhfive.v_rule.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author vgbhfive
 * @Date 2025/12/3 11:43
 */
public class Md5Util {

    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest((data).getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                //sb.append(Integer.toHexString(b & 0xff));
                // 字符串格式转成 16 进制
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(String oldPassword, Integer salt, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest((oldPassword + salt).getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                //sb.append(Integer.toHexString(b & 0xff));
                // 字符串格式转成 16 进制
                sb.append(String.format("%02x", b));
            }
            return sb.toString().equals(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

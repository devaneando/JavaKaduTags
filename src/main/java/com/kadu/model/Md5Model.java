package com.kadu.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Md5Model {

    public static String calculateHash(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayBytes.length; i++) {
            builder.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return builder.toString();
    }

    public String getHash();
}

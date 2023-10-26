package com.kadu.model;

import com.kadu.validator.ValidFolder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Directory
{

    private static String calculateHash(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

        return convertByteArrayToHexString(hashedBytes);
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayBytes.length; i++) {
            builder.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return builder.toString();
    }

    @NotNull(message = "The directory cannot be null!")
    @NotEmpty(message = "The directory cannot be empty!")
    private String hash;

    @NotNull(message = "The directory cannot be null!")
    @NotEmpty(message = "The directory cannot be empty!")
    @ValidFolder()
    private String path;

    public String getHash()
    {
        return this.hash;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path) throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        this.path = path.trim();
        this.hash = calculateHash(this.path);
    }
}

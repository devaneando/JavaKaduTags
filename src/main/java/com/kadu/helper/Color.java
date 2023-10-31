package com.kadu.helper;

public class Color
{
    public static final String COLOR_BLACK = "black";
    public static final String COLOR_BLUE = "blue";
    public static final String COLOR_BLUE_DARK = "blue_dark";
    public static final String COLOR_BLUE_LIGHT = "blue_light";    
    public static final String COLOR_GREEN = "green";
    public static final String COLOR_GREEN_DARK = "green_dark";
    public static final String COLOR_GREEN_LIGHT = "green_light";    
    public static final String COLOR_RED = "red";
    public static final String COLOR_RED_DARK = "red_dark";
    public static final String COLOR_RED_LIGHT = "red_light";
    public static final String COLOR_WHITE = "white";
    public static final String COLOR_YELLOW = "yellow";
    public static final String COLOR_YELLOW_DARK = "yellow_dark";
    public static final String COLOR_YELLOW_LIGHT = "yellow_light";
    
    public static final String COLOR_ERROR = "error";
    public static final String COLOR_INFO = "info";
    public static final String COLOR_OK = "ok";
    public static final String COLOR_WARNING = "warning";


    public static java.awt.Color fromRGB(int red, int green, int blue)
    {
        return new java.awt.Color(red, green, blue);
    }
    
    public static java.awt.Color color(String name)
    {
        switch (name) {
            case COLOR_BLUE:
                return fromRGB(0, 128, 255);
            case COLOR_BLUE_DARK:
                return fromRGB(0, 77, 153);
            case COLOR_BLUE_LIGHT:
                return fromRGB(77, 166, 255);              
            case COLOR_GREEN:
            case COLOR_OK:
                return fromRGB(51, 153, 51);
            case COLOR_GREEN_DARK:
                return fromRGB(31, 96, 31);
            case COLOR_GREEN_LIGHT:
                return fromRGB(102, 204, 102);            
            case COLOR_RED:
            case COLOR_ERROR:
                return fromRGB(255, 51, 51);
            case COLOR_RED_DARK:
                return fromRGB(179, 0, 0);
            case COLOR_RED_LIGHT:
                return fromRGB(255, 128, 128);
            case COLOR_WHITE:
                return fromRGB(255, 255, 255);                
            case COLOR_YELLOW:
            case COLOR_WARNING:
                return fromRGB(255, 214, 51);                   
            case COLOR_YELLOW_DARK:
                return fromRGB(204, 163, 0);   
            case COLOR_YELLOW_LIGHT:
                return fromRGB(255, 209, 26);                         
            case COLOR_BLACK:
            case COLOR_INFO:
            default:
                return fromRGB(0, 0, 0);
        }
    }
}

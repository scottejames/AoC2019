package com.scottejames.util;

public class Util {
    public static int charToInt(char c,boolean ignoreCase){
        if (ignoreCase == true)
            c= Character.toLowerCase(c);
        return c-'a'+1;

    }
}

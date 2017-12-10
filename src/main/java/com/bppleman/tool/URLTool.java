package com.bppleman.tool;

public class URLTool {
    public static String getURIWithoutPrefixAndSuffix(String url, String prefix, String suffix) {
        String result = "";
        String[] strs = url.split(prefix);
        result = strs[1];
        strs = result.split(suffix);
        result = strs[0];
        return result;
    }
}

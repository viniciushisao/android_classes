package br.com.hisao.githubrepo.util;


import java.util.ArrayList;

/**
 * made by viniciushisao
 * <p>
 * Pattern to make your log template
 * Log.d("$CLASS_NAME$:$METHOD_NAME$:$LINE$ " $END$);
 */

public class Log {

    private static final boolean LOG = true;
    public static final String LOG_TAG = "HISAO.COM.BR";

    public static void e(String string) {
        if (LOG) {
            android.util.Log.e(LOG_TAG, " error\n" + string);
        }
    }

    public static void e(String string, Exception e) {
        if (LOG) {
            android.util.Log.e(LOG_TAG, " error\n" + string, e);
        }
    }

    public static void d(String string) {
        if (LOG) {
            ArrayList<String> textList = splitString(string);
            int length = (textList == null ? 0 : textList.size());

            // Single line
            if (length <= 1) {
                android.util.Log.d(LOG_TAG, string);
                return;
            }

            // Multiple lines
            int counter = 1;
            for (String s : textList) {
                android.util.Log.d(LOG_TAG, "part " + counter++ + "/" + length + ": " + s);
            }
        }
    }

    private static ArrayList<String> splitString(String string) {
        return splitString(string, 950);
    }

    private static ArrayList<String> splitString(String text, int sliceSize) {
        if (text == null || !(text.length() > 0)) {
            return null;
        }
        ArrayList<String> textList = new ArrayList<>();

        if (text.length() <= sliceSize) {
            textList.add(text);
            return textList;
        }

        String aux;
        int left, right = 0;
        int charsLeft = text.length();
        while (charsLeft != 0) {
            left = right;
            if (charsLeft >= sliceSize) {
                right += sliceSize;
                charsLeft -= sliceSize;
            } else {
                right = text.length();
                charsLeft = 0;
            }
            aux = text.substring(left, right);
            textList.add(aux);
        }
        return textList;
    }
}
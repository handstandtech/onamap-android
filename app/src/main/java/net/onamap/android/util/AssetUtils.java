package net.onamap.android.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetUtils {
    public static String doit(Context context, String filename) {
        String str = null;

        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = null;
            json = context.getAssets().open(filename);

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
            str = buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
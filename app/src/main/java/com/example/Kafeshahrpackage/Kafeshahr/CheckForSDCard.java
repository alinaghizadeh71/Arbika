package com.example.Kafeshahrpackage.Kafeshahr;

import android.os.Environment;

/**
 * Created by Maliheh on 09/08/2018.
 */

public class CheckForSDCard {
    public static boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}

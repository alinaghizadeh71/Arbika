package com.example.Kafeshahrpackage.Kafeshahr;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Maliheh on 06/24/2018.
 */

class FileExtensionFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".wma") || name.endsWith(".wma"));
    }
}

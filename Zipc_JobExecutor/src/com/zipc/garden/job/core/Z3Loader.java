package com.zipc.garden.job.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * A class that loads Z3 from the outside and makes it available programmatically.
 */
public class Z3Loader {

    /** Error message when z3 library is not found. */
    private static final String ERROR_NOT_FOUND = "Coudln't find z3 library.";

    /**
     * <pre>
     * Load Z3 into the system.
     * Z3をシステムにロードする
     * </pre>
     * 
     * @return Success: true Failure: false
     */
    public static boolean loadZ3Lib() {
        String z3Path = System.getProperty("user.dir") + "/lib/z3";
        if (!new File(z3Path).exists()) {
            throw new IllegalStateException(ERROR_NOT_FOUND);
        }
        try {
            // load necessary z3 DLLs
            loadDLLs(z3Path);
            // add z3 path to java.library.path
            addLibraryPath(z3Path);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * load z3 DLLs from the path
     * @param z3Path
     */
    private static void loadDLLs(String z3Path) throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("windows")) {
            System.load(z3Path + "/Microsoft.Z3.dll");
            System.load(z3Path + "/msvcr110.dll");
            System.load(z3Path + "/msvcp110.dll");
            System.load(z3Path + "/vcomp110.dll");
            System.load(z3Path + "/libz3.dll");
            System.load(z3Path + "/libz3java.dll");
        } else if (os.startsWith("linux")) {
            System.load(z3Path + "/libz3.so");
            System.load(z3Path + "/libz3java.so");
        }
    }

    /**
     * add z3 path to java.library.path
     * @param pathToAdd
     */
    private static void addLibraryPath(String pathToAdd) throws Exception {
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);
        // get array of paths
        final String[] paths = (String[]) usrPathsField.get(null);
        // check if the path to add is already present
        for (String path : paths) {
            if (path.equals(pathToAdd)) {
                return;
            }
        }
        // add the new path
        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length - 1] = pathToAdd;
        usrPathsField.set(null, newPaths);
    }
}

package com.kadu.helper;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;

public class OperationSystem {

    private static boolean isWindowsLike;
    private static boolean isLinuxLike;

    public static boolean isWindows() {
        if (!isWindowsLike && !isLinuxLike) {
            String os = System.getProperty("os.name").trim().toLowerCase();
            isWindowsLike = os.startsWith("windows");
            isLinuxLike = !isWindowsLike;
        }

        return isWindowsLike;
    }

    public static boolean isLinux() {
        if (!isWindowsLike && !isLinuxLike) {
            String os = System.getProperty("os.name").trim().toLowerCase();
            isWindowsLike = os.startsWith("windows");
            isLinuxLike = !isWindowsLike;
        }

        return isLinuxLike;
    }

    public static String separator() {
        return FileSystems.getDefault().getSeparator();
    }

    public static String trim(String path) {
        return StringUtils.strip(path.trim(), separator());
    }

    public static String rootFolder() {
        Path folder = FileSystems.getDefault().getRootDirectories().iterator().next();

        return folder.toString();
    }

    public static String tempFolder() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String homeFolder() {
        return System.getProperty("user.home");
    }

    public static String concatenate(String[] elements) {
        String path = "";
        for (String element : elements) {
            path = Paths.get(path, trim(element)).toString();
        }
        path = trim(path);

        if (!path.startsWith(rootFolder())) {
            return Paths.get(rootFolder(), path).toString();
        }

        return path;
    }

    public static String concatenate(String first, String second) {
        String[] elements = {
            first,
            second
        };

        return concatenate(elements);
    }
}

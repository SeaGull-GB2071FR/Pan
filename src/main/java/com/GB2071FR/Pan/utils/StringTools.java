package com.GB2071FR.Pan.utils;

import com.GB2071FR.Pan.entity.constants.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;


public class StringTools {
    /**
     * 生成随机数
     *
     * @param count
     * @return
     */
    public static final String getRandomNumber(Integer count) {
        return RandomStringUtils.random(count, false, true);
    }

    public static final String getRandomString(Integer count) {
        return RandomStringUtils.random(count, true, true);
    }

    public static boolean isEmpty(String str) {

        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String encodeByMd5(String orignString) {
        return isEmpty(orignString) ? null : DigestUtils.md5Hex(orignString);
    }

    public static boolean pathIsOk(String path) {
        if (StringTools.isEmpty(path)) {
            return true;
        }
        if (path.contains("../") || path.contains("..\\")) {
            return false;
        }
        return true;
    }

    public static String rename(String fileName) {
        String fileNameReal = getFileNameNoSuffix(fileName);
        String suffix = getFileSuffix(fileName);
        return fileNameReal + "_" + getRandomString(Constants.LENGTH_5) + suffix;
    }

    public static String getFileNameNoSuffix(String fileName) {
        Integer index = fileName.lastIndexOf(".");
        if (index == -1) {
            return fileName;
        }
        fileName = fileName.substring(0, index);
        return fileName;
    }

    public static String getFileSuffix(String fileName) {
        Integer index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        String suffix = fileName.substring(index);
        return suffix;
    }

}

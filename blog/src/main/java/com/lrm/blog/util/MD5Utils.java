package com.lrm.blog.util;

/**
 * 项目名称：blog
 * 类 名 称：MD5Utils
 * 类 描 述：TODO
 * 创建时间：2020/7/3 2:16 下午
 * 创 建 人：huanghao
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具类
 */
public class MD5Utils {

    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String code(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDiges = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0; offset < byteDiges.length; offset++) {
                i = byteDiges[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32加密
            return buf.toString();
            //16位的加密
            //retrun buf.toString().substring(8,24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}

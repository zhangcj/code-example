package com.xxx.beecho.jedis.util;

import java.io.*;

/**
 * 序列化工具类，负责byte[]和Object之间的相互转换.
 *
 * Created by Administrator on 2017/6/9.
 */
public class SerializingUtil {

    /**
     * 对 bean 进行序列化
     *
     * @param source
     * @return
     */
    public static byte[] serialize(Object source) {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream objOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(source);
            objOut.flush();
        } catch (IOException e) {
            System.out.println(source.getClass().getName() + " serialized error !" + e);
        } finally {
            try {
                if (null != objOut) {
                    objOut.close();
                }
            } catch (IOException e) {
                objOut = null;
            }
        }

        return byteOut.toByteArray();
    }

    /**
     * 将字节数组反序列化为实体 bean
     *
     * @param source
     * @return
     */
    public static Object deserialize(byte[] source) {
        ObjectInputStream objIn = null;
        Object retVal = null;
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
            objIn = new ObjectInputStream(byteIn);
            retVal = objIn.readObject();
        } catch (Exception e) {
            System.out.println("deserialized error  !" + e);
        } finally {
            try {
                if (null != objIn) {
                    objIn.close();
                }
            } catch (IOException e) {
                objIn = null;
            }
        }

        return retVal;
    }
}

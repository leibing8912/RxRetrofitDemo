package com.ym.rxretrofitdemo.commons.data.rxdata;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CacheManager {

    /**
     *  保存对象
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param context 上下文
     * @param ser 序列化数据
     * @param keyName key名称
     * @throws IOException
     * @return
     */
    public static boolean saveObject(Context context, Serializable ser, String keyName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(keyName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 读取对象
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param context 上下文
     * @param cacheKey 缓存key
     * @param expireTime 缓存过期时间
     * @return
     */
    public static Serializable readObject(Context context, String cacheKey, final long expireTime) {
        if (!isExistDataCache(context, cacheKey))
            return null;
        if (isDataTimeOut(context, cacheKey, expireTime) && expireTime != 0)
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(cacheKey);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = context.getFileStreamPath(cacheKey);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     *
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param context 上下文
     * @param cacheKey 缓存key
     * @param exprieTime 缓存过期时间
     * @return
     */
    private static boolean isDataTimeOut(Context context, String cacheKey, long exprieTime) {
        File data = context.getFileStreamPath(cacheKey);
        long time = data.lastModified();
        if (System.currentTimeMillis() - time > exprieTime) {
            return true;
        }
        return false;
    }

    /**
     * 判断缓存是否存在
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param context 上下文
     * @param cachefile 缓存路径
     * @return
     */
    public static boolean isExistDataCache(Context context, String cachefile) {
        if (context == null)
            return false;
        boolean exist = false;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * 删除缓存
     * @author leibing
     * @createTime 2016/8/12
     * @lastModify 2016/8/12
     * @param context 上下文
     * @param cachefile 缓存路径
     * @return
     */
    public static boolean deleteObject(Context context, String cachefile) {
        File data = context.getFileStreamPath(cachefile);
        if (data.exists()) {
            data.delete();
            return true;
        } else {
            return false;
        }
    }
}

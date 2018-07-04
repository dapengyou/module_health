package com.test.baselibrary.Utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileUtil {

    /**
     * 从资源文件读取文本内容
     *
     * @param fileName 文件名
     * @return 文件内容
     */
    public static String readStringFromAsset(Context context, String fileName) {
        AssetManager assetManager = context.getApplicationContext().getAssets();
        if (assetManager != null) {
            InputStream is = null;
            ByteArrayOutputStream bos = null;
            try {
                is = assetManager.open(fileName);
                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                byte[] data = bos.toByteArray();
                return new String(data, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

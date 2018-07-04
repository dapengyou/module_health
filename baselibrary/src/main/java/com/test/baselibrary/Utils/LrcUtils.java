package com.test.baselibrary.Utils;

import android.util.Log;

import com.test.baselibrary.bean.Lyric;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * Created by lady_zhou on 2018/5/29.
 */

public class LrcUtils {
    private static List<Lyric> lyricList;

    /**
     * 读取文件
     */
    public static List<Lyric> readLRC(File f) {
        try {
            if (f == null || !f.exists()) {
                lyricList = null;
            } else {
                lyricList = new Vector<Lyric>();
                InputStream is = new BufferedInputStream(new FileInputStream(f));
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        is, getCharset(f)));
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        is, converfile(string)));
                String strTemp = "";
                while ((strTemp = br.readLine()) != null) {
                    strTemp = processLRC(strTemp);
                }
                br.close();
                is.close();
                // 对歌词进行排序
                Collections.sort(lyricList, new Sort());
                // 计算每行歌词的停留时间
                for (int i = 0; i < lyricList.size(); i++) {

                    Lyric one = lyricList.get(i);
                    if (i + 1 < lyricList.size()) {
                        Lyric two = lyricList.get(i + 1);
                        one.sleepTime = two.timePoint - one.timePoint;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lyricList;
    }

    /**
     * 处理一行内容
     */
    private static String processLRC(String text) {
        try {
            int pos1 = text.indexOf("[");
            int pos2 = text.indexOf("]");

            if (pos1 >= 0 && pos2 != -1) {
                Long time[] = new Long[getPossiblyTagCount(text)];
                time[0] = timeToLong(text.substring(pos1 + 1, pos2));
                if (time[0] == -1)
                    return "";
                String strLineRemaining = text;
                int i = 1;
                while (pos1 >= 0 && pos2 != -1) {

                    strLineRemaining = strLineRemaining.substring(pos2 + 1);
                    pos1 = strLineRemaining.indexOf("[");
                    pos2 = strLineRemaining.indexOf("]");
                    if (pos2 != -1) {
                        time[i] = timeToLong(strLineRemaining.substring(
                                pos1 + 1, pos2));
                        if (time[i] == -1)
                            return ""; // LRCText
                        i++;
                    }
                }

                Lyric tl = null;
                //防止有的歌词文件是这种格式：[00:01:23][00:03:02]重复歌词
                //就是歌词重复的放在一起，将多个时间戳放在一起，所以在解析完歌词需要排序一下。
                for (int j = 0; j < time.length; j++) {
                    if (time[j] != null) {
                        tl = new Lyric();
                        tl.timePoint = time[j].intValue();
                        tl.lricString = strLineRemaining;
                        lyricList.add(tl);
                    }
                }
                return strLineRemaining;
            } else
                return "";
        } catch (Exception e) {
            return "";
        }
    }

    //获取一行中的时间标签的个数，为了防止将重复歌词放在一行上显示
    private static int getPossiblyTagCount(String Line) {
        String strCount1[] = Line.split("\\[");
        String strCount2[] = Line.split("\\]");
        if (strCount1.length == 0 && strCount2.length == 0)
            return 1;
        else if (strCount1.length > strCount2.length)
            return strCount1.length;
        else
            return strCount2.length;
    }

    /**
     * 时间转换，将time格式时间转换成秒
     */
    public static long timeToLong(String Time) {
        try {
            String[] s1 = Time.split(":");
            int min = Integer.parseInt(s1[0]);
            String[] s2 = s1[1].split("\\.");
            int sec = Integer.parseInt(s2[0]);
            int mill = 0;
            if (s2.length > 1)
                mill = Integer.parseInt(s2[1]);
            return min * 60 * 1000 + sec * 1000 + mill * 10;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 判断文件编码，防止文件解析成乱码
     */
    public static String getCharset(File file) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            bis.mark(4);//这里使用 bis.mark(0);时reset会报异常  为什么呢

            byte[] first3bytes = new byte[3];
            //找到文档的前三个字节并自动判断文档类型。
            int read = bis.read(first3bytes);
//            int read = bis.read(first3bytes,0,3);

            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1]
                    == (byte) 0xFF) {
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1]
                    == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    //单独出现BF以下的，也算是GBK
                    if (0x80 <= read && read <= 0xBF)
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF)// 双字节 (0xC0 - 0xDF)
                            // (0x80 -
                            // 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                        // 也有可能出错，但是几率较小
                    } else if (0xE0 <= read && read <= 0xEF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
                System.out.println(loc + " " + Integer.toHexString(read));
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;

    }

    //按照timePoint的大小进行升序排列
    private static class Sort implements Comparator<Lyric> {
        public Sort() {
        }

        public int compare(Lyric tl1, Lyric tl2) {
            return sortUp(tl1, tl2);
        }

        private int sortUp(Lyric tl1, Lyric tl2) {
            if (tl1.timePoint < tl2.timePoint)
                return -1;
            else if (tl1.timePoint > tl2.timePoint)
                return 1;
            else
                return 0;
        }
    }

//    public static String converfile(String filepath) {
//        System.out.println("ConvertFileCode--------->" + filepath);
//        File file = new File(filepath);
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        BufferedReader reader = null;
//        String text = "";
//        //自己添加
//        String type = null;
//        StringBuffer stringBuffer = new StringBuffer();
//        int read;
//
//        try {
//            fis = new FileInputStream(file);
//            bis = new BufferedInputStream(fis);
//            bis.mark(0);
//            byte[] first3bytes = new byte[30];
//
//
////            while ((read = fis.read()) != -1) {
////                stringBuffer.append((char) read);
////            }
////
////            Log.d("tag", stringBuffer + "");
//            //找到文档的前三个字节并自动判断文档类型。
//            bis.read(first3bytes, (int) skipBytesFromStream(fis, 10), 3);
//            Log.d("tag", first3bytes[0] + "==" + first3bytes[1] + "==" + first3bytes[2]);
//            bis.reset();
//
//
//            if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB
//                    && first3bytes[2] == (byte) 0xBF) {// utf-8
//
//                type = "utf-8";
//                reader = new BufferedReader(new InputStreamReader(bis, type));
//
//            } else if (first3bytes[0] == (byte) 0xFF
//                    && first3bytes[1] == (byte) 0xFE) {
//
//                type = "unicode";
//                reader = new BufferedReader(
//                        new InputStreamReader(bis, type));
//            } else if (first3bytes[0] == (byte) 0xFE
//                    && first3bytes[1] == (byte) 0xFF) {
//
//                type = "utf-16be";
//                reader = new BufferedReader(new InputStreamReader(bis,
//                        type));
//            } else if (first3bytes[0] == (byte) 0xFF
//                    && first3bytes[1] == (byte) 0xFF) {
//
//                type = "utf-16le";
//                reader = new BufferedReader(new InputStreamReader(bis,
//                        type));
//            } else {
//
//                type = "GBK";
//                reader = new BufferedReader(new InputStreamReader(bis, type));
//            }
//            String str = reader.readLine();
//
//            while (str != null) {
//                text = text + str;
//                str = reader.readLine();
//            }
//            System.out.println("text" + text);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return type;
//
//    }
//
//    /*重写了Inpustream 中的skip(long n) 方法，将数据流中起始的n 个字节跳过*/
//    public static long skipBytesFromStream(InputStream inputStream, long n) {
//        long remaining = n;
//        // SKIP_BUFFER_SIZE is used to determine the size of skipBuffer
//        int SKIP_BUFFER_SIZE = 2048;
//        // skipBuffer is initialized in skip(long), if needed.
//        byte[] skipBuffer = null;
//        int nr = 0;
//        if (skipBuffer == null) {
//            skipBuffer = new byte[SKIP_BUFFER_SIZE];
//        }
//        byte[] localSkipBuffer = skipBuffer;
//        if (n <= 0) {
//            return 0;
//        }
//        while (remaining > 0) {
//            try {
//                nr = inputStream.read(localSkipBuffer, 0,
//                        (int) Math.min(SKIP_BUFFER_SIZE, remaining));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (nr < 0) {
//                break;
//            }
//            remaining -= nr;
//        }
//        return n - remaining;
//    }
}

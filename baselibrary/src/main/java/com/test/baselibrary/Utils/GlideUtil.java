package com.test.baselibrary.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.baselibrary.R;


public class GlideUtil {

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context).load(url).into(imageView);

    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImageWithPlaceholder(Context context, String url, ImageView imageView, @DrawableRes int resId) {
        GlideApp.with(context).load(url).placeholder(resId).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImageWithPlaceholder(Context context, String url, ImageView imageView, Drawable drawable) {
        GlideApp.with(context).load(url).placeholder(drawable).into(imageView);
    }

    /**
     * 加载圆形头部图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadHeaderImage(Context context, String url, final ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }


    /**
     * 加载方形头部图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadSquareHeaderImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

    }

    /**
     * 加载方形头部图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadLogoImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

    }


    /**
     * 加载评论图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCommentImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

    }

    /**
     * 是否禁止磁盘缓存加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param type      缓存的类型
     *                  <li>磁盘缓存全部 DiskCacheStrategy.ALL</li>
     *                  <li>磁盘禁止缓存DiskCacheStrategy.NONE</li>
     */
    public static void loadImage(Context context, String url, ImageView imageView, DiskCacheStrategy type) {
        GlideApp.with(context).load(url).diskCacheStrategy(type).into(imageView);
    }

    /**
     * 是否禁止内存缓存加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param skipMemoryCache 禁止内存缓存 true为禁止
     */
    public static void loadImage(Context context, String url, ImageView imageView, boolean skipMemoryCache) {
        GlideApp.with(context).load(url).skipMemoryCache(skipMemoryCache).into(imageView);
    }

    /**
     * 是否禁止内存/磁盘缓存加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param type            缓存的类型
     *                        <li>磁盘缓存全部 DiskCacheStrategy.ALL</li>
     *                        <li>磁盘禁止缓存DiskCacheStrategy.NONE</li>
     * @param skipMemoryCache 禁止内存缓存 true为禁止
     */
    public static void loadImage(Context context, String url, ImageView imageView, DiskCacheStrategy type,
                                 boolean skipMemoryCache) {
        GlideApp.with(context).load(url).skipMemoryCache(skipMemoryCache).diskCacheStrategy(type).into(imageView);
    }

    /**
     * 清除内存中的缓存 必须在UI线程中调用
     *
     * @param context
     */
    public static void clearMemory(Context context) {
        GlideApp.get(context).clearMemory();
    }

    /**
     * 清除磁盘中的缓存 必须在后台线程中调用，建议同时clearMemory()
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        GlideApp.get(context).clearDiskCache();
    }

    /**
     * 优先级加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param priority  优先级  Priority.LOW/Priority.HIGH
     */
    public static void loadImageWithPriority(Context context, String url, ImageView imageView, Priority priority) {
        GlideApp.with(context).load(url).priority(priority).into(imageView);
    }

}

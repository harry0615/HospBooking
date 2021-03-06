package com.app.hosbooking;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class FontApplication extends Application {
    @Override
    public void onCreate() {
//        setDefaultFont(this, "DEFAULT", "SDMiSaeng.ttf");
//        setDefaultFont(this, "SANS_SERIF", "SDMiSaeng.ttf");
//        setDefaultFont(this, "SERIF", "SDMiSaeng.ttf");
    	
//    	setDefaultFont(this, "DEFAULT", "NotoSansKR-Regular-Hestia.otf.mp3");
        setDefaultFont(this, "SANS_SERIF", "NotoSansKR-Regular-Hestia.otf.mp3");
//        setDefaultFont(this, "SERIF", "NotoSansKR-Regular-Hestia.otf.mp3");
    }
 
    public static void setDefaultFont(Context ctx, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(ctx.getAssets(),fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }
 
    protected static void replaceFont(String staticTypefaceFieldName,final Typeface newTypeface) {
        try {
            final Field StaticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            StaticField.setAccessible(true);
            StaticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

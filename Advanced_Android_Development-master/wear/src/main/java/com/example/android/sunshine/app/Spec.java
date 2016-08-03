package com.example.android.sunshine.app;

import android.graphics.PointF;
import android.graphics.Typeface;

import java.text.SimpleDateFormat;

public class Spec {

    public static final int COLOR_INTERACTIVE_BACKGROUND = 0xFF9C27B0;
    public static final int COLOR_LOWBIT_BACKGROUND = 0xFF000000;
    public static final int COLOR_WHITE_100 = 0xFFFFFFFF;
    public static final int COLOR_WHITE_80 = 0xCCFFFFFF;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, MMM dd yyyy");
    public static final PointF DATE_POSITION = new PointF(160f, 160f);
    public static final float DATE_TEXT_SIZE = 24f;
    public static final PointF DIVIDER_POSITION = new PointF(160f, 190f);
    public static final PointF HIGH_POSITION = new PointF(165f, 240f);
    public static final float HIGH_TEXT_SIZE = 40f;
    public static final PointF LOW_POSITION = new PointF(235f, 240f);
    public static final float LOW_TEXT_SIZE = 40f;
    public static final SimpleDateFormat SECONDS_FORMAT = new SimpleDateFormat("ss");
    public static final PointF SECONDS_POSITION = new PointF(225f, 120f);
    public static final float SECONDS_TEXT_SIZE = 24f;
    public static final float SIZE = 320f;
    public static final SimpleDateFormat TIME_FORMAT_12 = new SimpleDateFormat("h:mm");
    public static final SimpleDateFormat TIME_FORMAT_24 = new SimpleDateFormat("HH:mm");
    public static final PointF TIME_POSITION = new PointF(150f, 120f);
    public static final float TIME_TEXT_SIZE = 48f;
    public static final Typeface TYPEFACE_SERIF = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
    public static final Typeface TYPEFACE_SERIF_LIGHT = Typeface.create("sans-serif-light", Typeface.NORMAL);
    public static final PointF WEATHER_ICON_POSITION = new PointF(65f, 210f);
    public static final float WEATHER_ICON_SIZE = 40f;

    private Spec() {
    }
}

package com.example.android.sunshine.app.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import java.util.Date;
import static com.example.android.sunshine.app.Spec.DATE_FORMAT;
import static com.example.android.sunshine.app.Spec.SECONDS_FORMAT;
import static com.example.android.sunshine.app.Spec.TIME_FORMAT_12;
import static com.example.android.sunshine.app.Spec.TIME_FORMAT_24;
import static com.example.android.sunshine.app.util.Utilities.getFloatValueFromSpec;

public class Text extends BaseFaceItem {

    private int mLowBitOffset;
    private boolean mLowBitVisible;
    private String mText;
    private float mTextSize;

    public Text(Typeface typeface, int color, float textSize, PointF position, int lowBitOffset, boolean lowBitVisible) {
        super();

        mPaint.setColor(color);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(typeface);

        mText = "";
        mTextSize = textSize;
        mPointF = new PointF(0f, 0f);
        mPosition = position;
        mLowBitOffset = lowBitOffset;
        mLowBitVisible = lowBitVisible;
    }

    public void interactiveState() {
        super.interactiveState();
        mPaint.setAntiAlias(true);
    }

    public void lowBitState() {
        super.lowBitState();
        mPaint.setAntiAlias(false);
    }

    public void onDraw(Canvas canvas, int positionX, int positionY) {
        if (mInteractive) {
            canvas.drawText(mText, positionX + mPointF.x, positionY + mPointF.y, mPaint);
        } else if (mLowBitVisible) {
            canvas.drawText(mText, positionX + mPointF.x + mLowBitOffset, positionY + mPointF.y, mPaint);
        }
    }

    public void onLayout(float renderSize) {
        super.onLayout(renderSize);
        mPaint.setTextSize(getFloatValueFromSpec(mTextSize, renderSize));
    }

    public void setDate(Date date) {
        mText = DATE_FORMAT.format(date).toUpperCase();
    }

    public void setSeconds(Date date) {
        mText = SECONDS_FORMAT.format(date);
    }

    public void setText(String text) {
        mText = text;
    }

    public void setTime(Date date, boolean is24HourFormat) {
        String time = is24HourFormat ? TIME_FORMAT_24.format(date) : TIME_FORMAT_12.format(date);
        mText = time.length() == 4 ? "0" + time : time;
    }
}
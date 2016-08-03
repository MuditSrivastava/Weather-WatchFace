/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.sunshine.app.service;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.wearable.watchface.WatchFaceStyle;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.WindowInsets;
import com.example.android.sunshine.app.ui.Background;
import com.example.android.sunshine.app.ui.BitmapDrawable;
import com.example.android.sunshine.app.ui.Line;
import com.example.android.sunshine.app.ui.Text;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.ustwo.clockwise.ConnectedWatchFace;
import com.ustwo.clockwise.WatchFaceTime;
import com.ustwo.clockwise.WatchMode;
import com.ustwo.clockwise.WatchShape;
import java.util.Date;
import static com.example.android.sunshine.app.Spec.*;
import static com.example.android.sunshine.app.util.Utilities.getIcon;


public class WatchFaceService extends ConnectedWatchFace {

    private static final String FORECAST_INFO_PATH = "/forecast/info";
    private static final String FORECAST_PATH = "/forecast";
    private static final String KEY_HIGH = "high";
    private static final String KEY_LOW = "low";
    private static final String KEY_WEATHER_ID = "weatherId";
    private Background mBackground;
    private Text mDateText;
    private Line mDivider;
    private Text mHighText;
    private Text mLowText;
    private Text mSecondsText;
    private Text mTimeText;
    private PointF mWatchFaceCenter = new PointF(0f, 0f);
    private BitmapDrawable mWeatherIcon;

    @Override
    public void onCreate() {
        super.onCreate();

        mBackground = new Background();
        mTimeText = new Text(TYPEFACE_SERIF, COLOR_WHITE_100, TIME_TEXT_SIZE, TIME_POSITION, 10, true);
        mSecondsText = new Text(TYPEFACE_SERIF, COLOR_WHITE_80, SECONDS_TEXT_SIZE, SECONDS_POSITION, 0, false);
        mDateText = new Text(TYPEFACE_SERIF_LIGHT, COLOR_WHITE_80, DATE_TEXT_SIZE, DATE_POSITION, 0, false);
        mDivider = new Line(DIVIDER_POSITION);
        mHighText = new Text(TYPEFACE_SERIF, COLOR_WHITE_100, HIGH_TEXT_SIZE, HIGH_POSITION, -30, true);
        mLowText = new Text(TYPEFACE_SERIF_LIGHT, COLOR_WHITE_80, LOW_TEXT_SIZE, LOW_POSITION, -30, true);
        mWeatherIcon = new BitmapDrawable(WEATHER_ICON_SIZE, WEATHER_ICON_POSITION);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED &&
                    FORECAST_INFO_PATH.equals(dataEvent.getDataItem().getUri().getPath())) {
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                if (dataMap.containsKey(KEY_HIGH)) {
                    mHighText.setText(dataMap.getString(KEY_HIGH));
                }
                if (dataMap.containsKey(KEY_LOW)) {
                    mLowText.setText(dataMap.getString(KEY_LOW));
                }
                if (dataMap.containsKey(KEY_WEATHER_ID)) {
                    mWeatherIcon.setBitmap(getResources(), getIcon(dataMap.getInt(KEY_WEATHER_ID)));
                }
            }
        }
    }

    @Override
    public void onWatchModeChanged(WatchMode watchMode) {
        refreshCurrentState();
    }

    @Override
    protected long getInteractiveModeUpdateRate() {
        return DateUtils.SECOND_IN_MILLIS;
    }

    @Override
    protected WatchFaceStyle getWatchFaceStyle() {
        return new WatchFaceStyle.Builder(this)
                .setCardPeekMode(WatchFaceStyle.PEEK_MODE_VARIABLE)
                .setAmbientPeekMode(WatchFaceStyle.AMBIENT_PEEK_MODE_VISIBLE)
                .setPeekOpacityMode(WatchFaceStyle.PEEK_OPACITY_MODE_TRANSLUCENT)
                .setCardProgressMode(WatchFaceStyle.PROGRESS_MODE_NONE)
                .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                .setViewProtectionMode(WatchFaceStyle.PROTECT_HOTWORD_INDICATOR | WatchFaceStyle.PROTECT_STATUS_BAR)
                .setHotwordIndicatorGravity(Gravity.TOP | Gravity.START)
                .setStatusBarGravity(Gravity.TOP | Gravity.START)
                .setShowSystemUiTime(false)
                .build();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int backgroundBitmapPositionX = -getWidth() / 2;
        int backgroundBitmapPositionY = -getHeight() / 2;

        canvas.save();

        canvas.translate(mWatchFaceCenter.x, mWatchFaceCenter.y);

        mBackground.onDraw(canvas);
        mTimeText.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        mSecondsText.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        mDateText.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        mDivider.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        mHighText.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        mLowText.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        mWeatherIcon.onDraw(canvas, backgroundBitmapPositionX, backgroundBitmapPositionY);
        canvas.restore();
    }

    @Override
    protected void onLayout(WatchShape watchShape, Rect rect, WindowInsets windowInsets) {

        float renderSize = Math.min(getWidth(), getHeight());

        mWatchFaceCenter.set(getWidth() * 0.5f, getHeight() * 0.5f);

        mTimeText.onLayout(renderSize);
        mSecondsText.onLayout(renderSize);
        mDateText.onLayout(renderSize);
        mDivider.onLayout(renderSize);
        mHighText.onLayout(renderSize);
        mLowText.onLayout(renderSize);
        mWeatherIcon.onLayout(renderSize);

        refreshCurrentState();

        updateDateTime(true, true);
        updateForecast(true);
    }

    @Override
    protected void onTimeChanged(WatchFaceTime oldTime, WatchFaceTime newTime) {
        updateDateTime(newTime.hasDateChanged(oldTime), newTime.hasMinuteChanged(oldTime));
        updateForecast(newTime.hasMinuteChanged(oldTime));
    }

    private void applyAmbientState() {
        mBackground.ambientState();
        mTimeText.ambientState();
        mSecondsText.ambientState();
        mDateText.ambientState();
        mDivider.ambientState();
        mWeatherIcon.ambientState();
        mHighText.ambientState();
        mLowText.ambientState();
    }

    private void applyInteractiveState() {
        mBackground.interactiveState();
        mTimeText.interactiveState();
        mSecondsText.interactiveState();
        mDateText.interactiveState();
        mDivider.interactiveState();
        mWeatherIcon.interactiveState();
        mHighText.interactiveState();
        mLowText.interactiveState();
    }

    private void applyLowBitState() {
        mBackground.lowBitState();
        mTimeText.lowBitState();
        mSecondsText.lowBitState();
        mDateText.lowBitState();
        mDivider.lowBitState();
        mWeatherIcon.lowBitState();
        mHighText.lowBitState();
        mLowText.lowBitState();
    }

    private void refreshCurrentState() {
        switch (getCurrentWatchMode()) {
            case INTERACTIVE:
                applyInteractiveState();
                break;
            case AMBIENT:
                // Non-low bit ambient mode
                applyAmbientState();
                break;
            default:
                // Other ambient modes (LOW_BIT, BURN_IN, LOW_BIT_BURN_IN)
                applyLowBitState();
                break;
        }
    }

    private void updateDateTime(boolean updateDate, boolean updateTime) {
        Date date = new Date();
        if (updateDate) {
            mDateText.setDate(date);
        }
        if (updateTime) {
            mTimeText.setTime(date, is24HourFormat());
        }
        mSecondsText.setSeconds(date);
    }

    private void updateForecast(boolean update) {
        if (update) {
            putMessage(FORECAST_PATH, new byte[0], null);
        }
    }
}

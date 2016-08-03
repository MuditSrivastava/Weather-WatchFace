package com.example.android.sunshine.app.util;

import android.graphics.PointF;
import com.example.android.sunshine.app.R;
import com.example.android.sunshine.app.Spec;

public class Utilities {


    public static void applyPointValueFromSpec(PointF output, PointF specValue, float currentRenderSize) {
        if (output == null || specValue == null) {
            return;
        }
        output.set(getFloatValueFromSpec(specValue.x, currentRenderSize), getFloatValueFromSpec(specValue.y, currentRenderSize));
    }

    public static float getFloatValueFromSpec(float specValue, float currentRenderSize) {
        return (specValue / Spec.SIZE) * currentRenderSize;
    }

    public static int getIcon(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_storm;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear;
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_cloudy;
        }
        return -1;
    }
}

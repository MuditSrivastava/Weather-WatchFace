package com.example.android.sunshine.app.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import static com.example.android.sunshine.app.Spec.COLOR_INTERACTIVE_BACKGROUND;
import static com.example.android.sunshine.app.Spec.COLOR_LOWBIT_BACKGROUND;

public class Background {

    private Paint mPaint;

    public Background() {
        mPaint = new Paint();
    }

    public void ambientState() {
        lowBitState();
    }

    public void interactiveState() {
        mPaint.setColor(COLOR_INTERACTIVE_BACKGROUND);
    }

    public void lowBitState() {
        mPaint.setColor(COLOR_LOWBIT_BACKGROUND);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(mPaint.getColor());
    }
}

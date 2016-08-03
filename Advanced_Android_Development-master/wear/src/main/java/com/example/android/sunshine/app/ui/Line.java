package com.example.android.sunshine.app.ui;

import android.graphics.Canvas;
import android.graphics.PointF;
import static com.example.android.sunshine.app.util.Utilities.applyPointValueFromSpec;

public class Line extends BaseFaceItem {

    public Line(PointF position) {
        super();
        mPosition = position;
    }
    public void onDraw(Canvas canvas, int positionX, int positionY) {
        if (mInteractive) {
            float x = positionX + mPointF.x;
            float y = positionY + mPointF.y;
            canvas.drawLine(x - 20, y, x + 20, y, mPaint);
        }
    }
    public void onLayout(float renderSize) {
        applyPointValueFromSpec(mPointF, mPosition, renderSize);
    }
}

package com.example.android.sunshine.app.wear;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class SunshineWearService extends WearableListenerService {

    private static final String FORECAST_PATH = "/forecast";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (FORECAST_PATH.equals(messageEvent.getPath())) {
            SunshineSyncAdapter.syncImmediately(this);
        }
    }
}
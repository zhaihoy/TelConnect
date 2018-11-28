package com.github.florent37.materialviewpager.sample.service;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telecom.Call;
import android.telecom.InCallService;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class PhoneService extends InCallService {
    private Call.Callback callback = new Call.Callback() {
        @Override
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);
            switch (state) {
                case Call.STATE_ACTIVE: {
                    break; // 通话中
                }
                case Call.STATE_DISCONNECTED: {
                    break; // 通话结束
                }
            }
        }
    };

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);

        call.registerCallback(callback);
    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);

        call.unregisterCallback(callback);
    }

}

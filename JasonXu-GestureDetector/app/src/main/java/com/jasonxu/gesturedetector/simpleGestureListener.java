package com.jasonxu.gesturedetector;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.jasonxu.gesturedetector.utils.LogUtils;

/**
 * Created by t_xuz on 6/22/16.
 */
public class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int MIN_DISTANCE = 100;
    private static final int MIN_VELOCITY = 200;
    private Context mContext;

    public simpleGestureListener(Context mContext) {
        this.mContext = mContext;
    }

    // 参数解释：
    // e1：第1个ACTION_DOWN MotionEvent
    // e2：最后一个ACTION_MOVE MotionEvent
    // velocityX：X轴上的移动速度，像素/秒
    // velocityY：Y轴上的移动速度，像素/秒
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //向左滑
        if (e2.getX() - e1.getX() < MIN_DISTANCE && velocityX > MIN_VELOCITY) {
            Toast.makeText(mContext, "向左边滑动", Toast.LENGTH_SHORT).show();
            LogUtils.e("向左边滑动");
        } else if (e2.getX() - e1.getX() > MIN_DISTANCE && velocityX > MIN_VELOCITY) { //向右滑
            Toast.makeText(mContext, "向右边滑动", Toast.LENGTH_SHORT).show();
            LogUtils.e("向右边滑动");
        }

        return true;
    }
}

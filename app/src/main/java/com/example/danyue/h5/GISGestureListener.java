package com.example.danyue.h5;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by danyue on 2017/4/1.
 */
public class GISGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final String TAG = "*GISGestureListener";

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown: "+e.getX()+","+e.getY());

        return super.onDown(e);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: "+e.getX()+","+e.getY());
        return super.onSingleTapUp(e);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: "+e.getX()+","+e.getY());
        super.onShowPress(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, "onSingleTapConfirmed: "+e.getX()+","+e.getY());
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: "+e1.getX()+","+e1.getY()+"&"+e2.getX()+","+e2.getY());
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: "+e1.getX()+","+e1.getY()+"&"+e2.getX()+","+e2.getY());
        return super.onFling(e1, e2, velocityX, velocityY);
    }
}
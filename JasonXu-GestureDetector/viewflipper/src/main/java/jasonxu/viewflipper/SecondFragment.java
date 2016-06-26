package jasonxu.viewflipper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

/**
 * Created by t_xuz on 6/22/16.
 */
public class SecondFragment extends Fragment {

    private ViewFlipper mFlipper;
    private GestureDetector mGestureDetector;
    private MainActivity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout parent = (LinearLayout) view;
        mFlipper = (ViewFlipper) parent.getChildAt(0);
        mFlipper.addView(getImageView(R.mipmap.jr1));
        mFlipper.addView(getImageView(R.mipmap.jr2));
        mFlipper.addView(getImageView(R.mipmap.jr3));
        mFlipper.addView(getImageView(R.mipmap.jr4));
        mFlipper.addView(getImageView(R.mipmap.jr5));

        mGestureDetector = new GestureDetector(mContext, new MySimpleGestureListener());
        mFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private ImageView getImageView(int id){
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(id);
        return imageView;
    }

    private class MySimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int MIN_DISTANCE = 100;
        private static final int MIN_VELOCITY = 200;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
           /* if (e2.getX() - e1.getX() > MIN_DISTANCE
                    && Math.abs(velocityX) > MIN_VELOCITY) {
                //向右滑动
                mFlipper.showPrevious();
            } else if (e2.getX() - e1.getX() < MIN_DISTANCE
                    && Math.abs(velocityX) > MIN_VELOCITY) {
                //向左边滑动
                mFlipper.showNext();
            }
            return true;*/

            //上面的是轮播,fipper默认支持的,以下是解决不去轮播
            if (e2.getX() - e1.getX() > MIN_DISTANCE
                    && Math.abs(velocityX) > MIN_VELOCITY) {
                if (mFlipper.getDisplayedChild() != 0 ){
                    //从左向右滑动
                    mFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext,R.anim.in_left_right));
                    mFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.out_left_right));
                    mFlipper.showPrevious();
                }else {
                    mFlipper.stopFlipping();
                }

            } else if (e2.getX() - e1.getX() < MIN_DISTANCE
                    && Math.abs(velocityX) > MIN_VELOCITY) {
                if (mFlipper.getDisplayedChild() != mFlipper.getChildCount()-1) {
                    //从右向左边滑动
                    mFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext,R.anim.in_right_left));
                    mFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.out_right_left));
                    mFlipper.showNext();
                }else {
                    mFlipper.stopFlipping();
                }
            }
            return true;
        }
    }
}

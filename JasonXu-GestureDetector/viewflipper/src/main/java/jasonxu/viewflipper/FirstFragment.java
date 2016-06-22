package jasonxu.viewflipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

/**
 * Created by t_xuz on 6/22/16.
 */
public class FirstFragment extends Fragment{

    ViewFlipper mFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first,container,false);
        mFlipper = (ViewFlipper) view.findViewById(R.id.flipper);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFlipper.setFlipInterval(2000);
        mFlipper.startFlipping();
    }

    @Override
    public void onDestroy() {
        if (mFlipper.isFlipping()){
            mFlipper.stopFlipping();
        }
        super.onDestroy();
    }
}

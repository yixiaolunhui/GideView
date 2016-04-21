package com.dalong.gideview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by zhouweilong on 16/4/21.
 */
public class GideFragment4 extends Fragment {

    private TextView mName;
    private ImageView mPicture;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gide_fragment4,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mPicture = (ImageView) view.findViewById(R.id.picture4_image);
        mName = (TextView) view.findViewById(R.id.meinv4_name);

    }

    public void startAnim(){
        if(mPicture!=null&&mName!=null){
            YoYo.with(Techniques.BounceInUp)
                    .duration(500)
                    .playOn(mPicture);

            YoYo.with(Techniques.BounceInRight)
                    .duration(500)
                    .playOn(mName);
        }
    }
}

package com.maxclub.android.sunset;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class SunsetFragment extends Fragment {

    private View mSceneView;
    private View mSunView;
    private View mSkyView;
    private View mSeaView;
    ObjectAnimator mHeightAnimator;
    private AnimationDrawable mSunAnimationDrawable;
    private AnimationDrawable mSkyAnimationDrawable;
    private AnimationDrawable mSeaAnimationDrawable;
    private AnimationDrawable mSunAnimationDrawableRev;
    private AnimationDrawable mSkyAnimationDrawableRev;
    private AnimationDrawable mSeaAnimationDrawableRev;
    private boolean mIsSunset;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);

        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);
        mSeaView = view.findViewById(R.id.sea);

        mIsSunset = true;

        mSceneView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSceneView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                float sunYStart = mSunView.getTop();
                float sunYEnd = mSkyView.getHeight();
                mHeightAnimator = ObjectAnimator
                        .ofFloat(mSunView, "y", sunYStart, sunYEnd)
                        .setDuration(5000);
                mHeightAnimator.setInterpolator(new AccelerateInterpolator());
            }
        });

        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsSunset) {
                    startSunsetAnimation();
                    mIsSunset = false;
                } else {
                    startSunriseAnimation();
                    mIsSunset = true;
                }
            }
        });

        return view;
    }

    private void startSunsetAnimation() {
        if (mHeightAnimator.isRunning()) {
            mHeightAnimator.cancel();
        }
        if (mSunAnimationDrawableRev != null && mSunAnimationDrawableRev.isRunning()) {
            mSunAnimationDrawableRev.stop();
        }
        if (mSkyAnimationDrawableRev != null && mSkyAnimationDrawableRev.isRunning()) {
            mSkyAnimationDrawableRev.stop();
        }
        if (mSeaAnimationDrawableRev != null && mSeaAnimationDrawableRev.isRunning()) {
            mSeaAnimationDrawableRev.stop();
        }

        mHeightAnimator.start();

        mSunView.setBackground(getResources().getDrawable(R.drawable.sun_gradient));
        mSunAnimationDrawable = (AnimationDrawable) mSunView.getBackground();
        mSunAnimationDrawable.setEnterFadeDuration(1500);
        mSunAnimationDrawable.setExitFadeDuration(1500);
        mSunAnimationDrawable.setOneShot(true);
        mSunAnimationDrawable.start();

        mSkyView.setBackground(getResources().getDrawable(R.drawable.sky_gradient));
        mSkyAnimationDrawable = (AnimationDrawable) mSkyView.getBackground();
        mSkyAnimationDrawable.setEnterFadeDuration(1500);
        mSkyAnimationDrawable.setExitFadeDuration(1500);
        mSkyAnimationDrawable.setOneShot(true);
        mSkyAnimationDrawable.start();

        mSeaView.setBackground(getResources().getDrawable(R.drawable.sea_gradient));
        mSeaAnimationDrawable = (AnimationDrawable) mSeaView.getBackground();
        mSeaAnimationDrawable.setEnterFadeDuration(1500);
        mSeaAnimationDrawable.setExitFadeDuration(1500);
        mSeaAnimationDrawable.setOneShot(true);
        mSeaAnimationDrawable.start();
    }

    private void startSunriseAnimation() {
        if (mHeightAnimator.isRunning()) {
            mHeightAnimator.cancel();
        }
        if (mSunAnimationDrawable != null && mSunAnimationDrawable.isRunning()) {
            mSunAnimationDrawable.stop();
        }
        if (mSkyAnimationDrawable != null && mSkyAnimationDrawable.isRunning()) {
            mSkyAnimationDrawable.stop();
        }
        if (mSeaAnimationDrawable != null && mSeaAnimationDrawable.isRunning()) {
            mSeaAnimationDrawable.stop();
        }

        mHeightAnimator.reverse();

        mSunView.setBackground(getResources().getDrawable(R.drawable.sun_gradient_rev));
        mSunAnimationDrawableRev = (AnimationDrawable) mSunView.getBackground();
        mSunAnimationDrawableRev.setEnterFadeDuration(1500);
        mSunAnimationDrawableRev.setExitFadeDuration(1500);
        mSunAnimationDrawableRev.setOneShot(true);
        mSunAnimationDrawableRev.start();

        mSkyView.setBackground(getResources().getDrawable(R.drawable.sky_gradient_rev));
        mSkyAnimationDrawableRev = (AnimationDrawable) mSkyView.getBackground();
        mSkyAnimationDrawableRev.setEnterFadeDuration(1500);
        mSkyAnimationDrawableRev.setExitFadeDuration(1500);
        mSkyAnimationDrawableRev.setOneShot(true);
        mSkyAnimationDrawableRev.start();

        mSeaView.setBackground(getResources().getDrawable(R.drawable.sea_gradient_rev));
        mSeaAnimationDrawableRev = (AnimationDrawable) mSeaView.getBackground();
        mSeaAnimationDrawableRev.setEnterFadeDuration(1500);
        mSeaAnimationDrawableRev.setExitFadeDuration(1500);
        mSeaAnimationDrawableRev.setOneShot(true);
        mSeaAnimationDrawableRev.start();
    }
}

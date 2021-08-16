package com.maxclub.android.sunset;

import android.animation.ObjectAnimator;
import android.graphics.drawable.TransitionDrawable;
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
    private ObjectAnimator mSunMoveYAnimator;
    private ObjectAnimator mSunScaleXAnimator;
    private ObjectAnimator mSunScaleYAnimator;
    private TransitionDrawable mSunDrawableAnim;
    private TransitionDrawable mSkyDrawableAnim;
    private TransitionDrawable mSeaDrawableAnim;
    private boolean mIsSunset;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        mSunDrawableAnim = (TransitionDrawable) mSunView.getBackground();
        mSkyDrawableAnim = (TransitionDrawable) mSkyView.getBackground();
        mSeaDrawableAnim = (TransitionDrawable) mSeaView.getBackground();

        mSunScaleXAnimator = ObjectAnimator
                .ofFloat(mSunView, "scaleX", 1.5f)
                .setDuration(4000);
        mSunScaleXAnimator.setInterpolator(new AccelerateInterpolator());

        mSunScaleYAnimator = ObjectAnimator
                .ofFloat(mSunView, "scaleY", 1.5f)
                .setDuration(4000);
        mSunScaleYAnimator.setInterpolator(new AccelerateInterpolator());

        mSceneView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSceneView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                float sunYStart = mSunView.getTop();
                float sunYEnd = mSkyView.getHeight() - mSunView.getHeight() / 2.0f;
                mSunMoveYAnimator = ObjectAnimator
                        .ofFloat(mSunView, "y", sunYStart, sunYEnd)
                        .setDuration(4000);
                mSunMoveYAnimator.setInterpolator(new AccelerateInterpolator());
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
        mSunMoveYAnimator.start();
        mSunScaleXAnimator.start();
        mSunScaleYAnimator.start();
        mSunDrawableAnim.startTransition(4000);
        mSkyDrawableAnim.startTransition(4000);
        mSeaDrawableAnim.startTransition(4000);
    }

    private void startSunriseAnimation() {
        mSunMoveYAnimator.reverse();
        mSunScaleXAnimator.reverse();
        mSunScaleYAnimator.reverse();
        mSunDrawableAnim.reverseTransition(4000);
        mSkyDrawableAnim.reverseTransition(4000);
        mSeaDrawableAnim.reverseTransition(4000);
    }
}

package com.adamkis.astronchallenge.common;

import android.view.View;

/**
 * Created by adam on 8/28/15.
 */
public class Utils {


    public static void crossfadeViews(View firstView, View secondView, boolean showFirst, boolean animate, int animationDurationMillis){

        float loadingAlpha;
        float contentAlpha;

        if( showFirst ){
            loadingAlpha = 1f;
            contentAlpha = 0f;
        }
        else{
            loadingAlpha = 0f;
            contentAlpha = 1f;
        }

        if( firstView != null && firstView.getAlpha() != loadingAlpha ){
            if( animate ) {
                firstView.animate()
                        .alpha(loadingAlpha)
                        .setDuration(animationDurationMillis)
//                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(null);
            }
            else{
                firstView.setAlpha(loadingAlpha);
            }
        }
        if( secondView != null && secondView.getAlpha() != contentAlpha ) {
            if( animate ) {
                secondView.animate()
                        .alpha(contentAlpha)
                        .setDuration(animationDurationMillis)
//                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setListener(null);
            }
            else{
                secondView.setAlpha(contentAlpha);
            }
        }

    }

    private static final int loadingAnimationDurationMillis = 2000;

    public static void hideLoadingAnimated( View loading, View container ){
        crossfadeViews(loading, container, false, true, loadingAnimationDurationMillis);
    }


    public static void revealViewAnimated( View view ){
        crossfadeViews(view, null, true, true, loadingAnimationDurationMillis);
    }



}

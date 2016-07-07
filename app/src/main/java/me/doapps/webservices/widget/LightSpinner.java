package me.doapps.webservices.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by frank on 01/07/16.
 */
public class LightSpinner extends Spinner {



    public LightSpinner(Context context) {
        super(context);


    }

    public LightSpinner(Context context, int mode) {
        super(context, mode);
    }

    public LightSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LightSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LightSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LightSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode) {
        super(context, attrs, defStyleAttr, defStyleRes, mode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public LightSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, defStyleRes, mode, popupTheme);

    }

}

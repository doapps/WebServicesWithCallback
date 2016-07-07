package me.doapps.webservices.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.doapps.luis.ServicesWithCallBacks.R;

/**
 * Created by frank on 01/07/16.
 */
public class DarkEditText extends EditText {
    public DarkEditText(Context context) {
        super(context);

        setStyle(context);

    }

    public DarkEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStyle(context);

    }

    public DarkEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setStyle(context);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DarkEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setStyle(context);
    }

    private void setStyle(Context context) {
        setTextColor(getResources().getColor(R.color.colorEditTextText));
        setPadding(getResources().getDimensionPixelSize(R.dimen.edititext_padding),
                getResources().getDimensionPixelSize(R.dimen.edititext_padding),
                getResources().getDimensionPixelSize(R.dimen.edititext_padding),
                getResources().getDimensionPixelSize(R.dimen.edititext_padding));
        setBackgroundResource(R.drawable.custom_edittext);
        setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
    }
}

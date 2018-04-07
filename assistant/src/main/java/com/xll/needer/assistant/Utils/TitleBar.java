package com.xll.needer.assistant.Utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xll.needer.assistant.R;

/**
 * Created by Administrator on 2018/4/5.
 */

public class TitleBar {
    private Context context;

    private ImageView iconLeft;

    private ImageView iconRight;

    private TextView title;

    public interface Action{
        void call();
    }

    public TitleBar(View rootView) {
        iconLeft = rootView.findViewById(R.id.iconLeft);
        iconRight = rootView.findViewById(R.id.iconRight);
        title = rootView.findViewById(R.id.title);
    }

    public void setIconLeft(@DrawableRes int resId) {
        if (iconLeft != null) {
            iconLeft.setBackgroundResource(resId);
        }
    }

    public void setIconLeftListener(final Action action) {
        if (iconLeft != null && action != null) {
            iconLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action.call();
                }
            });
        }
    }

    public void setIconRight(@DrawableRes int resId) {
        if (iconRight != null) {
            iconRight.setBackgroundResource(resId);
        }
    }

    public void setIconRightListener(final Action action) {
        if (iconRight != null && action != null) {
            iconRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action.call();
                }
            });
        }
    }

    public void setTitle(@StringRes int resId) {
        if (title != null) {
            title.setText(resId);
        }
    }
}

package ledkis.ledkisbaseapp.ui.views;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import ledkis.ledkisbaseapp.R;

/**
 * http://stackoverflow.com/questions/11607302/how-to-change-text-color-of-preference-category-in-android
 */
public class ColoredHeaderPreferenceCategory extends PreferenceCategory {

    public ColoredHeaderPreferenceCategory(Context context) {
        super(context);
    }

    public ColoredHeaderPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColoredHeaderPreferenceCategory(Context context, AttributeSet attrs,
                                           int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        titleView.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
    }
}

package co.ahuskano.something.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by ahuskano on 12.7.2015..
 */
public class FontUtils {

    public static final String ROBOTO_BLACK = "Roboto-Black.ttf";
    public static final String ROBOTO_BLACK_ITALIC = "Roboto-BlackItalic.ttf";
    public static final String ROBOTO_BOLD = "Roboto-Bold.ttf";
    public static final String ROBOTO_BOLD_ITALIC = "Roboto-BoldItalic.ttf";
    public static final String ROBOTO_ITALIC = "Roboto-Italic.ttf";
    public static final String ROBOTO_LIGHT = "Roboto-Light.ttf";
    public static final String ROBOTO_LIGHT_ITALIC = "Roboto-LightItalic.ttf";
    public static final String ROBOTO_MEDIUM = "Roboto-Medium.ttf";
    public static final String ROBOTO_MEDIUM_ITALIC = "Roboto-MediumItalic.ttf";
    public static final String ROBOTO_REGULAR = "Roboto-Regular.ttf";
    public static final String ROBOTO_THIN = "Roboto-Thin.ttf";
    public static final String ROBOTO_THIN_ITALIC = "Roboto-ThinItalic.ttf";

    public static void applyFont(Context context, TextView textView, String font) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "/font/" + font);
        textView.setTypeface(typeface);
    }
}

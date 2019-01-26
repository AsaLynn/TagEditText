package com.zxn.tagedit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 一个带标记标记的输入框.
 * Created by zxn on 2019/1/26.
 */
public class TagEditView extends LinearLayout {


    private TextView tvTag;
    private EditText etInput;
    private String TAG = "TagEditView";

    public TagEditView(Context context) {
        this(context, null);
    }

    public TagEditView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInit(attrs, defStyleAttr);
    }

    private void onInit(AttributeSet attrs, int defStyleAttr) {
        onInitView();

        final TypedArray typedArray
                = getContext()
                .obtainStyledAttributes(attrs, R.styleable.TagEditView, defStyleAttr, 0);
        CharSequence mTagName = typedArray.getText(R.styleable.TagEditView_tagName);
        CharSequence mHintText = typedArray.getText(R.styleable.TagEditView_hintText);

        int mTagNameColor = typedArray.getColor(R.styleable.TagEditView_tagNameColor, Color.parseColor("#a8b1b2"));
        int mHintTextColor = typedArray.getColor(R.styleable.TagEditView_hintTextColor, Color.parseColor("#a7acab"));

        //此方法读取的属性值的单位为px
        int dTagNameSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics());
        int mTagNameSize = typedArray.getDimensionPixelSize(R.styleable.TagEditView_tagNameSize, dTagNameSize);
        //float mTagNameSize = typedArray.getDimension(R.styleable.TagEditView_tagNameSize, 15);

        int dHintTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics());
        int mHintTextSize = typedArray.getDimensionPixelSize(R.styleable.TagEditView_hintTextSize, dHintTextSize);
        Log.i(TAG, "onInit: -->" + mTagNameSize);
        //onInit: -->52--->15
        //onInit: -->51.749996
        Log.i(TAG, "onInit: -->" + mHintTextSize);
        //onInit: -->13

        boolean mEditEnabled = typedArray.getBoolean(R.styleable.TagEditView_editEnabled, true);

        tvTag.setText(mTagName);
        //tvTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTagNameSize);
        tvTag.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTagNameSize);
        tvTag.setTextColor(mTagNameColor);

        etInput.setHint(mHintText);
        etInput.setHintTextColor(mHintTextColor);
        etInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, mHintTextSize);
        typedArray.recycle();

        //editEnabled--->
        etInput.setEnabled(mEditEnabled);
    }

    private void onInitView() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.layout_tag_edit_view, this, true);
        setOrientation(HORIZONTAL);
        tvTag = findViewById(R.id.tv_tag);
        etInput = findViewById(R.id.et_input);
    }

    public void setHintText(String text) {
        etInput.setHint(text);
    }

    public void setHintText(@StringRes int textId) {
        etInput.setHint(textId);
    }

    public void setInputText(String text) {
        etInput.setText(text);
    }

    public void setInputText(@StringRes int textId) {
        etInput.setText(textId);
    }

    public String getInputText() {
        return etInput.getText().toString();
    }

    public TextView getTagNameView() {
        return tvTag;
    }

    public EditText getEditText() {
        return etInput;
    }
}

/*    public static float applyDimension(int unit, float value,
                                       DisplayMetrics metrics)
    {
        switch (unit) {
            case COMPLEX_UNIT_PX:
                return value;
            case COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f/72);
            case COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f/25.4f);
        }
        return 0;
    }*/


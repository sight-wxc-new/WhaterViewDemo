package com.sight.water.whaterviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Sight on 2016/4/6.
 * 自定义 itemView
 * 希望做的像water一样灵活
 */

public class WaterView extends LinearLayout
{

    TextView mTvLeftTilte;
    TextView mTvRightTitle;
    ImageView mIvLeftIcon;
    TextView mTvLeftTip;
    View view;

    public WaterView(Context context)
    {
        super(context);

    }

    public WaterView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context, attrs);
    }


    public WaterView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

    }

    private void initView(Context context, AttributeSet attributeSet)
    {
        View mView = LayoutInflater.from(context).inflate(R.layout.water_item, this, true);
        mTvLeftTilte = (TextView) findViewById(R.id.tv_left);
        mTvRightTitle = (TextView) findViewById(R.id.tv_right);
        mIvLeftIcon = (ImageView) findViewById(R.id.iv_left_icon);
        mTvLeftTip = (TextView) findViewById(R.id.tv_left_tip);
        view = findViewById(R.id.vi_view);

        View mParentView = findViewById(R.id.lin_layout);

        TypedArray attrubite = context.obtainStyledAttributes(attributeSet, R.styleable.WaterView);
        int leftResID = attrubite.getResourceId(R.styleable.WaterView_left_title_drawable, 0);
        int rightResID = attrubite.getResourceId(R.styleable.WaterView_right_title_drawable, 0);
        if (leftResID != 0) {
        }
        if (rightResID != 0)
            setRightDrawable(rightResID);
        String leftTitle = attrubite.getString(R.styleable.WaterView_left_title);
        if (leftTitle != null)
            setLeftTitle(leftTitle);
        String rightTitle = attrubite.getString(R.styleable.WaterView_right_title);
        if (rightTitle != null)
            setRightTitle(rightTitle);
        String leftTip = attrubite.getString(R.styleable.WaterView_left_tip);
        if (leftTip != null)
            setLeftTip(leftTip);
        int leftBack = attrubite.getColor(R.styleable.WaterView_left_tip_color, -1);
        if (leftBack != -1) {
            setLeftBackground(leftBack);
        }
        boolean isShowRightBack = attrubite.getBoolean(R.styleable.WaterView_right_icon_visable, true);
        if (!isShowRightBack)
            setRightDrawableNull();
        int titlecolor = attrubite.getColor(R.styleable.WaterView_right_text_color, -1);
        if (titlecolor != -1)
            setRightTitleTextColor(titlecolor);
        int padding = (int) attrubite.getDimension(R.styleable.WaterView_radius_padding, 0);
        if (padding != 0) {
            mParentView.setPadding(padding, padding, padding, padding);
        }
        attrubite.recycle();
    }


    public void setLeftTitle(String title)
    {
        mTvLeftTilte.setText(title);
    }

    public void setLeftTip(String title)
    {
        mTvLeftTip.setText(title);
    }

    public void setLeftBackground(int color)
    {
        mTvLeftTip.setBackgroundColor(color);
    }

    public void setRightTitle(String title)
    {
        mTvRightTitle.setText(title);
    }

    public void setRightTitleTextColor(int color)
    {
        mTvRightTitle.setTextColor(color);
    }

    public void setLeftDrawable(int id)
    {
        ResourcesCompat.setLeftDrawable(mTvLeftTilte, id);
    }

    public void setRightDrawable(int id)
    {
        ResourcesCompat.setRightDrawable(mTvRightTitle, id);
    }

    public void setRightDrawableNull()
    {
        setRightDrawable(R.mipmap.ic_action_back_water_null);
    }

    /***
     * 设置左边按钮
     */
    public void setLeftIcon(int id)
    {
        if (id!=0){
            mIvLeftIcon.setVisibility(View.VISIBLE);
            mIvLeftIcon.setBackgroundResource(id);
        }else{
            mIvLeftIcon.setVisibility(View.GONE);
        }

    }


    /**
     * 设置右边的Icon
     *
     * @param leftID
     * @param color
     * @param onClickListener
     */
    public void setRightIcon(String text, int leftID, int color, OnClickListener onClickListener, Object tag)
    {
        view.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = (LayoutParams) mTvRightTitle.getLayoutParams();
        params.width = LayoutParams.WRAP_CONTENT;
        params.weight = 0;
        params.gravity = Gravity.RIGHT;
        mTvRightTitle.setLayoutParams(params);
        mTvRightTitle.setTextColor(color);
        mTvRightTitle.setText(text);
        mTvRightTitle.setOnClickListener(onClickListener);
        mTvRightTitle.setTag(tag);
        ResourcesCompat.setLeftDrawable(mTvRightTitle, leftID);

    }


}


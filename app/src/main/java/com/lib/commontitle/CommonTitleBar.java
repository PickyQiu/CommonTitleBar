package com.lib.commontitle;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by PickyJun on 2018/4/23
 * 通用的title
 */
public class CommonTitleBar {

    private static final String TAG = "CommonTitleBar-->";

    private LinkedList<Activity> mList = new LinkedList<>();//activity存入list中，每次挂壁页面时删除最上面的activity

    private View view;//整个标题栏view

    private int isLeftIconVisible = View.VISIBLE;//默认左边的icon可见


    public CommonTitleBar() {

    }

    private View.OnClickListener mTitleClickListener;

    private View.OnClickListener mRightClickListener;

    private View.OnClickListener mLeftClickListener;

    /**
     * 必须调用
     */
    public CommonTitleBar init(Activity activity) {
        mList.add(activity);
        createAndBindView();
        return this;
    }


    private void createAndBindView() {

        ViewGroup group = (ViewGroup) (mList.getLast()).getWindow().getDecorView();
        ViewGroup mParent = (ViewGroup) group.getChildAt(0);
        view = LayoutInflater.from(mList.getLast()).
                inflate(bindLayoutId(), mParent, false);
        mParent.addView(view, 0);

    }


    private int bindLayoutId() {
        return R.layout.title_bar;
    }

    /**
     * 设置文本
     */
    private void setText(int viewId, String text) {
        TextView tv = findViewById(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    /**
     * 设置文本
     */
    private void setText(int viewId, int res) {
        TextView tv = findViewById(viewId);
        if (res != 0) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(res);
        }
    }

    private <T extends View> T findViewById(int viewId) {
        return (T) view.findViewById(viewId);
    }

    private void setOnClickListener(int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
    }


    private void setVisibility(int viewId, int visibility) {
        findViewById(viewId).setVisibility(visibility);
    }


    /**
     * 设置标题栏背景
     * 推荐在xml中设置统一背景，如果有某个页面有特殊需求再调用此方法
     */
    public CommonTitleBar setBackground(int res) {
        findViewById(R.id.rl_title).setBackgroundResource(res);
        return this;
    }

    /**
     * 设置右侧图标
     *
     * @param res  R.mipmap.···
     * @param seat 图标相对于文字的位置，可选： left，right
     */
    public CommonTitleBar setRightIcon(int res, String seat) {
        if (res != 0) {
            TextView tv = findViewById(R.id.tv_right);
            tv.setVisibility(View.VISIBLE);
            Resources resources = mList.getLast().getResources();
            Drawable drawable = resources.getDrawable(res);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            switch (seat) {
                case "right":
                    tv.setCompoundDrawables(null, null, drawable, null);
                    break;
                case "left":
                default:
                    tv.setCompoundDrawables(drawable, null, null, null);
                    break;
            }

        }
        return this;
    }

    /**
     * 设置标题
     */
    public CommonTitleBar setTitle(int stringResource) {
        setText(R.id.title, stringResource);
        return this;
    }

    /**
     * 设置标题
     */
    public CommonTitleBar setTitle(String mTitle) {
        setText(R.id.common_title, mTitle);
        return this;
    }

    /**
     * 设置右标题
     */
    public CommonTitleBar setRightTitle(String mRightText) {
        setText(R.id.tv_right, mRightText);
        return this;
    }

    /**
     * 设置右标题
     */
    public CommonTitleBar setRightTitle(int stringResource) {
        setText(R.id.tv_right, stringResource);
        return this;
    }

    /**
     * 设置标题颜色
     */
    public CommonTitleBar setTitleColor(int color) {
        setColor(R.id.common_title, color);
        return this;
    }


    /**
     * 设置右侧文字颜色
     */
    public CommonTitleBar setRightTextColor(int color) {
        setColor(R.id.tv_right,color);
        return this;
    }

    /**
     * 设置右侧文字和图标间距
     */
    public CommonTitleBar setDrawablePadding(int dp) {
        TextView tv = findViewById(R.id.tv_right);
        tv.setCompoundDrawablePadding(dp2px(dp));
        return this;
    }

    /**
     * 隐藏左侧图标
     */
    public CommonTitleBar hideLeftIcon() {
        this.isLeftIconVisible = View.INVISIBLE;
        return this;
    }

    /**
     * 设置左侧图标点击事件
     */
    public CommonTitleBar setRightClickListener(View.OnClickListener mRightClickListener) {
        this.mRightClickListener = mRightClickListener;
        return this;
    }

    /**
     * 设置左侧图标点击事件
     */
    public CommonTitleBar setTitleClickListener(View.OnClickListener mTitleClickListener) {
        this.mTitleClickListener = mTitleClickListener;
        return this;
    }

    /**
     * 重新设置左侧图标点击事件
     */
    public CommonTitleBar setLeftClickListener(View.OnClickListener mLeftClickListener) {
        this.mLeftClickListener = mLeftClickListener;
        return this;
    }

    /**
     * 设置颜色
     * @param viewId
     * @param color
     */
    private void setColor(int viewId, int color) {
        TextView tv = findViewById(viewId);
        tv.setTextColor(color);
    }

    public void create() {
        setOnClickListener(R.id.tv_right, mRightClickListener);
        setOnClickListener(R.id.common_title, mTitleClickListener);
        setVisibility(R.id.back, isLeftIconVisible);
        if (mLeftClickListener == null) {
            setOnClickListener(R.id.back, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.getLast().finish();
                    mList.removeLast();
                }
            });
        } else {
            setOnClickListener(R.id.back, mLeftClickListener);
        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, mList.getLast().getResources().getDisplayMetrics());
    }
}

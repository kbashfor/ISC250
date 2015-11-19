package edu.oswego.kbashfor.draganddropv4;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class Tile extends TextView {
    public int pre_pos;
    public int cur_pos;

    /**
     * This is called when Android constructs view from XML.
     *
     * @param context
     * @param attrs
     */
    public Tile(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init(-1, -1);
    }

    /**
     * This is used when inflating View in code
     *
     * @param context
     */
    public Tile(Context context) {
        super(context);
        init(-1, -1);
    }

    /**
     * This would be used when XML includes @style element
     *
     * @param context
     * @param attrs
     * @param style
     */
    public Tile(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init(-1, -1);
    }

    public void init(int pre_pos, int cur_pos) {
        this.pre_pos = pre_pos;
        this.cur_pos = cur_pos;
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.match);

//        Log.d("OD", arr.getString(
//                R.styleable.Tile_android_text));
//        Log.d("OD", "" + arr.getColor(
//                R.styleable.Tile_android_textColor, Color.BLACK));
//        Log.d("OD", arr.getString(
//                R.styleable.Tile_alpha));

        //Don't forget this
        arr.recycle();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(40,40);
//    }

}
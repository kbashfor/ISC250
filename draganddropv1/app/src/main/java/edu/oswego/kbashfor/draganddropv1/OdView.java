package edu.oswego.kbashfor.draganddropv1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class OdView extends TextView {
    public int pre_pos;
    public int cur_pos;

    /** This is called when Android constructs view from XML.
     *
     * @param context
     * @param attrs
     */
    public OdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(-1,-1);
    }

    /** This is used when inflating View in code
     *
     * @param context
     */
    public OdView(Context context) {
        super(context);
        init(-1, -1);
    }

    /** This would be used when XML includes @style element
     *
     * @param context
     * @param attrs
     * @param style
     */
    public OdView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init(-1,-1);
    }

    public void init(int pre_pos, int cur_pos) {
        this.pre_pos = pre_pos; this.cur_pos = cur_pos;
    }
}

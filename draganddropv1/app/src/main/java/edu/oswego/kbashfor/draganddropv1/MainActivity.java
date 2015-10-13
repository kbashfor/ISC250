package edu.oswego.kbashfor.draganddropv1;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends Activity {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in View.setId().
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        java.util.ArrayList<String> list = new ArrayList<>();
        list.add("A"); list.add("B"); list.add("C");
        LayoutInflater vi = (LayoutInflater) getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView v = null;
        ViewGroup insertPoint1 = (ViewGroup) findViewById(R.id.linearLayoutInner1);
        ;
        ViewGroup insertPoint2 = (ViewGroup) findViewById(R.id.linearLayoutInner2);
        // insertPoint2.setOnDragListener(new OdDragListener(this));

        android.view.ViewGroup.LayoutParams lop1 = insertPoint1.getLayoutParams();
        android.view.ViewGroup.LayoutParams lop2 = insertPoint2.getLayoutParams();

        for (int i = 0,  len = list.size(); i < len; i++) {
            v = (TextView)vi.inflate(R.layout.tile, null);
            int id = generateViewId();
            v.setId(id);
            v.setText(list.get(i));
            ((OdView)v).init(-1, i);
            insertPoint1.addView(v, i, lop1);
            v.setOnTouchListener(new OdTouchListener(id));

            v = (TextView)vi.inflate(R.layout.tile, null);
            id = generateViewId();
            v.setId(id);
            v.setText("_");
            ((OdView)v).init(-1, i);
            insertPoint2.addView(v, i, lop2);
            v.setOnDragListener(new OdDragListener(this, v));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class OdTouchListener implements View.OnTouchListener {
    private int viewId;
    public OdTouchListener(int id) {
        this.viewId = id;
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            // view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}

class OdDragListener implements View.OnDragListener {

    public OdDragListener(Context context, TextView v) {
        this.context = context;
        this.v = v;
        // enterShape = context.getResources().getDrawable(R.drawable.shape_droptarget);
        enterShape = ResourcesCompat.getDrawable(context.getResources(), R.drawable.after_shape, null);
        // normalShape = context.getResources().getDrawable(R.drawable.shape);
        normalShape = ResourcesCompat.getDrawable(context.getResources(), R.drawable.after_shape, null);
    }
    private Context context;
    private TextView v;
    Drawable enterShape;
    Drawable normalShape;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                if (v == this.v) {
                    v.setBackgroundDrawable(enterShape);
                }
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                if (v == this.v) {
                    v.setBackgroundDrawable(normalShape);
                }
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup

                if (v == this.v) {
                    View view = (View) event.getLocalState();
                    int view_pre_pos = ((OdView)view).pre_pos;
                    int view_cur_pos = ((OdView)view).cur_pos;
                    int v_pre_pos = ((OdView)v).pre_pos;
                    int v_cur_pos = ((OdView)v).cur_pos;
                    ((OdView)view).pre_pos = ((OdView)view).cur_pos;
                    ((OdView)view).cur_pos = v_cur_pos;
                    ((OdView)v).pre_pos = ((OdView)v).cur_pos;
                    ((OdView)v).cur_pos = view_cur_pos;
                    ((OdView)view).setText(((OdView)view).getText().subSequence(0,1)+Integer.toString(((OdView) view).cur_pos));
                    ((OdView)v).setText(((OdView)v).getText().subSequence(0,1)+Integer.toString(((OdView) v).cur_pos));
                    Log.d("OD", "view_pre_pos = " + view_pre_pos);
                    Log.d("OD", "view_cur_pos = " + view_cur_pos);
                    Log.d("OD", "v_pre_pos = " + v_pre_pos);
                    Log.d("OD", "v_pre_pos = " + v_cur_pos);

                    ViewGroup owner = (ViewGroup) view.getParent();
                    // LinearLayout container = (LinearLayout) v;
                    LinearLayout container = (LinearLayout) v.getParent();
                    owner.removeView(view);
                    container.removeView(v);
                    if (v_cur_pos < view_cur_pos) {
                        container.addView(view, v_cur_pos);
                        owner.addView(v, view_cur_pos);
                    } else {
                        owner.addView(v, view_cur_pos);
                        container.addView(view, v_cur_pos);
                    }
                    // view.setVisibility(View.VISIBLE);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                if (v == this.v) {
                    // v.setBackgroundDrawable(normalShape);
                }
            default:
                break;
        }
        return true;
    }
}
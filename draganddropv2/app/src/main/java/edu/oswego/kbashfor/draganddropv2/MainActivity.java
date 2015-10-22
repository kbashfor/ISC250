package edu.oswego.kbashfor.draganddropv2;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected final int COLS = 5;
    protected int[] nums;
    protected int[] ids;
    protected final java.util.Random rand = new java.util.Random();
    protected CharSequence dragged = "";
    protected Tile tile_dragged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nums = new int[COLS];
        ids = new int[COLS];
        initIds();
        // genNums();
        initNums();
        buttonListener();

        for (int i: ids) {
            final Tile tile = (Tile)findViewById(i);
            tile.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        // Toast.makeText(getApplicationContext(), "onTouch:" + ((Tile)v).getText(), Toast.LENGTH_SHORT).show();
                        dragged = ((Tile)v).getText();
                        tile_dragged = tile;
                        // view.setVisibility(View.INVISIBLE);
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            final int temp = i;

            tile.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    // Toast.makeText(getApplicationContext(), "onDrag:" + ((Tile)v).getText(), Toast.LENGTH_SHORT).show();
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            // Debug
                            // Toast.makeText(getApplicationContext(), "Started:" + ((Tile) findViewById(temp)).getText(), Toast.LENGTH_SHORT).show();
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            // Toast.makeText(getApplicationContext(), "Entered: " + ((Tile) findViewById(temp)).getText(), Toast.LENGTH_SHORT).show();
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            // Toast.makeText(getApplicationContext(), "Exited: " + ((Tile) findViewById(temp)).getText(), Toast.LENGTH_SHORT).show();
                            break;
                        case DragEvent.ACTION_DROP:
                            Tile t = (Tile) findViewById(temp);
                            CharSequence text = t.getText();
                            t.setText(dragged);
                            tile_dragged.setText(text);
                            t.setTextColor(Color.CYAN);
                            tile_dragged.setTextColor(Color.CYAN);
                            // Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                            break;
                        case DragEvent.ACTION_DRAG_ENDED:
                            break;
                        default:
                            // Toast.makeText(getApplicationContext(), "Default: " + ((Tile) findViewById(temp)).getText(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return true;
                }
            });
        }
    }

    protected void initIds() {
        ids[0] = R.id.cell11;
        ids[1] = R.id.cell12;
        ids[2] = R.id.cell13;
        ids[3] = R.id.cell14;
        ids[4] = R.id.cell15;
    }

    protected void initNums() {
        for (int i = 0; i < COLS; i++) {
            Tile tile = (Tile)findViewById(ids[i]);
            nums[i] = i + 1;
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                tile.setBackgroundDrawable( getResources().getDrawable(R.drawable.bg) );
            } else {
                tile.setBackground( getResources().getDrawable(R.drawable.bg));
            }
            tile.setText("" + nums[i]);
            tile.init(i - 1, i);
            tile.setTextColor(Color.GREEN);
        }
    }

    protected void genNums() {
        for (int i = 0; i < COLS; i++) {
            nums[i] = rand.nextInt(COLS) + 1;
            Tile tile = (Tile)findViewById(ids[i]);
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                tile.setBackgroundDrawable( getResources().getDrawable(R.drawable.bg) );
            } else {
                tile.setBackground( getResources().getDrawable(R.drawable.bg));
            }
            tile.setText("" + nums[i]);
            tile.setTextColor(Color.YELLOW);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonListener(){
        Button button = (Button) findViewById(R.id.button_generator);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genNums();
            }
        });
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

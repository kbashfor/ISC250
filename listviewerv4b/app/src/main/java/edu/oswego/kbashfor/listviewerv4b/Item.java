package edu.oswego.kbashfor.listviewerv4b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kyle on 9/29/2015.
 */

public class Item extends Activity {
    private Intent intent = null;
    private Word w = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        // Intent intent = getIntent();
        intent = getIntent();
        String what = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        w = intent.getParcelableExtra("word");
        TextView tv1 = (TextView) findViewById(R.id.odtext1);
        // tv.setText(what);
        tv1.setText(w.lexeme());
        tv1.setTextSize(16 * getResources().getDisplayMetrics().density);
        TextView tv2 = (TextView) findViewById(R.id.odtext2);
        // tv.setText(what);
        tv2.setText(w.getDefinition());
        tv2.setTextSize(16 * getResources().getDisplayMetrics().density);
    }

    public void od_back(View v) {
        setResult(Activity.RESULT_OK, intent);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, "Showed def for " + w);
        finish();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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
    }*/
}
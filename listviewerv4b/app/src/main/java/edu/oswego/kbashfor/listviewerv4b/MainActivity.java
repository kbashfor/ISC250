package edu.oswego.kbashfor.listviewerv4b;

import android.app.Activity;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends ListActivity {

    public static final String EXTRA_MESSAGE = "name";
    public static final int INTENT_RESULT_SHOWED_DEF = 1;
    public static final boolean DEBUG = false;
    public static final String INTENT_FILTER1 = "WORD_READER_DONE";
    public static final String INTENT_FILTER2 = "PROGRESS_UPDATE";
    public static final String INTENT_KEY1 = "wordList";
    public static final String INTENT_KEY2 = "progressUpdate";
    private ArrayList<Word> wordList;
    private ListView listView;
    private ProgressBar pb;
    private ArrayAdapter<Word> adapter;
    private final MainActivity that = this;
    public static final String URL = "http://cs.oswego.edu/~kbashfor/isc250/parse-xml/parseB/words.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        TextView tv = (TextView) findViewById(R.id.odprogress);
        tv.setText("0");
        listView = (ListView) findViewById(android.R.id.list);
        if (listView == null) {
            Log.d("OD", "listView == null");
        } else {
            Log.d("OD", "listView NOT null");
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver,
                new IntentFilter(INTENT_FILTER1));
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver,
                new IntentFilter(INTENT_FILTER2));
        // ArrayList<String> list = new ArrayList<>();
        // wordList = new ArrayList<>();
        pb = (ProgressBar) findViewById(R.id.busy);
        pb.setVisibility(ProgressBar.VISIBLE);
        pb.setProgress(0);
        pb.setMax(100);
        // AsyncTask<String, Integer, Integer> task = new ReaderTask(getApplicationContext());
        // task.execute("the_words");
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new ReaderTask(getApplicationContext(), URL));
    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("OD", "Got broadcast message");
            if (intent.getAction().equals(INTENT_FILTER1)) {
                pb.setVisibility(ProgressBar.INVISIBLE);
                wordList = intent.getParcelableArrayListExtra(INTENT_KEY1);
                if (wordList == null) {
                    wordList = new ArrayList<>();
                }
                adapter = new ArrayAdapter<Word>(
                        // context,
                        that.getBaseContext(),
                        android.R.layout.simple_list_item_1, android.R.id.text1,
                        wordList);

                listView.setAdapter(adapter);
            } else if (intent.getAction().equals(INTENT_FILTER2)) {
                int progress = intent.getIntExtra(INTENT_KEY2, 1);
                TextView tv = (TextView) that.findViewById(R.id.odprogress);
                tv.setText(""+progress);
            }

        }
    };

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, Item.class);
        ListView lv = (ListView) findViewById(android.R.id.list);
        ListAdapter la = lv.getAdapter();
        if (la == null) {
            Log.d("OD", "null list adapter");
        } else {
            // String item = (String) la.getItem(position);
            String item = la.getItem(position).toString();
            intent.putExtra(EXTRA_MESSAGE, item);
            Word w = (Word) la.getItem(position);
            intent.putExtra("word", w);
        }
        // startActivity(intent);
        startActivityForResult(intent, INTENT_RESULT_SHOWED_DEF);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_RESULT_SHOWED_DEF) {
            if (resultCode == Activity.RESULT_OK) {
                String what = data.getStringExtra(MainActivity.EXTRA_MESSAGE);
                Toast.makeText(this, what, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
        super.onDestroy();
    }
}
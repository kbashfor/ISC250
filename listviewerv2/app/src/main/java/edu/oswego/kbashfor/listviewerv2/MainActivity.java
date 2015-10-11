package edu.oswego.kbashfor.listviewerv2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends ListActivity {

    public static final String EXTRA_MESSAGE = "name";
    public static final boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(android.R.id.list);
        if (listView == null) {
            Log.d("OD", "listView == null");
        } else {
            Log.d("OD", "listView NOT null");
        }
        ArrayList<String> list = new ArrayList<>();
        try {
            // int id = getResources().getIdentifier("words.txt", "raw", this.getPackageName());
            // Scanner s = new Scanner(getResources().openRawResource(id));
            Scanner s = new Scanner(getResources().openRawResource(R.raw.the_words));

            int i = 0;

            while (s.hasNext()) {
                if (DEBUG) {
                    i++;
                    if (i > 3) break;
                }
                String word = s.nextLine();
                list.add(word);
                Log.d("OD", "just added " + word);
            }
        } catch (Exception e) {
            Log.d("OD", "try-catch failed");
        }

        String[] players = new String[]{
                "Homer", "Marge", "Bart", "Lisa", "Maggie"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1,
                list);

        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView clickedView = (TextView) view;
                Toast.makeText(MainActivity.this, "Item with id \"" + id + "\" " +
                                "& Position \"" + position + "\" " +
                                "& Name \"" + clickedView.getText() + "\" ",
                        Toast.LENGTH_LONG).show();
            }
        });*/
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, Item.class);
        ListView lv = (ListView) findViewById(android.R.id.list);
        ListAdapter la = lv.getAdapter();
        if (la == null) {
            Log.d("OD", "null list adapter");
        } else {
            String item = (String) la.getItem(position);
            intent.putExtra(EXTRA_MESSAGE, item);
        }
        startActivity(intent);
    }
}
package edu.oswego.kbashfor.listviewerv1;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    public static final String EXTRA_MESSAGE = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(android.R.id.list);
        if (listView == null) {
            Log.d("Main", "listView == null");
        }

        String[] players = new String[] {
                "Homer", "Marge", "Bart", "Lisa", "Maggie"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1,
                players);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView clickedView = (TextView) view;
                Toast.makeText(MainActivity.this, "Item with id \"" + id + "\" " +
                                "& Position \"" + position + "\" " +
                                "& Name \"" + clickedView.getText() + "\" ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

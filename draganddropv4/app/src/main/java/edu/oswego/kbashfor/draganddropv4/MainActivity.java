package edu.oswego.kbashfor.draganddropv4;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int COLUMNS = 5;
    private final int max_width = 9;
    private int gen_button_id;
    protected Tile[] ids;
    protected String check;
    protected String definition;
    protected final java.util.Random rand = new java.util.Random();
    protected CharSequence dragged = "";
    protected Tile tile_dragged;
    private ArrayList<Word> wordList;
    public static final String URL = "http://cs.oswego.edu/~kbashfor/isc250/parse-xml/parseB/words.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                wordList = new Fetcher(URL).fetch();
                return null;
            }
        };

        task.execute();

        RelativeLayout rel = (RelativeLayout) findViewById(R.id.rellay);
        TableLayout table = new TableLayout(this);
        table.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        // table.setStretchAllColumns(true);
        TableRow table_row = new TableRow(this);
        Button generator = new Button(this);
        Button checker = new Button(this);
        Button deff = new Button(this);
        generator.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        generator.setText(R.string.button_gen);
        checker.setText(R.string.check);
        deff.setText(R.string.deff_word);
        gen_button_id = generator.getId();

        if (wordList == null){
            wordList = new ArrayList<Word>();
            wordList.add(new Word("124124", "Something", "Noun"));
            wordList.add(new Word("Tests2", "Something", "Noun"));
            wordList.add(new Word("Jack_j", "Something", "Noun"));
            wordList.add(new Word("asdaaa", "Something", "Noun"));
        }

        genNewWord(table_row, false);
        buttonListener(generator, table_row);
        checkListener(checker);
        deffListener(deff);
        table.addView(table_row);

        table.addView(generator);
        table.addView(deff);
        table.addView(checker);
        rel.addView(table);

    }

    protected void genNewWord(TableRow tr, Boolean bool) {
        if (bool) {
            tr.removeAllViews();
        }
        Word word = wordList.get(rand.nextInt(wordList.size()));
        this.COLUMNS = word.length();
        ids = new Tile[COLUMNS];
        char[] letters = word.toArray();
        this.check = word.lexeme();
        this.definition = word.getDefinition();
        letters = jumbleWord(letters);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < COLUMNS; i++){
            Tile temp = (Tile) inflater.inflate(R.layout.tile, null);
            temp.setGravity(Gravity.CENTER_HORIZONTAL);
            temp.setId(i);
            temp.setText("" + letters[i]);
            temp.init(i - 1, i);;
            ids[i] = temp;
            tr.addView(ids[i]);
        }

        for (Tile i: ids) {
            final Tile tile = i;
            i.setOnTouchListener(new View.OnTouchListener() {
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

            final Tile temp = i;

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
                            Tile t = (Tile) findViewById(temp.getId());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonListener(Button b, TableRow tr){
        // Button button = (Button) findViewById(R.id.button_generator);
        final TableRow trf = tr;
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genNewWord(trf, true);
            }
        });
    }

    public void checkListener(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWords();
            }
        });
    }

    public void deffListener(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Definition: " + definition, Toast.LENGTH_LONG).show();
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

    public char[] jumbleWord(char[] word) {
        for (int j = word.length - 1; j > 0; j--) {
            int i = (int) ( Math.random() * (j+1) );
            char temp = word[j];
            word[j] = word[i];
            word[i] = temp;
        }
        return word;
    }

    public void checkWords() {
        String temp = "";
        for(Tile i : ids) {
            temp = temp + i.getText();
        }
        if(temp.matches(check.toString())) {
            Toast.makeText(getApplicationContext(), check + "is the correct word", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "WRONG", Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(getApplicationContext(), "Word: " + check + "; Jumble: " + temp, Toast.LENGTH_SHORT).show();
    }
}
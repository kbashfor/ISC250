package edu.oswego.kbashfor.listviewerv4b;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kyle on 9/29/2015.
 */

public class ReaderTask implements Runnable {

    private Context context;
    private ArrayList<Word> wordList;
    private String url;

    //    public ReaderTask(Context context) {
//        this.context = context;
//    }
    public ReaderTask(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Override
    public void run() {
        Fetcher f = new Fetcher(this.url);
        Log.i("TEST_1" ,this.url);
        wordList = (ArrayList<Word>)f.fetch();
        postExecute();
    }
//    @Override
//    public void run() {
//        // ArrayList<String> list = new ArrayList<>();
//        wordList = new ArrayList<>();
//        Scanner scanner = null;
//        try {
//            // int id = getResources().getIdentifier("words.txt", "raw", this.getPackageName());
//            // Scanner s = new Scanner(getResources().openRawResource(id));
//            scanner = new Scanner(context.getResources().openRawResource(R.raw.the_words));
//
//            int j = 0;
//            int i = 1;
//            Random rand = new Random();
//            while (scanner.hasNext()) {
//                String word = scanner.nextLine();
//                // list.add(word);
//                wordList.add(new Word(word));
//                // publishProgress(i++);
//                updateProgress(i++);
//                for (int k = 0; k < 10; k++) {
//                    j = rand.nextInt();
//                    rand.setSeed(System.nanoTime() + j);
//                }
//                // Log.d("OD", "just added " + word + j);
//            }
//        } catch (Exception e) {
//            Log.d("OD", "try-catch failed");
//        } finally {
//            scanner.close();
//        }
//        postExecute();
//    }

    // protected void onProgressUpdate(Integer ... progress ) {
    protected void updateProgress(Integer... progress) {
        Intent intent = new Intent(MainActivity.INTENT_FILTER2);
        intent.putExtra(MainActivity.INTENT_KEY2, progress[0]);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    // protected void onPostExecute(Integer result) {
    protected void postExecute() {
        Intent intent = new Intent(MainActivity.INTENT_FILTER1);
        intent.putParcelableArrayListExtra(MainActivity.INTENT_KEY1, wordList);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
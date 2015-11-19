package edu.oswego.kbashfor.draganddropv4;

import android.util.Log;
// import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 6/5/2015.
 * Parse XML file indicated by URL; see http://developer.android.com/training/basics/network-ops/xml.html
 */
public class Fetcher {
    static private final String WORDS = "words";
    static private final String WORD = "word";
    static private final String LEXEME = "lexeme";
    static private final String POS = "pos";
    static private final String DEFINITION = "definition";

    // static private java.util.regex.Pattern isDataFilePattern;
    static private java.util.regex.Pattern isXmlFilePattern;
    static private String nameSpace;

    static {
        // isDataFilePattern = java.util.regex.Pattern.compile(".*\\.txt");
        isXmlFilePattern = java.util.regex.Pattern.compile(".*\\.xml");
        nameSpace = null;
    }

    private String url = null;
    public Fetcher(String url) {
        this.url = url;
    }
    public ArrayList<Word> fetch() {
        try {
            URL url = new URL(this.url);
            URLConnection client = url.openConnection();
            Log.i("TEST", client.getContent().toString());
            InputStream in = client.getInputStream();
            // XmlPullParser parser = Xml.newPullParser();
            XmlPullParserFactory ppf = XmlPullParserFactory.newInstance();
            XmlPullParser parser = ppf.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return parseWords(parser);
        } catch (XmlPullParserException xppe) {
            Log.i("Catch - XmlPull", xppe.toString());
            return null;
        } catch (Exception ioe) {
            Log.i("Catch - IO", ioe.toString());
            return null;
        } finally {
        }

    }

    private ArrayList<Word> parseWords(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Word> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, nameSpace, WORDS);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the word tag (entry would have been a better term but...)
            if (name.equals(WORD)) {
                entries.add(readWord(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Word readWord(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, nameSpace, WORD);
        String lexeme = null;
        String definition = null;
        String pos = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(LEXEME)) {
                lexeme = readLexeme(parser);
                Log.i("LEXME", lexeme);
            } else if (name.equals(DEFINITION)) {
                definition = readDefinition(parser);
                Log.i("DEF", definition);
            } else if (name.equals(POS)) {
                pos = readPOS(parser);
                Log.i("POS", pos);
            } else {
                skip(parser);
            }
        }
        return new Word(lexeme, definition, pos);
    }

    // THE NEXT TWO METHODS COULD BE ABSTRACTED INTO ONE: String readLeaf(String, String)
    //     - explain advantages
    // Processes lexeme tags in the feed.
    private String readLexeme(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, nameSpace, LEXEME);
        String lexeme = readText(parser);
        parser.require(XmlPullParser.END_TAG, nameSpace, LEXEME);
        return lexeme;
    }

    // Processes lexeme tags in the feed.
    private String readDefinition(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, nameSpace, DEFINITION);
        String definition = readText(parser);
        parser.require(XmlPullParser.END_TAG, nameSpace, DEFINITION);
        return definition;
    }

    // Processes lexeme tags in the feed.
    private String readPOS(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, nameSpace, POS);
        String pos = readText(parser);
        parser.require(XmlPullParser.END_TAG, nameSpace, POS);
        return pos;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
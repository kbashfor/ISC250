package edu.oswego.kbashfor.listviewerv4b;

/**
 * Created by Kyle on 9/29/2015.
 */

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse XML file indicated by URL; see http://developer.android.com/training/basics/network-ops/xml.html
 */
public class Fetcher {
    static private final String WORDS = "words";
    static private final String WORD = "word";
    static private final String LEXEME = "lexeme";
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
    public List<Word> fetch() {
        try {
            URL url = new URL(this.url);
            URLConnection client = url.openConnection();
            Log.i("TEST", client.getContent().toString());
            InputStream in = client.getInputStream();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return parseWords(parser);
        } catch (Exception ioe) {
            return null;
        } finally {
        }

    }

    private List<Word> parseWords(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Word> entries = new ArrayList<>();

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
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(LEXEME)) {
                lexeme = readLexeme(parser);
            } else if (name.equals(DEFINITION)) {
                definition = readDefinition(parser);
            } else {
                skip(parser);
            }
        }
        return new Word(lexeme);
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
# Assignment 1 - setup and "Hello World" app

## Introduction

The typical way to create an app is to use an IDE (Integrated Development Environment.) The environment includes

 * An editor.
 * Access to a compiler (or compilers.) Typically, the IDE uses compiler tools available on the host system.
 *Tools for the various end-products that the IDE supports.
The assignment

## The assignment
Follow the instructions contained at [this URL](http://www.raywenderlich.com/78574/android-tutorial-for-beginners-part-1) to install the [Android Studio](http://developer.android.com/tools/studio/index.html) IDE, and create a variant of the typical [hello, world program.](https://en.wikipedia.org/wiki/%22Hello,_World!%22_program)

**Note** — to avoid conflicts, you should make the package for your app: 
`edu.oswego.YOUR_USER_ID.hellov1`

Concepts

 * SDK
 * XML
 * layout
 * layout manager
 * form factor
 * API (minimum/target version)
 * Activity
 * onCreate method
 * class
 * subclass (aka derived class)
 * override
 * Ancillary concepts

IntelliJ
Gradle build tool
AVD
AVD Manager
SDK Manager

***

# Assignment 2 — vocabulary app version 1

## Introduction

Android has a myriad of contrivances to match standard software patterns. We'll use one such construct to anchor a sequence of applications, each with more complex features.

## The assignment

Create a new project with the name ListViewerV1. 
This should result in the package: 
`edu.oswego.YOUR_USER_ID.listviewerv1`

## Code and resource XML

 * [MainActivity](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/02/MainActivity.txt)
 * [activity_main's XML](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/02/activity_main.txt)

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the MainActivity code (by using "alt-return".)

## Concepts

 * `onCreate` — called when this activity is first created.
 * `setContentView` — "inflate" (a view or tree of views contained in a layout) to be the View of this activity.
 * `findViewById` — "inflate" a View from an XML source.
 * `ListView` — a special view group which manages interaction with a data list.
 * `ArrayAdapter` — "adapt" an interface between a data array and a ListViewlistener — an object with prescribed properties which will be "manipulated" when events of interest occur. 
`AdapterView.OnItemClickListener` is an example.

***

# Assignment 3 — vocabulary app version 2

## Introduction

This next version of the vocabulary app adds

 * reading from a file
 * starting a second Activity

## The assignment

Create a new project with the name ListViewerV2. 
This should result in the package: 
`edu.oswego.YOUR_USER_ID.listviewerv`

## Code and resource XML and data

 * [MainActivity.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/MainActivity.txt)
 * [activity_main's XML](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/activity_main.txt)
 * [Item.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/Item.txt)
 * [activity_item's XML](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/activity_item.txt)
 * [raw resource data file — the_words](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/the_words)
   — make a new folder named raw in the res folder and put `the_words` in it.

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the `MainActivity` code (by using "alt-return".)

## Concepts

 * `Intent` — what is an Intent?
 * `StartActivity` — explain.

***

# Assignment 4 — vocabulary app version 3

## Introduction

This next version of the vocabulary app adds

 * creating a Java class — `Word`
 * exchanging Objects as data between Activities
	— which requires implementing

 * [Parcelable](http://developer.android.com/reference/android/os/Parcelable.html)

## The assignment

Create a new project with the name ListViewerV3. 
This should result in the package: 
`edu.oswego.YOUR_USER_ID.listviewerv3`

We're going to extend the concept of a list of `String` objects to that of using a list `Word` objects.

## Code and resource XML and data

 * [MainActivity.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/04/MainActivity.txt)
 * [Item.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/04/Item.txt)

And a new class:

 * [Word.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/04/Word.txt)

You can use the same layouts and data file as in the previous assignment:

 * [activity_main's XML](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/activity_main.txt)
 * [activity_item's XML](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/activity_item.txt)
 * [raw resource data file — the_words](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/the_words) 
  	— make a new folder named `raw` in the `res` folder and put the_words in it.

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the `MainActivity`, `Item`, and `Word` code (by using "alt-return".)
Concepts

 * Java generics
 * Parcelable
 * Intent.putExtra
 * Intent.getParcelableExtra

***

# Assignment 5 — XML Parsing

## The assignment

 1. Modify the XML file of ["parse XML #3"](http://cs.oswego.edu/~odendahl/coursework/isc250/code/java/parse-xml/c/) and change "word" to "color", "lexeme" to "name", and definition" to "hex". Use this format to create your own XML file of "web colors" where the contents of "name" elements are the ["official" HTML color names](http://www.w3schools.com/html/html_colornames.asp) and the contents of "hex" elements are their hex representation.

  * Create your own XML file to demonstrate the result.

  *  Use [this method to create a demo](http://cs.oswego.edu/~odendahl/coursework/notes/scripting.html) of your program run.

 2. Modify the XML format used in ["parse XML #3"](http://cs.oswego.edu/~odendahl/coursework/isc250/code/java/parse-xml/c/) to include a pos (part of speech) element within the word element. (Now the word element contains 3 elements instead of just 2.)

  * Create your own XML document by editing the example words_defs.xml file. Move the part of speech content from the definition element into the pos element.

  * Use [this method to create a demo](http://cs.oswego.edu/~odendahl/coursework/notes/scripting.html) of your program run.

 3. Link your Java source files to your Web site.
 4. Link your two script files to your Web site.

***

# Assignment 6 A — vocabulary app version 4a

## Introduction

This version of the vocabulary app

 * introduces the necessity of concurrency to avoid slowing down [the UiThead (user interface thread)](http://stackoverflow.com/questions/3652560/what-is-the-android-uithread-ui-thread).

New Android classes include:

 * AsyncTask
	From the Android Javadoc — ...AsyncTasks should ideally be used for short operations (a few seconds at the most.) If you need to keep threads running for long periods of time, it is highly recommended you use the various APIs provided by the java.util.concurrent package such as Executor, ThreadPoolExecutor and FutureTask.

 * LocalBroadcastManager
 * BroadcastReceiver

## The assignment

Create a new project with the name ListViewerV4a. 
This should result in the package: 
`edu.oswego.YOUR_USER_ID.listviewerv4a`

## Code and resource XML and data

 * [MainActivity.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/MainActivity.txt)
 * [ReaderTask.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/ReaderTask.txt)
 * [Item.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/Item.txt)
 * [Word.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/Word.txt)
 * [activity_main.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/activity_main.xml)
 * [activity_item.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/activity_item.xml)

You can use the same data file as in the previous assignment:

 * [raw resource data file — the_words](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/03/the_words) 
  	— make a new folder named `raw` in the `res` folder and put `the_words` in it.

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the `MainActivity`, `Item`, and `Word` code (by using "alt-return".)

## Concepts

 * concurrency
 * broadcast exchanges

***

# Assignment 6 C — vocabulary app version 4c

## Introduction

This version of the vocabulary app

replaces Android's AsyncTask with [java.util.concurrent.ExecutorService](http://developer.android.com/reference/java/util/concurrent/ExecutorService.html)
Uses an XML data source.
Connects to the data source using HTTP.
T
## The assignment

Create a new project with the name ListViewerV4b. 
(This should result in the package: 
`edu.oswego.YOUR_USER_ID.listviewerv4b`

## Code and resource XML and data

 * [MainActivity.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/c/MainActivity.txt)
 * [ReaderTask.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/c/ReaderTask.txt)
 * [Fetcher.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/c/Fetcher.txt)
 * [Word.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/c/Word.txt)

These Java files are the same as in 4a:

 * [Item.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/Item.txt) — same as version 4a

The XML layout files can stay the same, too:

 * [activity_main.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/activity_main.xml)
 * [activity_item.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/activity_item.xml)

You need to add:
`<uses-permission android:name="android.permission.INTERNET" />` 
to your AndroidManifest.xml file.

Don't put my package name in your [AndroidManifest.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/b/AndroidManifest.xml) file.

The data file is

 * [words.xml](http://cs.oswego.edu/~odendahl/words-and-defs.xml)

and needs to be placed at the URL specified in your code.

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the Java files (by using "alt-return".)

Concepts

 * `java.util.concurrent package`
 * parsing XML

***
# Assignment 6 B — vocabulary app version 4b

## Introduction

This version of the vocabulary app

 * replaces Android's AsyncTask with [java.util.concurrent.ExecutorService](http://developer.android.com/reference/java/util/concurrent/ExecutorService.html)
 * Uses an XML data source.
 * Connects to the data source using HTTP.

## The assignment

Create a new project with the name ListViewerV4b. 
This should result in the package: 
`edu.oswego.YOUR_USER_ID.listviewerv4b`

## Code and resource XML and data

 * [MainActivity.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/b/MainActivity.txt)
 * [ReaderTask.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/b/ReaderTask.txt)
 * [Fetcher.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/b/Fetcher.txt)

These Java files are the same as in 4a:

 * [Item.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/Item.txt) — same as version 4a
 * [Word.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/Word.txt) — same as version 4a

The XML layout files can stay the same, too:

 * [activity_main.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/activity_main.xml)
 * [activity_item.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/a/activity_item.xml)

**You need to add:**

```
<uses-permission android:name="android.permission.INTERNET" />
``` 
to your [AndroidManifest.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/06/b/AndroidManifest.xml) file.

Don't put my package name in your `AndroidManifest.xml` file.
**The data file** is

 * [words.xml](http://cs.oswego.edu/~odendahl/words.xml)

and **needs to be placed at the URL** specified in your code.

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the Java files (by using "alt-return".)

## Concepts

 * `java.util.concurrent` package
 * parsing XML

***

# Assignment 7 A — drag and drop V1

## Introduction

This app begins to demonstrate how to

 * drag an element (a View) from one ViewGroup to another.
 * create a "custom" View.
 * create View elements programmatically.

New Android classes include:

 * OnTouchListener
 * OnDragListener

## The assignment

Create a new project with the name DragAndDropV1 
This should result in the package: 
`edu.oswego.YOUR_USER_ID.draganddropv1`

## Code and resource XML and data

 * [MainActivity.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/07/a/MainActivity.txt)
 * [OdView.java](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/07/a/OdView.txt) <= change this name to something you like

In layout folder:

 * [activity_main.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/07/a/activity_main.txt)
 * [tile.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/07/a/tile.txt) <= be sure to replace OdView with your own idea for a name

In drawable folder:

 * [bg.xml](http://cs.oswego.edu/~odendahl/coursework/isc250/201509/assignments/07/a/bg.txt)

## Notes

 * Make sure you don't erase your package name when pasting in the above code.
 * You'll have to help the IDE supply the correct imports for the `MainActivity`, `OdView` (by using "alt-return".)

## Concepts

 * —

***

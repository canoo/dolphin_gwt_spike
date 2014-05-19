Running the application
=======================
on commandline:
- ./gradlew gwtDev

This opens a littel swing gui. Press the button 'Launch Default Browser'
which should automatically open a new tab in your browser showing the HTML page of the gwt app.

Concepts
========
Binding Java JS
- from <ABC>.java to <ABC>JS.java
  e.g.: Dolphin.java -> DolphinJS.java



Get into Development
====================
- main entry point: src/main/java/com/canoo/dolphingwtspike/mainApplication/MainApplication.gwt.xml
  -> MainApplication.java
  -> DolphinLoaderJS
  -> (via JS) DolphinBaseStarter::start
  -> DolphinStarter::start
  -> MainApplication$DolphinStarter::start


Temporary notes (just that we do not forget them):
===================================================
send dolphin request via curl:

  curl -X POST http://127.0.0.1:8888/dolphin -d @post.json

require.js optimazation:
  node r.js -o build.js

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


Application Bootstrap:
======================
- main HTML entry point: src/main/webapp/MainApplication.html
- main GWT entry point: src/main/java/com/canoo/dolphingwtspike/mainApplication/MainApplication.gwt.xml
  -> MainApplication.java
  -> DolphinLoaderJS
  -> (via JS) DolphinBaseStarter::start
  -> DolphinStarter::start
  -> MainApplication$DolphinStarter::start

- Note: the instruction '<script src="require.js"/>' causes require.js to get loaded by the browser.
  This is not visible by with 'view source' command of the browser. Use 'inspect element' instead and take a look at the <head> element.


TODO:
=====
- probably unused (check by deletion): /home/sven/canoo/git/dolphin_gwt_spike/src/main/java/com/canoo/opendolphin/public/config.js

Temporary notes (just that we do not forget them):
===================================================
send dolphin request via curl:

  curl -X POST http://127.0.0.1:8888/dolphin -d @post.json

require.js optimazation:
  node r.js -o build.js

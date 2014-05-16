on commandline:
- ./gradlew gwtDev

This opens a littel swing gui. Press the button 'Launch Default Browser'
which should automatically open a new tab in your browser showing the HTML page of the gwt app.


Temporary notes (just that we do not forget them):
===================================================
send dolphin request via curl:

  curl -X POST http://127.0.0.1:8888/dolphin -d @post.json

require.js optimazation:
  node r.js -o build.js

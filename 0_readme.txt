Running the application
=======================
open two commandline terminals in the project's folder:
- in the first one invoke ./gradlew jettyDraftWar
- in the second one invoke ./gradlew gwtSuperDev

On first usage open browser at:
  http://localhost:9876/
and drag and drop the two scriptlet buttons 'Dev Mode On' and 'Dev Mode Off' to your browsers toolbar.

Then point browser to:
  http://localhost:8080/dolphin_gwt_spike/MainApplication.html
press 'Dev Mode On' and press the compile button appearing in the browser window. The the Application page is supposed to appear and working.


This opens a littel swing gui. Press the button 'Launch Default Browser'
which should automatically open a new tab in your browser showing the HTML page of the gwt app.

Concepts
========
- JavaScript files which got loaded by require.js and thus served as a module (also just another JavaScript object)
  can be cast to a GWT-Java Object of type 'JavaScriptObject'. Example: 'DolphinLoaderJS.java' loads 'OpenDolphin.js' via require.js
  and then passes it as first parameter to 'DolphinBaseStarter::start(...)' casting it to type 'OpenDolphinJS.java' (which extends 'JavaScriptObject').

Binding Java <-> JS
- from <ABC>.java to <ABC>JS.java
  e.g.: Dolphin.java -> DolphinJS.java

- ClientDolphin.presentationModel()  (ClientDolphin.java):
    PresentationModelJS presentationModelJS = PresentationModelJS.newPresentationModelJS(...)
    return new PresentationModel(presentationModelJS)

- public class PresentationModel {
  	private final PresentationModelJS pmJS;
  	...
  }
- public class PresentationModelJS extends JavaScriptObject
  {
  	...

  	public static final native PresentationModelJS newPresentationModelJS(ClientDolphinJS clientDolphin, String pmId, String type, JsArray<ClientAttributeJS> clientAttributesJS) /*-{
  		return clientDolphin.presentationModel(pmId, type, clientAttributesJS);
  	}-*/;

    ...
  }
---

- Dolphin.java:
    DolphinJS dolphinJS = DolphinJS.newDolphinJS(dolphinModule, dolphinUrl);
    -> DolphinJS.java:
       	public static final native DolphinJS newDolphinJS(DolphinJS dolphinModule, String url) /*-{
       		return new dolphinModule(url); // <====== call into Dolphin.js passing 'url'
       	}-*/;
      -> Dolphin.js:
         define([
             'comm/ClientDolphin',
             'comm/ClientModelStore',
             'comm/HttpClientConnector'
         ], function (ClientDolphin, ClientModelStore, HttpClientConnector) {

             return function(serverUrl) { // <========= call from DolphinJS.java receiving 'url'
                 ...
             }
            };

         });





Application Bootstrap:
======================
- main HTML entry point: src/main/webapp/MainApplication.html
- main GWT entry point: src/main/java/com/canoo/dolphingwtspike/mainApplication/MainApplication.gwt.xml
  -> MainApplication.java
  -> DolphinLoaderJS
  -> (via JS) DolphinBaseStarter::start
     -> new Dolphin()
        -> DolphinJS.newDolphinJS(...)
        -> clientDolphin = new ClientDolphin
  -> DolphinStarter::start
  -> MainApplication$DolphinStarter::start

- Note: the instruction '<script src="require.js"/>' causes require.js to get loaded by the browser.
  This is not visible by with 'view source' command of the browser. Use 'inspect element' instead and take a look at the <head> element.

Other Notes:
============
- modifications of original open-dolphin.js code are marked with: OD-GWT

TODO:
=====
- probably unused (check by deletion): /home/sven/canoo/git/dolphin_gwt_spike/src/main/java/com/canoo/opendolphin/public/config.js
- BasePresentationModel.findAttributeByPropertyNameAndTag: should throw IllegArgException if 'propertyName' or 'tag' is null ?

Temporary notes (just that we do not forget them):
===================================================
send dolphin request via curl:

  curl -X POST http://127.0.0.1:8888/dolphin -d @post.json

require.js optimazation:
  node r.js -o build.js

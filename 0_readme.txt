Running the application
=======================
open two commandline terminals in the project's folder:
- in the first one invoke ./gradlew clean jettyDraftWar
- in the second one invoke ./gradlew gwtSuperDev

On first usage open browser at:
  http://localhost:9876/
and drag and drop the two scriptlet buttons 'Dev Mode On' and 'Dev Mode Off' to your browsers toolbar.

Then point browser to:
  http://localhost:8080/dolphin_gwt_spike/MainApplication.html
press 'Dev Mode On' and press the compile button appearing in the browser window. The the Application page is supposed to appear and working.

When you change server side code like TutorialAction.java you need to restart the ./gradlew jettyDraftWar command.


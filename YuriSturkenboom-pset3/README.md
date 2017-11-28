# TODO: screen orientation, make listeners non-anonymous, make item viewer ui acceptable

# Chez la Droide

![wireframe](/YuriSturkenboom-pset3/doc/Appstudio%20pset3%20wireframe%20v2.png)
![screenie](/YuriSturkenboom-pset3/doc/screenshot.png)

Requires Android Studio to build!
Not tested for Android Wear.

## Description
Eliminate all social interaction by using this app to order your food! 

This app is an exercise in using JSON to retrieve data from an API, and a bit of UI design.
The UI is constructed such that easy intuitive back navigation is always easy.

## Building
Clone the repository or download the zip file if you're on Windows, and you should be able to open the project root folder as a project in
Android Studio. From there you can build with Gradle in Android Studio and run.

## Operation
Use the menu to navigate to an item and add it to your order. You can use the tabs below to navigate to your order or back to the menu at any time. If you are happy with your order, go to the order menu via the order tab to view the order including the total price, and press order to confirm your order. The estimated waiting time for your order will then be displayed.

## Notes
Again: should probably use Fragments, but that is taught in a later assignment. Really hacky use of buttons to make 'tabs', but, it works :)
and I haven't noticed any problems with them. On a more serious note, lots of repeated code for JSON handling is pretty bad. It occurred to
me later that I should have made a seperate class to handle JSON, but I did not (yet) implement it. I may still do so if I have time left 
after the final assignment.

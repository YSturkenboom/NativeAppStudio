# MadLibz

![screenie](/YuriSturkenboom-pset2/doc/screenshot_large_l.png)

Requires Android Studio to build!
Not tested for Android Wear.

## Description
Are you bored and do you need a story to spice up your life? Is there no one to read you a bed time story? For these unlikely reasons and 
more, you can use MadLibz! Pick some funny words and make a great story!

This app serves to demonstrate the use of multiple activities, and passing values from one to another. The app uses three activities: the
main/input screen, the 'thinking' screen (it secretly does nothing, don't tell anyone) and the result screen.

This time around a bit more design went into the app, and it is designed to provide a good UI Flow and confortable user experience. It features custom layouts for most standardised screen sizes (small, normal, large, x-large).

## Building
Clone the repository or download the zip file if you're on Windows, and you should be able to open the project root folder as a project in
Android Studio. From there you can build with Gradle in Android Studio and run.

## Operation
Detailed instructions also available in-app: click on the '?' button for information.

To get started making your stories, just choose a story, or let the app choose a random story for you. Enter the required amount of words and press the "Go!" button.
After a bit of 'thinking', your story will show up!

## Notes
The large and extra large layouts should probably use Fragments instead of separate activities. According to the Google design patterns the tablet layouts should uitilise the large screen by placing multiple 'activities' next to each other. However I decided not to research/implement these right now due to time constraints.

Also, the small layout is untested, because making an app for Android Wear requires some additional setup that I decided not to do again due to time constraints.

README

GROUP C

Our project name is Puzzle Timer. It is an Android application that records the
times of the activities, mainly the solving of Rubik�s Cubes, and other twisty puzzles. 
It works like a stopwatch. We can record the unlimited number of intervals, and provide stats 
based on those intervals. It works at a fixed rate.


Implementation Details:

The main source code files can be found in the app\src\main\java\sep file path. 
The code is split up into three main packages, defining our 3-tier architecture 
(business, view, persistence). The view package contains all code related to the interface, 
the business package contains all code that is related to the logic of our application, and 
the persistence contains our mock 'stub' database, which for now is just an arraylist.

There is no local.properties file in the code that is handed in, because the gradle build should generate one with an sdk path.

The log file can be found in the root of our project folder, and is called log.txt.

At the bottom of the log file there is an explanation as to how our dev tasks were handled.

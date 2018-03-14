README

Our project name is “Puzzle Timer”. It is an Android application that records the times of the activities,
mainly the solving of Rubik’s Cubes, and other twisty puzzles. It works like a stopwatch. We can record the
unlimited number of intervals, and provide stats based on those intervals. It works at a fixed rate.

Interface Details:

The way our app works is you start and stop the timer by touching the screen anywhere within the application.
When you touch the screen, a countdown from 15 seconds starts and allows you inspection time for the solve.
Once the time runs down to 0 seconds, or you touch the screen again, the timer starts. When the solve is
completed, you touch the screen again, and the timer stops. For every solve, there is a unique randomly generated
scramble at the top of the screen, which is supposed to be applied to the Rubiks Cube being solved before the
timer is started. When the solve is done, and the timer is stopped, the recorded time gets added to the list of
times, which is accessible by touching the black clock on the top right of the screen. It brings you to a list of
your times (prepopulated), and we plan to display a lot more statistics there as well.

On the main screen, there are some statistics shown, like different averages. These change as new times are added.

Added in iteration two are a few new features added at the top of the screen. There are icons that can
take you to a graph representation of your solves, and another that lets you pick the scramble time for
the type of puzzle you want to solve. Right now we only have two options, which are 2x2 and 3x3 puzzles,
though we are looking to add more in the next iteration. Removing and modifying times from the previous
times list is something that we didn’t quite get around to in this iteration, although it will be of the
highest priority in the next.

Implementation Details:

The main source code files can be found in the app\src\main\java\sep file path. The code is split up
into three main packages, defining our 3-tier architecture (business, view, persistence). The view
package contains all code related to the interface. We only, have two files in this package as of now,
which are the timer view, and the list of times view. These files interact with the logic of our
program to display values to the screen, as well as provide an interface to the application user.
The business package contains all code that is related to the logic of our application, which contains
the running of the timer, the scramble generation, as well as the calculation of the statistics within
our program. The persistence contains our mock “stub” database, the actual implementation of the HSQLDB,
as well as our Database Interface, which outlines the implementation of both our real DB, and our stub DB.

In the tests package, there are three corresponding packages to match the source code packages, with tests
for the SUT. All the tests available as of this iteration can be run by running the AllTests file.

There is no local.properties file in the code that is handed in, because the gradle build should generate
one with an sdk path.

The log file can be found in the root of our project folder, and is called log.txt.

All of our dev tasks were organized through our Slack group, and we would pick off our developer task
list of things that needed to be done, and then work on them.



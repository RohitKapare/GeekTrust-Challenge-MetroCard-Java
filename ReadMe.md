# Pre-requisites

* Java 1.8/1.11/1.15
* Gradle 6

# Note for anyone who want to examine the code:

The well written and well organized code with testing cases, proper implementation of factory and
strategy pattern will be commited after this readme update.
This code is actually more scalable in case Metro Service wants more complex strategy for base fare
and
discounted fare for certain category of passengers (e.g. DISABLED_PASSENGER, GOVERNMENT_OFFICERS,
EMERGENCY_SERVICE_WORKER, REPORTERS, etc.).
In such cases we want to separate the strategies for more complex fare and discount calculations and
not
just have base fare and 50% discount for return journey.

But for now, such modular code does not work for passing evaluation through automated systems of
placement platforms.
So from hear on out, the changes in core logic will be made to satisfy the requirements of the
problem statement and
not to make it more modular and scalable.

If you want to check out the original design solution of mine `git checkout <commit-hash>` at
the commit where the message is `Checkout here for first solution code.` in the commit history.

# How to run the code

We have provided scripts to execute the code.

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows. Both
the files run the commands silently and prints only output from the input file
`sample_input/input1.txt`. You are supposed to add the input commands in the file from the
appropriate problem statement.

Internally both the scripts run the following commands

* `gradle clean build -x test --no-daemon` - This will create a jar file `geektrust.jar` in the
  `build/libs` folder.
* `java -jar build/libs/geektrust.jar sample_input/input1.txt` - This will execute the jar file
  passing in the sample input file as the command line argument

We expect your program to take the location to the text file as parameter. Input needs to be read
from a text file, and output should be printed to the console. The text file will contain only
commands in the format prescribed by the respective problem.

Use the build.gradle file provided along with this project. Please change the main class entry under
the `jar` task

 ```
 manifest {
        attributes 'Main-Class' : 'com.example.geektrust.Main' //Change this to the main class of your program which will be executed
    }
```

in the build.gradle if your main class has changed.

# How to run without using jar file and using 'gradlew.bat' file.

First update `build.gradle' file's these fields as follows"

```
plugins {
    id 'java'
    id 'jacoco'
    id 'application'
}
application {
mainClassName = 'com.example.geektrust.Main'
}
```

Then use this command:

```
.\gradlew.bat run --args="sample_input/input1.txt"
```

---

# How to run the tests and effectively run anything in this project:

Well, the starter kit provided for this project is really out-dated. And will give trouble running
in modern setups and IDEs that fire ups gradle as soon as you open IDE.

1. Install java 8 a.k.a. 1.8 and set it as environmental variable.
2. Even though there gradlew wrapper in this project which is of version 5.1, which works with java
   8. Still need to install and set path of gradle 5.1 in C directory. (This seems to fix most of
   the IDE issues.)
3. Before running tests:
   Stop running gradle Daemons `./gradlew --stop`, Delete `.gradle` folder in project root. Delete
   `gradle 5.1` folder from `%userprofile%/gradle/caches` folder.
   **Then use this command to run tests: **

```
./gradlew clean test "-Dorg.gradle.java.home=C:\Program Files\Java\jdk1.8.0_202"
```

Replace path with your java 8 folder location.

---

# Running the code for multiple test cases

Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat`
or `run.sh`. Replace `java -jar build/libs/geektrust.jar sample_input/input1.txt` with
`java -jar build/libs/geektrust.jar sample_input/input2.txt` to run the test case from the second
file.

# How to execute the unit tests

`gradle clean test --no-daemon` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.com)
You can read build
instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)
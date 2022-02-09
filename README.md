# JavaDataFrame

Implementation of the Java Dataframe library using Design Patterns.

Factory Pattern implemented in factory/ package. It accepts 3 types of files: csv, json and txt.
The api/ package contains different operations that can be applied to the dataframes.

Composite Pattern implemented in composite/ package. Enables processing data stored in 
directories and subdirectories.

In mapreduce/ package enables processing collections of dataframes and applying filters column-wise.

Visitor pattern implemented in visitor/ package where there are more operations for processing data
in Dataframes.

Observer and Dynaminc proxy patterns implemented in observerDynamicproxy/ package.
**QueryObserver doesn't work as intended for the time being**

All packages contain a test/ folder with some JUnits testing the different functionalities.

#Scala

Added Scala package

This package contains an adaptation of part of the Java Dataframe implementation.
Contains a part of the Composite pattern from Java.
Implements a new vistor with a couple new operations.
It also implements a couple new operations using Stack and Tail recursion.




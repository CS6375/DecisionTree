# DecisionTree

The Decision Tree is implemented in Java SE 1.8.

## Structure

The `src` directory has the following structure:

    src
    ├── main
    │   └── java
    │       ├── Main.java                           # Driver program entrance.
    │       └── cs6375
    │           └── github
    │               └── io
    │                   ├── DataSet.java            # Class to store a DataSet.
    │                   ├── DecisionTree.java       # Class representing a Decision tree.
    │                   ├── DecisionTreeNode.java   # Class representing a node in the Decision tree.
    │                   ├── Instance.java           # Class to store a line of information in the DataSet.
    │                   ├── Math.java               # A self defined math function to gompute plogp()
    │                   └── TrainingDataSet.java    # The subclass of DataSet, able to compute Entropy.
    |
    └── test                                        # Unit test class for major function.
        ├── java
        │   └── cs6375
        │       └── github
        │           └── io
        │               ├── DataSetTest.java
        │               ├── DecisionTreeNodeTest.java
        │               ├── DecisionTreeTest.java
        │               ├── InstanceTest.java
        │               └── TrainingDataSetTest.java
        └── resources                               # Unit test resources.
            ├── data_sets1
            │   ├── test_set.csv
            │   ├── training_set.csv
            │   └── validation_set.csv
            └── data_sets2
                ├── test_set.csv
                ├── training_set.csv
                └── validation_set.csv

Note that, the structure is default project structure of Gradle.

## Compilation

The easy way to build the project is using __Gradle__. The following commands was tested on environment list below:

    > gradle -version
    ------------------------------------------------------------
    Gradle 4.2
    ------------------------------------------------------------

    Build time:   2017-09-20 14:48:23 UTC
    Revision:     5ba503cc17748671c83ce35d7da1cffd6e24dfbd

    Groovy:       2.4.11
    Ant:          Apache Ant(TM) version 1.9.6 compiled on June 29 2015
    JVM:          9 (Oracle Corporation 9+181)
    OS:           Mac OS X 10.12.6 x86_64

-   In the root directory, execute command `gradle build` would compile and unit test the source file into java binary and jar file in `build` directory.

        > gradle build

        BUILD SUCCESSFUL in 4s
        8 actionable tasks: 8 executed
        
        > tree build/classes
        build/classes
        └── java
            ├── main
            │   ├── Main.class
            │   └── cs6375
            │       └── github
            │           └── io
            │               ├── DataSet.class
            │               ├── DecisionTree.class
            │               ├── DecisionTreeNode.class
            │               ├── Instance.class
            │               ├── Math.class
            │               └── TrainingDataSet.class
            └── test
                └── cs6375
                    └── github
                        └── io
                            ├── DataSetTest.class
                            ├── DecisionTreeNodeTest.class
                            ├── DecisionTreeTest.class
                            ├── InstanceTest.class
                            └── TrainingDataSetTest.class

        9 directories, 12 files
        
-   Or simply execute command `gradle jar`, executable binary class file and jar file would be generated in `build` directory in following struture.

        > gradle jar

        BUILD SUCCESSFUL in 0s
        2 actionable tasks: 2 executed
        
        > tree build
        build
        ├── classes
        │   └── java
        │       └── main
        │           ├── Main.class
        │           └── cs6375
        │               └── github
        │                   └── io
        │                       ├── DataSet.class
        │                       ├── DecisionTree.class
        │                       ├── DecisionTreeNode.class
        │                       ├── Instance.class
        │                       ├── Math.class
        │                       └── TrainingDataSet.class
        ├── libs
        │   └── DecisionTree-1.0.jar
        └── tmp
            ├── compileJava
            └── jar
                └── MANIFEST.MF

        10 directories, 9 files

If `gradle` is not available, simply execute `javac src main/java/Main.java` would generate executable binary file `Main.class`.

## Execution

After generating jar file, execute the following command to run the driver program of the Decision tree.

    java -jar build/libs/DecisionTree-1.0.jar <Training Data>  <Validation Data>  <Testing Data> <Pruning Factor>
    
For example, training and pruning based on `data_sets1`:

    > java -jar build/libs/DecisionTree-1.0.jar  src/test/resources/data_sets1/training_set.csv src/test/resources/data_sets1/validation_set.csv src/test/resources/data_sets1/test_set.csv 0.2

    Pre-Pruned Accuracy
    -------------------------------------
    Number of training instances = 600
    Number of training attributes = 20
    Total number of nodes in the tree = 265
    Number of leaf nodes in the tree = 133
    Accuracy of the model on the training dataset = 100.0%

    Number of validation instances = 2000
    Number of validation attributes = 20
    Accuracy of the model on the validation dataset before pruning = 74.3%

    Number of testing instances = 2000
    Number of testing attributes = 20
    Accuracy of the model on the testing dataset = 75.1%


    Post-Pruned Accuracy
    -------------------------------------
    Number of training instances = 600
    Number of training attributes = 20
    Total number of nodes in the tree = 265
    Number of leaf nodes in the tree = 133
    Accuracy of the model on the training dataset = 89.8%

    Number of validation instances = 2000
    Number of validation attributes = 20
    Accuracy of the model on the validation dataset before pruning = 78.8%

    Number of testing instances = 2000
    Number of testing attributes = 20
    Accuracy of the model on the testing dataset = 78.6%

Training and pruning based on `data_sets2`:

    > java -jar build/libs/DecisionTree-1.0.jar  src/test/resources/data_sets2/training_set.csv src/test/resources/data_sets2/validation_set.csv src/test/resources/data_sets2/test_set.csv 0.2

    Pre-Pruned Accuracy
    -------------------------------------
    Number of training instances = 600
    Number of training attributes = 20
    Total number of nodes in the tree = 307
    Number of leaf nodes in the tree = 154
    Accuracy of the model on the training dataset = 99.8%

    Number of validation instances = 600
    Number of validation attributes = 20
    Accuracy of the model on the validation dataset before pruning = 76.7%

    Number of testing instances = 600
    Number of testing attributes = 20
    Accuracy of the model on the testing dataset = 74.5%


    Post-Pruned Accuracy
    -------------------------------------
    Number of training instances = 600
    Number of training attributes = 20
    Total number of nodes in the tree = 307
    Number of leaf nodes in the tree = 154
    Accuracy of the model on the training dataset = 87.7%

    Number of validation instances = 600
    Number of validation attributes = 20
    Accuracy of the model on the validation dataset before pruning = 82.7%

    Number of testing instances = 600
    Number of testing attributes = 20
    Accuracy of the model on the testing dataset = 76.5%

Note that in the example, the sample data set is located in the resources directory of test.

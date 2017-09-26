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


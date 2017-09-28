# DecisionTree

The Decision Tree is implemented in Java SE 1.8.

## Structure

The `src` directory has the following structure:

    src
    ├── main
    │   └── java
    │       ├── Main.java                           # Driver program entrance.
    │       └── cs6375.github.io
    │           ├── DataSet.java                    # Class to store a DataSet.
    │           ├── DecisionTree.java               # Class representing a Decision tree.
    │           ├── DecisionTreeNode.java           # Class representing a node in the Decision tree.
    │           ├── ID3DecisionTree.java            # Subclass of DecisionTree using ID3DecisionTreeNode as node class
    │           ├── ID3DecisionTreeNode.java        # Subclass of DecisionTreeNode using ID3 algorithm.
    │           ├── Instance.java                   # Class to store a line of information in the DataSet.
    │           ├── Math.java                       # A self defined math function to gompute plogp()
    │           ├── RandomDecisionTree.java         # Subclass of DecisionTree using RandomDecisionTreeNode as node class
    │           ├── RandomDecisionTreeNode.java     # Subclass of DecisionTreeNode using Random selection.
    │           └── TrainingDataSet.java            # The subclass of DataSet, able to compute Entropy.
    |
    └── test                                        # Unit test resources.
        ├── java
        │   └── cs6375.github.io
        │       ├── DataSetTest.java
        │       ├── DecisionTreeNodeTest.java
        │       ├── DecisionTreeTest.java
        │       ├── InstanceTest.java
        │       └── TrainingDataSetTest.java
        └── resources                               # Unit test resources.
            ├── data_sets1
            │   ├── test_set.csv
            │   ├── training_set.csv
            │   └── validation_set.csv
            └── data_sets2
                ├── test_set.csv
                ├── training_set.csv
                └── validation_set.csv

    13 directories, 22 files

Note that, the structure is default project structure of __Gradle__.

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

In the root directory, execute command `gradle build` would compile and unit test the source file into java binary and jar file in `build` directory.

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

If `gradle` is not available, simply execute `javac src/main/java/Main.java` would generate executable binary file `Main.class`.

## Execution

After generating jar file, execute the following command to run the driver program of the Decision tree.

    java -jar build/libs/DecisionTree-1.0.jar <Training Data>  <Validation Data>  <Testing Data> <Pruning Factor>

For example, training and pruning based on `data_sets1`:

    > java -jar build/libs/DecisionTree-1.0.jar \
        src/test/resources/data_sets1/training_set.csv \
        src/test/resources/data_sets1/validation_set.csv \
        src/test/resources/data_sets1/test_set.csv 0.2

    --------------------------------------------------------------------------
    ID3 Algorithm Generated Decision Tree
    --------------------------------------------------------------------------

    Pre-Pruned Accuracy
    -------------------------------------
    Number of training instances = 600
    Number of training attributes = 20
    Total number of nodes in the tree = 265
    Number of leaf nodes in the tree = 133
    Average depth in the tree = 8.1
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
    Total number of nodes in the tree = 119
    Number of leaf nodes in the tree = 60
    Average depth in the tree = 6.7
    Accuracy of the model on the training dataset = 89.8%

    Number of validation instances = 2000
    Number of validation attributes = 20
    Accuracy of the model on the validation dataset before pruning = 78.8%

    Number of testing instances = 2000
    Number of testing attributes = 20
    Accuracy of the model on the testing dataset = 78.6%


    --------------------------------------------------------------------------
    Random Generate Decision Tree
    --------------------------------------------------------------------------
    Node No.    Leaf No.    Avg Depth       Training    Validation  Test Set
    311         156         8.506410        100.0%      71.8%       74.0%
    271         136         8.375000        100.0%      75.1%       76.1%
    249         125         8.360000        100.0%      79.4%       78.7%
    287         144         8.319444        100.0%      73.1%       73.4%
    321         161         8.099379        100.0%      70.8%       69.9%
    315         158         8.291139        100.0%      71.8%       71.4%
    265         133         8.052632        100.0%      74.3%       75.1%
    249         125         8.360000        100.0%      79.4%       78.7%
    255         128         8.218750        100.0%      76.0%       75.8%
    299         150         8.373333        100.0%      70.5%       72.5%

    Average based on 1000 randomly generated decision tree:
    Node count = 303.888
    Leaf count = 152.444
    Depth = 8.311703087757838


Note that in the example, the sample data set is located in the resources directory of test.

## Design

There are mainly five class in the implementation.

-   `Instance`

    Represented a line of information in the data set file, including,

    -   A list of boolean value, storing the values of each attribute in
        the instance. Currently only boolean type is supported.

    -   A boolean label, storing the label of the instance. Likely, only
        boolean type classification is supported currently.

-   `DataSet`

    Represented a dataset with only boolean attribute and boolean label,
    including,

    -   A list of `Instance` instances.

    -   A list of `String` indicating the name of each attributes. Used
        ArrayList instead of array for the `indexOf` function. The
        variable is defined as **final** since the data set is fixed once read
        from file. No modification to current data set is allow. If a split
        operation is needed, allocate new data set to store new collection of
        instances.

    -   A list of boolean indicator to determine whether an attribute with
        certain index is excluded in computing.

    -   A int variable storing current `dimension` of the data set, i.e., the
        number of not-excluded attributes. Like the attribute above,
        intuitively, the `dimension` should be fixed once the data set was
        form.

-   `TrainingDataSet`

    Based on `DataSet` class, extended with entropy computation and split
    function. This class standalone because only the training data need compute
    entropy and split. Additionally, `TrainingDataSet` includes,

    -   A double variable storing the entropy of current data set.

    -   A boolean label indicating the most common label at current level. This
        value is acquired alongside the entropy calculation.

    Note that, the `split()` operation do not modify current data set or any
    instances, it simply generate a new data set instance and assign the
    references of specific set of `Instance` instances to the new data set.

-   `DecisionTreeNode`

    Represented a node in the decision tree, including,

    -   A string label indicate the attribute used to split in this node.

    -   A boolean label indicate the most common class at current node. This
        value can be computed in building the tree. Although in internal node,
        the value is meaningless, it is very helpful in pruning, since no more
        computation is needed after pruning a subtree.

    -   A int value indicating the depth of current node.

    -   Left and Right reference of subtree.

    -   A boolean label indicating whether current node has been pruned. *Note
        that, in actual pruning, no reference pointing to the subtree is
        deleted. Only this boolean label is switched to `true`.* Since no
        actual modification is done to the tree, to recover the “pruned”
        subtree back, only operation needed is switch back this boolean label.
        It is relatively easy to calculate the accuracy of different ways of
        pruning.

-   `DecisionTree`

    Represented a decision tree, including,

    -   An `ArrayList` to store the whole tree in BFS order. Generated after
        the tree is constructed by the `DecisionTreeNode` of root. The first
        element is the root node. Note that, generally speaking, to store a
        tree, only the root of the tree is needed. This additional list does
        not modify the structure of the tree, yet it offers a much more
        convenient way to iterate thought the nodes in the tree. And more
        importantly, it gives indexes to the nodes, allowing random access,
        i.e., pruning the node randomly.

    -   An `ArrayList` to store all the leaf nodes according to BFS order.
        Generated alongside the `nodes`.

-   `ID3DecisionTreeNode` and `RandomDecisionTreeNode`

    Based on `DecisionTreeNode`, extended with different method to select
    attribute to split.

-   `ID3DecisionTree` and `RandomDecisionTree`

    Based on `DecisionTree`, extended with different constructor create root
    node. `ID3DecisionTree` also override the method `prune()`.

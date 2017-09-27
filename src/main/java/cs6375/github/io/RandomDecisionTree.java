package cs6375.github.io;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RandomDecisionTree extends DecisionTree {
    public RandomDecisionTree(TrainingDataSet trainingDataSet) {
        // Generate tree and store the root.
        DecisionTreeNode root = new RandomDecisionTreeNode(trainingDataSet);

        // Put all node into list for random access.
        super.generate(root);
    }
}

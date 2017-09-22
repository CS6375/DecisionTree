package cs6375.github.io;

import java.lang.Math;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DecisionTree {

    /**
     * An ArrayList to store the whole tree in BFS order. Generated after the tree is constructed by
     * the {@code DecisionTreeNode} of root.
     */
    private ArrayList<DecisionTreeNode> nodes;

    /**
     * Public constructor. First construct the tree recursively using constructor of {@code
     * DecisionTreeNode}, then compute the array to store all the node.
     *
     * @param trainingDataSet Training DataSet to build the decision tree with.
     */
    public DecisionTree(TrainingDataSet trainingDataSet) {
        DecisionTreeNode root = new DecisionTreeNode(trainingDataSet);

        this.nodes = new ArrayList<>();

        Queue<DecisionTreeNode> queue = new LinkedList<>();

        queue.offer(root);

        DecisionTreeNode next;

        while ((next = queue.poll()) != null) {
            this.nodes.add(next);
            if (next.getLeft() != null) {
                assert next.getRight() != null;

                queue.add(next.getLeft());
                queue.add(next.getRight());
            }
        }
    }

    /**
     * Return the total number of nodes.
     *
     * @return The total number of nodes.
     */
    public int getSize() {
        return nodes.size();
    }

    /**
     * Print the tree to a string using {@code DecisionTreeNode} {@code toString} method.
     *
     * @return String representation of the decision tree.
     */
    public String treeToString() {
        return this.nodes.get(0).toString();
    }

    /**
     * Predict the label of a given instance based on current decision tree.
     *
     * @param instance The instance to be predicted.
     * @return Predicted boolean value.
     */
    public boolean predict(Instance instance, List<String> attributes) {
        DecisionTreeNode it = this.nodes.get(0);
        while (!it.isLeaf())
            it = it.getNext(instance.getValue(attributes.indexOf(it.getAttribute())));
        return it.getLabel();
    }

    /**
     * Test the decision tree on specific test data set.
     * @param dataSet The data set to be tested on.
     * @return Accuracy.
     */
    public double test(final DataSet dataSet) {
        double correct = 0;
        for (Instance instance : dataSet.getInstances()) {
            if (instance.test(this.predict(instance, dataSet.getAttributes())))
                correct++;
        }
        return correct / dataSet.getNumOfInstance();
    }

    /**
     * Prune the decision tree based on specific pruning factor.
     * @param factor Pruning factor.
     */
    public void prune(double factor) {
        final int num = (int) Math.floor(this.getSize() * factor);
        System.out.println(num);
    }
}

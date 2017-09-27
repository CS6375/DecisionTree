package cs6375.github.io;

import java.lang.Math;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ID3DecisionTree extends DecisionTree {

    public ID3DecisionTree(TrainingDataSet trainingDataSet) {
        // Generate tree and store the root.
        DecisionTreeNode root = new ID3DecisionTreeNode(trainingDataSet);

        // Put all node into list for random access.
        super.generate(root);
    }

    /**
     * Prune the decision tree based on specific pruning factor.
     *
     * @param factor Pruning factor.
     */
    @Override
    public void prune(double factor, DataSet validation) {
        final int num = (int) Math.floor(this.getSize() * factor);

        double oriRate = this.test(validation);

        double rate;
        for (int count = 0; count < num; count++) {

            double maxIncrease = 0;
            DecisionTreeNode prunedNode = null;

            for (DecisionTreeNode node : this.nodes) {
                if (node.isPruned())
                    continue;
                node.setPruned();
                rate = this.test(validation);
                if (rate - oriRate <= maxIncrease) {
                    node.resetPruned();
                } else {
                    maxIncrease = rate - oriRate;
                    prunedNode = node;
                    node.resetPruned();
                }
            }

            if (prunedNode == null) {
                break;
            }
            prunedNode.setPruned();
            oriRate += maxIncrease;
        }

        // After pruning is complete, regenerate list storing the nodes.
        super.generate(this.nodes.get(0));
    }
}

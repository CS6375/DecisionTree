import cs6375.github.io.*;


public class Main {
    /**
     * The program read four arguments from the command line
     * <ul>
     *     <li>complete path of the training dataset,</li>
     *     <li>complete path of the validation dataset, </li>
     *     <li>complete path of the test dataset, </li>
     *     <li>the pruning factor </li>
     * </ul>
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 4)
            throw new Exception("Number of arguments is less than 4.");
        String training = args[0];
        String validation = args[1];
        String test = args[2];
        Double pruningFactor = Double.parseDouble(args[3]);

        TrainingDataSet trainingDataSet = new TrainingDataSet(training);
        DataSet validationDataSet = new DataSet(validation);
        DataSet testDataSet = new DataSet(test);

        DecisionTree decisionTree = new ID3DecisionTree(trainingDataSet);

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("ID3 Algorithm Generated Decision Tree");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Pre-Pruned Accuracy \n-------------------------------------");
        System.out.println("Number of training instances = " + trainingDataSet.getNumOfInstance());
        System.out.println("Number of training attributes = " + trainingDataSet.getDimension());
        System.out.println("Total number of nodes in the tree = " + decisionTree.getSize());
        System.out.println("Number of leaf nodes in the tree = " + decisionTree.getLeafSize());
        System.out.println(String.format("Average depth in the tree = %.1f", decisionTree.avgDepth()));
        System.out.println("Accuracy of the model on the training dataset = " + String.format("%.1f%%", 100 * decisionTree.test(trainingDataSet)));
        System.out.println();
        System.out.println("Number of validation instances = " + validationDataSet.getNumOfInstance());
        System.out.println("Number of validation attributes = " + validationDataSet.getDimension());
        System.out.println("Accuracy of the model on the validation dataset before pruning = " + String.format("%.1f%%", 100 * decisionTree.test(validationDataSet)));
        System.out.println();
        System.out.println("Number of testing instances = " + testDataSet.getNumOfInstance());
        System.out.println("Number of testing attributes = " + testDataSet.getDimension());
        System.out.println("Accuracy of the model on the testing dataset = " + String.format("%.1f%%", 100 * decisionTree.test(testDataSet)));
        System.out.println();

        if (args[4].equals("verbose"))
            System.out.println("\nDecision Tree after pruning:\n" + decisionTree.treeToString());

        decisionTree.prune(pruningFactor, validationDataSet);

        System.out.println();
        System.out.println("Post-Pruned Accuracy \n-------------------------------------");
        System.out.println("Number of training instances = " + trainingDataSet.getNumOfInstance());
        System.out.println("Number of training attributes = " + trainingDataSet.getDimension());
        System.out.println("Total number of nodes in the tree = " + decisionTree.getSize());
        System.out.println("Number of leaf nodes in the tree = " + decisionTree.getLeafSize());
        System.out.println(String.format("Average depth in the tree = %.1f", decisionTree.avgDepth()));
        System.out.println("Accuracy of the model on the training dataset = " + String.format("%.1f%%", 100 * decisionTree.test(trainingDataSet)));
        System.out.println();
        System.out.println("Number of validation instances = " + validationDataSet.getNumOfInstance());
        System.out.println("Number of validation attributes = " + validationDataSet.getDimension());
        System.out.println("Accuracy of the model on the validation dataset before pruning = " + String.format("%.1f%%", 100 * decisionTree.test(validationDataSet)));
        System.out.println();
        System.out.println("Number of testing instances = " + testDataSet.getNumOfInstance());
        System.out.println("Number of testing attributes = " + testDataSet.getDimension());
        System.out.println("Accuracy of the model on the testing dataset = " + String.format("%.1f%%", 100 * decisionTree.test(testDataSet)));

        double nodeSum  = 0;
        double leafSum  = 0;
        double depthSum = 0;
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Random Generate Decision Tree");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Node No.    Leaf No.    Avg Depth       Training    Validation  Test Set");
        for (int i = 0; i < 10; i++) {
            DecisionTree randomDecisionTree = new RandomDecisionTree(trainingDataSet);
            int node  = randomDecisionTree.getSize();
            int leaf  = randomDecisionTree.getLeafSize();
            double depth = randomDecisionTree.avgDepth();
            nodeSum  += node;
            leafSum  += leaf;
            depthSum += depth;
            System.out.println(String.format("%d         %d         %f        %.1f%%      %.1f%%       %.1f%%",
                    node,
                    leaf,
                    depth,
                    100 * randomDecisionTree.test(trainingDataSet),
                    100 * randomDecisionTree.test(validationDataSet),
                    100 * randomDecisionTree.test(testDataSet)));
        }
        for (int i = 0; i < 990; i++) {
            DecisionTree randomDecisionTree = new RandomDecisionTree(trainingDataSet);
            int node  = randomDecisionTree.getSize();
            int leaf  = randomDecisionTree.getLeafSize();
            double depth = randomDecisionTree.avgDepth();
            nodeSum  += node;
            leafSum  += leaf;
            depthSum += depth;
        }

        System.out.println();
        System.out.println("Average based on 1000 randomly generated decision tree:");
        System.out.println("Node count = " + nodeSum / 1000);
        System.out.println("Leaf count = " + leafSum / 1000);
        System.out.println("Depth = " + depthSum / 1000);

    }
}

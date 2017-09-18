import cs6375.github.io.DecisionTreeNode;
import cs6375.github.io.TrainingDataSet;

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
        System.out.println(trainingDataSet.getEntropy());

        DecisionTreeNode root = new DecisionTreeNode(trainingDataSet);

        return;
    }
}

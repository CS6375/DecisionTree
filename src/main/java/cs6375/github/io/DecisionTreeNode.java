package cs6375.github.io;

public class DecisionTreeNode {
    private String attribute;
    private boolean label;
    private DecisionTreeNode left;
    private DecisionTreeNode right;

    public DecisionTreeNode(TrainingDataSet dataSet) {
        // If all Examples are positive, Return the single-node tree Root, with label = +
        // If all Examples are negative, Return the single-node tree Root, with label = -
        // If Attributes is empty, Return the single-node tree Root, with label = most common value of Target attribute in Examples
        if (dataSet.getEntropy() == 0 || dataSet.getDimension() == 0)
            this.label = dataSet.getMostCommonLabel();
        this.attribute = dataSet.getMaxIG();
    }

    public DecisionTreeNode(boolean label) {
        this.label = label;
        this.left = null;
        this.right = null;
    }
}

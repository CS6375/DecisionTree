package cs6375.github.io;

public class DecisionTreeNode {
    private String attribute;
    private boolean label;
    private int depth;
    private DecisionTreeNode left;
    private DecisionTreeNode right;

    public DecisionTreeNode(TrainingDataSet dataSet) {
        // Following three cases are considered together.
        // If all Examples are positive, Return the single-node tree Root, with label = +
        // If all Examples are negative, Return the single-node tree Root, with label = -
        // If Attributes is empty, Return the single-node tree Root, with label = most common value of Target attribute in Examples
        this.label = dataSet.getMostCommonLabel();

        if (dataSet.getEntropy() == 0 || dataSet.getDimension() == 0) {
            return;
        }

        // Otherwise, first compute the best attributes as current node's attribute.
        this.attribute = dataSet.getMaxIG();
        TrainingDataSet a = new TrainingDataSet(dataSet.attributes, dataSet.getDimension() - 1);
        TrainingDataSet b = new TrainingDataSet(dataSet.attributes, dataSet.getDimension() - 1);
        dataSet.split(a, b, this.attribute);
        this.left = new DecisionTreeNode(a);
        this.right = new DecisionTreeNode(b);

        // Basically, the depth of current node is the different between {@code DataSet} dimension
        // and total number of attributes.
        this.depth = dataSet.attributes.size() - dataSet.getDimension();
    }


    public DecisionTreeNode(boolean label) {
        this.label = label;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        StringBuilder vertical = new StringBuilder();

        for (int i = 1; i <= depth; i++)
            vertical.append("| ");

        StringBuilder ret = new StringBuilder();

        if (this.attribute == null) {
            ret.append(this.label);
        } else {
//            ret.append(vertical).append(attribute).append("= 0 : ").append().append(left).append('\n');
            if (this.left.attribute == null) {
                ret.append(vertical).append(attribute).append("= 0 : ").append(left).append('\n');
            } else {
                ret.append(vertical).append(attribute).append("= 0 : \n").append(left).append('\n');
            }
            if (this.right.attribute == null) {
                ret.append(vertical).append(attribute).append("= 1 : ").append(right);
            } else {
                ret.append(vertical).append(attribute).append("= 1 : \n").append(right);
            }
        }
        return ret.toString();
    }
}

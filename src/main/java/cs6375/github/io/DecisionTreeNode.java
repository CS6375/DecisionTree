package cs6375.github.io;

public class DecisionTreeNode {
    String attribute;
    boolean label;
    int depth;
    DecisionTreeNode left;
    DecisionTreeNode right;
    boolean pruned = false;

    TrainingDataSet dataSet;

    public DecisionTreeNode(TrainingDataSet dataSet) {
        this.dataSet = dataSet;
        // Following three cases are considered together.
        // If all Examples are positive, Return the single-node tree Root, with label = +
        // If all Examples are negative, Return the single-node tree Root, with label = -
        // If Attributes is empty, Return the single-node tree Root, with label = most common value of Target attribute in Examples
        this.label = dataSet.getMostCommonLabel();

        // Basically, the depth of current node is the different between {@code DataSet} dimension
        // and total number of attributes.
        this.depth = dataSet.attributes.size() - dataSet.getDimension();

        if (dataSet.getEntropy() == 0 || dataSet.getDimension() == 0) {
            this.left = this.right = null;
            return;
        }
    }

    public boolean isLeaf() {
        return this.pruned || this.right == null;
    }

    public boolean isPruned() {
        return pruned;
    }

    public void setPruned() {
        this.pruned = true;
    }

    public void resetPruned() {
        this.pruned = false;
    }

    public String getAttribute() {
        return attribute;
    }

    public boolean getLabel() {
        return label;
    }

    public DecisionTreeNode getLeft() {
        return this.left;
    }

    public DecisionTreeNode getRight() {
        return this.right;
    }

    public DecisionTreeNode getNext(boolean value) {
        return value ? this.left : this.right;
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

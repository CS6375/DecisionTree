package cs6375.github.io;

public class RandomDecisionTreeNode extends DecisionTreeNode {

    public RandomDecisionTreeNode(TrainingDataSet dataSet) {
        super(dataSet);

        if (dataSet.getEntropy() == 0 || dataSet.getDimension() == 0) {
            this.left = this.right = null;
            return;
        }

        // Otherwise, first compute the best attributes as current node's attribute.
        this.attribute = dataSet.getRandomLabel();
        assert this.attribute != null;
        TrainingDataSet a = new TrainingDataSet(dataSet.attributes, dataSet.getDimension() - 1);
        TrainingDataSet b = new TrainingDataSet(dataSet.attributes, dataSet.getDimension() - 1);
        dataSet.split(a, b, this.attribute);
        this.left = new ID3DecisionTreeNode(a);
        this.right = new ID3DecisionTreeNode(b);
    }
}

package cs6375.github.io;

public class ID3DecisionTreeNode extends DecisionTreeNode {

    public ID3DecisionTreeNode(TrainingDataSet dataSet) {
        super(dataSet);

        if (dataSet.getEntropy() == 0 || dataSet.getDimension() == 0) {
            super.left = super.right = null;
            return;
        }


//        System.out.println(this.depth);

        // Otherwise, first compute the best attributes as current node's attribute.
        super.attribute = dataSet.getMaxIG();
        TrainingDataSet a = new TrainingDataSet(dataSet.attributes, dataSet.getDimension() - 1);
        TrainingDataSet b = new TrainingDataSet(dataSet.attributes, dataSet.getDimension() - 1);
        dataSet.split(a, b, this.attribute);
        super.left = new ID3DecisionTreeNode(a);
        super.right = new ID3DecisionTreeNode(b);

    }
}

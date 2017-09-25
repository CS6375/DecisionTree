package cs6375.github.io;

import org.junit.Test;

public class DecisionTreeNodeTest {

    @Test
    public void DecisionTreeNode() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();
        TrainingDataSet ds = new TrainingDataSet(trainingfile);

        DecisionTreeNode dt = new DecisionTreeNode(ds);
//        System.out.println(dt);
        System.out.println(dt.getRight().getLabel());
    }
}
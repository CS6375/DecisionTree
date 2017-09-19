package cs6375.github.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class DecisionTreeTest {
    @Test
    public void predict() throws Exception {
    }

    @Test
    public void test() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();
        String testfile = this.getClass().getClassLoader().getResource("data_sets1/test_set.csv").getFile();
        TrainingDataSet ds = new TrainingDataSet(trainingfile);
        DataSet ts = new DataSet(testfile);

        DecisionTree dt = new DecisionTree(ds);
        System.out.println(dt.test(ts));
    }

    @Test
    public void getSize() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();
        TrainingDataSet ds = new TrainingDataSet(trainingfile);

        DecisionTree dt = new DecisionTree(ds);

        System.out.println(dt.getSize());
//        System.out.println(dt.treeToString());

        System.out.println(dt.predict(ds.getInstances().get(1), ds.attributes));
    }

}
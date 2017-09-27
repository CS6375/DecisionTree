package cs6375.github.io;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class DataSetTest {

    @Test
    public void DecisionTree() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();
        TrainingDataSet ds = new TrainingDataSet(trainingfile);
        DecisionTree dt = new ID3DecisionTree(ds);
        System.out.println(dt.treeToString());
    }

}
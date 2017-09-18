package cs6375.github.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrainingDataSetTest {
    @Test
    public void getEntropy() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();

        TrainingDataSet ds = new TrainingDataSet(trainingfile);

        assertEquals(1.0, ds.getEntropy(), 0);
    }

}
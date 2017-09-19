package cs6375.github.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrainingDataSetTest {
    @Test
    public void computeIGAtI() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();
        TrainingDataSet ds = new TrainingDataSet(trainingfile);
        System.out.println(ds.getMaxIG());
    }

    @Test
    public void getEntropy() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();

        TrainingDataSet ds = new TrainingDataSet(trainingfile);

        assertEquals(1.0, ds.getEntropy(), 0);
    }
    @Test
    public void split() throws Exception {
        String trainingfile = this.getClass().getClassLoader().getResource("data_sets1/training_set.csv").getFile();

        TrainingDataSet ds = new TrainingDataSet(trainingfile);
        TrainingDataSet a = new TrainingDataSet(ds.getAttributes(), ds.getDimension() - 1);
        TrainingDataSet b = new TrainingDataSet(ds.getAttributes(), ds.getDimension() - 1);
        ds.split(a, b, "XH");
        System.out.println(a);
        System.out.println(b);
        System.out.println(ds.getNumOfInstance());
    }
}
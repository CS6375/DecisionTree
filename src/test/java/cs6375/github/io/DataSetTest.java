package cs6375.github.io;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class DataSetTest {
    @Test
    public void split() throws Exception {
        DataSet ds = new DataSet("resources/data_sets1/training_set.csv");
        DataSet a = new DataSet(ds.getAttributes(), ds.getDimension());
        DataSet b = new DataSet(ds.getAttributes(), ds.getDimension());
        ds.split(a, b, "XH");
        System.out.println(a);
        System.out.println(b);
        System.out.println(ds.getNumOfInstance());
    }

    @Test
    public void DataSet() throws IOException {
        DataSet ds = new DataSet("resources/data_sets1/training_set.csv");
        System.out.println(ds);
    }

}
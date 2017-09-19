package cs6375.github.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represent a dataset with only boolean attribute and boolean label.
 */
public class DataSet {
    /**
     * List of instances, internally an ArrayList.
     */
    private List<Instance> instances;

    /**
     * The name of each attributes. Used ArrayList instead of array for the {@code indexOf}
     * function. This is final since the data set is fixed once read from file. No modification to
     * current data set is allow. If a split operation is needed, allocate new data set to store new
     * collection of instances.
     */
    final List<String> attributes;

    /**
     * Boolean indicator to determine whether an attribute with certain index is excluded in
     * computing.
     */
    boolean[] exclude;

    /**
     * Current dimension of the data set, i.e., the number of not-excluded attributes. Like the
     * {@code attributes}, intuitively, the {@code dimension} should be fixed once the data set was
     * form.
     */
    private final int dimension;

    public List<String> getAttributes() {
        return attributes;
    }

    public int getDimension() {
        return dimension;
    }

    public int getOriginalDimension() {
        return this.exclude.length;
    }

    public DataSet(List<String> attributes, int dimension) {
        this.attributes = attributes;
        this.dimension = dimension;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public DataSet(final String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String[] temp = br.readLine().split(",");
        this.dimension = temp.length - 1;
        this.attributes = new ArrayList<>(dimension);
        this.exclude = new boolean[dimension];

        for (int i = 0; i < this.dimension; i++) {
            this.attributes.add(temp[i]);
            this.exclude[i] = false;
        }

        this.instances = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            instances.add(new Instance(line));
            line = br.readLine();
        }

        br.close();
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    public void setExclude(boolean[] exclude) {
        this.exclude = exclude;
    }

    public int getNumOfInstance() {
        return this.instances.size();
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "\ninstances=" + instances +
                ", \nattributes=" + attributes +
                ", \nexclude=" + Arrays.toString(exclude) +
                ", \ndimension=" + dimension +
                '}';
    }

}

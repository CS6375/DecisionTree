package cs6375.github.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSet {
    List<Instance> instances;
    final List<String> attributes;
    Boolean[] exclude;
    final int dimension;

    public List<String> getAttributes() {
        return attributes;
    }

    public int getDimension() {
        return dimension;
    }

    public DataSet(List<String> attributes, int dimension) {
        this.attributes = attributes;
        this.dimension = dimension;
    }

    public DataSet(final String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String[] temp = br.readLine().split(",");
        this.dimension = temp.length - 1;
        this.attributes = new ArrayList<>(dimension);
        this.exclude = new Boolean[dimension];

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

    public void setExclude(Boolean[] exclude) {
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

    public void split(DataSet a, DataSet b, String attribute) {
        final int index = this.attributes.indexOf(attribute);

        Boolean[] newExclude = new Boolean[this.dimension];
        System.arraycopy(this.exclude, 0, newExclude, 0, this.dimension);
        newExclude[index] = true;

        List<Instance> alist = new ArrayList<>();
        List<Instance> blist = new ArrayList<>();

        for (Instance instance : this.instances) {
            if (instance.getValues()[index])
                alist.add(instance);
            else
                blist.add(instance);
        }
        a.setExclude(newExclude);
        b.setExclude(newExclude);
        a.setInstances(alist);
        b.setInstances(blist);
    }
}

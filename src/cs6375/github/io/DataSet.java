package cs6375.github.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSet {
    private List<Instance> instances;
    private List<String> attributes;
    private Boolean[] exclude;
    private final int dimension;

    public DataSet(List<Instance> instances, List<String> attributes, Boolean[] exclude, int dimension) {
        this.instances = instances;
        this.attributes = attributes;
        this.exclude = exclude;
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

    @Override
    public String toString() {
        return "DataSet{" +
                "instances=" + instances +
                ", attributes=" + attributes +
                ", exclude=" + Arrays.toString(exclude) +
                ", dimension=" + dimension +
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
        a = new DataSet(alist, this.attributes, newExclude, this.dimension);
        b = new DataSet(alist, this.attributes, newExclude, this.dimension);
    }

}

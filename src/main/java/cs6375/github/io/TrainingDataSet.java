package cs6375.github.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingDataSet extends DataSet {
    private Double entropy;
    private Boolean mostCommonLabel;

    public TrainingDataSet(List<String> attributes, int dimension) {
        super(attributes, dimension);
        this.mostCommonLabel = null;
    }

    public TrainingDataSet(String filename) throws IOException {
        super(filename);
        this.computeEntropy();
    }

    public void computeEntropy() {
        double total = this.getNumOfInstance();
        double pos = this.instances.stream().filter(Instance::getLabel).count();
        double neg = this.instances.stream().filter(x -> !x.getLabel()).count();

        assert pos + neg == total;

        this.entropy = -Math.plogp(pos / total) - Math.plogp(neg / total);
        this.mostCommonLabel = pos > neg;
    }

    public double getEntropy() {
        assert entropy != null;
        return entropy;
    }

    public String getMaxIG() {
        double maxIG = 0;
        int maxIndex = -1;
        double IG;
        for (int i = 0; i < this.getDimension(); i++) {
            if (this.exclude[i])
                continue;
            IG = this.computeIGAtI(i);
            if (maxIG < IG) {
                maxIG = IG;
                maxIndex = i;
            }
        }
        return this.attributes.get(maxIndex);
    }

    public double computeIGAtI(int i) {
        double total = this.instances.size();

        double pos = this.instances.stream().filter(x -> x.getValues()[i]).count();
        double pospos = this.instances.stream().filter(x -> x.getValues()[i]).filter(Instance::getLabel).count();
        double posneg = this.instances.stream().filter(x -> x.getValues()[i]).filter(x -> !x.getLabel()).count();

        double posEntropy = -Math.plogp(pospos / pos) - Math.plogp(posneg / pos);

        double neg = this.instances.stream().filter(x -> !x.getValues()[i]).count();
        double negpos = this.instances.stream().filter(x -> !x.getValues()[i]).filter(Instance::getLabel).count();
        double negneg = this.instances.stream().filter(x -> !x.getValues()[i]).filter(x -> !x.getLabel()).count();

        double negEntropy = -Math.plogp(negpos / neg) - Math.plogp(negneg / neg);

        return this.entropy - (posEntropy * pos / total + negEntropy * neg / total);
    }

    @Override
    public void setInstances(List<Instance> instances) {
        super.setInstances(instances);
        this.computeEntropy();
    }

    public void split(TrainingDataSet a, TrainingDataSet b, String attribute) {
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

    public boolean getMostCommonLabel() {
        return this.mostCommonLabel;
    }
}

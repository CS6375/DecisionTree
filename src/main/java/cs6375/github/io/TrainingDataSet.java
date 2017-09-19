package cs6375.github.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Based on {@code DataSet} class, extended with entropy computation and split function.
 */
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
        double pos = this.getInstances().stream().filter(Instance::getLabel).count();
        double neg = this.getInstances().stream().filter(x -> !x.getLabel()).count();

        assert pos + neg == total;

        this.entropy = -Math.plogp(pos / total) - Math.plogp(neg / total);
        this.mostCommonLabel = pos > neg;
    }

    public double getEntropy() {
        assert entropy != null;
        return entropy;
    }

    /**
     * Compute the next attribute with the largest information gain (IG).
     *
     * @return The next attribute in String.
     */
    public String getMaxIG() {
        assert this.getDimension() > 0;
        assert this.entropy > 0 && this.entropy <= 1;

        double maxIG = 0;
        int maxIndex = -1;
        double IG;
        for (int i = 0; i < this.exclude.length; i++) {
            if (this.exclude[i])
                continue;
            IG = this.computeIGAtI(i);
            if (maxIG <= IG) {
                maxIG = IG;
                maxIndex = i;
            }
        }
        return this.attributes.get(maxIndex);
    }

    public double computeIGAtI(int i) {
        double total = this.getInstances().size();

        double pos = this.getInstances().stream().filter(x -> x.getValues()[i]).count();
        double pospos = this.getInstances().stream().filter(x -> x.getValues()[i]).filter(Instance::getLabel).count();
        double posneg = this.getInstances().stream().filter(x -> x.getValues()[i]).filter(x -> !x.getLabel()).count();

        double posEntropy = -Math.plogp(pospos / pos) - Math.plogp(posneg / pos);
        assert posEntropy >= 0 && posEntropy <= 1;

        double neg = this.getInstances().stream().filter(x -> !x.getValues()[i]).count();
        double negpos = this.getInstances().stream().filter(x -> !x.getValues()[i]).filter(Instance::getLabel).count();
        double negneg = this.getInstances().stream().filter(x -> !x.getValues()[i]).filter(x -> !x.getLabel()).count();

        double negEntropy = -Math.plogp(negpos / neg) - Math.plogp(negneg / neg);
        assert negEntropy >= 0 && negEntropy <= 1;

        assert pos == pospos + posneg;
        assert neg == negpos + negneg;
        assert total == pos + neg;


        return this.entropy - (posEntropy * pos / total + negEntropy * neg / total);
    }

    @Override
    public void setInstances(List<Instance> instances) {
        super.setInstances(instances);
        this.computeEntropy();
    }

    /**
     * Split this training data set into two by specific attribute.
     *
     * @param a         First part of the split data set.
     * @param b         Second part of the split data set.
     * @param attribute Attribute to split upon.
     */
    public void split(TrainingDataSet a, TrainingDataSet b, String attribute) {

        // The new allocated data set should have the dimension one less then current data set.
        assert a.getDimension() + 1 == this.getDimension();
        assert b.getDimension() + 1 == this.getDimension();

        // Index of the attribute to be split upon.
        final int index = this.attributes.indexOf(attribute);

        // Set up the new exclude flags.
        boolean[] newExclude = new boolean[this.exclude.length];
        System.arraycopy(this.exclude, 0, newExclude, 0, this.getDimension());
        newExclude[index] = true;

        List<Instance> alist = new ArrayList<>();
        List<Instance> blist = new ArrayList<>();

        for (Instance instance : this.getInstances()) {
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

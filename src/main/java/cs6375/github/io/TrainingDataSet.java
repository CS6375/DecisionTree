package cs6375.github.io;

import java.io.IOException;

public class TrainingDataSet extends DataSet {
    private Double entropy;

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
}

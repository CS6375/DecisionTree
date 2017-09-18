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
        double pos = super.instances.stream().filter(Instance::getLabel).count();
        double neg = this.instances.stream().filter(x -> !x.getLabel()).count();

        assert pos + neg == total;

        this.entropy = -Math.plogp(pos / total) - Math.plogp(neg / total);
    }

    public double getEntropy() {
        assert entropy != null;
        return entropy;
    }
}

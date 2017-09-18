package cs6375.github.io;


import java.util.Arrays;

public class Instance {
    private final boolean[] values;

    private final boolean label;

    public boolean[] getValues() {
        return values;
    }

    public boolean getLabel() {
        return label;
    }

    public Instance(String values) {
        String[] valuesArray = values.split(",");
        final int numOfAttributes = valuesArray.length - 1;
        this.values = new boolean[numOfAttributes];

        for (int i = 0; i < numOfAttributes; i++)
            this.values[i] = valuesArray[i].equals("1");

        this.label = valuesArray[numOfAttributes].equals("1");
    }

    @Override
    public String toString() {
        return "Instance{" +
                "values=" + Arrays.toString(values) +
                ", label=" + label +
                '}';
    }
}

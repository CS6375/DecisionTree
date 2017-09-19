package cs6375.github.io;


import java.util.Arrays;

/**
 * Represent a line in the data set file.
 */
public final class Instance {

    /**
     * Store the values of each attribute in the instance. Currently only boolean type is
     * supported.
     */
    private final boolean[] values;

    /**
     * Store the label of the instance. Currently only boolean type classification is supported.
     */
    private final boolean label;

    /**
     * Return the values array.
     *
     * @return values array.
     */
    public boolean[] getValues() {
        return values;
    }

    /**
     * Return the value of specific index.
     * @param i The index of value needed.
     * @return The value of index i.
     */
    public boolean getValue(int i) {
        return this.values[i];
    }

    public boolean test(boolean predict) {
        return predict == this.label;
    }

    /**
     * Return the label of current instance.
     *
     * @return label of current instance.
     */
    public boolean getLabel() {
        return label;
    }

    /**
     * Public constructor from a given string. The string should be separated by comma(,).
     * @param values The string representing current instance.
     */
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

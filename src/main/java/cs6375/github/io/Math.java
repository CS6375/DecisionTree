package cs6375.github.io;

class Math {
    private static double log2 = java.lang.StrictMath.log(2);

    static double plogp(double x) {
        return x == 0 ? 0 : x * java.lang.StrictMath.log(x) / log2;
    }
}

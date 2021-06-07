package util;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Math {
    public static double hypotenuse(double a, double b) {
        return sqrt(pow(a, 2.0) + pow(b, 2.0));
    }
}

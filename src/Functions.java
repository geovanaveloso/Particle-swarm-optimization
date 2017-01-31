/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovana
 */
public abstract class Functions {

    public static double VEL_LOW = -5;
    public static double VEL_HIGH = 5;
    public static double PRECISION = 1E-10;
    public double LOC_X_LOW = 0;
    public double LOC_X_HIGH = 0;
    public String name;
    double optimum;

    public double evaluate(Localization localization) {
        return Double.MAX_VALUE;
    }

}

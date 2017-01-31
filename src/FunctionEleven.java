/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovana
 */
public class FunctionEleven extends Functions {

    public FunctionEleven() {
        LOC_X_HIGH = 600;
        LOC_X_LOW = -600;
        name = "Funcao 11";
        optimum = 0;
    }

    @Override
    public double evaluate(Localization localization) {
        double loc[] = localization.getLoc();
        double fitness = 0, prod;
        for (int i = 0; i < loc.length; i++) {
            prod = 0;
            for (int j = 1; j < loc.length; j++) {
                prod *= Math.cos((loc[j] / Math.sqrt(j))) + 1;
            }
            fitness+=Math.pow(loc[i], 2)-prod;
        }

        return fitness/4000;
    }
}

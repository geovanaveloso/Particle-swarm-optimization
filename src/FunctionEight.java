/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovana
 */
public class FunctionEight extends Functions{
       
    public FunctionEight() {
        LOC_X_HIGH = 500;
        LOC_X_LOW = -500;
        name = "Funcao 8";
        optimum = -12569.5;
    }

    @Override
    public double evaluate(Localization localization) {
        double loc[] = localization.getLoc();
        double fitness = 0;
        for (int i =0; i<loc.length; i++){
            fitness+= (loc[i]*Math.sin(loc[i])*Math.sqrt(Math.abs(loc[i])));
        }
        return fitness;  
    }
}

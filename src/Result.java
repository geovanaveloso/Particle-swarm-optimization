/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovana
 */
public class Result implements Comparable<Result>{
     double C1;
     double C2;
     double fitness;
     String function;

    public Result(double C1, double C2, double fitness, String function) {
        this.C1 = C1;
        this.C2 = C2;
        this.fitness = fitness;
        this.function = function;
    }

    public double getC1() {
        return C1;
    }

    public void setC1(double C1) {
        this.C1 = C1;
    }

    public double getC2() {
        return C2;
    }

    public void setC2(double C2) {
        this.C2 = C2;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public int compareTo(Result o) {
        if (this.fitness<o.fitness){
            return -1;
        }else{
          if (this.fitness>o.fitness){
              return 1;
          }  else{
              return 0;
          }
        }
    }
}

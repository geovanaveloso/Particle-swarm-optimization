/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovana
 */
public class Particle {

    private double fitness;
    private Speed speed;
    private Localization localization;

    public Particle() {
        super();
    }

    public Particle(double fitness, Speed speed, Localization localization) {
        this.fitness = fitness;
        this.speed = speed;
        this.localization = localization;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalizacao(Localization localization){
        this.localization = localization;
    }

    public double getFitness(Functions f) {
        fitness = f.evaluate(localization);
        return fitness;
    }
}

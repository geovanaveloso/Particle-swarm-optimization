/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author geovana
 */
public class Speed {

    private double[] speeds;

    public Speed(double[] speed) {
        super();
        this.speeds = speed;
    }

    public double[] getPos() {
        return speeds;
    }

    public void setPos(double[] vel) {
        this.speeds = vel;
    }
}

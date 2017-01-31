
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author geovana
 */
public class PSO {

    private static final int SIZE_DIMENSIONS = 30;
    private static final double UPPER_INERTIA = 0.9;
    private static final double LOWER_INERTIA = 0.4;
    int SIZE_SWARM;
    int MAX_GENERATIONS;
    ArrayList<Particle> swarm = new ArrayList<>();
    ArrayList<Localization> pBestLocalization = new ArrayList<>();
    double[] pBest;
    double[] listFitness;
    Localization gBestLocalization;
    Functions function;
    double gBest;
    double C1 = 0.1;
    double C2 = 0.1;
    double[] convergence;
    Random random = new Random();

    public PSO(Functions f, int size_swarm, int max_generations) {
        this.function = f;
        this.SIZE_SWARM = size_swarm;
        pBest = new double[SIZE_SWARM];
        listFitness = new double[SIZE_SWARM];
        this.MAX_GENERATIONS = max_generations;
        convergence = new double[MAX_GENERATIONS];
    }

    public double execute() {
        swarm.clear();
        InitiateSwarm();
        updateFitness();

        // Initialization of pbest and pBestLocalization variables
        for (int i = 0; i < SIZE_SWARM; i++) {
            pBest[i] = listFitness[i];
            pBestLocalization.add(swarm.get(i).getLocalization());
        }

        int generation = 0;
        double w;

        while (generation < MAX_GENERATIONS) {

            //Checks and updates if in the new generation the particles obtained lesser fitness
            //than they had already achieved. Saves the best fitness and best location of each 
            //particle
            for (int i = 0; i < SIZE_SWARM; i++) {
                if (listFitness[i] < pBest[i]) {
                    pBest[i] = listFitness[i];
                    pBestLocalization.set(i, swarm.get(i).getLocalization());
                }
            }

            //Verifical which fitness is better and returns the index of the particulate q has less 
            //fitness of the whole swarm.
            //If smaller fitness found is less than the best kept the better guarded is updated saves
            //the best fitness and best location of the entire population
            int bestParticleIndex = getMinPos(listFitness);
            if (generation == 0 || listFitness[bestParticleIndex] < gBest) {
                gBest = listFitness[bestParticleIndex];
                gBestLocalization = swarm.get(bestParticleIndex).getLocalization();
            }

           //Check if the swarm is in convergence,
           //if it is attempting to perturbation by increasing the c1 and c2
            if (checkconvergence()) {
                int i = random.nextInt(3);
                if (i == 0) {
                    C1 += 0.1;
                } else {
                    if (i == 1) {
                        C2 += 0.1;
                    } else {
                        C1 += 0.1;
                        C2 += 0.1;
                    }
                }
            }

            w = UPPER_INERTIA - (((double) generation / MAX_GENERATIONS) * (UPPER_INERTIA - LOWER_INERTIA));

            // Swarm movement
            for (int i = 0; i < SIZE_SWARM; i++) {
                double r1 = random.nextDouble();
                double r2 = random.nextDouble();

                Particle p = swarm.get(i);

                double[] newSpeed = new double[SIZE_DIMENSIONS];
                for (int j = 0; j < SIZE_DIMENSIONS; j++) {
                    // new speed = inertia * speed of particle + r1 * c1 * Best location visited by particle i
                    // Best location particle i + r2*c2 * Best location visited by swarm - Current location of the particle
                    newSpeed[j] = (w * p.getSpeed().getPos()[j]) + (r1 * C1) * (pBestLocalization.get(i).getLoc()[j]
                            - p.getLocalization().getLoc()[j]) + (r2 * C2) * (gBestLocalization.getLoc()[j] - p.getLocalization().getLoc()[j]);
                }
                p.setSpeed(new Speed(newSpeed));

                // Particle motion according to velocity
                double[] newLocalization = new double[SIZE_DIMENSIONS];
                for (int j = 0; j < SIZE_DIMENSIONS; j++) {
                    newLocalization[j] = p.getLocalization().getLoc()[j] + newSpeed[j];
                }
                p.setLocalizacao(new Localization(newLocalization));
            }
            updateFitness();
            double best = Utils.checkPrecision(gBest, function);
            Utils.writeConvergence(best, SIZE_SWARM, MAX_GENERATIONS, function.name);
            generation++;
        }
        Utils.writeParameters(C1, C2, SIZE_SWARM,function.name);
        return function.evaluate(gBestLocalization);
    }

    public void InitiateSwarm() {
        Particle p;
        for (int i = 0; i < SIZE_SWARM; i++) {
            p = new Particle();
            double[] loc = new double[SIZE_DIMENSIONS];
            double[] speed = new double[SIZE_DIMENSIONS];
            for (int j = 0; j < SIZE_DIMENSIONS; j++) {
                loc[j] = function.LOC_X_LOW + random.nextDouble() * (function.LOC_X_HIGH - function.LOC_X_LOW);
                speed[j] = Functions.VEL_LOW + random.nextDouble() * (Functions.VEL_HIGH - Functions.VEL_LOW);
            }
            p.setLocalizacao(new Localization(loc));
            p.setSpeed(new Speed(speed));
            swarm.add(p);
        }
    }

    // Upadate fitness after movement swarm
    public void updateFitness() {
        for (int i = 0; i < SIZE_SWARM; i++) {
            listFitness[i] = swarm.get(i).getFitness(this.function);
        }
    }

    // Returns index with best fitness
    public static int getMinPos(double[] list) {
        int pos = 0;
        double minValue = list[0];
        for (int i = 0; i < list.length; i++) {
            if (list[i] < minValue) {
                pos = i;
                minValue = list[i];
            }
        }
        return pos;
    }

    //Check that the particles have stagnated
    public boolean checkconvergence() {
        int convergents = 0;
        for (int i = 0; i < SIZE_SWARM; i++) {
            for (int j = 0; j < SIZE_SWARM; j++) {
                if (i != j) {
                    double f = Math.abs(listFitness[i] - listFitness[j]);
                    if (f < Functions.PRECISION) {
                        convergents++;
                    }
                }
            }
        }
        return (convergents / SIZE_SWARM * SIZE_SWARM) >= 0.8;
    }
}

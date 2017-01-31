

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author geovana
 */
public class Main {

    static String path;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_TESTS = 10;
        path = args[0];
        long begin, end;
        Functions f;
        int[] test_population_size = {100, 200, 500};
        int[] test_max_generation = {2000};

        for (int m = 0; m < 4; m++) {
            if (m == 0) {
                f = new FunctionOne();
                Utils.CreateDirectory(path + f.name);
            } else {
                if (m == 1) {
                    f = new FunctionThree();
                    Utils.CreateDirectory(path + f.name);
                } else {
                    if (m == 2) {
                        f = new FunctionNine();
                        Utils.CreateDirectory(path + f.name);
                    } else {
                        f = new FunctionEleven();
                        Utils.CreateDirectory(path + f.name);
                    }
                }
            }
            for (int k = 0; k < test_max_generation.length; k++) {
                for (int j = 0; j < test_population_size.length; j++) {
                    System.out.println("Executando " + f.name + " Tamanho enxame " + test_population_size[j]);
                    PSO pso = new PSO(f, test_population_size[j], test_max_generation[k]);
                    begin = System.currentTimeMillis();
                    for (int i = 0; i < MAX_TESTS; i++) {
                        double r = pso.execute();
                        r = Utils.checkPrecision(r, f);
                        Utils.writeResultTestes(r, test_population_size[j], test_max_generation[k], f.name);
                    }
                    end = System.currentTimeMillis();
                    Utils.writeTime((end - begin), f.name, test_population_size[j], test_max_generation[k]);
                }
            }
        }
    }
}

package fr.guillaume;

public class Main {

    public static void main(String[] args) {
        seuilPercolation(400, 1000);
    }


    public static void seuilPercolation(int size, int iterations) {
        long time1=System.currentTimeMillis();
        double sum = 0;
        for (int i = 0; i < iterations; i++) {
            sum += new Percolation(size).run();
        }
        long time2=System.currentTimeMillis();

        System.out.println(sum / iterations);
        System.out.println("Time (s): "+(((double)(time2-time1))/1000));
    }

}



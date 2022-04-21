package fr.guillaume;

import java.util.Random;

public class Percolation {
    private final int size;
    private boolean[] grid;
    private int[] links;
    private int[] treeHeigths;
    private final Random random = new Random();

    public Percolation(int size) {
        //size*size haut et +1 le bas
        this.size = size;
        grid = new boolean[size * size];
        links = new int[size * size+2];
        //We initialise links
        for (int i = 0; i < size * size+2; i++) {
            links[i] = i;
        }
        for(int i=0;i<size;i++) {
            links[i]=size*size;
            links[size*(size-1)+i]=size*size+1;
        }
        treeHeigths = new int[size * size+2];
        for (int i = 0; i < size * size+2; i++) {
            treeHeigths[i] = 1;
        }
    }


    public double run() {
        int index = 0;
        while (find(size*size) != find(size*size+1) && index < size * size - 1) {

            //We color a new square
            int randomIndex = random.nextInt(size * size);
            while (grid[randomIndex]) {
                randomIndex = random.nextInt(size * size);
            }
            grid[randomIndex] = true;

            //We make the changes necessary for the union-find
            int x = randomIndex % size;
            int y = randomIndex / size;
            if (x > 0 && grid[randomIndex - 1]) {
                union(randomIndex, randomIndex - 1);
            }
            if (x < (size - 1) && grid[randomIndex + 1]) {
                union(randomIndex, randomIndex + 1);

            }
            if (y > 0 && grid[randomIndex - size]) {
                union(randomIndex, randomIndex - size);

            }
            if (y < size - 1 && grid[randomIndex + size]) {
                union(randomIndex, randomIndex + size);
            }

            index++;
        }
        return ((double) (index)) / ((double) (size * size));
    }

    public void showGrid() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int index = i + size * j;
                if (grid[index])
                    System.out.print(find(index) + "|");
                else
                    System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println();


    }

    private void union(int i, int j) {
        int iFather = find(i);
        int jFather = find(j);
        if (treeHeigths[iFather] > treeHeigths[jFather]) {
            links[jFather] = iFather;
        } else if (treeHeigths[iFather] == treeHeigths[jFather]) {
            links[jFather] = iFather;
            treeHeigths[iFather]++;
        } else {
            links[iFather] = jFather;
        }
    }

    private int find(int i) {
        int current = i;
        while (links[current] != current) {
            current = links[current];
        }

        return current;
    }

}

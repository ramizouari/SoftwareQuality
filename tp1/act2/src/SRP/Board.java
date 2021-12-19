package SRP;

import java.util.ArrayList;

public class Board {

    int size;
    ArrayList<String> spots;

    public Board(int size) {
        this.size = size;
        this.spots = new ArrayList<String>();

        for (int i=0; i < size; i++) {
            for (int j=0; j< size ; j++) {
                this.spots.add(String.valueOf(size*i + j));
            }
        }
    }

    public ArrayList<String> valuesAt(ArrayList<Integer> indexes) {
        ArrayList<String> values = new ArrayList<>();

        for (int index: indexes) {
            values.add(this.spots.get(index));
        }
        return values ;
    }



}

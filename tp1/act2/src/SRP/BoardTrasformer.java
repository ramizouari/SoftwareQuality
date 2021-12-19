package SRP;

import java.util.*;

/**
 * Transforms the Board into an Array of lines
 */
public class BoardTrasformer {
    public ArrayList<ArrayList<Integer>> rowIndexes(Board board) {
        int size = board.size ;
        ArrayList<ArrayList<Integer>> rowIndexes = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < size; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < size; j++) {
                row.add((i*size)+(j));
            }
            rowIndexes.add(row);
        }

        return rowIndexes;
    }
}

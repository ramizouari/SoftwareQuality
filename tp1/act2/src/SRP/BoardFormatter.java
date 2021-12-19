package SRP;

public class BoardFormatter {

    /**
     * Shows a Board
     * @param board
     */
    public static void display(Board board) {
        String formattedBoard = "" ;
        int size = board.size;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                formattedBoard += board.spots.get(i*size + j);
                formattedBoard += (j+1 < size) ? "|" : "\n";
            }
        }
        System.out.print(formattedBoard);
    }
}

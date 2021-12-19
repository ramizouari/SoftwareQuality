package LSP;


/**
 * We removed the inheritance because it doesn't make much sense, a studio doesn't have bedrooms
 */
public class Studio {
    int squareFootage;
    int numberOfRooms;

    public Studio() {
        this.numberOfRooms = 0;
    }

    public void setSquareFootage(int sqft) {
        this.squareFootage = sqft;
    }
}

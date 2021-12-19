package LSP;


public class BedroomAdder {
    /**
     * We can add bedrooms to penthouses only, this is why we pass PenthouseSuite in Parameter
     * @param penthouse
     */
    public void addBedroom(PenthouseSuite penthouse) {
        penthouse.numberOfBedrooms += 1;
    }
}

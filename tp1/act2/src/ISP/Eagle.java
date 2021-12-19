package ISP;

/**
 * An eagle can molt and fly
 */
public class Eagle implements  Flying, Feathered{

    String currentLocation;
    int numberOfFeathers;

    public Eagle(int initialFeatherCount) {
        this.numberOfFeathers = initialFeatherCount;
    }


    @Override
    public void molt() {
        this.currentLocation = "in the air";
    }

    @Override
    public void fly() {
        this.numberOfFeathers -= 1;
    }
}

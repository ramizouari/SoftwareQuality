package ISP;

/**
 * A penguin can Molt and Swim
 */
public class Penguin implements Feathered, Swimming{
    String currentLocation;
    int numberOfFeathers;

    @Override
    public void molt() {
        this.numberOfFeathers -= 1;
    }

    @Override
    public void swim() {
        this.currentLocation = "in the water";
    }
}

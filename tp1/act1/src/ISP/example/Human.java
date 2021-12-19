package ISP.example;

public class Human implements Workable, Feedable{

    @Override
    public String eat() {
        return "Human eats";
    }

    @Override
    public String work() {
        return "Human works";
    }
}

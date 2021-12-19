package DIP;

public class Emailer implements Notifier{
    @Override
    public void generateWeatherAlert(String weatherConditions) {
        if (weatherConditions == "sunny") {
            String alert = "It is " + weatherConditions;
            System.out.println(alert);
        }
    }
}

package DIP;

public class Phone implements Notifier{
    @Override
    public void generateWeatherAlert(String weatherConditions) {
        if (weatherConditions == "rainy") {
            String alert = "It is " + weatherConditions;
            System.out.println(alert);
        }
    }
}

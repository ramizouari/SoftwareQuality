package ISP.exercice;

import java.util.Random;

public class Sensor
{
    public void register(Sensing sensing)
    {
        while (true) {
            if (isPersonClose()) {
                sensing.proximityCallback();
                break;
            }
        }
    }

    private boolean isPersonClose()
    {
        return new Random().nextBoolean();
    }
}


package ISP.example;

import java.util.List;

/**
 * Seulement les "eaters" font parti du "Messhall"
 */
public class MessHall {
    private List<Feedable> _eaters;

    public MessHall(List<Feedable> eaters)
    {
        _eaters = eaters;
    }

    public void manage()
    {
        for (Feedable eater : _eaters) {
            eater.eat();
        }
    }
}

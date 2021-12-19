package DIP.example;

/**
 * Au lieu de passer "un Lamp" on passe un "Equipment", on laisse le type
 * pour qu'il soit spécifier durant l'instanciation -> ceci nous permet de passer n'importe
 * quel type d'equipement au bouton, et de découpler la "Lamp" de "Button"
 */
public class Button
{
    private boolean _state;
    private Equipment _equipment;

    public Button(Equipment equipment)
    {
        _equipment = equipment;
    }

    public void toggle()
    {
        _state = !_state;
        boolean buttonOn = _state;
        if (buttonOn) {
            _equipment.turnOn();
        } else {
            _equipment.turnOff();
        }
    }

}

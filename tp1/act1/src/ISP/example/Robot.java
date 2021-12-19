package ISP.example;

/**
 * le Robot n'a pas de méthode eat -> on applique ISP sur eat/feed
 */
public class Robot implements Workable{

    @Override
    public String work()
    {
        return "Robot works";
    }

}

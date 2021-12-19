package DIP.exercice;

public class DatabaseWriter implements IWriter{
    @Override
    public void write(String in) {
        MyDatabase database = new MyDatabase();
        database.write(in);
    }
}

package LSP.exercice;

public interface IDuck
{
    void quack() throws DuckException;

    void swim() throws DuckException;

    class DuckException extends Exception
    {
        public DuckException(String message)
        {
            super(message);
        }
    }
}

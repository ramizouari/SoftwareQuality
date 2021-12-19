package OCP.example;

public class Square extends Shape{
    private final double _side;

    public Square(double side)
    {
        _side = side;
    }

    public double getSide()
    {
        return _side;
    }

    @Override
    public double getArea() {
        return _side * _side;
    }
}

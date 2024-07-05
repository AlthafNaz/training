public class Square extends Shapes{
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea(){
        return side*side;
    }

    @Override
    public double calculatePerimeter(){
        return 4*side;
    }

    @Override
    void printProperties() {
        System.out.println("\nProperties:\nSide length = " + side);
    }
}

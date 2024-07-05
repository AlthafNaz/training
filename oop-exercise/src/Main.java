import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print("\nSelect a shape: \n" +
                    "1. Square\n" +
                    "2. Rectangle\n" +
                    "3. Circle\n" +
                    "4. Exit\n" +
                    "Enter Number: ");
            int choice = input.nextInt();

            Shapes shape = null;
            String type = "";

            switch (choice) {
                case 1 -> {
                    System.out.print("\nEnter the side length of the square: ");
                    double side = input.nextDouble();
                    shape = new Square(side);
                    type="Square";
                }

                case 2 -> {
                    System.out.print("\nEnter the height of the rectangle: ");
                    double height = input.nextDouble();
                    System.out.print("Enter the width of the rectangle: ");
                    double width = input.nextDouble();
                    shape = new Rectangle(height, width);
                    type = "Rectangle";
                }

                case 3 -> {
                    System.out.print("\nEnter the radius of the circle: ");
                    double radius = input.nextDouble();
                    shape = new Circle(radius);
                    type = "Circle";
                }

                case 4 -> continueProgram = false;

                default -> System.out.println("\nInvalid choice. Please select a valid option.");
            }
            if (shape != null) {
                printResults(type, shape);
            }
        }
    }

    public static void printResults(String type, Shapes shape) {
        System.out.println("\nShape: " + type);
        shape.printProperties();
        System.out.println("\nArea = " + shape.calculateArea());
        System.out.println("Perimeter = " + shape.calculatePerimeter());
    }
}
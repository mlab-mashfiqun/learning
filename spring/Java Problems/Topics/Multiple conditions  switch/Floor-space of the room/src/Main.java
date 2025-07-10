import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String type = scanner.nextLine();
        if (type.equals("triangle")) {
            double a  = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();
            double s = (a + b + c) / 2;
            double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
            System.out.println(area);
        }
        else if (type.equals("circle")) {
            double r =  scanner.nextDouble();
            double area = 3.14 * r * r;
            System.out.println(area);
        }
        else if (type.equals("rectangle")) {
            double a  = scanner.nextDouble();
            double b = scanner.nextDouble();
            double area = a * b;
            System.out.println(area);
        }
    }
}
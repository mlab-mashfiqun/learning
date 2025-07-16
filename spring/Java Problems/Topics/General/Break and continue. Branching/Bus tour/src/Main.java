import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        int n = scanner.nextInt();
        boolean crash = false;
        for (int i = 0; i < n; i++) {
            int bridge = scanner.nextInt();
            if (bridge <= height) {
                System.out.println("Will crash on bridge " + (i + 1));
                crash = true;
                break;
            }
        }
        if (!crash)
            System.out.println("Will not crash");
        scanner.close();
    }
}
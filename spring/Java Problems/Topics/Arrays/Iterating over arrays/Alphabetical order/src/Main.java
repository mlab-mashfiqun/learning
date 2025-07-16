import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        String[] strings = scanner.nextLine().split(" ");
        for (int i = 0; i < strings.length - 1; i++) {
            if (strings[i].compareTo(strings[i + 1]) > 0) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);
    }
}
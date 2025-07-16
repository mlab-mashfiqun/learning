import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int t = scanner.nextInt();
        int sum = 0;
        for (int num : arr) {
            if (num > t)
                sum += num;
        }
        System.out.println(sum);
    }
}
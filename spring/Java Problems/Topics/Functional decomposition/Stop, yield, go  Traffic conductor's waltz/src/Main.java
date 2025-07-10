import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currentState = scanner.nextInt();
        scanner.close();

        String nextState = getNextState(currentState);
        String action = getAction(currentState);

        System.out.println(nextState);
        System.out.println(action);
    }

    public static String getNextState(int currentState) {
        String nextState = "";
        switch (currentState) {
            case 0:
                nextState = "1";
                break;
            case 1:
                nextState = "2";
                break;
            case 2:
                nextState = "0";
                break;
        }
        return nextState;
    }

    public static String getAction(int currentState) {
        String nextState = "";
        switch (currentState) {
            case 0:
                nextState = "Change to Yellow";
                break;
            case 1:
                nextState = "Change to Green";
                break;
            case 2:
                nextState = "Change to Red";
                break;
        }
        return nextState;

    }
}
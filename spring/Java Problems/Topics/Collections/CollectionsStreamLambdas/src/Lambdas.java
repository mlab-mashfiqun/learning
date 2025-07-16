import java.util.ArrayList;
import java.util.List;
public class Lambdas {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Jahan");
        list.add("Mashfiq");
        list.add("Aritra");
        Thread t1 = new Thread(() -> {list.forEach(name -> System.out.println("Name: " + name));});
        t1.start();
    }
}
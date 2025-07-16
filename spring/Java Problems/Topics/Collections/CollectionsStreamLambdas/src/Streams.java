import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));

        List<Integer> modifiedNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(modifiedNumbers);

        long count = numbers.stream()
                .filter(n -> n > 5)
                .count();
        System.out.println("Count > 5: " + count);
    }

}
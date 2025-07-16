import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class Collections {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Mashfiq");
        list.add("Aritra");
        list.add("Jahan");
        System.out.println(list.get(1));
        System.out.println(list);

        Set<String> set = new HashSet<>();
        set.add("Mashfiq");
        set.add("Aritra");
        set.add("Mashfiq");
        System.out.println(set);

        Map<String, Integer> map = new HashMap<>();
        map.put("Mashfiq", 24);
        map.put("Aritra", 24);
        map.put("Jahan", 26);
        System.out.println(map);
    }
}
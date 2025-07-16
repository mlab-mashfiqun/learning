import java.util.Optional;

public class Optionals {
    public static Optional<String> findNameById(int id) {
        if (id == 1) {
            return Optional.of("Mashfiq");
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<String> name1 = findNameById(1);
        if (name1.isPresent()) {
            System.out.println("Found name: " + name1.get());
        } else {
            System.out.println("Name not found.");
        }

        Optional<String> name2 = findNameById(2);
        System.out.println("Name or default: " + name2.orElse("Default User"));

        // Using lambdas too
        String upperCaseName = findNameById(1)
                .map(name -> name.toUpperCase())
                .orElse("UNKNOWN");
        System.out.println(upperCaseName);

        String upperCaseNameNotFound = findNameById(3)
                .map(name -> name.toUpperCase())
                .orElse("UNKNOWN");
        System.out.println(upperCaseNameNotFound);
    }
}

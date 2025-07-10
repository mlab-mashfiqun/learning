class Util {
    // correct this method to avoid NPE
    public static void printLength(String name) {
        if (name == null) {
            return;
        }
        System.out.println(name.length());
    }
}
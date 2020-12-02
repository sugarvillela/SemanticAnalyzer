package erlog;

public class Erlog {
    public static void warn(String... text){
        System.out.println(String.join("\n", text));
    }
    public static void kill(String... text){
        System.out.println(String.join("\n", text));
        System.exit(0);
    }
}

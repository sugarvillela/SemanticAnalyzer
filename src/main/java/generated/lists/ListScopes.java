package generated.lists;

public class ListScopes {
    public static final int SCOPES = 0x14;

    public static final int ALL = 0x015;
    public static final int SENTENCE = 0x016;
    public static final int PHRASE = 0x017;
    public static final int WORD = 0x018;
    public static final int CHAR = 0x019;

    public static String categoryByRange (int index) {
        return "SCOPES";
    }
    public static String categoryByBaseIndex (int index) {
        return "SCOPES";
    }
    public static int baseIndexByRange (int index) {
        return 0x14;
    }
    public static int offset () {
        return 0x14;
    }
    public static boolean isTopScope (int index) {
        return index == 0x15;
    }
    public static boolean isBottomScope (int index) {
        return index == 0x019;
    }
}

package W4;

public class P4 {
    public static void main(String[] args) {
        System.out.println(reverseString("Hello, World!"));
    }
    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}

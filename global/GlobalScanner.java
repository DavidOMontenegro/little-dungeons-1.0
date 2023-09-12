package global;

import java.util.Scanner;

public class GlobalScanner {
    private static Scanner scanner = new Scanner(System.in);

    private GlobalScanner() {}

    public static String nextLine() {
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }
}
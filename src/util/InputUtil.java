package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String inputString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    // Input integer with validation
    public static int inputInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    // Input double with validation
    public static double inputDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực hợp lệ!");
            }
        }
    }

    // Input date with validation
    public static LocalDate inputDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                LocalDate date = LocalDate.parse(input, FORMATTER);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println(" Sai định dạng! Vui lòng nhập đúng (yyyy-MM-dd)");
            }
        }
    }
}

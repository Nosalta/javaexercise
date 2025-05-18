//package Utillities;
//
//import java.util.Scanner;
//import java.util.regex.Pattern;
//
//public class Validation {
//
//    // Email regex pattern
//    // Accepts formats like:
//    // - user@example.com
//    // - first.last@domain.co.uk
//    // - user123@subdomain.domain.org
//    private static final String EMAIL_PATTERN =
//            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//
//    // Phone regex pattern
//    // Accepts formats like:
//    // - (123) 456-7890
//    // - 123-456-7890
//    // - 123.456.7890
//    // - +1 123 456 7890
//    // - 0101-123123 (XXXX-XXXXXX format)
//    // - +44 123 456 7890
//    private static final String PHONE_PATTERN =
//            "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,4})[- .]?\\d{3,6}[- .]?\\d{3,6}$";
//
//    // Validates and retrieves an integer input.
//    public static int getValidInt(Scanner scanner, String prompt, int minValue, String errorMessage) {
//        System.out.println("0: <- Go back");
//        System.out.println(prompt);
//
//        try {
//            int value = Integer.parseInt(scanner.nextLine());
//            if (value == 0) {
//                return -1; // Special return value to indicate "go back"
//            }
//
//            if (value < minValue) {
//                System.out.println(errorMessage);
//                return -1;
//            }
//
//            return value;
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input. Please enter a valid number.");
//            return -1;
//        }
//    }
//
//    // Validates and retrieves a double input
//    public static double getValidDouble(Scanner scanner, String prompt, double minValue, String errorMessage) {
//        System.out.println("0: <- Go back");
//        System.out.println(prompt);
//
//        try {
//            double value = Double.parseDouble(scanner.nextLine());
//            if (value == 0) {
//                return -1.0; // Special return value to indicate "go back"
//            }
//
//            if (value < minValue) {
//                System.out.println(errorMessage);
//                return -1.0;
//            }
//
//            return value;
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input. Please enter a valid number.");
//            return -1.0;
//        }
//    }
//
//    // Validates and retrieves a string input
//    public static String getValidString(Scanner scanner, String prompt) {
//        System.out.println("0: <- Go back");
//        System.out.println(prompt);
//
//        String input = scanner.nextLine();
//        if (input.equals("0")) {
//            return null;
//        }
//
//        if (input.trim().isEmpty()) {
//            System.out.println("Input cannot be empty.");
//            return null;
//        }
//
//        return input;
//    }
//
//    // Validates and retrieves an email address
//    public static String getValidEmail(Scanner scanner, String prompt) {
//        System.out.println("0: <- Go back");
//        System.out.println(prompt);
//
//        String input = scanner.nextLine();
//        if (input.equals("0")) {
//            return null;
//        }
//
//        if (!isValidEmail(input)) {
//            System.out.println("Invalid email format. Please try again.");
//            return null;
//        }
//
//        return input;
//    }
//
//    // Validates and retrieves a phone number
//    public static String getValidPhoneNumber(Scanner scanner, String prompt) {
//        System.out.println("0: <- Go back");
//        System.out.println(prompt);
//
//        String input = scanner.nextLine();
//        if (input.equals("0")) {
//            return null;
//        }
//
//        if (!isValidPhoneNumber(input)) {
//            System.out.println("Invalid phone number format. Please try again.");
//            return null;
//        }
//
//        return input;
//    }
//
//    // Checks if the provided string is a valid email address
//    private static boolean isValidEmail(String email) {
//        if (email == null || email.isEmpty()) {
//            return false;
//        }
//        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
//    }
//
//    // Checks if the provided string is a valid phone number
//    private static boolean isValidPhoneNumber(String phoneNumber) {
//        if (phoneNumber == null || phoneNumber.isEmpty()) {
//            return false;
//        }
//        return Pattern.compile(PHONE_PATTERN).matcher(phoneNumber).matches();
//    }
//}
package com.mjc.school.controller.menu;

import java.util.Scanner;

public class Utils {
    public static Long readNumberFromUser(Scanner scanner) {
        return Long.parseLong(scanner.nextLine());
    }

    public static String readStringFromUser(Scanner scanner) {
        return scanner.nextLine();
    }
}

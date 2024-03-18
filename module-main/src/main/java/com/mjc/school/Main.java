package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.controller.menu.NewsMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NewsController newsController = new NewsControllerImpl();
        Scanner scanner = new Scanner(System.in);

        NewsMenu menu = new NewsMenu(newsController, scanner);

        menu.displayMenu();

        scanner.close();
    }
}

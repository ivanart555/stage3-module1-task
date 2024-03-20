package com.mjc.school.controller.menu;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.menu.impl.*;

import java.util.Scanner;

public class NewsMenu {
    private final Command[] commands;
    private final Scanner scanner;

    public NewsMenu(NewsController newsController, Scanner scanner) {
        this.scanner = scanner;
        commands = new Command[]{
                new CreateNewsCommand(newsController, scanner),
                new ReadAllNewsCommand(newsController),
                new ReadNewsByIdCommand(newsController, scanner),
                new UpdateNewsByIdCommand(newsController, scanner),
                new DeleteNewsByIdCommand(newsController, scanner)

        };
    }

    public void displayMenu() {
        System.out.println("News Management Menu:");
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + ". " + commands[i].getDescription());
        }
        System.out.println("0. Exit");

        handleUsersChoice();
    }

    public void executeCommand(int choice) {
        if (choice > 0 && choice <= commands.length) {
            commands[choice - 1].execute();
        } else {
            System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private void handleUsersChoice() {
        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) System.exit(0);

                executeCommand(choice);
            } catch (Exception e) {
                System.out.println("Invalid choice. Please select a valid option. " + e.getMessage());
            }
            displayMenu();
        }


    }
}

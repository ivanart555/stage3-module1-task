package com.mjc.school.controller.menu.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.menu.Command;
import com.mjc.school.controller.menu.Utils;

import java.util.Scanner;

public class DeleteNewsByIdCommand implements Command {
    private final NewsController newsController;
    private final Scanner scanner;

    public DeleteNewsByIdCommand(NewsController newsController, Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }


    @Override
    public void execute() {
        Long id;

        System.out.println("Please enter the news id to delete:");
        id = Utils.readNumberFromUser(scanner);

        if (newsController.deleteById(id)) {
            System.out.printf("News with id %d deleted.%n", id);
        }
    }

    @Override
    public String getDescription() {
        return "Delete News by id";
    }
}
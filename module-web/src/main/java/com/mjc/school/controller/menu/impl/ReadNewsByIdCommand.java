package com.mjc.school.controller.menu.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.menu.Command;
import com.mjc.school.controller.menu.Utils;

import java.util.Scanner;

public class ReadNewsByIdCommand implements Command {
    private final NewsController newsController;
    private final Scanner scanner;

    public ReadNewsByIdCommand(NewsController newsController, Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Long id;

        System.out.println("Please enter the news id:");
        id = Utils.readNumberFromUser(scanner);

        System.out.println(newsController.readById(id));
    }

    @Override
    public String getDescription() {
        return "Read news by id";
    }
}

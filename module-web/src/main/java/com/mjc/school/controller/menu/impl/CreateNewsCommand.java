package com.mjc.school.controller.menu.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.menu.Command;
import com.mjc.school.controller.menu.Utils;
import com.mjc.school.service.dto.NewsDto;

import java.util.Scanner;

public class CreateNewsCommand implements Command {
    private final NewsController newsController;
    private final Scanner scanner;

    public CreateNewsCommand(NewsController newsController, Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        String title;
        String content;
        Long authorId;

        System.out.println("Please enter the news title:");
        title = Utils.readStringFromUser(scanner);

        System.out.println("Please enter the news content:");
        content = Utils.readStringFromUser(scanner);

        System.out.println("Please enter the author's ID:");
        authorId = Utils.readNumberFromUser(scanner);

        NewsDto newsDto = new NewsDto(title, content, authorId);

        System.out.println(newsController.create(newsDto));
    }

    @Override
    public String getDescription() {
        return "Create News";
    }
}

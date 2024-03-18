package com.mjc.school.controller.menu.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.menu.Command;
import com.mjc.school.controller.menu.Utils;
import com.mjc.school.service.dto.NewsDto;

import java.util.Scanner;

public class UpdateNewsByIdCommand implements Command {
    private final NewsController newsController;
    private final Scanner scanner;

    public UpdateNewsByIdCommand(NewsController newsController, Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Long id;
        String title;
        String content;
        Long authorId;

        System.out.println("Please enter the news id to edit:");
        id = Utils.readNumberFromUser(scanner);

        System.out.println("Please enter new news title:");
        title = Utils.readStringFromUser(scanner);

        System.out.println("Please enter new the news content:");
        content = Utils.readStringFromUser(scanner);

        System.out.println("Please enter new the author's ID:");
        authorId = Utils.readNumberFromUser(scanner);

        NewsDto newsDto = new NewsDto(id, title, content, authorId);

        System.out.println(newsController.update(newsDto));
    }

    @Override
    public String getDescription() {
        return "Edit news by id";
    }
}
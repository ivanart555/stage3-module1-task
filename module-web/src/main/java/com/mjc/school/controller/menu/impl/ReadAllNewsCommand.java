package com.mjc.school.controller.menu.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.menu.Command;

public class ReadAllNewsCommand implements Command {
    private final NewsController newsController;

    public ReadAllNewsCommand(NewsController newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute() {
        newsController.readAll().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Read all News";
    }
}

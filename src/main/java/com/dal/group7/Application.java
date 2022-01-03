package com.dal.group7;

import com.dal.group7.view.implementations.HomeCommand;

public class Application {

    public static void main(String[] args) {
        final HomeCommand homeCommand = new HomeCommand();
        homeCommand.execute();
    }
}

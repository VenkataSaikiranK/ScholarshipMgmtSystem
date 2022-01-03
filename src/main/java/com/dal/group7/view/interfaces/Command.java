package com.dal.group7.view.interfaces;

import java.util.Scanner;

public abstract class Command {
    protected Command nextCommand;
    protected final Scanner scanner = new Scanner(System.in);

    public void execute() {
        printView();
        handle();
        setNextCommand();
        nextCommand.execute();
    }

    public abstract void printView();
    public abstract void handle();
    public abstract void setNextCommand();



}

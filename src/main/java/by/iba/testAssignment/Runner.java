package by.iba.testAssignment;

import by.iba.testAssignment.command.Command;
import by.iba.testAssignment.command.CommandFactory;


import java.util.Scanner;


public class Runner {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a command: ");
        String input = in.nextLine();
        String [] parsedCommand = input.split(" ", 2);

        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.build(parsedCommand[0]);
        command.execute(parsedCommand[1]);
    }
}

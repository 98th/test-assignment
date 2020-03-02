package by.iba.testAssignment;

import by.iba.testAssignment.command.Command;
import by.iba.testAssignment.command.CommandFactory;

import java.util.Scanner;


public class Runner {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Parameter flag: ");
        String flag = in.nextLine();
        System.out.println("Flag value: ");
        String value = in.nextLine();

        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.build(flag);
        command.execute(value);
    }

}

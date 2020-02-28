package by.iba.testAssignment.command;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class CmdCommand implements Command {
    private static final Logger log = LogManager.getLogger(CmdCommand.class);

    @Override
    public void execute(String value) {

    }

    //TODO
    public Map<String, String> read () {
        String command = "";
        Map<String, String> output = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            command = reader.readLine();
        } catch (IOException e) {
        String [] commandArray = command.split(" ");
        output.put(commandArray[0], commandArray[1]);
            return output;
        }
        return null;
    }
}

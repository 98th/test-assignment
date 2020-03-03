package by.iba.testAssignment.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.iba.testAssignment.ApplicationConstants.*;


public class CommandFactory {
    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    public Command build(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is empty");
        }
        switch (name) {
            case FILE_COMMAND:
                return new FileReaderCommand();
            case CMD_COMMAND:
                return new CmdCommand();
            case REGISTRY_KEY_COMMAND:
                return new RegistryKeyCommand();
            default:
                log.error("Failed to get command. Invalid type " + name);
                throw new IllegalArgumentException();
        }
    }
}

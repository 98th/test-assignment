package by.iba.testAssignment.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.iba.testAssignment.ApplicationConstants.*;

public class CommandFactory {
    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    public Command build(String name) {
        switch (name) {
            case FILE_READER:
                return new FileReaderCommand();
            case CMD_READER:
                return new CmdCommand();
            case REGISTRY_KEY_READER:
                return new RegistryKeyCommand();
            default:
                log.error("Failed to get command. Wrong type " + name);
                throw new IllegalArgumentException();
        }
    }
}

package by.iba.testAssignment.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandFactory {
    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    public Command build(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is empty");
        }
        Optional<CommandType> commandTypeOptional = CommandType.fromString(name);
        if (!commandTypeOptional.isPresent()) {
            throw new IllegalArgumentException("Failed to get command. Invalid type.");
        }
        CommandType commandType = commandTypeOptional.get();
        switch (commandType) {
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

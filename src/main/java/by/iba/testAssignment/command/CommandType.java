package by.iba.testAssignment.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum  CommandType {
    FILE_COMMAND ("-f"),
    CMD_COMMAND("-cmd"),
    REGISTRY_KEY_COMMAND ("-rk");

    private String value;

     CommandType(String value) {
        this.value = value;
    }

    public static Optional<CommandType> fromString(String type) {
        return Stream.of(CommandType.values())
                .filter(i -> i.value.equalsIgnoreCase(type))
                .findFirst();
    }
}

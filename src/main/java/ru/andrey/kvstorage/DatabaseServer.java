package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if(commandText == null)
            return DatabaseCommandResult.error("Command is null.");
        String[] commandWords = commandText.split(" ");
        try {
            return DatabaseCommands
                    .valueOf(commandWords[0])
                    .getCommand(env, Arrays.copyOfRange(commandWords, 1, commandWords.length))
                    .execute();
        } catch (IllegalArgumentException ex) {
            return DatabaseCommandResult.error(String.format("Command \"%s\" doesn't exist.", commandWords[0]));
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }
    }
}

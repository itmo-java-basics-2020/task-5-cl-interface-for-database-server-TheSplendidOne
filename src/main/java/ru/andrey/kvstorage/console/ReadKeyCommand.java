package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;

    public ReadKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String key) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if(database.isPresent())
            try {
                return DatabaseCommandResult.success(database.get().read(tableName, key));
            }
            catch (DatabaseException e) {
                return DatabaseCommandResult.error(e.getMessage());
            }
        return DatabaseCommandResult.error(String.format("Database with name \"%s\" doesn't exist", databaseName));
    }
}

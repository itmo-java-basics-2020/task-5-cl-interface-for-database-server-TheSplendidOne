package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    public UpdateKeyCommand(ExecutionEnvironment environment, String databaseName, String tableName, String key, String value) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if(database.isPresent())
            try {
                database.get().write(tableName, key, value);
                return DatabaseCommandResult.success(String.format(
                        "Now key \"%s\" has value \"%s\" in table \"%s\" in database \"%s\".",
                        key, value, tableName, databaseName));
            } catch (DatabaseException ex) {
                return DatabaseCommandResult.error(ex.getMessage());
            }
        return DatabaseCommandResult.error(String.format("Database with name \"%s\" doesn't exist.", databaseName));
    }
}

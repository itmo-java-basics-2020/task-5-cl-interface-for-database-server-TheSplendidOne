package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand{
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;

    public CreateTableCommand(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if(database.isPresent())
        {
            try {
                database.get().createTableIfNotExists(tableName);
                return DatabaseCommandResult.success(
                        String.format("Table \"%s\" in database \"%s\" is created successfully.", tableName, databaseName));
            } catch (DatabaseException ex) {
                return DatabaseCommandResult.error(ex.getMessage());
            }
        }
        return DatabaseCommandResult.error(String.format("Database \"%s\" doesn't exist.", databaseName));
    }
}

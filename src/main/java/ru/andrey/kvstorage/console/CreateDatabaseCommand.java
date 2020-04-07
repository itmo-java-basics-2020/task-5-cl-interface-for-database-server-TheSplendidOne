package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.DummyDatabase;

public class CreateDatabaseCommand implements DatabaseCommand {
    private final ExecutionEnvironment environment;
    private final String databaseName;

    public CreateDatabaseCommand(ExecutionEnvironment environment, String databaseName)
    {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute(){
        if(environment.getDatabase(databaseName).isPresent())
            return DatabaseCommandResult.error(String.format("Database with name \"%s\" already exists.", databaseName));
        environment.addDatabase(new DummyDatabase(databaseName));
        return DatabaseCommandResult.success(String.format("Database \"%s\" is created successfully.", databaseName));
    }
}

package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public enum DatabaseCommands {
    CREATE_DATABASE {
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) throws DatabaseException {
            if(args.length != 1)
                throw new DatabaseException("Command takes only one argument.");
            return new CreateDatabaseCommand(environment, args[0]); }},
    CREATE_TABLE {
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) throws DatabaseException {
            if(args.length != 2)
                throw new DatabaseException("Command takes only two arguments.");
            return new CreateTableCommand(environment, args[0], args[1]); }},
    UPDATE_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) throws DatabaseException {
            if(args.length != 4)
                throw new DatabaseException("Command takes only four arguments.");
            return new UpdateKeyCommand(environment, args[0], args[1], args[2], args[3]); }},
    READ_KEY {
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) throws DatabaseException {
            if(args.length != 3)
                throw new DatabaseException("Command takes only three arguments.");
            return new ReadKeyCommand(environment, args[0], args[1], args[2]); }};

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) throws DatabaseException;
}

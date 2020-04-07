package ru.andrey.kvstorage.console;

import javax.xml.transform.Result;
import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    static DatabaseCommandResult success(String result)
    {
        return new DefaultResult(result, null, true, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String message)
    {
        return new DefaultResult(null, message, false, DatabaseCommandStatus.FAILED);
    }

    class DefaultResult implements DatabaseCommandResult {
        private String result;
        private String message;
        private boolean isSuccess;
        private DatabaseCommandStatus status;

        DefaultResult(String result, String message, boolean isSuccess, DatabaseCommandStatus status) {
            this.result = result;
            this.message = message;
            this.isSuccess = isSuccess;
            this.status = status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return isSuccess;
        }

        @Override
        public String getErrorMessage() {
            return message;
        }
    }

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }
}
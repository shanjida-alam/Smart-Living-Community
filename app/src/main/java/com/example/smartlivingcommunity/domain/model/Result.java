package com.example.smartlivingcommunity.domain.model;

/**
 * A generic class that represents the result of an operation.
 * It can either be a success or an error.
 * @author Irtifa
 * @param <T> The type of data associated with the result.
 */
public abstract class Result<T> {
    /**
     * Protected constructor to prevent direct instantiation of the abstract class.
     */
    protected Result() {}

    /**
     * Represents a successful operation result, holding the resulting data.
     *
     * @param <T> The type of the successful result data.
     */
    public static class Success<T> extends Result<T> {
        private final T data; // The data associated with the successful result

        /**
         * Constructor for creating a success result.
         *
         * @param data The data associated with the success.
         */
        public Success(T data) {
            this.data = data;
        }

        /**
         * Returns the data associated with the success result.
         *
         * @return The data of type T.
         */
        public T getData() {
            return data;
        }
    }

    /**
     * Represents an error result, holding an error message.
     *
     * @param <T> The type of the result, even if it's an error.
     */
    public static class Error<T> extends Result<T> {
        private final String errorMessage; // The error message describing the failure

        /**
         * Constructor for creating an error result.
         *
         * @param errorMessage The error message associated with the failure.
         */
        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        /**
         * Returns the error message associated with the error result.
         *
         * @return The error message as a String.
         */
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}

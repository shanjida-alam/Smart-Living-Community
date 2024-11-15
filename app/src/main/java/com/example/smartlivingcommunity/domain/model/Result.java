package com.example.smartlivingcommunity.domain.model;

public abstract class Result<T> {
    private Result() {} // Private constructor to prevent direct instantiation

    public abstract boolean isSuccess();
    public abstract T getData();
    public abstract String getError();

    public static final class Success<T> extends Result<T> {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public String getError() {
            return null;
        }
    }

    public static final class Error<T> extends Result<T> {
        private final String error;

        public Error(String error) {
            this.error = error;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public T getData() {
            return null;
        }

        @Override
        public String getError() {
            return error;
        }
    }

    public static <T> Result<T> success(T data) {
        return new Success<>(data);
    }

    public static <T> Result<T> error(String error) {
        return new Error<>(error);
    }
}
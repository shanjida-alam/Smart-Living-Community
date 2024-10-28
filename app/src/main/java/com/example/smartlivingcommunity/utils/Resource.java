package com.example.smartlivingcommunity.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A generic class representing a resource with a status, data, and message.
 * This class is useful for handling data responses in different states (e.g., success, error, loading),
 * commonly used in ViewModel and LiveData components for effective state management.
 *
 * @param <T> the type of data the resource holds
 */
public class Resource<T> {

    /** The status of the resource, indicating success, error, or loading */
    @NonNull
    public final Status status;

    /** The data associated with the resource, if any */
    @Nullable
    public final T data;

    /** An optional message providing additional information about the resource state */
    @Nullable
    public final String message;

    /**
     * Private constructor for creating a Resource with a specified status, data, and message.
     *
     * @param status  the status of the resource
     * @param data    the data associated with the resource, if any
     * @param message a message providing details about the resource state
     */
    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /**
     * Creates a Resource instance representing a successful state.
     *
     * @param data the data associated with the success state
     * @param <T>  the type of the data
     * @return a Resource with status SUCCESS, containing the provided data
     */
    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    /**
     * Creates a Resource instance representing an error state.
     *
     * @param msg  an error message providing details about the failure
     * @param data the data associated with the error state, if any
     * @param <T>  the type of the data
     * @return a Resource with status ERROR, containing the provided data and message
     */
    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    /**
     * Creates a Resource instance representing a loading state.
     *
     * @param data the data associated with the loading state, if any
     * @param <T>  the type of the data
     * @return a Resource with status LOADING, containing the provided data
     */
    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    /**
     * Enum representing the status of a Resource, indicating whether the resource
     * is in a success, error, or loading state.
     */
    public enum Status {
        SUCCESS, ERROR, LOADING
    }
}

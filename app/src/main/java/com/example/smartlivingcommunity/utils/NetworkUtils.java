package com.example.smartlivingcommunity.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

/**
 * @author solaimi
 * Utility class to check the network connectivity status of the device.
 * This class provides methods to check if the device has an active network connection.
 */
public class NetworkUtils {

    /** The application context used to access system services */
    private final Context context;

    /**
     * Constructor to initialize the NetworkUtils object with the application context.
     *
     * @param context The application context to access system services.
     */
    public NetworkUtils(Context context) {
        this.context = context;
    }

    /**
     * Checks whether the device has an active network connection.
     *
     * @return true if there is a network connection (WiFi, Cellular, or Ethernet), false otherwise.
     */
    public boolean isNetworkAvailable() {
        // Get the connectivity manager system service
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // If the connectivity manager is null, return false as network cannot be checked
        if (connectivityManager == null) return false;

        // Get the network capabilities for the active network
        NetworkCapabilities capabilities = connectivityManager
                .getNetworkCapabilities(connectivityManager.getActiveNetwork());

        // Return true if there is an active network and it has any of the supported transports
        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        );
    }
}

package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartlivingcommunity.data.model.SecurityLog;
import com.example.smartlivingcommunity.data.repository.SecurityLogRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel responsible for managing the security log data and validating security logs.
 * It acts as a bridge between the SecurityLogRepository and the UI components.
 *
 * @author Saon
 */
public class SecurityLogViewModel extends ViewModel {

    private final List<SecurityLog> securityLogs = new ArrayList<>();
    private final SecurityLogRepository repository;
    private final LiveData<List<SecurityLog>> logs;

    /**
     * Constructor that initializes the repository and retrieves security log data.
     */
    public SecurityLogViewModel() {
        repository = new SecurityLogRepository();
        logs = repository.getSecurityLogs();
    }


    /**
     * Adds a new {@link SecurityLog} to the internal list of logs.
     *
     * @param log the security log to be added
     */
    public void addSecurityLog(SecurityLog log) {
        securityLogs.add(log);
    }

    /**
     * Retrieves a copy of the internal list of security logs.
     *
     * @return a new list containing the security logs
     */
    public List<SecurityLog> getSecurityLogs() {
        return new ArrayList<>(securityLogs);
    }

    /**
     * Validates a given {@link SecurityLog} to ensure that its data is correct.
     * Checks for non-null fields, correct date and time formats, and logical validity.
     *
     * @param log the security log to validate
     * @return true if the log is valid; false otherwise
     */
    public boolean isValidSecurityLog(SecurityLog log) {

        if (log == null ||
                log.getVisitorName() == null ||
                log.getVisitorType() == null ||
                log.getEntryTime() == null ||
                log.getExitTime() == null ||
                log.getEntryDate() == null ||
                log.getExitDate() == null) {
            return false;
        }

        if (log.getExitTime() != null && log.getEntryTime() == null) {
            return false;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(log.getEntryDate());
            dateFormat.parse(log.getExitDate());

            if (dateFormat.parse(log.getEntryDate()).after(dateFormat.parse(log.getExitDate()))) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormat.setLenient(false);
            timeFormat.parse(log.getEntryTime());
            timeFormat.parse(log.getExitTime());
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Returns a LiveData object containing a list of security logs.
     *
     * @return LiveData object with the security logs
     */
    public LiveData<List<SecurityLog>> getLogs() {
        return logs;
    }

}

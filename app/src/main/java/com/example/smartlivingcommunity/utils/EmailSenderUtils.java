package com.example.smartlivingcommunity.utils;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import android.content.Context;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Utility class for sending emails.
 * This class provides a method to send an email asynchronously using JavaMail.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 */
public class EmailSenderUtils {

    /**
     * Sends an email to the specified recipient.
     *
     * @param recipientEmail The email address of the recipient.
     * @param unitCode      The unique unit code to include in the email body.
     * @param context       The context from which this method is called, used for displaying Toast messages.
     */
    public static void sendEmail(String recipientEmail, String unitCode, Context context) {
        // ExecutorService to run the email sending task in a background thread.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // Handler to post messages to the main thread for UI updates.
        Handler mainHandler = new Handler(Looper.getMainLooper());

        // Execute the email sending task.
        executorService.execute(() -> {
            // Sender's email credentials.
            final String username = "livsmart29@gmail.com"; // Sender's email address.
            final String password = "lmsq esxi hutf bhsl";  // App password or generated password for the email account.

            // Setup mail server properties.
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true"); // Enable authentication.
            props.put("mail.smtp.starttls.enable", "true"); // Enable TLS.
            props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host for Gmail.
            props.put("mail.smtp.port", "587"); // SMTP port for TLS.

            // Initialize session with the provided properties and authentication.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password); // Authenticate with username and password.
                        }
                    });

            try {
                // Create a new email message.
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username)); // Set the sender's email address.
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Set the recipient's email address.
                message.setSubject("Welcome to LivSmart : A Smart Living Community"); // Set the subject of the email.

                // Create the email body with HTML content.
                String emailContent = "<h1>Welcome to LivSmart : A Smart Living Community</h1>"
                        + "<p>Your registration is successful!</p>"
                        + "<p>Your unit code is: <span style='background-color: lightgray;'>" + unitCode + "</span></p>"
                        + "<p>Thank you for choosing our community.</p>";

                // Set the email content type to HTML.
                message.setContent(emailContent, "text/html; charset=utf-8");

                // Send the email message.
                Transport.send(message);

                // Display a Toast message on the main thread after successfully sending the email.
                mainHandler.post(() ->
                        Toast.makeText(context, "Registration successful! Check your e-mail", Toast.LENGTH_LONG).show()
                );

                System.out.println("Email sent successfully!"); // Log success message.

            } catch (MessagingException e) {
                e.printStackTrace(); // Print the stack trace for any messaging exceptions.
                // Display a Toast message on the main thread if sending email fails.
                mainHandler.post(() ->
                        Toast.makeText(context, "Failed to send email.", Toast.LENGTH_LONG).show()
                );
            } finally {
                executorService.shutdown(); // Shutdown the executor service to free up resources.
            }
        });
    }
}


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
 * Utility class for sending emails asynchronously.
 * This class leverages JavaMail to send an email with an HTML-formatted body in a background thread.
 * It allows for sending an email with user registration details and displays success or failure notifications to the user.
 *
 * @author Hasneen Tamanna Totinee
 * @version 1.0
 * @since 2024-10-30
 */
public class EmailSenderUtils {

    /**
     * Sends a registration email to the specified recipient with an HTML-formatted message.
     * This method runs the email-sending operation asynchronously using an ExecutorService to avoid
     * blocking the main thread and uses a Handler to show success or failure messages on the UI.
     *
     * @param recipientEmail  The email address of the recipient to whom the email is sent.
     * @param userNameMessage The user's name message to include in the email content.
     * @param unitCodeMessage The unique unit code message to include in the email content.
     * @param context         The context from which this method is called, used for displaying Toast messages to the user.
     */
    public static void sendEmail(String recipientEmail, String userNameMessage, String unitCodeMessage, Context context) {
        // ExecutorService to handle the email sending operation in a background thread.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Handler to post messages to the main thread for displaying Toast messages.
        Handler mainHandler = new Handler(Looper.getMainLooper());

        // Execute the email-sending operation asynchronously.
        executorService.execute(() -> {
            // Sender's email credentials.
            final String username = "livsmart29@gmail.com"; // Sender's email address.
            final String password = "lmsq esxi hutf bhsl";  // Application-specific password for the email account.

            // Mail server properties configuration.
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true"); // Enables SMTP authentication.
            props.put("mail.smtp.starttls.enable", "true"); // Enables STARTTLS.
            props.put("mail.smtp.host", "smtp.gmail.com"); // Specifies the Gmail SMTP server.
            props.put("mail.smtp.port", "587"); // Specifies the TLS port.

            // Initialize the email session with SMTP properties and authentication details.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password); // Provides the sender's email credentials.
                        }
                    });

            try {
                // Create a new email message.
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username)); // Sets the sender's email address.
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Sets the recipient's email address.
                message.setSubject("Welcome to LivSmart : A Smart Living Community"); // Sets the subject of the email.

                // Constructs the HTML email content using the provided user and unit code messages.
                String emailContent = "<h1>Welcome to LivSmart : A Smart Living Community</h1>"
                        + userNameMessage
                        + "<p>Your registration is successful!</p>"
                        + unitCodeMessage
                        + "<p>Thank you for choosing our community.</p>";

                // Sets the content type to HTML with UTF-8 encoding.
                message.setContent(emailContent, "text/html; charset=utf-8");

                // Sends the email message using the configured SMTP session.
                Transport.send(message);

                // Posts a success Toast message on the main thread after email is sent.
                mainHandler.post(() ->
                        Toast.makeText(context, "Registration successful! Check your e-mail", Toast.LENGTH_LONG).show()
                );

                System.out.println("Email sent successfully!"); // Logs a success message.

            } catch (MessagingException e) {
                e.printStackTrace(); // Logs the exception stack trace for troubleshooting.

                // Posts a failure Toast message on the main thread if the email sending fails.
                mainHandler.post(() ->
                        Toast.makeText(context, "Failed to send email.", Toast.LENGTH_LONG).show()
                );
            } finally {
                // Shuts down the ExecutorService to release resources after task completion.
                executorService.shutdown();
            }
        });
    }
}



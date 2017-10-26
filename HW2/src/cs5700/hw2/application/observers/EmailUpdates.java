package cs5700.hw2.application.observers;

import cs5700.hw2.application.tools.CalculateInfo;
import cs5700.hw2.application.subjects.Athlete;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUpdates implements IAthleteObserver {
    private CalculateInfo calculator;
    private String toEmail;
    private int minuteDelay;
    private final String user = "nothingReallyMattersButMwah@gmail.com";
    private final String password = "BohemianRhapsody";

    public EmailUpdates(String toEmail, int minuteDelay) {
        this.minuteDelay = minuteDelay;
        this.toEmail = toEmail;
        this.calculator = CalculateInfo.getInstance();
    }

    @Override
    public void update(Athlete athlete) {

        int key = athlete.getBibNumber();
        if (athletesObserved.get(key) == null) {
            athletesObserved.put(key, athlete);
        }

        checkNeedForUpdate(athlete);

        if (sendUpdate.get(key)) {
            sendEmail(setTitle(athlete), setMessage(athlete));
            System.out.println("Sent Email for Athlete # " + athlete.getBibNumber());
        }
    }

    private void sendEmail(String title, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            message.setSubject(title);
            message.setText(body);


            Transport.send(message);

        }catch (MessagingException mex) {mex.printStackTrace();}
    }

    private String setTitle(Athlete athlete) {
        return "Race Update: #" + athlete.getBibNumber() + " " + athlete.getFirstName() + " " + athlete.getLastName();
    }

    private String setMessage(Athlete athlete) {
        String message =
                "Name: " + athlete.getFirstName() + " " + athlete.getLastName() + "\n" +
                "ID: " + athlete.getBibNumber() + "\n" +
                "Race Length: " + calculator.getRaceMiles() + " miles\n";
        if (athlete.isDidNotStart()) {
            message += "DID NOT COMPETE\n";
        } else {
            message +=
                    "Start Time: " + athlete.getOfficialStartTime().toLocalTime() + "\n" +
                    String.format("Distance: %.2f miles\n", calculator.metersToMiles(athlete.getLocationOnCourse())) +
                    "Status: ";
            if (athlete.isQuitRace()) {
                message +=
                        "DID NOT FINISH\n" +
                        "Quit Time: " + athlete.getTimeStamp().toLocalTime() + "\n";
            } else if (!athlete.isFinished()) {
                message +=
                        "RACING\n" +
                        "Current Time: " + athlete.getTimeStamp().toLocalTime() + "\n";
            } else {
                message +=
                        "FINISHED\n" +
                        "Official End Time: " + athlete.getOfficialStopTime().toLocalTime() + "\n";
            }
            long[] time = calculator.getElapsedTime(athlete);
            message += String.format("Elapsed Time: %d:%02d:%02d", time[0], time[1], time[2]);
        }
        return message;
    }

    private Boolean exceedsTimeDelay(Athlete a) {
        double deltaSeconds = calculator.getTotalSeconds(calculator.getElapsedTime(a));
        return deltaSeconds > 0 && deltaSeconds % (minuteDelay * 60) == 0;
    }

    private void checkNeedForUpdate(Athlete a) {
        int key = a.getBibNumber();
        if (sendUpdate.get(key) == null) {
            sendUpdate.put(key, true);
            return;
        }
        sendUpdate.remove(key);
        if (a.isDidNotStart() || a.isQuitRace() || a.isFinished()) {
                sendUpdate.put(key, true);
        } else {

            if (exceedsTimeDelay(a)) {
                sendUpdate.put(key, true);
            } else {
                sendUpdate.put(key, false);
            }
        }
    }

}

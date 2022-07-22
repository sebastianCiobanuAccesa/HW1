package logic;

import communication.Client;
import communication.Server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logic implements Runnable {
    private final String ERROR_MESSAGE = "ERROR! Try again later!";
    private final String LOG_FILE = "logs.txt";

    public void run() {
        String message = null;
        Client client = new Client(new Server());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            double currencyExchange = client.call();
            message = timestamp + " OK! " + currencyExchange + "\n";
        } catch (Exception e) {
            message = timestamp + " " + ERROR_MESSAGE + "\n";
        } finally {
            writeMessage(message);
        }
    }

    private void writeMessage(String message) {
        System.out.print(message);

        try (BufferedWriter output = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            output.append(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

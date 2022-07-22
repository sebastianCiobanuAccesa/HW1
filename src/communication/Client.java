package communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Client {
    private final Server server;
    private final int ROW_INDEX = 2;

    public Client(Server server) {
        this.server = server;
    }

    public Double call() throws Exception {
        Double currencyExchange = null;
        URL url = new URL(server.ADDRESS);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try (AutoCloseable conc = con::disconnect;
             BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
//        int status = con.getResponseCode();
            String inputLine;
            int counter = 0;
            while ((inputLine = in.readLine()) != null) {
                if (counter == ROW_INDEX) {
                    String[] keyValue = inputLine.split(": ");
                    currencyExchange = Double.parseDouble(keyValue[1]);
                }
                ++counter;
            }
        }
        return currencyExchange;
    }
}

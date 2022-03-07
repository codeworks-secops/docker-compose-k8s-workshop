import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/noun", handler(() -> randomWord("nouns")));
        server.createContext("/verb", handler(() -> randomWord("verbs")));
        server.createContext("/adjective", handler(() -> randomWord("adjectives")));
        server.start();
    }

    private static String randomWord(String table) {
        final String jdbcUrl = "jdbc:postgresql://" + System.getenv("POSTGRES_HOST_SERVICE") + ":5432/postgres";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, "postgres", "pass")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet set = statement.executeQuery("SELECT word FROM " + table + " ORDER BY random() LIMIT 1")) {
                    while (set.next()) {
                        return set.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new NoSuchElementException(table);
    }

    private static HttpHandler handler(Supplier<String> word) {
        return t -> {
            String response = "{\"word\":\"" + word.get() + "\"}";
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

            System.out.println(response);

            t.getResponseHeaders().add("content-type", "application/json; charset=utf-8");
            t.getResponseHeaders().add("cache-control", "private, no-cache, no-store, must-revalidate, max-age=0");
            t.getResponseHeaders().add("pragma", "no-cache");

            t.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = t.getResponseBody()) {
                os.write(bytes);
            }
        };
    }
}
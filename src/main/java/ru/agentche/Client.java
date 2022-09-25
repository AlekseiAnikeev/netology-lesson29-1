package ru.agentche;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 24.09.2022
 */
public class Client implements Runnable {
    private static final int PORT = 8080;
    private static final String SERVER_IP = "localhost";

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try (Socket clientSocket = new Socket(SERVER_IP, PORT);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                System.out.println("\nКлиент:\n");
                System.out.println("Введи имя для передачи на сервер");
                String name = scanner.nextLine();
                if ("end".equals(name)) {
                    System.out.println("Клиент остановлен");
                    out.println(name);
                    break;
                }
                out.println(name);
                System.out.println(in.readLine());
            } catch (
                    IOException  e) {
                throw new RuntimeException(e);
            }
        }
    }
}

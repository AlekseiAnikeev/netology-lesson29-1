package ru.agentche;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 24.09.2022
 */
public class Server implements Runnable {
    private static final int PORT = 8080;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен и ждет подключения");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String name = in.readLine();
                    if("end".equals(name)){
                        System.out.println("Сервер остановлен");
                        break;
                    }
                    System.out.println("\nОтвет от сервера:\n");
                    out.println(String.format("Привет %s, твой порт: %d", name, clientSocket.getPort()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

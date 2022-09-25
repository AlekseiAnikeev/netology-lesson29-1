package ru.agentche;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 24.09.2022
 */
public class Main {
    public static void main(String[] args) {
        Thread server = new Thread(new Server());
        server.start();
        Thread client = new Thread(new Client());
        client.start();
    }
}

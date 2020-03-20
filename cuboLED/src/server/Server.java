package server;

import java.io.*;
import java.net.Socket;

public class Server extends Thread{
    private Socket socket;
    private final int port = 8080;
    private BufferedReader input;
    private BufferedWriter output;

    public Server() {

    }

    public void run() {
        this.Connect();
    }

    public void POST(String msg) throws IOException {
        post_aux(msg);
    }

    private void post_aux(String msg) throws IOException {
        this.output = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        System.out.println("Sending Message...");
        this.output.write(msg + "\n");
        this.output.flush();
    }

    public void GET() {
        try {
            GET_aux();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void GET_aux() throws IOException {
        while(true){
            try {
                this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                System.out.println("Client response: " + this.input.readLine());
            }finally {
                this.socket.close();
            }
        }
    }
    public void Connect(){
        try {
            try {
                socket = new Socket("192.168.43.70", 8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket.setKeepAlive(true);
            System.out.println("Client Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
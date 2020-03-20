package server;

public class ServerThread  {
    public static Server server;

    public static void main(String[] args) {

    }
    public static void initServer(){
        server = new Server();
        server.Connect();
        server.start();
    }
}
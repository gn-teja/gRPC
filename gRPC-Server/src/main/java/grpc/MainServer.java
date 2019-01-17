package grpc;

import grpc.unary.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.logging.Logger;

public class MainServer {

    private static final Logger logger = Logger.getLogger(MainServer.class.getName());

    private Server server;

    // pass the port on which the server would run
    private void run(int port) throws IOException {

        logger.info("Initializing gRPC server ...");
        server = ServerBuilder.forPort(port)
                .addService(new GreetingServiceImpl())
                .build()
                .start();
        logger.info("MainServer initialized successfully.. listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            MainServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if(server != null){
            server.shutdown();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final  MainServer main = new MainServer();

        int port = 16004;
        //Start server
        main.run(port);

        // wait till the server is shutdown
        main.server.awaitTermination();

    }
}

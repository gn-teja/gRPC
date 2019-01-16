package grpc;

import io.grpc.ServerBuilder;
import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Initializing gRPC grpc.Server ...");

        io.grpc.Server server = ServerBuilder.forPort(16002)
                .addService(new GreetingServiceImpl())
                .build();

        server.start();

        System.out.println("Waiting for request ...");

        Runtime.getRuntime().addShutdownHook(
                new Thread(()-> {
                    System.out.println("Received Shutdown");
                    server.shutdown();
                    System.out.println("grpc.Server shutdown sucessful");
                }));

        server.awaitTermination();
    }
}

package grpc;

import grpc.unary.GreetingClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

public class Client {

    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) throws InterruptedException {

        logger.info("Initializing gRPC Client ...");

        Client main = new Client();
        main.run();
    }

    private void run () throws InterruptedException {

        logger.info("Creating stub ...");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",16004)
                .usePlaintext()
                .build();

        //calling the Greeting Client
        GreetingClient.greetingCall(channel);


        // Shutting down the client
        shutdown(channel);

    }

    private void shutdown(ManagedChannel channel) throws InterruptedException {

        Thread.sleep(100);
        logger.info("Shutting down channel ...");
        channel.shutdown();
    }
}

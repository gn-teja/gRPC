package grpc.unary;

import greeting.GreetingServiceGrpc;
import greeting.Message;
import grpc.Client;
import io.grpc.ManagedChannel;

import java.util.logging.Logger;

public class GreetingClient {

    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public static void greetingCall(ManagedChannel channel) {

        logger.info("In Method : greeting call");

        GreetingServiceGrpc.GreetingServiceBlockingStub greetingClient = GreetingServiceGrpc.newBlockingStub(channel);

        Message.GreetingRequest request = Message.GreetingRequest.newBuilder()
                .setName("Tony Stark ...")
                .build();

        Message.GreetingResponse response = greetingClient.greeting(request);

        System.out.println(response.getGreeting());
    }
}

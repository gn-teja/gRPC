package grpc;

import greeting.GreetingServiceGrpc;
import greeting.Message;
import greeting.Message.GreetingRequest;
import greeting.Message.GreetingResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

    public static void main(String[] args) {

        System.out.println("Initializing gRPC grpc.Client ...");

        Client main = new Client();
        main.run();
    }

    private void run (){

        System.out.println("Creating stub");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",16002)
                .usePlaintext()
                .build();

        greetingCall(channel);


        System.out.println("\n Shutting down channel");
        channel.shutdown();
    }

    private void greetingCall(ManagedChannel channel) {
        System.out.println("In greet call \n");

        GreetingServiceGrpc.GreetingServiceBlockingStub greetingClient = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingRequest request = Message.GreetingRequest.newBuilder()
                .setName("Gnana Teja")
                .build();

        GreetingResponse response = greetingClient.greeting(request);

        System.out.println(response.getGreeting());

    }
}

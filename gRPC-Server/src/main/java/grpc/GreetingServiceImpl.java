package grpc;

import greeting.GreetingServiceGrpc;
import greeting.Message;
import greeting.Message.GreetingResponse;
import greeting.Message.GreetingRequest;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends
        GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingRequest request, StreamObserver<Message.GreetingResponse> responseObserver) {

        GreetingResponse response = GreetingResponse.newBuilder()
                .setGreeting("Hello " + request.getName() +
                        "\nWelcome to gRPC Intro ...")
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}

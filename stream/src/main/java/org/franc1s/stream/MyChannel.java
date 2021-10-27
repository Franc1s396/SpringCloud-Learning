package org.franc1s.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannel {
    String input = "javaboy-input";
    String output = "javaboy-output";

    @Output(output)
    MessageChannel output();

    @Input(input)
    SubscribableChannel input();
}

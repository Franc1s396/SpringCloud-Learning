package org.franc1s.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MyChannel.class)
public class MsgReceiver2 {
    private static final Logger logger= LoggerFactory.getLogger(MsgReceiver2.class);
    @StreamListener(MyChannel.input)
    public void receive(Object payload){
        logger.info("<-------Received2:"+payload+"------->");
    }
}

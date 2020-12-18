package com.kaistsw.arch.subscribe.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SpringCloudStreamBindings {

    static String BINDING_JOB_ITEM_TOPIC = "job_item_topic";

    @Input(BINDING_JOB_ITEM_TOPIC)
    public  SubscribableChannel inputEventOrd();

}

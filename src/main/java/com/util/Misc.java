package com.util;

import io.vertx.core.Vertx;
import io.vertx.kafka.client.consumer.KafkaConsumer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bhargodev on 02/12/23
 *
 * Temp class to keep make shift code/**
 **/
public class Misc {

    /**
     * Creates a kafka consumer to read messages from the topic.
     * Also, sends a message to event bus address which picked by the Kafka producer and consumed by the consumer created earlier.
     * @param vertx
     */
    public static void extracted(Vertx vertx) {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:29092");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("group.id", "my_group");
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");

        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);
        consumer.subscribe(Constant.KAFKA_PROD_CONS_TOPIC).onSuccess(handler -> System.out.println("Subscribe successful"));
        consumer.handler(kafkaConsRec -> {
            System.out.println(">>>>>>>> got the value " + kafkaConsRec.value());
        });


        vertx.eventBus().request(Constant.MESSAGE_CREATION_ADDRESS, "Sent from Bootstrap", reply -> {
            if (reply.succeeded()) {
                System.out.println(">> Message sent successfully");
            } else {
                System.out.println(">> Error occurred while sending message " + reply.cause().getMessage());
            }
        });
    }
}

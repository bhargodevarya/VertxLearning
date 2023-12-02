package com.kafka;

import com.util.Constant;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bhargodev on 02/12/23
 **/
public class KafkaMessenger extends AbstractVerticle {

    private Map<String, String> config;

    public KafkaMessenger() {
        this.config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:29092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "1");
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        getVertx().eventBus().consumer(Constant.MESSAGE_CREATION_ADDRESS, message -> {
            String messageToSend = message.body().toString();
            Future<Void> future = this.sendMessage(messageToSend, Constant.KAFKA_PROD_CONS_TOPIC);
            future.onSuccess(handler -> message.reply("Sent successfull")).
                    onFailure(error -> message.fail(0, error.getMessage()));

        });
    }

    public Future<Void> sendMessage(String message, String topic) {
        Promise<Void> promise = Promise.promise();
        KafkaProducer<Object, Object> producer = KafkaProducer.create(getVertx(), this.config);
        producer.send(KafkaProducerRecord.create(topic, "Hello to test Kafka, this is my message, ".concat(message)), recordMetaData -> {
            if (recordMetaData.succeeded()) {
                promise.complete();
            } else if (recordMetaData.failed()){
                promise.fail("Failed to send to kafka ".concat(recordMetaData.cause().getMessage()));
            }
        });
        return promise.future();
    }
}

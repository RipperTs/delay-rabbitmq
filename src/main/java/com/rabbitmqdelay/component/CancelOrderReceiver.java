package com.rabbitmqdelay.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 */
@Component
@RabbitListener(queues = "order.cancel")
@Slf4j
public class CancelOrderReceiver {


    @RabbitHandler
    public void handle(Long orderId){
        log.info("接收消息:{}",orderId);
        // 订单业务处理逻辑
    }

}

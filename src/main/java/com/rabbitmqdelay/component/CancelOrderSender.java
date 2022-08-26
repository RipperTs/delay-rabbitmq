package com.rabbitmqdelay.component;

import com.rabbitmqdelay.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的发出者
 */
@Component
@Slf4j
public class CancelOrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 直接给队列发送消息
     *
     * @param orderId
     */
    public void sendMessage(Long orderId) {
        //直接给队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey(), orderId);
        log.debug("发出消息:{}", orderId);
    }

    /**
     * 给延迟队列发送消息
     *
     * @param orderId
     * @param delayTimes
     */
    public void sendMessage(Long orderId, final long delayTimes) {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, (Message message) -> {
            //给消息设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        });
        log.debug("发出消息:{}", orderId);
    }
}

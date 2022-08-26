package com.rabbitmqdelay.controller;

import com.rabbitmqdelay.component.CancelOrderSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OmsPortalOrderController {

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @GetMapping("/delay-message")
    public void sendDelayMessage() {
        // 生成订单
        // 为订单设置超时时间
        long delayTimes = 3 * 1000;
        // 发送延迟消息
        cancelOrderSender.sendMessage(Long.parseLong("20102010201"), delayTimes);
        log.info("发送延迟消息成功");
    }

    @GetMapping("/send-message")
    public void sendMessage() {
        // 生成订单
        // 发送消息
        cancelOrderSender.sendMessage(Long.parseLong("20102010201"));
        log.info("直接发送消息成功");
    }
}

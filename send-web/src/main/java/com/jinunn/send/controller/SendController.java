package com.jinunn.send.controller;

import com.jinunn.send.entity.SendRequest;
import com.jinunn.send.entity.SendResponse;
import com.jinunn.send.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : JinDun
 * @date : 2022/6/16 16:36
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendService sendService;

    @PostMapping("/sms")
    public SendResponse sendSmsTest(@RequestBody SendRequest sendRequest) {
        return sendService.send(sendRequest);
    }
}

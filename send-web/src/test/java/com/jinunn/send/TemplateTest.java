package com.jinunn.send;

import com.jinunn.send.entity.MessageTemplate;
import com.jinunn.send.service.IMessageTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Jin
 * @date : 2022/6/15 16:24
 */
@Slf4j
@SpringBootTest
public class TemplateTest {

    @Autowired
    private IMessageTemplateService messageTemplateService;

    @Test
    public void add(){
        assertNotNull(messageTemplateService);

        messageTemplateService.add();
    }

    @Test
    public void getList() {
        assertNotNull(messageTemplateService);

        List<MessageTemplate> list = messageTemplateService.getList();
        for (MessageTemplate template : list) {
            log.info("template ==>{}", template.getName());
        }
    }


}

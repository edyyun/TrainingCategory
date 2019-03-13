package com.example.categorymongo.Controller;

import com.example.categorymongo.Entities.ApiKey;
import com.example.categorymongo.Entities.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(
            value = "/publish",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String publish(ApiKey apiKey)throws Exception{
        Category category= new Category("10","Mainan");
        String json = objectMapper.writeValueAsString(category);
        kafkaTemplate.send("categories",json);
        return "Success";
    }

}

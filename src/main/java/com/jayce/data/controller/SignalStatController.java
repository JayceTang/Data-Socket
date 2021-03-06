package com.jayce.data.controller;

import com.jayce.data.dto.SignalStatDataDto;
import com.jayce.data.service.StatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class SignalStatController {
    
    @Autowired
    private StatService statService;

    @ResponseBody
    @GetMapping("/getData")
    public List<SignalStatDataDto> getData() {
        return statService.findAll();
    }

    /**  test */
    @GetMapping("/string")
    public String string(){
        return "tom";
    }

    /**  test */
    @GetMapping("/list")
    public Integer list(){
        return 5345345;
    }
}

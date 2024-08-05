package com.sb.demo.controller;

import com.sb.demo.threadPool.task.ThreadPoolTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("demo")
@RestController
public class DemoController {

    @Autowired
    private ThreadPoolTask threadPoolTask;

    @GetMapping("test")
    public @ResponseBody String test() {
        threadPoolTask.threadTask();
        return "success";
    }



}

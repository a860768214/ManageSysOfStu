package com.hsy.dbexp.controller;

import com.hsy.dbexp.response.CommonReturnType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dbexp")
@CrossOrigin
public class TestController
{
    @GetMapping("/test")
    public CommonReturnType index() {
        return CommonReturnType.create("response");
    }
}

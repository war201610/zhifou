package edu.dwlx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController//与@Controller + @ResponseBody等价
public class ExceptionHandler {
    @RequestMapping("/exceptionHandler")
    public Map<String, String> exceptionHandler(String exceptionName, String exceptionContent) {
        HashMap<String ,String> map = new HashMap<String, String>();
        map.put(exceptionName, exceptionContent);
        return map;
    }
}

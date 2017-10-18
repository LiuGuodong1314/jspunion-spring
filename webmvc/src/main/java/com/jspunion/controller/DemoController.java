package com.jspunion.controller;

import com.jspunion.beans.HttpEntity;
import com.jspunion.beans.Pager;
import com.jspunion.exception.BadRequestException;
import com.jspunion.exception.NotFoundExceptoin;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/json")
    @ResponseBody
    public Object getJson(Pager pager) {
        Integer size = pager.getSize();
        if (size == null) {
            throw new BadRequestException();
        }

        if(size == -1) {
            throw new NotFoundExceptoin();
        }

        if(size < 0) {
            throw new RuntimeException();
        }

        pager.setParams(new HashMap<String, Object>() {{
            put("name", "Liu Guodong");
            put("nameZh", "刘国栋");
            put("age", 31);
            put("testTime", new Date());
            put("text", "text");
        }});
        pager.setFromTime(new Date());
        pager.setOffset(0);

        return  HttpEntity.ok(pager);
    }
}




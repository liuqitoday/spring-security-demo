package com.liuqitech.demo.web;

import com.liuqitech.demo.model.vo.Info;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/hello")
@RestController
public class HelloController {

  @GetMapping("/info")
  public Info getInfo() {
    Info info = new Info();
    info.setName("liuqitech");
    return info;
  }

}

package cn.hzu;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Soft1709081602513Activity {

    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Soft1709081602513Activity.class, args);
    }

}
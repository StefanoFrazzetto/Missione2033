package server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.responses.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WelcomeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/welcome")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Player") String name) {

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
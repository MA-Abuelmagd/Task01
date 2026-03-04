package __8078.T_08.Mohamed_Ayman.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Value("${USERNAME}")
    private String username;
    @Value("${ID}")
    private String id;
    @GetMapping
    public String welcome() {
        return "Hello " + username + " "+ id+", welcome to the Users and Notes API!";
    }
    
}

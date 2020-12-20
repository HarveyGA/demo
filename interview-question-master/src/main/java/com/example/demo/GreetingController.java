package com.example.demo;

import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	public static int duration = 10000; //Number of milliseconds to determine valid request amount
	public static int validRequestAmount = 3; //Number of valid requests per set duration
	private static HashMap<String, Client> clients = new HashMap<>();
			

    @GetMapping("/api/myapi")
    public String greeting(@RequestParam(name = "client", required = false, defaultValue = "0000") String clientID) {
    	boolean result = true;
        Client client = clients.get(clientID);

    	if (client != null) {
    		result = client.checkRequest();
        }
        else {
        	client = new Client(clientID);
        	client.checkRequest();    	
        	clients.put(clientID, client);
        }
    	if (result) {
    		return "Valid request";
    	}
    	else {
    		return "403 exceeded request threshold";
    	}
    	    
    }
}
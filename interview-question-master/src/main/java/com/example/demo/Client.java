package com.example.demo;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class Client {
	private String clientID;
	private CircularFifoQueue<Long> requests = new CircularFifoQueue<>(GreetingController.validRequestAmount);

	public Client (String id) {
		this.clientID = id;
	}
	
	public String getClientID() {
		return this.clientID;
	}
	
	public boolean checkRequest() {
		boolean result = true;
		Long requestTime = System.currentTimeMillis();

		if (requests.isAtFullCapacity()) {
			// if at maximum requests, check to see if last request was less than 10 seconds ago
			if ((requestTime - GreetingController.duration) < requests.get(0)) {
				result = false;
			}
		}		
		// assuming invalid requests still counts as a request, add to the queue
		requests.add(requestTime);	
		return result;	
	}
}

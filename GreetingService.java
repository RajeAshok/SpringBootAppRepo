package com.codePractice.myapp.service;

import java.util.Collection;

import com.codePractice.myapp.ws.model.Greeting;

public interface GreetingService {
	
	Collection<Greeting> findAllGreeting();
	
	Greeting findAGreeting(Long id);
	
	Greeting createGreeting(Greeting newGreeting);
	
	Greeting updateGreeting(Greeting oldGreeting);
	
	void deleteGreeting(Long id);
	

}

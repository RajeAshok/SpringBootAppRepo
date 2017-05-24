package com.codePractice.myapp.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codePractice.myapp.ws.model.Greeting;
import com.codePractice.myapp.ws.repository.GreetingRepository;

@Service
public class GreetingServiceBean implements GreetingService {
	
	@Autowired
	private GreetingRepository greetingRepository;
	
	@Override
	public Collection<Greeting> findAllGreeting() {
		Collection<Greeting> greetings = greetingRepository.findAll();
		return greetings;
	}

	@Override
	public Greeting findAGreeting(Long id) {
		Greeting returnGreeting = greetingRepository.findOne(id);
		return returnGreeting;
	}

	@Override
	public Greeting createGreeting(Greeting newGreeting) {
		//if new greeting detail already exists we can create it
		if(newGreeting.getId()!=null){
			return null;
		}
		Greeting newGreetings = greetingRepository.save(newGreeting);
		return newGreetings;
	}

	@Override
	public Greeting updateGreeting(Greeting oldGreeting) {
		
	Greeting greetingToBePersisted = greetingRepository.findOne(oldGreeting.getId());
		if(greetingToBePersisted==null)
			return null;
		Greeting updatedGreeting = greetingRepository.save(oldGreeting);
		return updatedGreeting;
	}

	@Override
	public void deleteGreeting(Long id) {
		greetingRepository.delete(id);

	}

}

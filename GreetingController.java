package com.codePractice.myapp.web.api;

import java.awt.PageAttributes.MediaType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codePractice.myapp.service.GreetingService;
import com.codePractice.myapp.ws.model.Greeting;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;
    
	/*
	 * ResponseEntity is the wrapper object that will return the HTTP
	 * Response object with the returning java object type in its Response body
	 * RequestMapping tells spring to recieve https request which http request to map
	 * context path 
	 */
	@RequestMapping(value="/api/greeting",method=RequestMethod.GET)
	public ResponseEntity<Collection<Greeting>> getGreetings(){
		
		Collection<Greeting> greetings=greetingService.findAllGreeting();
		return  new ResponseEntity<Collection<Greeting>>(greetings,HttpStatus.OK);
	}
	
	/**
	 * Get Single Greeting based on id 
	 * @param Greeting
	 * @return
	 */
	@RequestMapping(value="/api/greeting/{id}", method=RequestMethod.GET)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id){
		Greeting returnGreeting = greetingService.findAGreeting(id);
		if(returnGreeting==null)
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Greeting>(returnGreeting,HttpStatus.OK);
		
	}
	
	/**
	 * create greeting from Greeting object in the requestbody
	 * @param greeting
	 * @return
	 */
	@RequestMapping(value="/api/greeting",method=RequestMethod.POST)
	public ResponseEntity<Greeting> addGreeting(@RequestBody Greeting  greeting){
		Greeting newGreeting = greetingService.createGreeting(greeting);
		return new ResponseEntity<Greeting>(newGreeting,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="api/greeting/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id){
		
		 greetingService.deleteGreeting(id);
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
		
	
	}
	
	
	@RequestMapping(value="api/greeting/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting){
		Greeting updatedGreeting = greetingService.updateGreeting(greeting);
		
		if(updatedGreeting==null){
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(updatedGreeting,HttpStatus.OK);
		
	}
	
	
	
}

package com.codePractice.myapp.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codePractice.myapp.ws.model.Greeting;

@Service
public class GreetingServiceBean implements GreetingService {
	  private static Long nextId;
	     private static Map<Long,Greeting> greetingMap;
	     
	     
	     private static Greeting saveGreeting(Greeting greeting){
	    	 if(greetingMap==null){
	    		 greetingMap = new HashMap<Long,Greeting>();
	    	 nextId= new Long(1);
	    	 }
	    	 //if update
	    	 if(greeting.getId()!=null){
	    		Greeting oldGreeting =greetingMap.get(greeting.getId());
	    		if(oldGreeting==null)
	    			return null;
	    	  greetingMap.remove(greeting.getId());
	    	  greetingMap.put(greeting.getId(),greeting);
	    	  return greeting;
	    	 }
	    	 
	    	 //if create
	    	 greeting.setId(nextId);
	    	 nextId += 1;
	    	 greetingMap.put(greeting.getId(),greeting);
	    	 
	    	 return greeting;
	     }
	     
	     
	     static{
	    	 Greeting g1= new Greeting();
	    	 g1.setText("Hello First Call");
	    	 saveGreeting(g1);
	    	 
	    	 Greeting g2 = new Greeting();
	    	 g2.setText("Hello Second Greeting");
	    	 saveGreeting(g2);
	    	 
	     }
	     public static boolean removeGreeting(Long id){
	 		Greeting greetingRemoved= greetingMap.remove(id);
	 		if ( greetingRemoved ==null)
	 			return false;
	 		
	 		return true;
	 	}
	 	

	@Override
	public Collection<Greeting> findAllGreeting() {
		Collection<Greeting> greetings = greetingMap.values();
		return greetings;
	}

	@Override
	public Greeting findAGreeting(Long id) {
		Greeting returnGreeting = greetingMap.get(id);
		return returnGreeting;
	}

	@Override
	public Greeting createGreeting(Greeting newGreeting) {
		Greeting newGreetings = saveGreeting(newGreeting);
		return newGreetings;
	}

	@Override
	public Greeting updateGreeting(Greeting oldGreeting) {
		Greeting updatedGreeting = saveGreeting(oldGreeting);
		return updatedGreeting;
	}

	@Override
	public void deleteGreeting(Long id) {
		removeGreeting(id);

	}

}

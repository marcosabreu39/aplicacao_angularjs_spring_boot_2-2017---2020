package br.agenda3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {

		return "default";

	}

	
	  /*@RequestMapping(value = "/", method = RequestMethod.GET, produces =
	  "application/json")
	  
	  @ResponseStatus(HttpStatus.OK)
	  
	  @ResponseBody public Map<String, Object> msgHome() {
	  
	  Map<String, Object> msgHome = new HashMap<>();
	  
	  msgHome.put("mensagem", "Bem vindo à agenda Spring e Hibernate!");
	  
	  return msgHome;
	  
	  }*/
	 

}

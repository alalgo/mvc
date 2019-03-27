package org.alalgo.almvc.test;

import org.alalgo.almvc.annotation.Autowired;
import org.alalgo.almvc.annotation.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller 
@Slf4j
public class AlmvcController {
	@Autowired
	private AlmvcService almvcServicee;
	
	public void hi() {
		 almvcServicee.hello();
	}
}

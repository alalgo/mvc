package org.alalgo.almvc.test;

import org.alalgo.almvc.annotation.Service;

@Service
public class AlmvcServiceImpl implements AlmvcService {

	@Override
	public void hello() {
		System.out.println("hello,i'm AlmvcServiceImpl ,just for test.");
	}


}

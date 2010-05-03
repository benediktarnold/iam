package de.metafinanz.campus.iam;

import javax.inject.Named;

@Named("bean1")
public class HelloWorldBean {
	public String getHelloWorld() {
		System.out.println("HelloWorldBean.getHelloWorld()");
		return "Hello Spring ";
	}
}

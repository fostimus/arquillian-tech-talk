package com.dfoster.test;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dfoster.Greeter;

@RunWith(Arquillian.class)
public class EmbeddedTest {
	
	@Deployment
	public static Archive<?> deployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
	            .addClass(Greeter.class)
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		
		return jar;
	}
	
	@Inject
	Greeter greeter;
	
	@Test
    public void should_create_greeting() {
        assertEquals("Hello, Earthling!",
            greeter.createGreeting("Earthling"));
        greeter.greet(System.out, "Earthling");
    }


}

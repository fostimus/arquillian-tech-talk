package com.dfoster.test;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dfoster.GreeterPB;
import com.dfoster.PhraseBuilder;

@RunWith(Arquillian.class)
public class InjectionTest {
	
	@Deployment(name = "dep1") @TargetsContainer("eap-managed-7.1")
	public static Archive<?> deployment() {
		
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
	            .addClass(GreeterPB.class)
	            .addClass(PhraseBuilder.class)
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		
		return jar;
	}
	
	@Inject
	GreeterPB greeter;
	
	@Test @OperateOnDeployment("dep1") 
    public void should_create_greeting1() {
        assertEquals("Hello, Earthling!",
            greeter.createGreeting1("Earthling"));
        greeter.greet1(System.out, "Earthling");
    }
	
	@Test @OperateOnDeployment("dep1")
    public void should_create_greeting2() {
        assertEquals("Hello, Earthling!",
            greeter.createGreeting2("Earthling"));
        greeter.greet2(System.out, "Earthling");
    }

}

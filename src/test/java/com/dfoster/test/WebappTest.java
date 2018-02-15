package com.dfoster.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class WebappTest {
	
	@Deployment
	public static Archive<?> deployment() {
		return ShrinkWrap.create(ZipImporter.class, "arquillian-webapp.war")
				.importFrom(new File("../arquillian-webapp/target/arquillian-webapp-1.0.war"))
	            .as(WebArchive.class);
	}
	
	@Test
	public void testCount() {
		
	}

}

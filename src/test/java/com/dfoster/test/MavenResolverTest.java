package com.dfoster.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Splitter;

@RunWith(Arquillian.class)
public class MavenResolverTest {
	
	static File[] dependencies = Maven.resolver().loadPomFromFile("/home/dfoster/Development/projects/arquillian-tech-talk/pom.xml").importDependencies(ScopeType.TEST, ScopeType.COMPILE)
			  .resolve().withTransitivity().asFile();
	
	@Deployment //@TargetsContainer("eap-managed-7.1")
	public static Archive<?> deployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				//.addClass(Splitter.class)
	            .addAsLibraries(dependencies)
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void testSplitter() {
		String numbers = "2,45,3,8";
		List<String> result = Splitter.on(",").trimResults().splitToList(numbers);
		
		System.out.print("\n");
		for (String s : result) {
			System.out.println(s);
		}
		assertEquals(4, result.size());
	}

}

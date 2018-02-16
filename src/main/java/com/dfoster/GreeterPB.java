package com.dfoster;

import java.io.PrintStream;

import javax.inject.Inject;

public class GreeterPB {
	private PhraseBuilder phraseBuilder;
	
	@Inject
    public GreeterPB(PhraseBuilder phraseBuilder) {
        this.phraseBuilder = phraseBuilder;
    }
	
	public void greet1(PrintStream to, String name) {
		to.print("\n\n");
        to.println(createGreeting1(name));
        to.print("\n\n");
    }
	
	public void greet2(PrintStream to, String name) {
		to.print("\n\n");
        to.println(createGreeting2(name));
        to.print("\n\n");
    }

    public String createGreeting1(String name) {
        return "Hello, " + name + "!";
    }

    public String createGreeting2(String name) {
        return phraseBuilder.buildPhrase("hello", name);
    }

}

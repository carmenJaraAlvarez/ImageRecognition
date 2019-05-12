package aiss.model.resources;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import aiss.model.resource.MicrosoftTranslateResource;
import aiss.model.resource.UnplashResource;
import aiss.model.unplash.*;
import junit.framework.Assert;



public class MicrosoftResourceTest {
	
	static MicrosoftTranslateResource mr;
	static List<String> tags;
	
	@BeforeClass
	public static void setUp() throws Exception {
		mr =new MicrosoftTranslateResource(null);
		tags=new ArrayList<>();

	}


	@Test
	public void testTranslate() throws IOException {
		String tag0="dog";
		String tag1="cat";
		tags.add(tag0);
		tags.add(tag1);
		String lang="es";
		List<String> translated=null;
		
		translated=mr.getTranslation(tags, lang);
		assertNotNull("The translation is null", translated);
		assertTrue("Error translated tag 0", translated.get(0).equals("Perro"));
		assertTrue("Error translated tag 1", translated.get(1).equals("Gato"));
		// Show result
		System.out.println("Listing translated tags:");
		int i=1;
		for (String s : translated) {
			System.out.println("- " + i++ + " : " + s);
		}
		
	}

	
	



}

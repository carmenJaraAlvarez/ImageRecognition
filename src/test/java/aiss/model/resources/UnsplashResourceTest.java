package aiss.model.resources;

import static org.junit.Assert.*;

import java.io.IOException;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import aiss.model.resource.UnplashResource;
import aiss.model.unplash.*;
import junit.framework.Assert;



public class UnsplashResourceTest {

	static User user;
	static String token;
	static UnplashResource ur;
	static UnplashCollection uc;

	
	@BeforeClass
	public static void setUp() throws Exception {
		token="77055ede0f9d16b667f64be4fa24e5306f6ce3e2bc70a308bef6f3b7c91ca2d5";//TODO preguntar en clase
		ur =new UnplashResource(token);

	}


	@Test
	public void testGetCollections() throws IOException {
		user =new User();
		user.setUsername("caejaralv");


		List<UnplashCollection> cs = ur.getCollections(); 
		
		assertNotNull("The collection is null", cs);
		
		// Show result
		System.out.println("Listing collections:");
		int i=1;
		for (UnplashCollection c : cs) {
			System.out.println("Collection " + i++ + " : " + c.getTitle() + " (ID=" + c.getId() + ")");
		}
		
	}

	@Test
	public void testGetImages() {


		List<ImagesSearch> is = ur.getImages(); 
		
		assertNotNull("The collection is null", is);
		
		// Show result
		System.out.println("Listing images:");
		int i=1;
		for (ImagesSearch im : is) {
			System.out.println("Image id " + i++ + " : " + im.getId());
		}
	}
	@Test
	public void testCreateCollection() throws IOException {
		List<UnplashCollection> cs = ur.getCollections(); 
		int initial=cs.size();
		ur.POSTRequest("new");
		List<UnplashCollection> cs2 = ur.getCollections(); 
		int end=cs2.size();
		assertTrue("Error create", end>initial);

		
	}

	



}

package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.Member;

import aiss.model.repository.MemberRepository;
import aiss.model.resource.GoogleVisionResource;
import aiss.model.resource.MicrosoftTranslateResource;

public class MicrosoftTranslateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(MicrosoftTranslateController.class.getName());
	
    public MicrosoftTranslateController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lang="es";
		List<String> ls=new ArrayList<String>();
		ls.add("water");
		ls.add("red");		
		String accessToken ="eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46bXMuY29nbml0aXZlc2VydmljZXMiLCJleHAiOiIxNTU3MTc0MzcxIiwicmVnaW9uIjoiZ2xvYmFsIiwic3Vic2NyaXB0aW9uLWlkIjoiNGNjYWE4MWQ4MGNiNGY5ZTg1NTNmMzFmYzVjYjVmNGUiLCJwcm9kdWN0LWlkIjoiVGV4dFRyYW5zbGF0b3IuRjAiLCJjb2duaXRpdmUtc2VydmljZXMtZW5kcG9pbnQiOiJodHRwczovL2FwaS5jb2duaXRpdmUubWljcm9zb2Z0LmNvbS9pbnRlcm5hbC92MS4wLyIsImF6dXJlLXJlc291cmNlLWlkIjoiL3N1YnNjcmlwdGlvbnMvNmM0NzA0ZDctMWQyYy00NGVhLWE0OTEtNTc4NTU4MmJjYjUyL3Jlc291cmNlR3JvdXBzL2YxL3Byb3ZpZGVycy9NaWNyb3NvZnQuQ29nbml0aXZlU2VydmljZXMvYWNjb3VudHMvdDEiLCJzY29wZSI6Imh0dHBzOi8vYXBpLm1pY3Jvc29mdHRyYW5zbGF0b3IuY29tLyIsImF1ZCI6InVybjptcy5taWNyb3NvZnR0cmFuc2xhdG9yIn0.aR-HazpbKWIM-I4UKKKOHHnVFZRyt0A2BoTR56rDkJA";
	        if (accessToken != null && !"".equals(accessToken)) {
	        	log.info("**********************");
	            MicrosoftTranslateResource mtResource = new MicrosoftTranslateResource(accessToken);
	            
	            List<String> translated = mtResource.getTranslation(ls,lang);

	            if (translated != null) {
	                req.setAttribute("translated", translated);
	                req.getRequestDispatcher("/MicrosoftTranslateListing.jsp").forward(req, resp);
	            } else {
	                log.info("The translated text is null... probably your bearer token has experied. Redirecting to Auth servlet.");
	                //req.getRequestDispatcher("/AuthControllerMicrosoftTranslate").forward(req, resp);
	            }
	        } else {
	            log.info("Trying to access Microsoft Translate without Bearer Token, redirecting to Auth servlet");
	            //req.getRequestDispatcher("/AuthControllerMicrosoftTranslate").forward(req, resp);
	        }
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

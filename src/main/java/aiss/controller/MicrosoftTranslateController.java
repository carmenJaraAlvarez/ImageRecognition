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
		 String accessToken = (String) req.getSession().getAttribute("Microsoft-token");

	        if (accessToken != null && !"".equals(accessToken)) {

	            MicrosoftTranslateResource mtResource = new MicrosoftTranslateResource(accessToken);
	            List<String> translated = mtResource.getTranslation(ls,lang);

	            if (translated != null) {
	                req.setAttribute("translated", translated);
	                req.getRequestDispatcher("/translate.jsp").forward(req, resp);
	            } else {
	                log.info("The translated text is null... probably your bearer token has experied. Redirecting to Auth servlet.");
	                req.getRequestDispatcher("/AuthControllerMicrosoftTranslate").forward(req, resp);
	            }
	        } else {
	            log.info("Trying to access Microsoft Translate without Bearer Token, redirecting to Auth servlet");
	            req.getRequestDispatcher("/AuthControllerMicrosoftTranslate").forward(req, resp);
	        }
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

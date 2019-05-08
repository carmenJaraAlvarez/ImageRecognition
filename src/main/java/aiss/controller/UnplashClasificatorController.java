package aiss.controller;




import aiss.model.resource.UnplashResource;
import aiss.model.unplash.ImagesSearch;
import aiss.model.unplash.UnplashCollection;
import aiss.model.unplash.Urls;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnplashClasificatorController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UnplashClasificatorController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String accessToken = (String) req.getSession().getAttribute("Unplash-token");
                
        if (accessToken != null && !"".equals(accessToken)) {
        	req.getRequestDispatcher("/index.html").forward(req, resp);
        	
        } else {
            log.info("Trying to access without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}

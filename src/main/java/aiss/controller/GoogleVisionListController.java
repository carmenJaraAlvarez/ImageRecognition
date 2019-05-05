package aiss.controller;

import aiss.model.google.drive.Files;
import aiss.model.resource.GoogleDriveResource;
import aiss.model.resource.GoogleVisionResource;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleVisionListController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GoogleVisionListController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String accessToken = (String) req.getSession().getAttribute("GoogleVision-token");

        if (accessToken != null && !"".equals(accessToken)) {

            GoogleVisionResource gdResource = new GoogleVisionResource(accessToken);
            List<String> tags = gdResource.getTags();

            if (tags != null) {
                req.setAttribute("tags", tags);
                req.getRequestDispatcher("/googleVisionListing.jsp").forward(req, resp);
            } else {
                log.info("The tags returned are null... probably your token has experied. Redirecting to OAuth servlet.");
                req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
            }
        } else {
            log.info("Trying to access Google Vision without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}

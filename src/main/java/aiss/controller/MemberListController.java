package aiss.controller;

import java.io.IOException;
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

public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(MemberListController.class.getName());
	
    public MemberListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Load contacts
		MemberRepository repository = MemberRepository.getInstance();
		Map<String,Member> members = repository.getMembers();
		
		 log.log(Level.FINE, "Processing GET request:  " + members.size() + " members loaded.");
		
		// Send contacts to index.jsp
		request.setAttribute("members", members);
		request.getRequestDispatcher("/about.jsp").forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

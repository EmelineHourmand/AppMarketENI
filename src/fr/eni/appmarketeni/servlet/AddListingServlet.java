package fr.eni.appmarketeni.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appmarketeni.bll.ListingManager;
import fr.eni.appmarketeni.bo.Listing;

/**
 * Servlet implementation class AddListing
 */
@WebServlet("/addListing")
public class AddListingServlet extends HttpServlet {
	private static final String PATH_TO_ADD_LISTING_JSP = "/WEB-INF/jsp/addListing.jsp";
	private static final long serialVersionUID = 1L;
	private static ListingManager listeMng = new ListingManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddListingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(PATH_TO_ADD_LISTING_JSP);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> listeArticle = new ArrayList<String>();
		listeArticle.add(request.getParameter("articleName"));
		String nomAjout = request.getParameter("listName");
		System.out.println(nomAjout);
		System.out.println(listeArticle.toString());
		listeMng.insertListing(nomAjout,listeArticle);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(PATH_TO_ADD_LISTING_JSP);
		rd.forward(request, response);
	}

}

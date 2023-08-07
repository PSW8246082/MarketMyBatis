package notice.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/notice/clist.do")
public class CListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String page = request.getParameter("currentPage") != null ? request.getParameter("currentPage") : "1";
		//int currentPage = Integer.parseInt(page);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/customerCenterList.jsp");
		view.forward(request, response);
		
		
		//response.sendRedirect("/WEB-INF/views/notice/customerCenterList.jsp");이건 왜 안되지?
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

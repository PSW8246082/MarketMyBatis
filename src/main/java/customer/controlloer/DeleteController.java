package customer.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/customer/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");   //로그인 했을때 저장해둔 값 = customerId
		
		CustomerService service = new CustomerService();
		int result = service.deleteCustomer(customerId);
		
		if(result > 0) {
			//성공 - 페이지 이동 방식 2가지
			//1.withData
			request.setAttribute("msg", "회원탈퇴성공");
			request.setAttribute("url", "/customer/logout.do");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/serviceSuccess.jsp");
			view.forward(request, response);
			
			//2.without Data
			response.sendRedirect("/WEB-INF/views/common/serviceSuccess.jsp");
			response.sendRedirect("/customer.logout.do");
		} else {
			//실패 - 페이지 이동 방식 2가지
			//1.withData
			request.setAttribute("msg", "회원탈퇴실패");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp");
			view.forward(request, response);
			
			//2.without Data
			response.sendRedirect("/WEB-INF/views/common/serviceFail.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

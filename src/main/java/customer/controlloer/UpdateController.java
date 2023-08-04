package customer.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import customer.model.vo.Customer;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/customer/update.do")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/costomer/CustomerModify.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  //한글 안깨지게
		//UPDATE CUSTOMER_TBL SET CUSTOMER_PW = ?,  MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ?, UPDATE_DATE = SYSDATE WHERE MEMBER_ID = ?
		String customerId = request.getParameter("customer-id");  //jsp에 name값 넣기
		String customerPw = request.getParameter("customer-pw");
		String customerName = request.getParameter("customer-name");
		String customerPhone = request.getParameter("phone");
		String customerEmail = request.getParameter("email");
		
		Customer customer = new Customer(customerId, customerPw, customerName, customerEmail, customerPhone);
		
		CustomerService service = new CustomerService();
		int result = service.updateCustomer(customer);
		
		if(result > 0) {
			//성공 -> 메인페이지 이동
			response.sendRedirect("/index.jsp");
		} else {
			//실패 -> 에러페이지 이동
			request.setAttribute("msg", "회원 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp").forward(request, response);
		}
				
	}

}

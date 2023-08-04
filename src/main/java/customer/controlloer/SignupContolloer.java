package customer.controlloer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import customer.model.vo.Customer;



/**
 * Servlet implementation class SignUpContolloer
 */
@WebServlet("/customer/register.do")
public class SignupContolloer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupContolloer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/costomer/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String customerId = request.getParameter("customer-id"); //jsp의 name값이 들어간다.
		String customerPw = request.getParameter("customer-pw");
		String customerName = request.getParameter("customer-name");
		String customerAgeYear = request.getParameter("customer-age-year");
		String customerAgeMonth = request.getParameter("customer-age-month");
		String customerAgeDay = request.getParameter("customer-age-day");
		String customerGender = request.getParameter("gender");
		String customerEmail = request.getParameter("email");
		String customerPhone = request.getParameter("phone");
		String customerNo = customerAgeYear + "-" + customerAgeMonth + "-" + customerAgeDay;
		
		Customer customer = new Customer(customerId, customerPw, customerName, customerNo, customerGender, customerEmail, customerPhone);
		
		//쿼리문 : INSERT INTO CUSTOMER_TBL VALUES(?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)
		CustomerService service = new CustomerService();
		int result = service.insertCustomer(customer);
		
		if(result > 0) {
			//성공 = 성공 페이지로 이동 -> RequestDispatcher
			request.setAttribute("msg", "회원가입 성공");
			request.setAttribute("url", "/index.jsp");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceSuccess.jsp")
			.forward(request, response);
		} else {
			//실패 = 실패 페이지로 이동 -> RequestDispatcher
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp")
			.forward(request, response);
		}
		
	}

}

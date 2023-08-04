package customer.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.model.service.CustomerService;
import customer.model.vo.Customer;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Market/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/costomer/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String costomerId = request.getParameter("costomer-id");
		String costomerPw = request.getParameter("costomer-pw");
		
		//두개의 변수를 하나의 변수로 이용할수있게 객체만들기 - Market에서 미리 생성자 만들어야함
		Customer market = new Customer(costomerId, costomerPw);
		
		//특정 아이디와 비밀번호인 고객의 정보 조회
		//SELECT * FROM COSTOMER_TBL WHERE COSTOMER_ID =? AND COSTOMER_PW = ?
		//int, Market(객체), List 중에 뭐로 받을지 체크
		
		CustomerService service = new CustomerService();
		Customer mOne = service.selectCheckLogin(market);
		
		
		
		if(mOne != null ) {
			
			//${sessionScope.memberName} 
			//""안에 저장한 이름(키값)으로 jsp에서 세션스코프로 값을 불러와 화면에 출력하기 위해 작성하는..
			HttpSession session = request.getSession();
			session.setAttribute("customerId", mOne.getCustomerId());
			session.setAttribute("costomerName", mOne.getCustomerName());
			
			//로그인 성공
			request.setAttribute("msg", "로그인 성공");
			request.setAttribute("url", "/index.jsp");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/serviceSuccess.jsp");
			view.forward(request, response); //누락 주의
		} else {
			//로그인 실패
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp");
			view.forward(request, response);
		}
		
		
	}

}

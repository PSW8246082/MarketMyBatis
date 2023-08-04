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
 * Servlet implementation class MyPageController2
 */
@WebServlet("/customer/mypage.do")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지 이동 2가지
				// 1.with Data (DataBase에서 가져온 데이터를 같이 가져감)
				// 쿼리문 : SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?      
				// List, int, Member(객체)중 리턴받을 타입 체크
				String customerId = request.getParameter("costomer-id");   //index에서 가져올 수 있나..? name값 어디서 가져와야하지....
				
				CustomerService service = new CustomerService();
				Customer customer  = service.selectOneById(customerId); 
				
				request.setAttribute("customer", customer);
				
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/costomer/MyPage.jsp");
				view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package inquiry.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;

/**
 * Servlet implementation class IDetailController
 */
@WebServlet("/inquiry/idetail.do")
public class IDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
		
		InquiryService service = new InquiryService();
		Inquiry inquiry = service.selectOneById(inquiryNo);
		
		if(inquiry != null) {
			request.setAttribute("inquiry", inquiry);
			request.getRequestDispatcher("/WEB-INF/views/inquiry/inquireDetail.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "데이터 조회 실패");
			request.setAttribute("url", "/inquiry/idetail.do");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp").forward(request, response);
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

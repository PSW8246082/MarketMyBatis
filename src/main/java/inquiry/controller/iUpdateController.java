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
 * Servlet implementation class iUpdateController
 */
@WebServlet("/inquiry/iupdate.do")
public class iUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iUpdateController() {
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
			request.getRequestDispatcher("/WEB-INF/views/inquiry/inquireUpdate.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
		
		String inquirySubject = request.getParameter("title");
		String inquiryContent = request.getParameter("content");
		
		Inquiry inquiry = new Inquiry(inquiryNo, inquirySubject, inquiryContent);
		
		InquiryService service = new InquiryService();
		int result = service.updateInquiry(inquiry);
		
		if(result > 0) {
			//성공 -> 상세페이지로 이동
			response.sendRedirect("/inquiry/idetail.do?inquiryNo=" + inquiryNo);
		} else {
			//실패 -> 에러메세지 출력
			request.setAttribute("msg", "공지사항 수정 실패");
			request.setAttribute("url", "/inquiry/iupdate.do?inquiryNo" + inquiryNo);
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp").forward(request, response);
		}
		
	}

}

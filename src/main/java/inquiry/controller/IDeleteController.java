package inquiry.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inquiry.model.service.InquiryService;


/**
 * Servlet implementation class IDeleteController
 */
@WebServlet("/inquiry/idelete.do")
public class IDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
		
		InquiryService service = new InquiryService();
		int result = service.deleteInquiryByNo(inquiryNo);
		
		if(result > 0) {
			response.sendRedirect("/inquiry/ilist.do");
		} else {
			request.setAttribute("msg", "공지사항 삭제 실패");
			request.setAttribute("url", "/WEB-INF/views/inquiry/inquireDetail.jsp");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp");
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

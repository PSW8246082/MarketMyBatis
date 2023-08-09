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
 * Servlet implementation class InquireController
 */
@WebServlet("/inquiry/iinsert.do")
public class IInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/inquiry/inquireInsert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 등록
		request.setCharacterEncoding("UTF-8");
		String inquiryWriter = request.getParameter("writer");
		String inquirySubject = request.getParameter("title");
		String inquiryContent = request.getParameter("content");
		
		Inquiry inquiry = new Inquiry(inquirySubject, inquiryContent, inquiryWriter);
		
		InquiryService service = new InquiryService();
		int result = service.insertInquiry(inquiry);
		if(result > 0) {
			//성공 -> 문의글 목록으로
			response.sendRedirect("/inquiry/ilist.do");
		} else {
			//실패 -> 실패 메세지
			request.setAttribute("msg", "공지사항 등록 실패");
			request.setAttribute("url", "/inquiry/list.do");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp").forward(request, response);
		}
	}

}

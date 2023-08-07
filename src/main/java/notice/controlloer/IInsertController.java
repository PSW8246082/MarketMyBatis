package notice.controlloer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;



/**
 * Servlet implementation class InquireController
 */
@WebServlet("/notice/iinsert.do")
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
		request.getRequestDispatcher("/WEB-INF/views/notice/inquireInsert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 등록
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		
		Notice notice = new Notice(noticeSubject, noticeContent);
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		if(result > 0) {
			//성공 -> 문의글 목록으로
			response.sendRedirect("/notice/ilist.do");
		} else {
			//실패 -> 실패 메세지
			request.setAttribute("msg", "공지사항 등록 실패");
			request.setAttribute("url", "/notice/list.do");
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp").forward(request, response);
		}
	}

}

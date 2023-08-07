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
 * Servlet implementation class iUpdateController
 */
@WebServlet("/notice/iupdate.do")
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeService service = new NoticeService();
		Notice notice = service.selectOneById(noticeNo);
		
		if(notice != null) {
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/notice/inquireUpdate.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		
		Notice notice = new Notice(noticeNo, noticeSubject, noticeContent);
		
		NoticeService service = new NoticeService();
		int result = service.updateNotice(notice);
		
		if(result > 0) {
			//성공 -> 상세페이지로 이동
			response.sendRedirect("/notice/iupdate.do?noticeNo=" + noticeNo);
		} else {
			//실패 -> 에러메세지 출력
			request.setAttribute("msg", "공지사항 수정 실패");
			request.setAttribute("url", "/notice/iupdate.do?noticeNo" + noticeNo);
			request.getRequestDispatcher("/WEB-INF/views/common/serviceFail.jsp").forward(request, response);
		}
		
	}

}

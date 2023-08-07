package notice.controlloer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;


/**
 * Servlet implementation class IDeleteController
 */
@WebServlet("/notice/idelete.do")
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeService service = new NoticeService();
		int result = service.deleteNoticeByNo(noticeNo);
		
		if(result > 0) {
			response.sendRedirect("/notice/ilist.do");
		} else {
			request.setAttribute("msg", "공지사항 삭제 실패");
			request.setAttribute("url", "/WEB-INF/views/notice/inquireDetail.jsp");
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

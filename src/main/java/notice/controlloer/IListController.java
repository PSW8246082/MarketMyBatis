package notice.controlloer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

/**
 * Servlet implementation class InquireController
 */
@WebServlet("/notice/ilist.do")
public class IListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("currentPage") != null ? request.getParameter("currentPage") : "1";
		int currentPage = Integer.parseInt(page);
		
		NoticeService service = new NoticeService();
		PageData pd = service.selectNoticeList(currentPage);
		List<Notice> nList = pd.getnList();
		String pageNavi = pd.getPageNavi();
		
		if(!nList.isEmpty()) {
			//성공 -> inquireList.jsp로 이동
			request.setAttribute("nList", nList);
			request.setAttribute("pageNavi", pageNavi);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/inquireList.jsp");
			view.forward(request, response);
		} else {
			//실패 -> 메세지 출력 후 메인으로 이동
			request.setAttribute("msg", "데이터 조회 실패");
			request.setAttribute("url", "/index.jsp");
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

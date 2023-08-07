package notice.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;



import common.SqlSessionTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {

	NoticeDAO nDao = null;
	
	public NoticeService() {
		nDao = new NoticeDAO();
	}
	
	public PageData selectNoticeList(int currentPage) {
		//서비스, DAO, mapper.xml 순으로 코딩
		//서비스는 연결생성, DAO호출, 연결해제
		
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<Notice> nList = nDao.selectNoticeList(session, currentPage);
		String pageNavi = nDao.generatePageNavi(session, currentPage);
		PageData pd = new PageData(nList, pageNavi);
		session.close();
		return pd;
	}

	public int insertNotice(Notice notice) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.insertNotice(session, notice);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public Notice selectOneById(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Notice notice = nDao.selectOneById(session, noticeNo);
		return notice;
	}

	public int updateNotice(Notice notice) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.updateNotice(session, notice);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int deleteNoticeByNo(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.deleteNoticeByNo(session, noticeNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

}

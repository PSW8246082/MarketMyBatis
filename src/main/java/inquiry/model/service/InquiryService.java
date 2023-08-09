package inquiry.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import inquiry.model.dao.InquiryDAO;
import inquiry.model.vo.Inquiry;
import inquiry.model.vo.PageData;


public class InquiryService {

	InquiryDAO iDao = null;
	
	public InquiryService() {
		iDao = new InquiryDAO();
	}
	
	public PageData selectInquiryList(int currentPage) {
		//서비스, DAO, mapper.xml 순으로 코딩
		//서비스는 연결생성, DAO호출, 연결해제
		
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<Inquiry> iList = iDao.selectInquiryList(session, currentPage);
		String pageNavi = iDao.generatePageNavi(session, currentPage);
		PageData pd = new PageData(iList, pageNavi);
		session.close();
		return pd;
	}

	public int insertInquiry(Inquiry inquiry) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = iDao.insertInquiry(session, inquiry);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public Inquiry selectOneById(int inquiryNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Inquiry Inquiry = iDao.selectOneById(session, inquiryNo);
		return Inquiry;
	}

	public int updateInquiry(Inquiry inquiry) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = iDao.updateInquiry(session, inquiry);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int deleteInquiryByNo(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = iDao.deleteInquiryByNo(session, noticeNo);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

}

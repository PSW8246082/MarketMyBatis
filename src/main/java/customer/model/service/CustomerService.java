package customer.model.service;

import java.sql.*;

import common.JDBCTemplate;
import customer.model.dao.CustomerDAO;
import customer.model.vo.Customer;



public class CustomerService {

	//연결생성 - 전역변수로 올려준다.
	private JDBCTemplate jdbcTemplate;
	private CustomerDAO cDao;
	
	
	public CustomerService() {
		jdbcTemplate = JDBCTemplate.getInstance();
		cDao = new CustomerDAO();
	}
	
	    //연결생성
		//DAO호출
		//커밋/롤백
	
	
	public Customer selectCheckLogin(Customer market) {
		//연결생성
		Connection conn = jdbcTemplate.createConnection();
		//DAO호출 (연결도 넘겨줘야함)
		Customer cOne = cDao.selectCheckLogin(conn, market);
		//커밋/롤백은 SELECT에서 안해줘도 된다. 할 필요가 없음 저장하고 롤백할 필요 없으니까
		return cOne;
	}

	public int insertCustomer(Customer customer) {
		//연결생성
		Connection conn = jdbcTemplate.createConnection();
		//DAO호출 (연결도 넘겨줘야함)
		int result = cDao.insertCustomer(conn, customer);
		//커밋/롤백
		if(result > 0) {
			//성공 = 커밋
			jdbcTemplate.comit(conn);
		} else {
			//실패 = 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public Customer selectOneById(String customerId) {
		//연결생성
		Connection conn = jdbcTemplate.createConnection();
		//DAO호출
		Customer customer = cDao.selectOneById(conn, customerId);
		//커밋/롤백은 SELECT에서 안해줘도 된다. 할 필요가 없음 저장하고 롤백할 필요 없으니까
		jdbcTemplate.close(conn);
		return customer;
	}

	public int deleteCustomer(String customerId) {
		//연결생성
		Connection conn = jdbcTemplate.createConnection();
		//DAO호출(연결 넘겨주기)
		int result = cDao.deleteCustomer(conn, customerId);
		//커밋/롤백
		if(result>0) {
			//성공 - 커밋
		} else {
			//실패 - 롤백
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int updateCustomer(Customer customer) {
		//연결생성
		Connection conn = jdbcTemplate.createConnection();
		//DAO호출
		int result = cDao.updateCustomer(conn,customer);
		//커밋/롤백
		if(result>0) {
			//성공 - 커밋
			jdbcTemplate.comit(conn);
		} else {
			//실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn); 
		return result;
	}

	
	
	
	

	
}

package customer.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import customer.model.vo.Customer;




public class CustomerDAO {

	public Customer selectCheckLogin(SqlSession session, Customer market) {
		Customer cOne = session.selectOne("CustomerMapper.selectCheckLogin", market);
		return cOne;
	}

	public int deleteCustomer(SqlSession session, String customerId) {
		int result = session.delete("CustomerMapper.deleteCustomer", customerId);
		return result;
	}

	public int insertCustomer(SqlSession session, Customer customer) {
		int result = session.insert("CustomerMapper.insertCustomer", customer);
		return result;
	}

	public int updateCustomer(SqlSession session, Customer customer) {
		int result = session.update("CustomerMapper,updateCustomer",customer);
		return result;
	}

	public Customer selectOneById(SqlSession session, String customerId) {
		Customer customer = session.selectOne("CustomerMapper.selectOneById",customerId);
		return customer;
	}
	
	
	

}

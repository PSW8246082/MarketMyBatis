package customer.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import customer.model.vo.Customer;




public class CustomerDAO {
	
	
	
	//DB에서 저장된 ID와 PW를 리턴함
	public Customer selectCheckLogin(Connection conn, Customer market) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Customer mOne = null;
		
		String query = "SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID = ? AND CUSTOMER_PW = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, market.getCustomerId());  //객체로 입력된 값이 넘어왔으니까 get으로 꺼내쓴다
			pstmt.setString(2, market.getCustomerPw());  //만약 costomerId로 넘어왔으면 그자체로 적어주면됨
			
			rset = pstmt.executeQuery();  //누락주의 , 결과값 rset으로 저장하기 주의!
			
			if(rset.next()) {
				mOne = rsetToCustomer(rset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mOne;
	}

	private Customer rsetToCustomer(ResultSet rset) throws SQLException {
		Customer market = new Customer();
		market.setCustomerId(rset.getString("CUSTOMER_ID"));
		market.setCustomerPw(rset.getString("CUSTOMER_PW"));
		market.setCustomerName(rset.getString("CUSTOMER_NAME"));
		market.setCustomerNo(rset.getString("CUSTOMER_NO"));
		market.setCustomerGender(rset.getString("CUSTOMER_GENDER"));
		market.setCustomerEmail(rset.getString("CUSTOMER_EMAIL"));
		market.setCustomerPhone(rset.getString("CUSTOMER_PHONE"));
		market.setCustomerDate(rset.getTimestamp("CUSTOMER_DATE"));
		market.setUpdateDate(rset.getTimestamp("UPDATE_DATE"));
		market.setCustomerYn(rset.getString("CUSTOMER_YN"));
		return market;	
	}

	//입력받은 customer객체의 값들을 DB에 저장해주고 1행이 추가됨을 리턴함 (int리턴)
	public int insertCustomer(Connection conn, Customer customer) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO CUSTOMER_TBL VALUES(?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			//jsp에서 입력된 값들을 customer 객체로 넘겨받아 DB에 넣어주는 과정 -> 입력되는 순서가 DB순서에 맞아야 한다.???
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer.getCustomerId());
			pstmt.setString(2, customer.getCustomerPw());
			pstmt.setString(3, customer.getCustomerName());
			pstmt.setString(4, customer.getCustomerNo());
			pstmt.setString(5, customer.getCustomerGender());
			pstmt.setString(6, customer.getCustomerEmail());
			pstmt.setString(7, customer.getCustomerPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	
	public Customer selectOneById(Connection conn, String customerId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID = ? ";
		Customer cOne = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerId);  
			
			
			rset = pstmt.executeQuery();    //누락주의 , 결과값 rset으로 받기 주의
			
			if(rset.next()) {
				cOne = rsetToCustomer(rset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return cOne;
	}

	public int deleteCustomer(Connection conn, String customerId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CUSTOMER_TBL WHERE CUSTOMER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateCustomer(Connection conn, Customer customer) {
		String query = "UPDATE CUSTOMER_TBL SET CUSTOMER_PW = ?, CUSTOMER_NAME = ?, CUSTOMER_PHONE = ?, CUSTOMER_EMAIL = ?, UPDATE_DATE = SYSDATE WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer.getCustomerPw());
			pstmt.setString(2, customer.getCustomerName());
			pstmt.setString(3, customer.getCustomerPhone());
			pstmt.setString(4, customer.getCustomerEmail());
			pstmt.setString(5, customer.getCustomerId());
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	

}

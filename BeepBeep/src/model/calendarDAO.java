package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class calendarDAO {
	
	
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pst = null;
	int cnt=0;
	calendarDTO dto = null;
	ArrayList<calendarDTO> al = new ArrayList<calendarDTO>();
	
	// ���������̽��� ����
	public void conn() {
		try {
		// 1. JDBC ����̹� �����ε�
				Class.forName("oracle.jdbc.driver.OracleDriver");
					
				String url = "jdbc:oracle:thin:@221.156.142.70:1521:xe";
				String user = "hr";
				String password = "hr";
					
				// 2. �����ͺ��̽� ���ᰴü(Connection)����
			    conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	//�ݱ�
	public void close() {
		
		try {
			if(rs !=null) {rs.close();}
			pst.close();
			conn.close();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
	}

	
	public ArrayList<calendarDTO> select() {
		
		// ��Ÿ�ӿ��� : �������� �� �߻��ϴ� ���� > ����ó��
		try{
			conn();
			
		    String sql = "select * from calendar";
		                                             // ? : ���ε� ����
		    // 3. sql�� ���ఴü ( PreparedStatement ) ����
		    pst = conn.prepareStatement(sql);
			
		    // 4. ���ε� ���� ä���ֱ�
		    
		    // 5. sql�� �����ϱ�
		    rs = pst.executeQuery();
		    
		    while ( rs.next() ) {
		    	String id = rs.getString(1);
		    	String title = rs.getString(2);
		    	String start = rs.getString(3);
		    	String end = rs.getString(4);
		    	String description = rs.getString(5);
		    	String type = rs.getString(6);
		    	String user = rs.getString(7);
		    	String color = rs.getString(8);
		    	String textColor = rs.getString(9);
		    	
		    	
		    	
		    	calendarDTO dto = new calendarDTO(id, title, start, end, description, type, user, color, textColor);
		    	
		    	
		    	al.add(dto);
		    	
		    	System.out.println("��ձ�");
		    }
		    
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("������");
		}finally {
			// ���������� �ݾ��� �� �����Ƿ� ���ܹ� �ѹ���
			close();
			
		}
		return al;
	}
	
	

}
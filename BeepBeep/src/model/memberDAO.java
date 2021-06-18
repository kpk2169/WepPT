package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class memberDAO {
	
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pst = null;
	int cnt=0;
	memberDTO dto =null;
	
	// 데이터페이스와 연결
		public void conn() {
			try {
			// 1. JDBC 드라이버 동적로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
						
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String user = "hr";
					String password = "hr";
						
					// 2. 데이터베이스 연결객체(Connection)생성
				    conn = DriverManager.getConnection(url, user, password);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void close() {
			
			try {
				if(rs !=null) {rs.close();}
				pst.close();
				conn.close();
				
				}catch(Exception e) {
					e.printStackTrace();
				}
		}

		
		
		
		public memberDTO login(String id, String pw) {
			try{
				conn();
				
			    String sql = "select * from member where id = ? and pw = ?";
			    pst = conn.prepareStatement(sql);
			    pst.setString(1, id);  
			    pst.setString(2, pw);
			    
			    rs = pst.executeQuery();
			    
			    if ( rs.next() ) {
			    	String get_id = rs.getString("id");
			    	String get_pw = rs.getString("pw");
			    	float get_height = rs.getFloat("height");
			    	float get_weight = rs.getFloat("weight");
			    	//1.내가 더 가지고 오고 싶은 행이 있다면 rs.getString(행이름)
			    	
			    	dto = new memberDTO(get_id, get_pw, get_height, get_weight);
			    	//2.dto안에 생성자로 더 가지고 오고싶은 데이터들을 넣어주기!
			    	System.out.println("로그인 성공");
			    }
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("로그인 실패");
			}finally {
				// 열려있으면 닫아줄 수 없으므로 예외문 한번더
				close();
			}
			return dto;
		}

		
		// 회원가입
		public int join(String id, String pw, String name, int age, int gender, String email, String tel, float height, float weight) {

			// 런타임오류 : 실행했을 때 발생하는 오류 -> 예외처리
			try {
				conn();
				
				String sql = "insert into member values('b',?,?,?,?,?,?,?,?,?)";
				// 3. sql문 실행객체(Prepared Statement)생성
				pst = conn.prepareStatement(sql);

				// 4. 바인드 변수 채우기
				pst.setString(1, id);
				pst.setString(2, pw);
				pst.setString(3, name);
				pst.setInt(4, age);
				pst.setInt(5, gender);
				pst.setString(6, email);
				pst.setString(7, tel);
				pst.setFloat(8, height);
				pst.setFloat(9, weight);

				// 5. sql문 실행하여 결과처리
				cnt = pst.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("memberDAO 오류");
			} finally {
				close();
			}
			return cnt;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		

}

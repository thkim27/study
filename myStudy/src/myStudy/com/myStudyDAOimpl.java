package myStudy.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class myStudyDAOimpl implements myStudyDAO {
	
	private Connection conn = null; // 전역변수는 적어도 null 안적어도 null 커넥션용 객체   
	private PreparedStatement pstmt; // 질의문 요청용 객체
	private ResultSet rs; // select 질의문 응답객체

	private String driver_name = "oracle.jdbc.OracleDriver"; //드라이버 이름
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe"; //url
	private final String user = "HR"; //접속 id
	private final String password = "h123456"; //접속 비밀번호
	//------------------------오라클에 있는 테이블 접속용
	
	public myStudyDAOimpl() {
		System.out.println("myStudyDAOimpl()..."); // 실행 확인용
		try {
			Class.forName(driver_name);
			System.out.println("Driver successed..."); // 접속되면 성공 띄우기
		} catch (ClassNotFoundException e) { //클래스 못찾았으면 익셉션
			e.printStackTrace(); 
		}
	}

	@Override
	public List<myStudyVO> selectAll() {
		System.out.println("myStudy selectAll()..."); //함수 확인
		List<myStudyVO> vos = new ArrayList<>();
		try { //conn = DriverManager.getConnection 작성하면 트라이캐치나 스로우 나옴
			conn = DriverManager.getConnection(url, user, password);//드라이버 연결
			System.out.println("conn successed....."); //드라이버 연결 확인
			
			pstmt = conn.prepareStatement("select * from emp"); //쿼리문 emp에서 가져옴
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				myStudyVO vo = new myStudyVO(); //값넣을 vo 생성
				vo.setEmployee_id(rs.getInt("employee_id")); //employee_id  값 가져옴
				vo.setFirst_name(rs.getString("first_name"));
				vo.setLast_name(rs.getString("last_name"));
				vo.setJob_id(rs.getString("job_id"));
				vo.setHire_date(rs.getDate("hire_date"));
				vo.setSalary(rs.getInt("salary"));
				vos.add(vo); //vos에 값받은것 추가
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} //null이 아닐경우 실행되어서 close 해줌

		return vos;

		
		
		
		
	}

	@Override
	public myStudyVO selectOne(myStudyVO vo) {
		System.out.println("selectOne()... \n" + "넘어온 값:" + vo);

		myStudyVO vo2 = new myStudyVO(); //vo2 생성 

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn successed...");

			pstmt = conn.prepareStatement("select * from emp where employee_id = ?");
			pstmt.setInt(1, vo.getEmployee_id()); // vo로 넘어온거 ?에 넣음 

			rs = pstmt.executeQuery();

			while (rs.next()) { //vo2에 넘어온값 넣음
				vo2.setEmployee_id(rs.getInt("employee_id"));
				vo2.setFirst_name(rs.getString("first_name"));
				vo2.setLast_name(rs.getString("last_name"));
				vo2.setJob_id(rs.getString("job_id"));
				vo2.setHire_date(rs.getDate("hire_date"));
				vo2.setSalary(rs.getInt("salary"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return vo2; //vo2리턴
	}

	@Override
	public List<myStudyVO> searchOneList(int employeeid) {
		System.out.println("searchOneList..." + employeeid + "값 검색");
		
		List<myStudyVO> vos = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn successed...");

			String sql = "select * from emp where employee_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeid); // 찾을 값

			rs = pstmt.executeQuery();

			while (rs.next()) {
				myStudyVO vo = new myStudyVO(); //값넣을 vo 생성
				vo.setEmployee_id(rs.getInt("employee_id")); //employee_id  값 가져옴
				vo.setFirst_name(rs.getString("first_name"));
				vo.setLast_name(rs.getString("last_name"));
				vo.setJob_id(rs.getString("job_id"));
				vo.setHire_date(rs.getDate("hire_date"));
				vo.setSalary(rs.getInt("salary"));
				vos.add(vo); //vos에 값받은것 추가
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return vos;
	}

	@Override
	public List<myStudyVO> searchList(String searchKey, String searchWorld) {
		System.out.println("searchList...");
		System.out.println("searchList..." + searchKey);
		System.out.println("searchList..." + searchWorld);

		List<myStudyVO> vos = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn successed...");

			String sql = "";
			if (searchKey.equals("first_name")) {
				sql = "select * from emp where first_name like ?";
			} else if (searchKey.equals("last_name")) {
				sql = "select * from emp where last_name like ?";
			} else if (searchKey.equals("job_id")) {
				sql = "select * from emp where job_id like ?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWorld + "%"); // 찾을 값

			rs = pstmt.executeQuery();

			while (rs.next()) {
				myStudyVO vo = new myStudyVO();
				vo.setEmployee_id(rs.getInt("employee_id")); //employee_id  값 가져옴
				vo.setFirst_name(rs.getString("first_name"));
				vo.setLast_name(rs.getString("last_name"));
				vo.setJob_id(rs.getString("job_id"));
				vo.setHire_date(rs.getDate("hire_date"));
				vo.setSalary(rs.getInt("salary"));
				vos.add(vo); //vos에 값받은것 추가
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return vos;
	}

	@Override
	public int insert(myStudyVO vo) {

		System.out.println("insert()......." + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn successed...");

			pstmt = conn
					.prepareStatement("insert into emp(employee_id, first_name, last_name, job_id, hire_date, salary) "
							+ "values(?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, vo.getEmployee_id());
			pstmt.setString(2, vo.getFirst_name());
			pstmt.setString(3, vo.getLast_name());
			pstmt.setString(4, vo.getJob_id());
			pstmt.setDate(5, vo.getHire_date());
			pstmt.setInt(6, vo.getSalary());
			
			flag = pstmt.executeUpdate(); // insert, update, delete;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return flag;
	}

	@Override
	public int update(myStudyVO vo) {
		System.out.println("update()......." + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn successed...");

			pstmt = conn.prepareStatement(
					"update emp set first_name = ?, last_name = ?, job_id = ?, hire_date = ?, salary =?  where employee_id = ? ");

			pstmt.setString(1, vo.getFirst_name());
			pstmt.setString(2, vo.getLast_name());
			pstmt.setString(3, vo.getJob_id());
			pstmt.setDate(4, vo.getHire_date());
			pstmt.setInt(5, vo.getSalary());
			pstmt.setInt(6, vo.getEmployee_id());

			flag = pstmt.executeUpdate(); // insert, update, delete;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return flag;
	}

	@Override
	public int delete(myStudyVO vo) {
		System.out.println("delete().......\n" + "넘어온 값:" + vo);
		int flag = 0;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn successed...");
			
			pstmt = conn.prepareStatement("delete from emp where employee_id = ?");
			
			pstmt.setInt(1, vo.getEmployee_id());
			
			flag = pstmt.executeUpdate(); // insert, update, delete;		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}

}

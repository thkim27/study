package myStudy.com;

import java.sql.Date;
import java.util.List;

public class myStudyMain {

	public static void main(String[] args) {
		System.out.println("hello mystudy");
		
		myStudyDAO dao = new myStudyDAOimpl(); //impl 사용할 dao생성
		
		myStudyVO vo = new myStudyVO(); // vo 생성
		
		//Date 에 넣을 sqldate 작성 java.util.date에서 시분초까지 현재 시간을 가져온다
		java.util.Date utilDate = new java.util.Date();
		//sql date 형식으로 변환 DATE는 시분초 값 안까지고 2000-00-00 형식의 값으로 변환
		Date sqldate = new Date(utilDate.getTime());
			
		//select All
		List<myStudyVO> vos = dao.selectAll(); //vos는 selectall
		System.out.println("레코드값 몇개임? : " + vos.size()); //받은 vos의 레코드값 몇개
		System.out.println("for(myStudyVO x : vos {print(x.get)}형식으로 출력한것");
		for(myStudyVO x : vos) {
			System.out.print(x.getEmployee_id() + " ");
			System.out.print(x.getFirst_name() + " ");
			System.out.print(x.getLast_name() + " ");
			System.out.print(x.getJob_id() + " ");
			System.out.print(x.getHire_date() + " ");
			System.out.println(x.getSalary() + " ");
		}
		
		//vos.forEach(System.out::println); foreach사용해서 출력가능 아마 tostring 계속 반복인듯
		
		//selectOne
		
		vo = new myStudyVO();
		vo.setEmployee_id(115); //검색할 employee_id 세팅
		myStudyVO vo2 = dao.selectOne(vo);
		System.out.println(vo2);
		
		//selectOneList
		vos = dao.searchOneList(115);
		vos.forEach(System.out::println);
		
		//selectList
		
		vos = dao.searchList("first_name", "kungfu"); // first_name, last_name, job_id 찾기가능
		vos.forEach(System.out::println);
		
		//insert employee_id, first_name, last_name, job_id, hire_date, salary 순
		vo.setEmployee_id(15);
		vo.setFirst_name("gildong");
		vo.setLast_name("Hong");
		vo.setJob_id("DOSA");
		vo.setHire_date(sqldate);
		vo.setSalary(22000);
		
		int result = dao.insert(vo);
		System.out.println("insert result:" + result);
			
		
		//update
		
		vo = new myStudyVO();
		vo.setEmployee_id(15);      //where에 들어갈 employee_id 
		vo.setFirst_name("kungfu");
		vo.setLast_name("fighter");
		vo.setJob_id("AD ART");
		vo.setHire_date(sqldate);
		vo.setSalary(23000);
		
		result = dao.update(vo);
		System.out.println("update result:" + result);
			
		//delete
		
		vo = new myStudyVO();
		vo.setEmployee_id(12); // employee_id 11번 세팅
		result = dao.delete(vo); // result
		System.out.println("delete result:" + result);
		
		vos = dao.selectAll();
		vos.forEach(System.out::println);

		
		
		
	}

}

package myStudy.com;

import java.util.List;

public interface myStudyDAO {
	
	public List<myStudyVO> selectAll();

	public myStudyVO selectOne(myStudyVO vo);
	
	public List<myStudyVO> searchOneList(int employeeid); //selectOne 리스트로 사용한것

	public List<myStudyVO> searchList(String searchKey, String searchWorld);

	public int insert(myStudyVO vo);

	public int update(myStudyVO vo);

	public int delete(myStudyVO vo);

}

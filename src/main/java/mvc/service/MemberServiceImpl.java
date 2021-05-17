package mvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.models.MemberDAO;
import mvc.models.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberDTO memberDTO) throws Exception {

		memberDAO.insertMember(memberDTO);
		
	}


	@Override
	public MemberDTO getDetail(long no) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int loginMember(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		
		int result=memberDAO.loginMember(memberDTO);
		return result;
	}

	@Override
	public List<MemberDTO> getMemberList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
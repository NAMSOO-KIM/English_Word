package mvc.service;

import java.util.List;

import mvc.models.MemberDTO;



public interface MemberService {

	int loginMember(MemberDTO memberDTO) throws Exception;
	
	void insertMember(MemberDTO memberDTO) throws Exception;
	
	
	
	List<MemberDTO> getMemberList() throws Exception;

	MemberDTO getDetail(long no) throws Exception;

	
}
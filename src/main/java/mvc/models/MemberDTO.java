package mvc.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberDTO implements Serializable {
	
	private long id;
	private String user_id;
	private String password;
	private String name;
	
}

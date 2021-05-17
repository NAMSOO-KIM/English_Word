package mvc.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class WordDTO implements Serializable {
	
	private long id;
	private String member_id;
	private String question;
	private String answer;
	
	
}

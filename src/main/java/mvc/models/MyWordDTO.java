package mvc.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class MyWordDTO implements Serializable {
	private long id;
	private long word_id;
	private String member_id;
	private String question;
	private String answer;
}

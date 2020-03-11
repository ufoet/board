package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor //모든 멤버변수가 포함되어 있는 생성자
@NoArgsConstructor //default생성자
public class BoardVO {
	private int bno;
	private String name;
	private String password;
	private String title;
	private String content;
	private String attach;
	private int re_ref;
	private int re_lev;
	private int re_seq;
	private int readcount;	
	private Date regdate;
	
}

package models;

import java.sql.Date;

public class Notice{
	String title;
	String content;
	String file;
	Date timestamp;
	public Notice(String title,String content, String file,Date timestamp) {
		this.title = title;
		this.content = content;
		this.file = file;
		this.timestamp = timestamp;
	}
	public String getTitle() {return title;}
	public String getContent() {return content;};
	public String getFile() {return file;}
	public Date getTimestamp() {return timestamp;}
}
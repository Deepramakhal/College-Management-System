package models;

public class Notice{
	private String id;
	private String title;
	private String content;
	private String batch_id;
	private String uploader_name;
	public Notice(String id,String title,String content,String batch_id,String uploader_name){
		this.id=id;
		this.title=title;
		this.content=content;
		this.batch_id=batch_id;
		this.uploader_name=uploader_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getUploader_name() {
		return uploader_name;
	}
	public void setUploader_name(String uploader_name) {
		this.uploader_name = uploader_name;
	}
	
	}
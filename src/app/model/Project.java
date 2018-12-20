package app.model;

public class Project {

	public int projectId;
	public String projectAuthor;
	public String projectDate;
	public String projectType;
	public String projectName;
	public String projectDescription;
	public String projectDownloadLink;
	public String projectThumbnail;
	
	
	public Project(int id, String name) {
		this(id, name, null);
	}

	public Project(int id, String name, String description) {
		this(id, name, description, null);
	}
	
	public Project(int id, String name, String description, String downloadLink) {
		this(id, name, description, downloadLink, null);
	}
	
	public Project(int id, String name, String description, String downloadLink, String thumbnail) {
		this(id, name, description, downloadLink, thumbnail, null, null, null);
	}
	
	public Project(int id, String name, String description, String date, String author, String type, String thumbnail) {
		this(id, name, description, date, author, type, thumbnail, null);
	}
	
	public Project(int id, String name, String description, String date, String author, String type, String thumbnail, String downloadLink) {
		projectId = id;
		projectName = name;
		projectDescription = description;
		projectThumbnail = thumbnail;
		projectDate = date;
		projectAuthor = author;
		projectType = type;
		if(downloadLink.equals("")) {
			projectDownloadLink = null;
		} else {
			projectDownloadLink = downloadLink;
		}
		
	}
	
	
}

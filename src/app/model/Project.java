package app.model;

public class Project {

	public int projectId;
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
		projectId = id;
		projectName = name;
		projectDescription = description;
		projectDownloadLink = downloadLink;
		projectThumbnail = thumbnail;
	}
	
	
}

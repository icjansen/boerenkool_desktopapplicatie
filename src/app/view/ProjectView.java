package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.model.Project;
import core.UI.views.UIImageView;
import core.UI.views.UILabel;
import core.UI.views.UIView;
import core.util.Base64converter;

@SuppressWarnings("serial")
public class ProjectView extends UIView {
	
	private UILabel titleLabel;
	
	private UIImageView imagePane;
	
	private Project storedContent;
	

	public ProjectView(Project project) {
		storedContent = project;
		titleLabel = new UILabel(project.projectName, SwingConstants.CENTER);
		titleLabel.setFont(new Font("helvetica neue", Font.BOLD, 40));
		BufferedImage convertedImage = Base64converter.decodeToImage(project.projectThumbnail);
		imagePane = new UIImageView(convertedImage);
		imagePane.setBackground(new Color(205, 205, 0));
		setPreferredSize(new Dimension(0, 500));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(new Color(122, 95, 166));
//		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setForeground(new Color(255, 255, 255));
		
		setupLayout();
		layoutSubviews();
	}
	
	public void setupLayout() {
		setLayout(new BorderLayout());
		imagePane.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	public void layoutSubviews() {
		add(imagePane, BorderLayout.CENTER);
		add(titleLabel, BorderLayout.SOUTH);
	}
	
	public Project getProject() {
		return storedContent;
	}

}

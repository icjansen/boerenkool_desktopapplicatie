package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.model.Project;
import core.UI.UIImage;
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
		titleLabel.setFont(new Font("helvetica neue", Font.BOLD, 30));
		new Thread() {
			@Override
			public void run() {
				try {
					UIImage image = new UIImage(new URL("http://boes.holidayrentcenter.com/boerenkool_cms/" + project.projectThumbnail));
					imagePane = new UIImageView(image);
				} catch (MalformedURLException e) {
					imagePane = new UIImageView();
				} catch (IOException e) {
					imagePane = new UIImageView();
				}
			}
		}.run();
		imagePane.setBackground(new Color(205, 205, 0));
		setPreferredSize(new Dimension(0, 500));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(new Color(122, 95, 166));
		titleLabel.setForeground(new Color(255, 255, 255));
		
		setupLayout();
		layoutSubviews();
	}
	
	public void setupLayout() {
		setLayout(new BorderLayout());
		imagePane.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	public void layoutSubviews() {
		UIView titleView = new UIView();
		titleView.setLayout(new BorderLayout());
		titleView.setBackground(new Color(205, 205, 0));
		titleView.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(imagePane, BorderLayout.CENTER);
		add(titleView, BorderLayout.SOUTH);
		titleView.add(titleLabel, BorderLayout.CENTER);
	}
	
	public Project getProject() {
		return storedContent;
	}

}

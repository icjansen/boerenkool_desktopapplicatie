package app.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.model.Project;
import core.UI.UIImage;
import core.UI.UIViewController;
import core.UI.views.UIButton;
import core.UI.views.UIImageView;
import core.UI.views.UILabel;
import core.UI.views.UITextArea;
import core.UI.views.UIView;

public class DetailViewController extends UIViewController {
	
	public UIButton dismissButton = new UIButton();

	public UILabel titleLabel = new UILabel("");
	
	public UITextArea descriptionLabel = new UITextArea("");
	
	public UILabel authorLabel = new UILabel("Gemaakt door: ");
	
	public UILabel dateLabel = new UILabel("Gemaakt op: ");
	
	public UILabel typeLabel = new UILabel("Type: ");
	
	public UIButton downloadButton = new UIButton("Download");
	
	public UIImageView imageView;
	
	public UIView leftView = new UIView();
	
	public UIView rightView = new UIView();
	
	public UIView bottomLeft = new UIView();
	
	public Project project;
	
	
	public DetailViewController(Project project) {
		super();
		this.project = project;
		
		dismissButton.setText("Close");

		titleLabel.setText(project.projectName);
		descriptionLabel.setText(project.projectDescription);
		new Thread() {
			@Override
			public void run() {
				try {
					UIImage image = new UIImage(new URL("http://boes.holidayrentcenter.com/boerenkool_cms/" + project.projectThumbnail));
					imageView = new UIImageView(image);
					authorLabel.setText("Gemaakt door: " + project.projectAuthor);
					dateLabel.setText("Gemaakt op: " + project.projectDate);
					typeLabel.setText("Type: " + project.projectType);
				} catch (MalformedURLException e) {
					imageView = new UIImageView();
				}
			}
		}.run();
		
		UIViewController that = this;
		dismissButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				that.dismiss();
			}
		});
		downloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					URI uri = new URI(project.projectDownloadLink);
					Desktop dt = Desktop.getDesktop();
					dt.browse(uri);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		layoutSubviews();
	}
	
	@Override
	public void layoutSubviews() {
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		authorLabel.setForeground(Color.WHITE);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(Color.WHITE);
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setForeground(Color.WHITE);
		descriptionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		descriptionLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Helvetica neue", Font.BOLD, 20));
		titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		titleLabel.setForeground(Color.WHITE);
		bottomLeft.setBackground(new Color(122, 95, 166));
		rightView.setBackground(new Color(122, 95, 166));
		view.setLayout(new GridLayout(1, 2));
		view.add(leftView);
		view.add(rightView);
		leftView.setLayout(new BorderLayout());
		leftView.add(imageView, BorderLayout.CENTER	);
		leftView.add(bottomLeft, BorderLayout.SOUTH);
		rightView.setLayout(new BorderLayout());
		rightView.add(titleLabel, BorderLayout.NORTH);
		rightView.add(descriptionLabel, BorderLayout.CENTER);
		bottomLeft.setLayout(new GridLayout(0, 1, 10, 10));
		bottomLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		bottomLeft.add(authorLabel);
		bottomLeft.add(dateLabel);
		bottomLeft.add(typeLabel);
		if(project.projectDownloadLink != null) {
			bottomLeft.add(downloadButton);
		}
		bottomLeft.add(dismissButton);
	}
	
	
}

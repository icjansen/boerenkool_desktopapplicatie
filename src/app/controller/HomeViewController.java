package app.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import app.AppDelegate;
import app.LoadingWindow;
import app.model.Project;
import app.services.AppService;
import app.view.ProjectView;
import core.UI.UIViewController;
import core.UI.UIWindow;
import core.UI.views.UIButton;
import core.UI.views.UIScrollView;
import core.UI.views.UISplitView;
import core.UI.views.UITextField;
import core.UI.views.UIView;

@SuppressWarnings("serial")
public class HomeViewController extends UIViewController implements MouseListener {
	
	private UISplitView contentPanel;
	
	private UIView centerPane;
	
	private UIView sidePane;
	
	private UIScrollView scrollView;
	
	private UIView postsPane;

	private UITextField searchTextField;
	
//	private UIButton reloadButton = new UIButton();
	
	private UIButton[] tabs;
	
	private ArrayList<ProjectView> posts;
	
	public String projectTypeString = "";
	
	
	public HomeViewController() {
		super();
	}
	
	@Override
	public void viewWillLoad() {
		super.viewWillLoad();
		
		contentPanel = new UISplitView();
		centerPane = new UIView();
		sidePane = new UIView();
		postsPane = new UIView();
		scrollView = new UIScrollView(postsPane);
		searchTextField = new UITextField();
		tabs = new UIButton[5];
		posts = new ArrayList<ProjectView>();
		
		view.setBackground(new Color(122, 95, 166));
		contentPanel.setBackground(new Color(122, 95, 166));
		centerPane.setBackground(new Color(122, 95, 166));
		sidePane.setBackground(new Color(122, 95, 166));
		postsPane.setBackground(new Color(122, 95, 166));
		searchTextField.setPlaceholder("Search");
		searchTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = e.getActionCommand();
				fetchAndLayoutPosts(searchText);
			}
		});
		
		setupLayout();
		fetchAndLayoutPosts();		
		layoutSubviews();
	}
	
	@Override
	public void layoutSubviews() {
		view.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setRightComponent(centerPane);
		contentPanel.setLeftComponent(sidePane);
		centerPane.add(searchTextField, BorderLayout.NORTH);
		centerPane.add(scrollView, BorderLayout.CENTER);
		scrollView.setVerticalScrollBarPolicy(UIScrollView.VERTICAL_SCROLLBAR_ALWAYS);
		
		layoutSidebar();
	}

	public void setupLayout() {
		view.setLayout(new BorderLayout());
		postsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		postsPane.setLayout(new GridLayout(0, 2, 10, 10));
		centerPane.setLayout(new BorderLayout());
		centerPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		sidePane.setBorder(new EmptyBorder(10, 10, 10, 10));
		sidePane.setLayout(new GridLayout(0, 1, 10, 10));
	}
	
	public void layoutSidebar() {
		tabs[0] = new UIButton("All");
		tabs[1] = new UIButton("Applicaties");
		tabs[2] = new UIButton("Games");
		tabs[3] = new UIButton("Websites");
		tabs[4] = new UIButton("Voeg project toe");
		tabs[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				projectTypeString = "";
				fetchAndLayoutPosts();
			}
		});
		tabs[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				projectTypeString = "desktop";
				fetchAndLayoutPosts();
			}
		});
		tabs[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				projectTypeString = "game";
				fetchAndLayoutPosts();
			}
		});
		tabs[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				projectTypeString = "web";
				fetchAndLayoutPosts();
			}
		});
		tabs[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					URI uri = new URI("http://boes.holidayrentcenter.com/boerenkool_cms/");
					Desktop dt = Desktop.getDesktop();
					dt.browse(uri);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		sidePane.add(tabs[0]);
		sidePane.add(tabs[1]);
		sidePane.add(tabs[2]);
		sidePane.add(tabs[3]);
		sidePane.add(tabs[4]);
	}
	
	public void fetchAndLayoutPosts() {
		fetchAndLayoutPosts("");
	}
	
	public void fetchAndLayoutPosts(String searchText) {
		searchTextField.setText("");
		System.out.println("Loading...");
		String query;
		if(projectTypeString == null || projectTypeString.equals("")) {
			if(searchText.equals("")) {
				query = "SELECT * FROM project";
			} else {
				query = "SELECT * FROM project WHERE project_name LIKE '%" + searchText + "%' OR student_name LIKE '%" + searchText + "%' OR project_year LIKE '%" + searchText + "%' OR course_name LIKE '%" + searchText + "%' OR study LIKE '%" + searchText + "%'";
			}
		} else {
			if(searchText.equals("")) {
				query = "SELECT * FROM project WHERE type = '" + projectTypeString + "'";
			} else {
				query = "SELECT * FROM project WHERE type = '" + projectTypeString + "' AND (project_name LIKE '%" + searchText + "%' OR student_name LIKE '%" + searchText + "%' OR project_year LIKE '%" + searchText + "%' OR course_name LIKE '%" + searchText + "%' OR study LIKE '%" + searchText + "%')";
			}
		}
		System.out.println(query);
		HomeViewController self = this;
		new Thread() {
			public void run() {
				Connection connection = AppService.instance.connection;
				try {
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(query);
			        posts.clear();
			        postsPane.removeAll();
					while(rs.next()) {
				        int id = rs.getInt(1);
				        String name = rs.getString(2);
				        String description = rs.getString(3);
				        String year = rs.getString(4);
				        String author = rs.getString(5);
				        String type = rs.getString(9);
				        String downloadLink = rs.getString(6);
				        String image = rs.getString(8);
				        Project project = new Project(id, name, description, year, author, type, image, downloadLink);
				        ProjectView post = new ProjectView(project);
				        post.addMouseListener(self);
						posts.add(post);
						postsPane.add(posts.get(posts.size() - 1));
					}
					AppDelegate.window.validate();
					System.out.println("Done!");
				} catch(SQLException e) {
					System.err.println("Database error");
				}
			}
		}.run();
	}
	
	public void viewTap(ProjectView projectView) {
		ProjectView temp = projectView;
		Project project = temp.getProject();
		DetailViewController detailViewController = new DetailViewController(project);
		present(detailViewController);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		viewTap((ProjectView) e.getComponent());
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		viewTap((ProjectView) e.getComponent());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	
}

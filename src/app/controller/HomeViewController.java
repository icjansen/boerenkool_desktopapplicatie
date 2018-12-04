package app.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

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
		tabs = new UIButton[3];
		posts = new ArrayList<ProjectView>();
		
		view.setBackground(new Color(122, 95, 166));
		contentPanel.setBackground(new Color(122, 95, 166));
		centerPane.setBackground(new Color(122, 95, 166));
		sidePane.setBackground(new Color(122, 95, 166));
		postsPane.setBackground(new Color(122, 95, 166));
		searchTextField.setPlaceholder("Search");
		
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
		sidePane.add(tabs[0] = new UIButton("Applicaties"));
		sidePane.add(tabs[1] = new UIButton("Games"));
		sidePane.add(tabs[2] = new UIButton("Websites"));
	}
	
	public void fetchAndLayoutPosts() {
		HomeViewController self = this;
		new Thread() {
			public void run() {
				Connection connection = AppService.instance.connection;
				try {
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM project");
					while(rs.next()) {
				        int id = rs.getInt(1);
				        String name = rs.getString(2);
				        String description = rs.getString(3);
				        String downloadLink = rs.getString(6);
				        String image = rs.getString(8);
				        Project project = new Project(id, name, description, downloadLink, image);
				        ProjectView post = new ProjectView(project);
				        post.addMouseListener(self);
						posts.add(post);
						postsPane.add(posts.get(posts.size() - 1));
						System.out.println(id + ", " + name + ", " + image);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}.run();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ProjectView temp = (ProjectView) e.getComponent();
		Project project = temp.getProject();
		DetailViewController detailViewController = new DetailViewController();
		present(detailViewController);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	
}

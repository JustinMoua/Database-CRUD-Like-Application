package moua_justin_app.frontend.authenticated.selection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoggedInJF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggedInJF frame = new LoggedInJF(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoggedInJF(int intUserID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("What would you like to do?");
		lblTitle.setBounds(334, 215, 603, 61);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
		contentPane.add(lblTitle);
		
		//We can see that in this button, there is an instantiation of 
		//of a variable of the SelectWatchListJF class. 
		//Then, the intUserID that was passed from LoggedInJF is passed into them
		//along with a hardcoded string to indicate what the user wants to do. 
		JButton btnCreateOrEditWatchlist = new JButton("Create/Edit Watchlist");
		btnCreateOrEditWatchlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectWatchListJF selectWatchList = new SelectWatchListJF(intUserID, "edit");
				selectWatchList.setVisible(true);
				dispose();
			}
		});

		btnCreateOrEditWatchlist.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCreateOrEditWatchlist.setBounds(158, 410, 340, 106);
		contentPane.add(btnCreateOrEditWatchlist);
		
		//We can see that in this button, there is an instantiation of 
		//of a variable of the SelectWatchListJF class. 
		//Then, the intUserID that was passed from LoggedInJF is passed into them
		//along with a hardcoded string to indicate what the user wants to do. 
		JButton btnViewMyWatchists = new JButton("View My Watchlists");
		btnViewMyWatchists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectWatchListJF selectWatchList = new SelectWatchListJF(intUserID, "view");
				selectWatchList.setVisible(true);
				dispose();
			}
		});
		btnViewMyWatchists.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnViewMyWatchists.setBounds(805, 410, 266, 106);
		contentPane.add(btnViewMyWatchists);
	}

}

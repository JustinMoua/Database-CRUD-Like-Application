package moua_justin_app.frontend.authenticated.selection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import moua_justin_app.frontend.authenticated.watchlist_editing.EditingCompletedWatchListJF;
import moua_justin_app.frontend.authenticated.watchlist_editing.EditingCurrentlyWatchingListJF;
import moua_justin_app.frontend.authenticated.watchlist_editing.EditingPlanToWatchListJF;
import moua_justin_app.frontend.authenticated.watchlist_viewing.ViewingCompletedWatchList;
import moua_justin_app.frontend.authenticated.watchlist_viewing.ViewingCurrentlyWatchingList;
import moua_justin_app.frontend.authenticated.watchlist_viewing.ViewingPlanToWatchList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectWatchListJF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectWatchListJF frame = new SelectWatchListJF(0, null);
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
	public SelectWatchListJF(int intUserID, String strEditOrViewing) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the watchlist!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(372, 35, 457, 116);
		contentPane.add(lblNewLabel);
		
		//strEditOrViewing that is passed from LoggedInJF.java is checked in here to 
		//tell the program whether the user wants to view or edit their watchlist!
		JButton btnPlan2WatchList = new JButton("Plan To Watch List");
		btnPlan2WatchList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnPlan2WatchList.setBounds(36, 434, 303, 108);
		contentPane.add(btnPlan2WatchList);
		btnPlan2WatchList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (strEditOrViewing == "edit") {
					System.out.println("Editing P2WL");
					EditingPlanToWatchListJF EP2WL = new EditingPlanToWatchListJF(intUserID);
					EP2WL.setVisible(true);
					dispose();
				}
				else if (strEditOrViewing == "view") {
					System.out.println("Viewing P2WL");
					ViewingPlanToWatchList vP2WL = new ViewingPlanToWatchList(intUserID);
					vP2WL.setVisible(true);
					dispose();
				}
				else {
					System.out.println("Null");
				}
				
			}
		});

		//strEditOrViewing that is passed from LoggedInJF.java is checked in here to 
		//tell the program whether the user wants to view or edit their watchlist!
		JButton btnCurrentlyWatchingList = new JButton("Currently Watching List");
		btnCurrentlyWatchingList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCurrentlyWatchingList.setBounds(401, 434, 390, 108);
		contentPane.add(btnCurrentlyWatchingList);
		btnCurrentlyWatchingList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (strEditOrViewing == "edit") {
					System.out.println("Editing P2WL");
					EditingCurrentlyWatchingListJF eCurrWatching = new EditingCurrentlyWatchingListJF(intUserID);
					eCurrWatching.setVisible(true);
					dispose();
				}
				else if (strEditOrViewing == "view") {
					System.out.println("Viewing Currently Watching List");
					ViewingCurrentlyWatchingList vCurrWatching = new ViewingCurrentlyWatchingList(intUserID);
					vCurrWatching.setVisible(true);
					dispose();
				}
				else {
					System.out.println("Null");
				}
				
			}
		});
		
		//strEditOrViewing that is passed from LoggedInJF.java is checked in here to 
		//tell the program whether the user wants to view or edit their watchlist!
		JButton btnCompletedWatchList = new JButton("Completed Watch List");
		btnCompletedWatchList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCompletedWatchList.setBounds(846, 434, 390, 108);
		contentPane.add(btnCompletedWatchList);
		btnCompletedWatchList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (strEditOrViewing == "edit") {
					System.out.println("Editing P2WL");
					EditingCompletedWatchListJF eComplWatching = new EditingCompletedWatchListJF(intUserID);
					eComplWatching.setVisible(true);
					dispose();
				}
				else if (strEditOrViewing == "view") {
					System.out.println("Viewing Completed Watching List");
					ViewingCompletedWatchList vComplWatching = new ViewingCompletedWatchList(intUserID);
					vComplWatching.setVisible(true);
					dispose();
				}
				else {
					System.out.println("Null");
				}
				
			}
		});
		
		
	}

}

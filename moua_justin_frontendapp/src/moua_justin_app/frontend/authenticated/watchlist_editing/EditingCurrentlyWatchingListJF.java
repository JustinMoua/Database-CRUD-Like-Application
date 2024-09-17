package moua_justin_app.frontend.authenticated.watchlist_editing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import moua_justin_app.frontend.authenticated.selection.LoggedInJF;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class EditingCurrentlyWatchingListJF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
    // JDBC variables for opening and managing connection
    Connection connection = null;
    private JTextField tfUserMovieIDInput;
    
    
	public void EstablishConn() {
	    //============DB CONN STARTS HERE============DB CONN STARTS HERE============DB CONN STARTS HERE============
	    // JDBC URL, username, and password of MySQL server
	    String url = "jdbc:mysql://localhost:3306/dbMywatchList"; //GRADER MUST CHANGE ACCORDINGLY IF NEEDED
	    String user = "root"; //GRADER MUST CHANGE ACCORDINGLY IF NEEDED
	    String password = "1234"; //GRADER MUST CHANGE ACCORDINGLY IF NEEDED

	
	    try {
	        // Open a connection
	    	//connection = DriverManager.getConnection(url, user, password);
	    	connection=(Connection)DriverManager.getConnection(url, user, password);
	        
	        if (connection != null) {
	        	System.out.println("database is connected");
	        }
	
	        // Perform database operations here...
	
	    } catch (Exception e) {
	        System.out.println("Connection failed.");
	    }
	    //============DB CONN ENDS HERE===============DB CONN ENDS HERE===============DB CONN ENDS HERE==============
	}
	public void closeConnection() {
	    try {
	        if (connection != null) {
	            connection.close();
	            System.out.println("Connection closed successfully.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditingCurrentlyWatchingListJF frame = new EditingCurrentlyWatchingListJF(0);
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
	public EditingCurrentlyWatchingListJF(int intUserID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 1244, 270);
		contentPane.add(scrollPane);
		
		//This helped a lot with the jtable https://youtu.be/_SP0Y7XN6Sk?si=9Qn9rb3NhzOylmLk
		//This helped with the mysql database stuff: https://youtu.be/frafcK6fhdQ?si=DM5sn77cH2xlxWng
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Genre", "Release Year", "Director", "Duration (Minutes)", "Release Date", "Actor Count"
			}
		));
		
		tfUserMovieIDInput = new JTextField();
		tfUserMovieIDInput.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tfUserMovieIDInput.setBounds(260, 521, 61, 71);
		contentPane.add(tfUserMovieIDInput);
		tfUserMovieIDInput.setColumns(10);
		
		JLabel lblTypeInMovieID = new JLabel("<html>Type in the<br>movie id here!</html>");
		lblTypeInMovieID.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTypeInMovieID.setBounds(67, 499, 158, 108);
		contentPane.add(lblTypeInMovieID);
		
	    EstablishConn();
	    try {
	        String query = "SELECT * FROM tblMovie_or_TVShow";

	        PreparedStatement statement = connection.prepareStatement(query);
	        // Execute the query and get the results
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	String strId = String.valueOf(resultSet.getInt(1));
	        	String strTitle = resultSet.getString(2);
	        	String strGenre = resultSet.getString(3);
	        	String strReleaseYear = resultSet.getString(4);
	        	String strDirector = resultSet.getString(5);
	        	String strDuration = resultSet.getString(6);
	        	String strReleaseDate = resultSet.getString(7);
	        	String strActorCount = resultSet.getString(8);
	        	
	        	String tbData[] = {strId, strTitle, strGenre, strReleaseYear, strDirector, strDuration, strReleaseDate, strActorCount};
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
	        	tblModel.addRow(tbData);
	        }
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
		

		/*
		 * DBConnection DBConnection = new DBConnection();
		 * 
		 * String strMovies = DBConnection.selectAll("tblMovie_or_TVShow");
		 * System.out.println(strMovies);
		 */
		JLabel lblNewLabel = new JLabel("Please type in the ID number of the movie you want to add to your Plan to Watch list!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(208, 32, 995, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnAddMovie = new JButton("Add");
		btnAddMovie.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnAddMovie.setBounds(349, 517, 178, 75);
		contentPane.add(btnAddMovie);
		
		btnAddMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        	    //=================STILL NEED TO WORK ON THIS!==================
        	    try {
        	    	EstablishConn();
    		        String strMovieId = tfUserMovieIDInput.getText();
    		        int intMovieId = Integer.parseInt(strMovieId);

    		        String query = "INSERT INTO tblcurrentlywatching (user_id, movie_or_tvshow_id) VALUES (?, ?)";

        	        PreparedStatement statement = connection.prepareStatement(query);

                    // Setting values for the parameters
        	        statement.setInt(1, intUserID);
        	        statement.setInt(2, intMovieId);
                    
                    // Executing the INSERT INTO statement
                    int rowsAffected = statement.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        System.out.println("Insertion into tblcurrentlywatching successful!");
                    } else {
                        System.out.println("Insertion into tblcurrentlywatching failed!");
                    }


        	    } catch (Exception exception) {
        	        exception.printStackTrace();
        	    }
            }
        });
		
		JButton btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnBackToMenu.setBounds(673, 517, 349, 75);
		contentPane.add(btnBackToMenu);
        //ACTION LISTENER
		btnBackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                LoggedInJF LoggedInPage = new LoggedInJF(intUserID); //instantiates LoginJF
                LoggedInPage.setVisible(true);
        	    closeConnection();
		        dispose();
            }
        });

	}
}

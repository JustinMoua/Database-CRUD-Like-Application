package moua_justin_app.frontend.unauthenticated;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import moua_justin_app.frontend.authenticated.selection.LoggedInJF;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

public class RegisterJF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUserName;
	private JTextField tfPassword;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	public String strGender;
	
    // JDBC variables for opening and managing connection
    Connection connection = null;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterJF frame = new RegisterJF();
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
	public RegisterJF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Please fill out the information below to register!");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblTitle.setBounds(110, -33, 1087, 134);
		contentPane.add(lblTitle);
		
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tfUserName.setBounds(290, 76, 639, 56);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);
		//Need to add an action listener that will put this information into the database. 
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUserName.setBounds(34, 76, 230, 56);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(34, 166, 230, 56);
		contentPane.add(lblPassword);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tfPassword.setColumns(10);
		tfPassword.setBounds(290, 166, 639, 56);
		contentPane.add(tfPassword);
		//Need to add an action listener that will put this information into the database. 
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblFirstName.setBounds(34, 269, 230, 56);
		contentPane.add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(290, 269, 639, 56);
		contentPane.add(tfFirstName);
		//Need to add an action listener that will put this information into the database. 
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLastName.setBounds(34, 360, 230, 56);
		contentPane.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tfLastName.setColumns(10);
		tfLastName.setBounds(290, 360, 639, 56);
		contentPane.add(tfLastName);
		
		
		JLabel lblBirthDate = new JLabel("Birthdate");
		lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBirthDate.setBounds(34, 474, 230, 56);
		contentPane.add(lblBirthDate);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(285, 442, 198, 153);
		contentPane.add(calendar);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGender.setBounds(34, 602, 103, 46);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnMale.setBounds(290, 602, 89, 46);
		contentPane.add(rdbtnMale);
        rdbtnMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	strGender = "Male";
            }
        });
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnFemale.setBounds(465, 602, 120, 46);
		contentPane.add(rdbtnFemale);
        rdbtnFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	strGender = "Female";
            }
        });
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rdbtnOther.setBounds(628, 602, 103, 46);
		contentPane.add(rdbtnOther);
        rdbtnOther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	strGender = "Other";
            }
        });
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rdbtnMale);
		btnGrp.add(rdbtnFemale);
		btnGrp.add(rdbtnOther);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstablishConn();
			    System.out.println("Register button clicked!");

			    String strUsername = tfUserName.getText();
			    String strPassword = tfPassword.getText();
			    String strFirstName = tfFirstName.getText();
			    String strLastName = tfLastName.getText();
			    // strGender is already initialized and defined when clicking the radio buttons
			    Date date = calendar.getDate();
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    String strDOB = formatter.format(date);

			    System.out.println("Username: " + strUsername);
			    System.out.println("Password: " + strPassword);
			    System.out.println("First Name: " + strFirstName);
			    System.out.println("Last Name: " + strLastName);
			    System.out.println("Date of Birth: " + strDOB);

			    try {
			        // Construct the SQL INSERT query to insert user's information when they sign up for the first time
			        String insertQuery = "INSERT INTO tblUser (username, password, firstname, lastname, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";

			        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

			        //setting value for prep statement
			        insertStatement.setString(1, strUsername);
			        insertStatement.setString(2, strPassword);
			        insertStatement.setString(3, strFirstName);
			        insertStatement.setString(4, strLastName);
			        insertStatement.setString(5, strDOB);
			        insertStatement.setString(6, strGender);
			        // Execute the INSERT query
			        insertStatement.executeUpdate();
			        System.out.println("User registered successfully!");
			        
			        //Grabs the user's id to be passed into the other functions.
			        String selectIdQuery = "SELECT id FROM tblUser WHERE username=? AND password =?";
			        PreparedStatement selectStatement = connection.prepareStatement(selectIdQuery);
			        selectStatement.setString(1, strUsername);
			        selectStatement.setString(2, strPassword);
			        ResultSet resultSet = selectStatement.executeQuery();

			        //Checks if user already exists
			        if (resultSet.next()) { // Move cursor to the first row
			            int intUserID = resultSet.getInt("id"); //Grabs user ID
			            System.out.println("User's ID is: " + intUserID);
	    		        dispose();
	                    LoggedInJF LoggedInPage = new LoggedInJF(intUserID); //instantiates LoginJF
	                    LoggedInPage.setVisible(true);
	                    insertStatement.close();
				        selectStatement.close();
			        } else {
			            System.out.println("No user found with the provided credentials.");
			        }

                    
                    

			    } catch (SQLException ex) {
			        // Handle any SQL exceptions
			        ex.printStackTrace();
			    }
			}

		});
		btnRegister.setBounds(1028, 590, 169, 70);
		contentPane.add(btnRegister);
		

		

		

	}
}

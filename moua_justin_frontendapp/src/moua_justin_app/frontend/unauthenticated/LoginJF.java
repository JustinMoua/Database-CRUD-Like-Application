package moua_justin_app.frontend.unauthenticated;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import moua_justin_app.frontend.authenticated.selection.LoggedInJF;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginJF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUserName;
	private JTextField tfPassword;
	private JLabel lblTitle;
	private JButton btnLogin;
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
					LoginJF frame = new LoginJF();
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
	public LoginJF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//=============TEXT FIELDS START HERE=============TEXT FIELDS START HERE=============TEXT FIELDS START HERE=============
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tfUserName.setBounds(598, 247, 391, 68);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);
		
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tfPassword.setColumns(10);
		tfPassword.setBounds(598, 358, 391, 68);
		contentPane.add(tfPassword);
		
		//=============TEXT FIELDS END HERE===============TEXT FIELDS END HERE=================TEXT FIELDS END HERE=============

		//=============LABELS STARTS HERES================LABELS STARTS HERES================LABELS STARTS HERES================
		JLabel lblEnterUserName = new JLabel("Enter Username: ");
		lblEnterUserName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEnterUserName.setBounds(343, 260, 193, 41);
		contentPane.add(lblEnterUserName);
		
		JLabel lblEnterPassword = new JLabel("Enter Password:");
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEnterPassword.setBounds(343, 364, 193, 41);
		contentPane.add(lblEnterPassword);
		
		lblTitle = new JLabel("Please Log In");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblTitle.setBounds(490, 80, 310, 101);
		contentPane.add(lblTitle);
		//=============LABELS END HERES===================LABELS END HERES===================LABELS END HERES===================

		//=============BUTTONS START HERE=================BUTTONS START HERE=================BUTTONS START HERE=================
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 50));
		btnLogin.setBounds(524, 512, 246, 101);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	EstablishConn();
		        System.out.println("Login button clicked!");
            	//All of this tries to see if the user can log in!
                
		        String strUsername = tfUserName.getText();
		        String strPassword = tfPassword.getText();

                try {
                    String query = "SELECT * FROM tblUser WHERE username=? AND password=? ";
					PreparedStatement statement = connection.prepareStatement(query);
                    //https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/sql/PreparedStatement.html
                    statement.setString(1, strUsername);
                    statement.setString(2, strPassword);
                    
                    ResultSet resultSet = statement.executeQuery();
                    //===================CHECKS IF USER CAN LOG IN================CHECKS IF USER CAN LOG IN================
                    if (resultSet.next()) {
                        //Preferences usrPref = Preferences.userRoot(); //preferences related thingy
                        
                        //usrPref.put("username", strUsername); //Stores username in preferences
                        
                        String selectIdQuery = "SELECT id FROM tblUser WHERE username=? AND password =?";
                        statement = connection.prepareStatement(selectIdQuery);
                        
                        int intUserID = resultSet.getInt("id"); //Grabs user ID

                        System.out.println(intUserID);
                        
                        JOptionPane.showMessageDialog(null, "Login successful");
        		        dispose();
                        LoggedInJF LoggedInPage = new LoggedInJF(intUserID); //instantiates LoginJF
                        LoggedInPage.setVisible(true);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                    //===================CHECKS IF USER CAN LOG IN================CHECKS IF USER CAN LOG IN================

				} catch (SQLException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}

		        

		        
		    }
		    
		});
		//=============BUTTONS END HERE===================BUTTONS END HERE===================BUTTONS END HERE===================


	}

}

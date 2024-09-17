package moua_justin_app.frontend.unauthenticated;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainpageJF extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainpageJF frame = new MainpageJF();
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
	
	//Idea: Try adding more arguments to the man file here. 
	public MainpageJF() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); //Sets layout to ABSOLUTE
		
		//==========LABELS START HERE==========LABELS START HERE==========LABELS START HERE==========LABELS START HERE===========================
		JLabel lblWelcome = new JLabel("Welcome to Justin's Watch List Application!");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblWelcome.setBounds(442, 11, 486, 120);
		contentPane.add(lblWelcome);
		
		JLabel lblSelectOptns = new JLabel("Please select one of the two options.");
		lblSelectOptns.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectOptns.setBounds(522, 142, 329, 107);
		contentPane.add(lblSelectOptns);
		//==========LABELS END HERE==========LABELS END HERE==========LABELS END HERE==========LABELS END HERE==========LABELS END HERE==========
		
		
		//==========BUTTONS START HERE===========BUTTONS START HERE===========BUTTONS START HERE===========BUTTONS START HERE====================
		
		//==========LOG IN BUTTON========LOG IN BUTTON========LOG IN BUTTON========
		//LOG IN BUTTON INITIALIZATION
		JButton btnLogin = new JButton("Log In");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogin.setBounds(854, 409, 221, 90);
		contentPane.add(btnLogin);
		
        //ACTION LISTENER
		btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Login Button Clicked!");
                // Action to perform when button is clicked
            	dispose(); //closes out of MainpageJF
                System.out.println("Successfully closed MainpageJF.java"); 
                LoginJF LoginPage = new LoginJF(); //instantiates LoginJF
                LoginPage.setVisible(true);
            }
        });
		//==========LOG IN BUTTON========LOG IN BUTTON========LOG IN BUTTON========

		//==========REGISTER BUTTON==========REGISTER BUTTON=======================
		//REGISTER BUTTON INITIALIZATION
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegister.setBounds(283, 409, 221, 90);
		contentPane.add(btnRegister);
		
        //ACTION LISTENER
		btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Register button clicked!");
                // Action to perform when button is clicked
            	dispose(); //closes out of MainpageJF
                System.out.println("Successfully closed MainpageJF.java");    
                RegisterJF RegisterPage = new RegisterJF(); //instantiates LoginJF
                RegisterPage.setVisible(true);
            }
        });
		//==========REGISTER BUTTON==========REGISTER BUTTON=======================
		
		
		//==========BUTTONS END HERE===========BUTTONS END HERE===========BUTTONS END HERE===========BUTTONS END HERE============================

	}
}

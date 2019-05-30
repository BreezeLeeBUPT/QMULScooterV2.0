package BoundaryClass;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ControlClass.CoreMethods;

/**
 * 
 * ClassName: AdmGUI  
 * Function: TODO the interface in which administrator interact with our software  
 * date: 2019/5/27 12:56:47 
 *  
 * @author Lingfeng Li  
 * @version 1.0
 * @since JDK 1.6
 */
public class AdmGUI {

	private JFrame frame;
	private JTextField userInputField;
	public static JTextArea txtrInformation;
	public static int f = 0;
	//public static JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmGUI window = new AdmGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdmGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public int initialize() {
		f = 2;
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 691, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    JLabel lblNewLabel = new JLabel("Management System");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel.setBounds(207, 0, 239, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Check Users");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(21, 56, 229, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		userInputField = new JTextField();//�����û��������û���Ϣ
		userInputField.setBounds(171, 51, 275, 37);
		frame.getContentPane().add(userInputField);
		userInputField.setColumns(10);
		
		txtrInformation = new JTextArea();//��ʾ�û���Ϣ
		txtrInformation.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtrInformation.setText("User Information");
		txtrInformation.setBounds(171, 100, 420, 101);
		frame.getContentPane().add(txtrInformation);
		
		JButton btnCheck = new JButton("Check");//ȷ�������û�
		btnCheck.setFont(new Font("/Calibri", Font.PLAIN, 24));
		btnCheck.setBounds(473, 51, 103, 35);
		frame.getContentPane().add(btnCheck);
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreMethods.checkUser(userInputField.getText());;
			}
		});
		
		JButton addNewUserButton = new JButton("Add");//����û�
		addNewUserButton.setFont(new Font("Calibri", Font.PLAIN, 24));
		addNewUserButton.setBounds(21, 106, 137, 36);
		frame.getContentPane().add(addNewUserButton);
		addNewUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreMethods.addUser(userInputField.getText());
			}
		});
		
		JButton removeUserButton = new JButton("Remove");//ɾ���û�
		removeUserButton.setFont(new Font("Calibri", Font.PLAIN, 24));
		removeUserButton.setBounds(21, 155, 139, 37);
		frame.getContentPane().add(removeUserButton);
		removeUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreMethods.removeUser(userInputField.getText());
			}
		});
		
		JButton payButton = new JButton("Pay the fine");//�û��Ѿ����˷�������û� Ҫ�ӽ����û��ӿ�
		payButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		payButton.setBounds(50, 232, 153, 37);
		frame.getContentPane().add(payButton);
		payButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreMethods.payfine(userInputField.getText());
			}
		});
		
		JButton clearButton = new JButton("Clear all users' fines");//clear the fines of all users
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		clearButton.setBounds(230, 232, 153, 37);
		frame.getContentPane().add(clearButton);
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreMethods.clearAllFines(txtrInformation);
			}
		});
		
		JButton searchButton = new JButton("Search unpaid users");//clear the fines of all users
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		searchButton.setBounds(400, 232, 153, 37);
		frame.getContentPane().add(searchButton);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreMethods.searchUnpaidUsers(txtrInformation);
			}
		});
			
		
		JTextArea txtrScooterIformation = new JTextArea();
		txtrScooterIformation.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtrScooterIformation.setText("Scooter Information");
		txtrScooterIformation.setBounds(14, 469, 630, 81);
		frame.getContentPane().add(txtrScooterIformation);
		
		//send email to user
		JTextArea txtrSendEmail = new JTextArea();
		txtrSendEmail.setFont(new Font("Courier New", Font.PLAIN, 16));
		txtrSendEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		txtrSendEmail.setText("Dear User");
		txtrSendEmail.setBounds(21, 307, 595, 95);
	
		JButton btnSendEmail = new JButton("Send Email");//button to send email
		btnSendEmail.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnSendEmail.setBounds(230, 411, 153, 37);
		frame.getContentPane().add(btnSendEmail);
		btnSendEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//new TimerTest(frame);
			}
		});
		return f;
	}
}

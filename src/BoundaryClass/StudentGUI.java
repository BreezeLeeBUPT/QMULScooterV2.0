package BoundaryClass;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.event.*;

import ControlClass.CoreMethods;
import ControlClass.ObjectIO;
import ControlClass.OtherMethods;
import EntityClass.InformationSystem;
import EntityClass.User;

/* when I try to add a Timer into User, there's always unwanted results
 * because the Timer I write into data file and the Timer I read from data file is NOT the same Timer
 * 	I don't know why
 * So I choose not to use Timer with Object Stream.
 */
/**
 * 
 * ClassName: StudentGUI 
 * Function: TODO the interface in which students interact with our software
 * date: 2019/5/27 12:56:09  
 *  
 * @author Lingfeng Li  
 * @version   
 * @since JDK 1.6
 */
public class StudentGUI {

	private JFrame frame;
	private JTextField textField;
	private static Timer timer;
	private static String userLastInput = "";  //only 1 exist
	private ImageIcon scooter;
	private ImageIcon light;
	public static JLabel[] photoLabels;
	public static JLabel[] lightLabels;
	public static JTextArea txtrMessages;
	public static JLabel lblUseTime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					StudentGUI window = new StudentGUI();
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
	public StudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//��ӵ�
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		textField.setBounds(281, 76, 329, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changeUpdate(DocumentEvent e) {
				if(timer!=null) timer.stop();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(timer!=null) timer.stop();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(timer!=null) timer.stop();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(timer!=null) timer.stop();
			}
		});
		
		//load image of scooters
		scooter = new ImageIcon("/Users/Administrator/Desktop/scooter2.png");
		photoLabels = new JLabel[8];
		for(int i = 0; i < 8;i++) {
			photoLabels[i] = new JLabel(scooter);	//create an image array
			photoLabels[i].setBounds(220+i*55, 350, scooter.getIconWidth(), scooter.getIconHeight());	//arrange the locations
			photoLabels[i].setVisible(false);
			frame.add(photoLabels[i]);	
		} 
		
		light = new ImageIcon("/Users/Administrator/Desktop/redlight.jpg");
		lightLabels = new JLabel[8];
		for(int i = 0; i < 8;i++) {
			lightLabels[i] = new JLabel(light);	//create an image array
			lightLabels[i].setBounds(220+i*55, 415, light.getIconWidth(), light.getIconHeight());	//arrange the locations
			lightLabels[i].setVisible(false);
			frame.add(lightLabels[i]);	
		}
		
		
		txtrMessages = new JTextArea();
		txtrMessages.setFont(new Font("Calibri", Font.PLAIN, 23));
		txtrMessages.setTabSize(1000);
		txtrMessages.setText("Messages:");
		txtrMessages.setBounds(281, 160, 329, 170);
		frame.getContentPane().add(txtrMessages);
		
		lblUseTime = new JLabel("time will appear here");
		lblUseTime.setForeground(Color.DARK_GRAY);
		lblUseTime.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblUseTime.setBounds(42, 123, 400, 29);
		frame.getContentPane().add(lblUseTime);
				
		JButton btnQmCard = new JButton("QM Card");//use this button to simulate the process of swiping card
		btnQmCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();			
				//if the user in the user input label is changed, then create a new timer.
				if (!textField.getText().equals(userLastInput)) {
					//get the last start time of specific user
					
					if(!OtherMethods.checkEmail(textField.getText())) { //input valid
						txtrMessages.setText("Message:" + '\n'+
								"Your input is NOT Valid!!" + '\n' +" Please input again!");
						return;
					}
					if(system.getCurrentLoc()==0) {
						txtrMessages.setText("Please choose a location first!");
						return;
					}
					ArrayList<User> list = system.getlist();
					//System.out.println("ve been here");
					for(User user:list) {
						if(user.getEmail().equals(textField.getText())) {
							Date date = new Date();
							timer = new Timer(1000, new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									InformationSystem system = (InformationSystem)ObjectIO.ImplicitReadObjectFromFile();
									ArrayList<User> list = system.getlist();
									for(User user:list) {
										if(user.getEmail().equals(textField.getText())) {
											Date date = new Date();    
											long lastStartTime = user.getLastStartTime();
											long timeInSeconds = (date.getTime()-lastStartTime)/1000;  
											lblUseTime.setText("You have ride:" + String.valueOf(timeInSeconds) + " seconds.");  
										}
									}
								} 
							});
							userLastInput = textField.getText();  
							//ensure that if going to channel 2, a Timer is already assigned
							//Otherwise a NullPointerException wiil appear
							CoreMethods.swipeCard(system,textField.getText(),txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
							ObjectIO.writeObjectToFile(system);
							System.out.println("channel 1");
							return;
						}
					}
					txtrMessages.setText("User not found2!");
					return;
				}
				else {
				//user's last input has not changed.
					CoreMethods.swipeCard(system,textField.getText(),txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
					System.out.println("channel 2");
					ObjectIO.writeObjectToFile(system);
				}
				
			}
		});
		btnQmCard.setBackground(new Color(153, 180, 209));
		
		btnQmCard.setBounds(38, 160, 217, 134);
		frame.getContentPane().add(btnQmCard);
		
		JLabel lblQmEmailAddress = new JLabel("QM Email address");
		lblQmEmailAddress.setForeground(Color.DARK_GRAY);
		lblQmEmailAddress.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblQmEmailAddress.setBounds(44, 76, 216, 29);
		frame.getContentPane().add(lblQmEmailAddress);
		
		JLabel lblScootersSharePoint = new JLabel("Scooters Share Point");
		lblScootersSharePoint.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblScootersSharePoint.setForeground(Color.BLACK);
		lblScootersSharePoint.setBounds(171, 0, 344, 38);
		frame.getContentPane().add(lblScootersSharePoint);
		

		
		JButton btnLocation = new JButton("Location A");//location A
		btnLocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
				CoreMethods.viewFreeSpace(1, system);
				system.setCurrentLoc(1); 
				//this instance variable is used to transfer parameter to swipe card method
				//0 stands for HAVING NOT CHOOSED A LOCATION yet, and 1,2,3 stands for A,B,C.
				ObjectIO.writeObjectToFile(system);

			}
		});
		btnLocation.setBounds(38, 315, 170, 37);
		frame.getContentPane().add(btnLocation);
		
		JButton btnLocationB = new JButton("Location B");//location B
		btnLocationB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
				CoreMethods.viewFreeSpace(2, system);
				system.setCurrentLoc(2);  
				//this instance variable is used to transfer parameter to swipe card method
				ObjectIO.writeObjectToFile(system);
				
			}
		});
		btnLocationB.setBounds(38, 368, 170, 37);
		frame.getContentPane().add(btnLocationB);
		
		JButton btnNewButton = new JButton("Location C");//location C
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
				CoreMethods.viewFreeSpace(3, system);
				system.setCurrentLoc(3);
				//this instance variable is used to transfer parameter to swipe card method
				ObjectIO.writeObjectToFile(system);
			}
		});
		btnNewButton.setBounds(38, 426, 170, 37);
		frame.getContentPane().add(btnNewButton);
	}
}

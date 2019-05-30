package TestClass;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Date;
import java.util.Timer;

public class PhotoTest {
	private JFrame frame;
	private ImageIcon scooter;
	private JLabel[] photoLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhotoTest window = new PhotoTest();
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
	public PhotoTest() {
		initialize();
		
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scooter = new ImageIcon("/Users/Administrator/Desktop/scooter.png");
		photoLabel = new JLabel[8];
		for(int i = 0; i < 8;i++) {
			photoLabel[i] = new JLabel(scooter);	//把背景图片添加到标签里
			if(i<4)photoLabel[i].setBounds(0+i*100, 0, scooter.getIconWidth(), scooter.getIconHeight());	//把标签设置为和图片等高等宽
			else photoLabel[i].setBounds(0+(i-4)*100, 200, scooter.getIconWidth(), scooter.getIconHeight());
			frame.add(photoLabel[i]);	
		}
		JButton btnNewButton_2 = new JButton("ff");//添加用户
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnNewButton_2.setBounds(221, 106, 137, 36);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Thread.sleep(500);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				photoLabel[5].setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("test");//添加用户
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		btnNewButton_1.setBounds(21, 106, 137, 36);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 TimerTask task=new TimerTask(){
					   boolean flag = false;
					   Date date = new Date();
					   long startTime = date.getTime();
					   @Override
					   public void run() {
						  if(flag) {
							  photoLabel[5].setVisible(true);
							  flag = !flag;
						  }
						  else {
							  photoLabel[5].setVisible(false);
							  flag = !flag;
						  }
						  Date newDate = new Date();
						  if(newDate.getTime()-startTime>500) {
							  System.out.println("431");
							  this.cancel();
							  System.gc();
						  }
					   }
					         
			};
					        Timer timer=new Timer();
					        timer.schedule(task, 0,500);  //0标识要延迟的时间，5000指毫秒
			}
		});
		
	}
	
	private void takeScooter(int n) {
		photoLabel[n].setVisible(false);
	}
	
	private void returnScooter(int n) {
		photoLabel[n].setVisible(true);
	}
}

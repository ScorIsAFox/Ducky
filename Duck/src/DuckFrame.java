import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DuckFrame extends JFrame  implements ActionListener, AWTEventListener{
	
	final int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width); // get the width of the screen
	final int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); // get the height of the screen
	final int imageWidth, imageHeight;
	final int posYError = screenWidth / 40;
	protected int posX, posY;
	protected ArrayList<URL> address = new ArrayList<URL>();
	JLabel label = new JLabel();
	int sounds = 11;
	
	public DuckFrame() {
		Container container = this.getContentPane();
		
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setType(JFrame.Type.UTILITY); //Hide task bar icon
		Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
		this.addKeyListener(new KeyAdapter() { //
			public void keyPressed(KeyEvent e) {
				System.out.println("Ducky");
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		
		this.getAddress();
		URL s = null;
		Random rand = new Random();
		posY = rand.nextInt(screenHeight/2) + screenHeight/4; //Set the initial position
		//Set the initial move direction
		if (posY % 2 == 1) { //The duck will appear from the left side
			s = address.get(0); //Move to right
			posX = 0;
		} else { //The duck will appear from the right side
			s = address.get(1); //Move to left
			posX = screenWidth;
		}
		ImageIcon image = new ImageIcon(s);
		imageWidth = image.getIconWidth();
		imageHeight = image.getIconHeight();
		label = new JLabel(image);		
		this.setBounds(posX, posY, imageWidth, imageHeight);
		container.add(label);
		label.setBounds(0, 0, imageWidth, imageHeight);
		
		/*Make a button to add the ActionListener*/
		JButton sounds = new JButton("");
		sounds.setSize(imageWidth, imageHeight/2);
		sounds.setBorder(null);
		sounds.setContentAreaFilled(false);
		sounds.addActionListener(this);
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setOpaque(false);
		p.add(sounds);
		this.setGlassPane(p);
		this.getGlassPane().setVisible(true);
		this.setVisible(true);
		
	}
	
	private void getAddress() {
		//String s = "D:\\0-Work\\Hello World\\Duck\\src\\Move\\";
		try {
			address.add(this.getClass().getResource("/move/Right.gif"));
			address.add(this.getClass().getResource("/move/Left.gif"));
			address.add(this.getClass().getResource("/move/UR.gif"));
			address.add(this.getClass().getResource("/move/UL.gif"));
			address.add(this.getClass().getResource("/move/BR.gif"));
			address.add(this.getClass().getResource("/move/BL.gif"));
			address.add(this.getClass().getResource("/move/RightStop.gif"));
			address.add(this.getClass().getResource("/move/LeftStop.gif"));
			address.add(this.getClass().getResource("/move/URStop.gif"));
			address.add(this.getClass().getResource("/move/ULStop.gif"));
			address.add(this.getClass().getResource("/move/BRStop.gif"));
			address.add(this.getClass().getResource("/move/BLStop.gif"));
			
			address.add(this.getClass().getResource("/sounds/你瞅啥.wav"));
			address.add(this.getClass().getResource("/sounds/你再瞅.wav"));
			address.add(this.getClass().getResource("/sounds/等我摇人.wav"));
			address.add(this.getClass().getResource("/sounds/你给我等着.wav"));
		}catch(Exception e) {
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (sounds == 15) {
			sounds = 12;
		} else {
			sounds++;
		} // sounds' addresses is from 12 to 15
		AudioClip clip = Applet.newAudioClip(address.get(sounds));
		clip.play();
		
	}

	@Override
	public void eventDispatched(AWTEvent event) {
		if (event instanceof KeyEvent) {
			KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
			     System.exit(0);
			}
		}
	}	
	
}

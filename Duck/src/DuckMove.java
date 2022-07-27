import java.awt.Container;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DuckMove{
	DuckFrame duck = new DuckFrame();
	private int posX = duck.getX();
	private int posY = duck.getY();
	Random rand = new Random();
	private int lastMove; //[0..5] = [Right, Left, UpperR, UpperL, BottomR, BottomL]
		
	private void swing(URL s, Container c) {
		ImageIcon move = new ImageIcon(s);
		JLabel imgLabel = new JLabel(move);
		c.remove(duck.label);
		duck.label = imgLabel;
		c.add(duck.label);
		duck.label.setBounds(0, 0, duck.imageWidth, duck.imageHeight);
	}
	
	private void duckWait() {
		for (int j = 0; j < 100; j++) {
			for (int k = 0; k < 100; k++) {
				for (int l = 0; l < 100; l++) {
					boolean a = rand.nextBoolean(); //Not use, just for time delay.
				}
			}
		}
	}
	
	protected void duckAppear() {
		int endX = rand.nextInt(duck.screenWidth/2) + duck.screenWidth/4; //Set the x-value of terminal point
		
		if (posX == 0) { //The duck will appear from the left side to the right
			this.rightMove(endX);			
		} else {
			this.leftMove(endX);
		}
	}
	
	protected void duckMove() {
		int endX = rand.nextInt(duck.screenWidth - duck.imageWidth); // Randomly generate the next position of the duck
		int endY = rand.nextInt(duck.screenHeight - duck.imageHeight);
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		
		if ((endY >= posY - duck.posYError) && (endY <= posY + duck.posYError)) { //Horizontal
			if (endX > posX && lastMove != 1) { //确保鸭鸭不会来回走
				this.rightMove(endX);
				return;
			} else {
				if (endX < posX && lastMove != 0) { 
					this.leftMove(endX);
					return;
				} else {
					this.duckMove();
				}
			}
		}
		
		if (endY < posY - duck.posYError) { //Upwards
			if (endX > posX && lastMove !=  5) {
				this.upperRightM(endX, endY);
			} else {
				if (endX < posX && lastMove != 4) {
					this.upperLeftM(endX, endY);
				} else {
					this.duckMove();
				}
			}
			return;
		}
		
		if (endY > posY + duck.posYError) { //Downwards
			if (endX > posX && lastMove !=  3) {
				this.bottomRightM(endX, endY);
			} else {
				if (endX < posX && lastMove != 2) {
					this.bottomLeftM(endX, endY);
				} else {
					this.duckMove();
				}
			}
			return;
		}
	}
	
	private void rightMove(int endX) {
		Container container = duck.getContentPane();
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		this.swing(duck.address.get(0), container);		
		for (int i = posX; i <= endX; i++) {
			duck.setBounds(i, posY, duck.imageWidth, duck.imageHeight);
			this.duckWait();
		}
		
		this.swing(duck.address.get(6), container); //Slow down until stop
		int wait = 0;
		while (wait < 350) {
			this.duckWait();
			if (wait % 30 == 0) {
				duck.setBounds(duck.getX() + 1, posY, duck.imageWidth, duck.imageHeight);
			}
			wait++;
		}
		for (int i = 0; i < 100; i++) {
			this.duckWait();
		}
		posX = duck.getX(); //Get the new position of the duck
		lastMove = 0; //Update the newest movement
	}
	
	private void leftMove(int endX) {
		Container container = duck.getContentPane();
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		this.swing(duck.address.get(1), container);		
		for (int i = posX; i >= endX; i--) {
			duck.setBounds(i, posY, duck.imageWidth, duck.imageHeight);
			this.duckWait();
		}
		
		this.swing(duck.address.get(7), container); //Slow down until stop
		int wait = 0;
		while (wait < 350) {
			this.duckWait();
			if (wait % 30 == 0) {
				duck.setBounds(duck.getX() - 1, posY, duck.imageWidth, duck.imageHeight);
			}
			wait++;
		}
		for (int i = 0; i < 100; i++) {
			this.duckWait();
		}
		posX = duck.getX();
		lastMove = 1;
	}
	
	private void upperRightM(int endX, int endY) {
		Container container = duck.getContentPane();
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		this.swing(duck.address.get(2), container);		
		//int speed = rand.nextInt(1) + 1; //The speed is between 1 and 2
		while (posX <= endX && posY >= endY) {
			posX += 2;
			posY -= 1;
			this.duckWait();
			duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			this.duckWait();
		}
		
		this.swing(duck.address.get(8), container);
		int wait = 0;
		while (wait < 350) {
			this.duckWait();
			if (wait % 10 == 0) {
				posX+= 2;
				posY--;
				duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			}
			wait++;
		}
		for (int i = 0; i < 100; i++) {
			this.duckWait();
		}
		lastMove = 2;
	}
	
	private void upperLeftM(int endX, int endY) {
		Container container = duck.getContentPane();
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		this.swing(duck.address.get(3), container);
		
		while (posX >= endX && posY >= endY) {
			posX -= 2;
			posY -= 1;
			this.duckWait();
			duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			this.duckWait();
		}
		
		this.swing(duck.address.get(9), container);
		int wait = 0;
		while (wait < 350) {
			this.duckWait();
			if (wait % 10 == 0) {
				posX -= 2;
				posY--;
				duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			}
			wait++;
		}
		for (int i = 0; i < 100; i++) {
			this.duckWait();
		}
		lastMove = 3;
	}
	
	private void bottomRightM(int endX, int endY) {
		Container container = duck.getContentPane();
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		this.swing(duck.address.get(4), container);
		
		while (posX <= endX && posY <= endY) {
			posX += 2;
			posY += 1;
			this.duckWait();
			duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			this.duckWait();
		}
		
		this.swing(duck.address.get(10), container);
		int wait = 0;
		while (wait < 350) {
			this.duckWait();
			if (wait % 10 == 0) {
				posX += 2;
				posY++;
				duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			}
			wait++;
		}
		for (int i = 0; i < 100; i++) {
			this.duckWait();
		}
		lastMove = 4;
	}
	
	private void bottomLeftM(int endX, int endY) {
		Container container = duck.getContentPane();
		posX = duck.getX(); //Get the current position of the duck
		posY = duck.getY();
		this.swing(duck.address.get(5), container);
		
		while (posX >= endX && posY <= endY) {
			posX -= 2;
			posY += 1;
			this.duckWait();
			duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			this.duckWait();
		}
		
		this.swing(duck.address.get(11), container);
		int wait = 0;
		while (wait < 350) {
			this.duckWait();
			if (wait % 10 == 0) {
				posX -= 2;
				posY++;
				duck.setBounds(posX, posY, duck.imageWidth, duck.imageHeight);
			}
			wait++;
		}
		for (int i = 0; i < 100; i++) {
			this.duckWait();
		}
		lastMove = 5;
	}
}

package org.noxo;

// Erkki 22-Jul-2014
// Lazy copy of http://zetcode.com/tutorials/javagamestutorial/snake/

import java.util.Timer;
import java.util.TimerTask;

import org.noxo.devices.api.Display;
import org.noxo.devices.api.Joystick;
import org.noxo.devices.api.Joystick.JoystickMove;

public class SnakeGame implements Joystick.JoystickListener {
	

	private final int B_WIDTH = 84; // 300
	private final int B_HEIGHT = 48; // 300
	private final int DOT_SIZE = 5;
	private final int ALL_DOTS = B_WIDTH * B_HEIGHT * (DOT_SIZE * 2);
	private final int RAND_POS = 4;
	private final int DELAY = 200;
	private final int INITIAL_SNAKE_SIZE = 3;
	
	private final int x[] = new int[ALL_DOTS];
	private final int y[] = new int[ALL_DOTS];

	private int dots;
	private int apple_x;
	private int apple_y;

	int dir = 1; // 0 = up, 1 = right, 2 = down, 3 = left

	private boolean inGame = true;

	private Timer timer;
	private Display display;
	
	public SnakeGame(Display display) {
		this.display = display;
		initGame();
	}

	private void initGame() {

		
		showNokia();
			
		dots = INITIAL_SNAKE_SIZE;

		for (int z = 0; z < dots; z++) {
			x[z] = 10 - z * DOT_SIZE;
			y[z] = 10;
		}

		locateApple();
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				gameLoop();
			}
		}, 0, DELAY);
	
	}
	
	private void showNokia()
	{
		try {
			display.drawRawPCD8544(SnakeLogo.NOKIA);
			display.sync();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void drawGame() {

		if (inGame) {

			display.clear();
			display.fillRect(0xFF0000, apple_x, apple_y, DOT_SIZE, DOT_SIZE);

			for (int z = 0; z < dots; z++) {
				if (z == 0) { // head
					display.fillRect(0x000000, x[z], y[z], DOT_SIZE, DOT_SIZE);
				} else { // rest
					display.fillRect(0x000000, x[z], y[z], DOT_SIZE, DOT_SIZE);
				}
			}


		} else {
			gameOver();
		}
		
		display.sync();
	}

	private void gameOver() {
		display.clear();
		display.drawString(0x000000, 15, (B_HEIGHT - 20) / 2, "Game Over");
		display.drawString(0x000000, 15, (B_HEIGHT + 10) / 2, "Score: " + (dots - INITIAL_SNAKE_SIZE));
	}

	private void checkApple() {

		if ((x[0] == apple_x) && (y[0] == apple_y)) {
			dots++;
			locateApple();
		}
	}

	private void move() {

		for (int z = dots; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (dir == 3) {
			x[0] -= DOT_SIZE;
		}

		if (dir == 1) {
			x[0] += DOT_SIZE;
		}

		if (dir == 0) {
			y[0] -= DOT_SIZE;
		}

		if (dir == 2) {
			y[0] += DOT_SIZE;
		}
	}

	private void checkCollision() {

		for (int z = dots; z > 0; z--) {

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				inGame = false;
			}
		}

		if (y[0] > B_HEIGHT) {
			inGame = false;
		}

		if (y[0] < 0) {
			inGame = false;
		}

		if (x[0] > B_WIDTH) {
			inGame = false;
		}

		if (x[0] < 0) {
			inGame = false;
		}
	}

	private void locateApple() {

		int r = (int) (Math.random() * RAND_POS);
		apple_x = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		apple_y = ((r * DOT_SIZE));
	}

	public void gameLoop() {

		if (inGame) {
			checkApple();
			checkCollision();
			move();
		}

		drawGame();
	}

	@Override
	public void joystickMove(JoystickMove move) {
		
		if (move == JoystickMove.LEFT) {
			dir = dir - 1 < 0 ? 3 : dir - 1;
		}

		if (move == JoystickMove.RIGHT) {
			dir = dir + 1 > 3 ? 0 : dir + 1;
		}
		
		if (move == JoystickMove.FIRE)
		{
			dir = dir + 1 > 3 ? 0 : dir + 1;
		}
	}

	

}

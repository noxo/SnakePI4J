package org.noxo;

// Erkki 22-Jul-2014

import org.noxo.devices.AWTDisplay;
import org.noxo.devices.AWTJoystick;
import org.noxo.devices.ConsoleEnterJoystick;
import org.noxo.devices.NokiaGPIODisplay;
import org.noxo.devices.api.Display;
import org.noxo.devices.api.Joystick;

public class Snake {
	
	public static void main(String arg[])
	{
		Display display = new AWTDisplay(84,48);//new NokiaGPIODisplay(84, 48);
		SnakeGame game = new SnakeGame(display);
		Joystick joystick = new AWTJoystick(display, game);// new ConsoleEnterJoystick(display, game);
		joystick.start();
	}
	

}

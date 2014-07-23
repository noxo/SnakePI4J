package org.noxo.devices;

// Erkki 22-Jul-2014

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.noxo.devices.api.Joystick;

public class ConsoleEnterJoystick extends Joystick {

	public ConsoleEnterJoystick(Object source, JoystickListener listener) {
		super(source, listener);
	}

	@Override
	public void start() {
		
		try {
			
			System.out.println("press ENTER to turn teh MATO");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			while (br.readLine() != null)
			{
				getListener().joystickMove(JoystickMove.FIRE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}

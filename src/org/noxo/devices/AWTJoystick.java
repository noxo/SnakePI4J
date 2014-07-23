package org.noxo.devices;

// Erkki 22-Jul-2014

import java.awt.event.KeyEvent;

import org.noxo.devices.api.Joystick;

public class AWTJoystick extends Joystick {

	public AWTJoystick(Object source, JoystickListener listener) {
		super(source, listener);
	}

	@Override
	public void start() {
		
		org.noxo.devices.api.Display display = (org.noxo.devices.api.Display) getSource();
		java.awt.Component comp = (java.awt.Component) display.getHID();
		comp.addKeyListener(new java.awt.event.KeyListener() {
			
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					getListener().joystickMove(JoystickMove.LEFT);
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					getListener().joystickMove(JoystickMove.RIGHT);
			}
		});

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}

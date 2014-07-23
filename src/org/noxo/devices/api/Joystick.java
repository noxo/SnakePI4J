package org.noxo.devices.api;

// Erkki 22-Jul-2014

public abstract class Joystick {

	public interface JoystickListener
	{
		public void joystickMove(JoystickMove move);
	}

	Object source;
	JoystickListener listener;
	
	public static enum JoystickMove {
		UP,
		RIGHT,
		DOWN,
		LEFT,
		FIRE
	}
	
	public Joystick(Object source, JoystickListener listener)
	{
		this.source = source;
		this.listener = listener;
	}
	
	public abstract void start();
	public abstract void stop();

	public Object getSource() {
		return source;
	}

	public JoystickListener getListener() {
		return listener;
	}
	
}

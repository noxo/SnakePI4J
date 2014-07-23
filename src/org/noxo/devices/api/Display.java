package org.noxo.devices.api;

// Erkki 22-Jul-2014

public abstract class Display {
	
	int width, height;
	
	public Display(int width ,int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public abstract void drawString(int color, int x, int y, String msg);
	public abstract void fillRect(int color, int x, int y, int width, int height);
	public abstract void fillCircle(int color, int x, int y, int radius);
	public abstract void drawImage(int x, int y, int w, int h, int pixels[]);
	public abstract void drawRawPCD8544(int pixels[]);

	public Object getHID()
	{
		return null;
	}
	
	public void sync()
	{
		// for flipping pixels from backing buffer to screen
	}
	
	public void clear()
	{
		fillRect(0xFFFFFF, 0, 0, width, height);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}

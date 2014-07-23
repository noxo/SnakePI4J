package org.noxo.devices;

// Erkki 22-Jul-2014

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.SampleModel;

import javax.swing.ImageIcon;

import org.noxo.devices.api.Display;

public class AWTDisplay extends Display {

	Frame screen;
	
	public AWTDisplay(int width, int height) {
		
		super(width, height);
		
		screen = new java.awt.Frame();
		screen.setSize(width, height);
		screen.setUndecorated(true);
		screen.validate();
		screen.setVisible(true);
		
		Dimension resolution = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		screen.setLocation(resolution.width/2-width/2, resolution.height/2-height/2);
		
	}
	
	@Override
	public Object getHID() {
		return screen;
	}
	
	@Override
	public void drawString(int color, int x, int y, String msg) {
		Graphics g = screen.getGraphics();
	    g.setColor(new Color(color));
	    g.drawString(msg, x, y);
	}

	@Override
	public void fillRect(int color, int x, int y, int width, int height) {
		Graphics g = screen.getGraphics();
	    g.setColor(new Color(color));
	    g.fillRect(x, y, width, height);
	}

	@Override
	public void fillCircle(int color, int x, int y, int radius) {
		Graphics g = screen.getGraphics();
	    g.setColor(new Color(color));
	    g.fillOval(x, y, radius * 2, radius * 2);
	}

	@Override
	public void drawImage(int x, int y, int w, int h, int[] pixels) {
		java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, w, h, pixels, 0, w);
		java.awt.Graphics g = screen.getGraphics();
		g.drawImage(img,0, 0, screen);	
	}
	
	private static int _BV (int bit) {
		return (0x1 << (bit));
	}

	@Override
	public void drawRawPCD8544(int[] pixels) {

		// http://forum.evilmadscientist.com/discussion/11/nokia-pcd8544-5110-lcd-display-is-a-little-strange
		
		int height = getHeight();
		int width = getWidth();

		java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);

		for (int j=0; j<height; j++) {
			  for (int i=0; i<width; i++ ) {
			    int col = pixels[i + (j/8)*width] & _BV(j%8);
			    col = col == 0 ? 0 : 0xff;
			    int rgb = (0xff << 24) | (col << 16) | (col << 8) | col;
			    img.setRGB(i, j, rgb);
			  }
		}
		
		java.awt.Graphics g = screen.getGraphics();
		g.drawImage(img,0, 0, screen);	}

}

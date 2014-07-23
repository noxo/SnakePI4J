package org.noxo.devices;

// Erkki 22-Jul-2014

import org.noxo.devices.api.Display;
import org.noxo.devices.driver.JPCD8544;

public class NokiaGPIODisplay extends Display {

	JPCD8544 screen;
	
	public NokiaGPIODisplay(int width, int height) {
		super(width, height);
		screen = new JPCD8544();
		screen.LCDInit(50);
	}
	
	@Override
	public void clear() {
		screen.LCDClear();
	}
	
	@Override
	public void drawString(int color, int x, int y, String msg) {
		screen.LCDDrawString(x, y, msg);
	}

	@Override
	public void fillRect(int color, int x, int y, int width, int height) {
		screen.LCDFillRect(x, y, width, width, color == 255 ? 0 : 1);
	}

	@Override
	public void fillCircle(int color, int x, int y, int radius) {
		screen.LCDDrawCircle(x, y, radius, color == 255 ? 0 : 1);
	}
	
	@Override
	public void sync() {
		screen.LCDDisplay();
	}

	@Override
	public void drawImage(int x, int y, int w, int h, int[] pixels) {
		screen.LCDDrawBitmap(0, 0, pixels, w, h, 1);
	}

	@Override
	public void drawRawPCD8544(int[] pixels) {
		screen.LCDSetRawBuffer(pixels);
	}

}

SnakePI4J
=========
This is a beginner level project to get familiar with Raspberry PI GPIO connection.

We'll create remake of Nokia's Snake game using real Nokia 5110 LCD display which
is connected over GPIO. 

Program is done in Java and PI4J 3rd party library is used to communicate over GPIO.

Project has also Java AWT "backend" for testing display and input.

Here's the video of finished project:

   https://www.youtube.com/watch?v=eN_V8ItDM20

1. Requirements
===============

1.1 Software
  
  - Linux (preferrable Debian Wheezy)
  - Java 
  - Wiring PI library (http://wiringpi.com/download-and-install/) [required by PI4J]
  - PI4J Java GPIO library (included in project)
  
1.2 Hardware
  
  - Raspberry PI (A/B/B+) model + accessories (WIFI, SD card)
  - 8 jumper "Dupont" cables (female - male) (*)
  - Nokia 5110 LCD (check that it has PCD8544 controller and 8 pins)
  - 8 pin PCB connector (to connect 5110 LCD on breadboard or jumper cables) 
  - Bread board (*)
  - Soldering iron (if PCB connector is not already soldered to LCD)
  
  (*) Alternatively you can connect LCD to GPIO with female to female jumper cables, and 
      skip bread board.

2. Setting up
=============

2.1 Connecting LCD

		 LCD pins      Raspberry Pi
		 LCD1 - GND    P06  - GND
		 LCD2 - VCC    P01 - 3.3V
		 LCD3 - CLK    P16 - GPIO4
		 LCD4 - Din    P12 - GPIO1
		 LCD5 - D/C    P15 - GPIO3
		 LCD6 - CS/CE  P11 - GPIO0
		 LCD7 - RST    P13 - GPIO2
		 LCD8 - LED    P01 - 3.3V 
		 
  Please refer to http://pi4j.com/usage.html for port details how GPIOx maps to PINx
  
  - solder PCB connector to LCD
  - connect PCB to bread board
  - connect jumper cables from GPIO PINs to breadboard
		 
2.2 Setup

  Setup appropiate Display and Joystick class into Snake class
  
  - AWTDisplay class is for testing to the game on desktop/X11 without GPIO
  - NokiaGPIODisplay class is for running game with actual hardware
  - AWTJoystick class is for controlling game in desktop/X11, use left/rigth to control the game.
  - ConsoleEnte class is for controlling game from console, use ENTER to control the game.

2.3 Compile and run

  - javac -cp ../lib/pi4j-core.jar:. org/noxo/Snake.java
  - java -cp ../lib/pi4j-core.jar:. org.noxo.Snake
  
3 Future plans
==============

  Connect switch via GPIO to control game






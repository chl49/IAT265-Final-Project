package main;

import static util.ImageLoader.loadImage;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import decorator.Room;
import decorator.SimpleRoom;
import decorator.*;
import art.*;
import home.*;
import home.Button;
import main.OSPanel.MyMouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;
//MAIN DRAW PANEL
public class OSPanel extends JPanel implements ActionListener {
	public final static int W_WIDTH = 1080;
	public final static int W_HEIGHT = 675;

	// variables for holding mouse position
	private double mouseX;
	private double mouseY;

	// Fields for state and transitions
	private int state = 0;
	private HomeScreen home;
	/*
	private StartButton startBtn;
	private RemoveButton removeBtn;
	private EndButton endBtn;
	*/
	
	private Button startBtn;
	private Button removeBtn;
	private Button endBtn;
	private Button fileBtn;
	private Button decBtn1;
	private Button decBtn2;
	private Button decBtn3;
	private Button decBtn4;
	
	private Trash trash;
	//private Virus virus;
	private Filetype virus;
	private Filetype safe1;
	private Filetype safe2;
	private Filetype removed;
	private ArrayList<Filetype> fileList;
	Room room;
	
	private TextMessage text;
	
	private RemoveWindow removeWin;
	
	private NoisySpiral nSpiral;
	private Square square;
	
	private float angle = 0;
	private float area = 1;

	private Timer timer;
	private boolean cleared = false;
	private boolean found = false;
	
	long start, end, total, seconds;
	int newImg = 0;
	boolean switchup = false;
	
	private Minim minim;
	private AudioPlayer bkmusic, click, scan, trace;

	OSPanel(JFrame frame) {
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		
		this.fileList = new ArrayList<>();
		
		//start = 0;

		home = new HomeScreen();
		startBtn = new StartButton(80, 80, 0.10);
		removeBtn = new RemoveButton(80, 190, 0.25);
		fileBtn = new FileButton(80, 300, 0.25);
		endBtn = new EndButton(80, 410, 0.25);
		decBtn1 = new DecoratorButton(W_WIDTH - 100, 80, 0.25,"BlankBackground");
		decBtn2 = new DecoratorButton(W_WIDTH - 100, 190, 0.25,"DesertBackground");
		decBtn3 = new DecoratorButton(W_WIDTH - 100, 300, 0.25,"SkyBackground");
		decBtn4 = new DecoratorButton(W_WIDTH - 100, 410, 0.25,"TreeBackground");
		trash = new Trash(W_WIDTH/2 +100, 100, 0.3);
		virus = new Virus(W_WIDTH / 2-80, W_HEIGHT / 2 - 200, 0.4, "DOOM");
		safe1 = new Safe(W_WIDTH / 2-160, W_HEIGHT / 2 - 100, 0.4, "LOVE");
		safe2 = new Safe(W_WIDTH / 2-260, W_HEIGHT / 2 + 100, 0.4, "DOLL");
		fileList.add(virus);
		fileList.add(safe1);
		fileList.add(safe2);
		//norm1 = new Safe(W_WIDTH / 2+180, W_HEIGHT / 2 + 160, 0.4, "LOVE");
		//norm2 = new Safe(W_WIDTH / 2+180, W_HEIGHT / 2 + 240, 0.4, "DOLL");
		//norm3 = new Safe(W_WIDTH / 2+180, W_HEIGHT / 2 + 280, 0.4, "FAKE");
		//removeWin = new RemoveWindow(W_WIDTH / 2, W_HEIGHT / 2, 1);
		removeWin = new RemoveWindow(0,0, 1);
		
		text = new TextMessage(0,0,0);
		
		nSpiral = new NoisySpiral();
		square = new Square();
		
		minim = new Minim(new MinimHelper());

		bkmusic = minim.loadFile("bgm.mp3");
		click = minim.loadFile("click.mp3");
		scan = minim.loadFile("scanned.mp3");
		trace = minim.loadFile("traced.mp3");
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		
		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);

		timer = new Timer(30, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.fill(new Rectangle2D.Double(0, 0, OSPanel.W_WIDTH, OSPanel.W_HEIGHT ));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		switch (state) {
		case 0: {
			home.setHomeImg(0);
			home.drawHome(g2);
			startBtn.draw(g2);
			text.drawText(g2);
			break;
		  }
		case 1: {
			removeWin.draw(g2);
			//home.setHomeImg(1);
			startBtn.draw(g2);
			removeBtn.draw(g2);
			decBtn1.draw(g2);
			decBtn2.draw(g2);
			decBtn3.draw(g2);
			decBtn4.draw(g2);
			fileBtn.draw(g2);
			if (room != null)
				room.showRoom(g2);
			text.drawText(g2);
			if(cleared==true) {
				endBtn.draw(g2);
				home.setHomeImg(2);
			}
			else {
				home.setHomeImg(1);
			}
			break;
		  }
		case 2: {
			removeWin.draw(g2);
			home.setHomeImg(2);
			//home.drawHome(g2);
			startBtn.draw(g2);
			removeBtn.draw(g2);
			endBtn.draw(g2);
			decBtn1.draw(g2);
			decBtn2.draw(g2);
			fileBtn.draw(g2);
			if (room != null)
				room.showRoom(g2);
			text.drawText(g2);
			break;
		  }
		case 3: {
			removeWin.draw(g2);
			home.setHomeImg(0);
			startBtn.draw(g2);
			fileBtn.draw(g2);
			trash.draw(g2);
			for (Filetype file:fileList ) {
				if(file.getHit()==false) {
					file.draw(g2);	
				}
			}
			text.drawText(g2);
			break;
		  }
		case 4: {
			removeWin.draw(g2);
			startBtn.draw(g2);
			removeBtn.draw(g2);
			square.drawSquare (g2, W_WIDTH/2, W_HEIGHT/2, 1, area);  
			nSpiral.drawSpiral(g2, angle);
			text.drawText(g2);
			break;
			
		  }
		case 5: {
			break;
		  }
		case -1: {
			home.setHomeImg(0);
			home.drawHome(g2);
			startBtn.draw(g2);
			text.drawText(g2);
			break;
		  }
		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(state==4) {
			int i = 0;
			if (angle < 1440) {
				if(angle>1.4*i) {
					area+=1;
					i+=1;
				}
				//repaint();
				removeWin.setState(-1);
				angle += 5;
			}
			else {
				//scan.pause();
				//click.play(0);
				nSpiral.setSwitch(true);
				if(removeWin.getState()==-1) {
					scan.play(0);
				}
				removeWin.setState(1);
				
			}
		}

		repaint();
	}

	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			switch (state) {
			    case 0: {
			    	if (startBtn.clicked(mouseX, mouseY)) {
						text.setState(1);
						//room = new SimpleRoom();
						if(cleared==false) {
							bkmusic.loop();
						}
						else{
							bkmusic.pause();
						}
						state = 1;
			    	}
			    	break;
			    }
			    case 1: {
			    	if (startBtn.clicked(mouseX, mouseY)) {
						text.setState(0);
						bkmusic.pause();
						state = 0;
						
					}
			    	else if (removeBtn.clicked(mouseX, mouseY)) {
			    		//click.play(0);
						text.setState(1);
						click.play(0);
						state = 4;
						
					}
			    	else if (fileBtn.clicked(mouseX, mouseY)) {
			    		click.play(0);
						text.setState(5);
						state = 3;
						
					} else if (endBtn.clicked(mouseX, mouseY)&&cleared==true) {
						click.play(0);
						bkmusic.pause();
			    		text.setState(-1);
			    		state = -1;
						
					}
					else if (decBtn1.clicked(mouseX, mouseY)) {
						click.play(0);
			    		room=null;
					}
					else if (decBtn2.clicked(mouseX, mouseY)&&cleared==true) {
						click.play(0);
						Room baseRoom = new SimpleRoom();
						room = new EarthDecorator(baseRoom);
					}

					else if (decBtn3.clicked(mouseX, mouseY)&&cleared==true) {
						click.play(0);
						Room baseRoom = new SimpleRoom();
						room = new SkyDecorator(baseRoom);
					}
					else if (decBtn4.clicked(mouseX, mouseY)&&cleared==true) {
						click.play(0);
						Room baseRoom = new SimpleRoom();
						room = new NeonDecorator(baseRoom);
					}
					else if (decBtn2.clicked(mouseX, mouseY)&&cleared==false) {
						click.play(0);
						Room baseRoom = new SimpleRoom();
						room = new BadEarthDecorator(baseRoom);
					}

					else if (decBtn3.clicked(mouseX, mouseY)&&cleared==false) {
						click.play(0);
						Room baseRoom = new SimpleRoom();
						room = new BadSkyDecorator(baseRoom);
					}
					else if (decBtn4.clicked(mouseX, mouseY)&&cleared==false) {
						click.play(0);
						Room baseRoom = new SimpleRoom();
						room = new BadNeonDecorator(baseRoom);
					}
			    	break;
			    }
			    case 2: {
			    	if (startBtn.clicked(mouseX, mouseY)) {
			    		state = 0;
						
					}
			    	else if (endBtn.clicked(mouseX, mouseY)) {
			    		text.setState(-1);
			    		state = -1;
						
					}
			    	break;
			    }
			    case 3: {
			    	if (startBtn.clicked(mouseX, mouseY)) {
			    		state = 0;
			    		bkmusic.pause();
						
					}else if (fileBtn.clicked(mouseX, mouseY)) {
			    		state = 1;
					}
			    	/*
			    	else if (fileBtn.clicked(mouseX, mouseY)&&cleared==false) {
			    		state = 1;
					}
			    	else if (fileBtn.clicked(mouseX, mouseY)&&cleared==true) {
			    		state = 2;
					}
					*/
			    	break;
			    }
			    case 4: {
			    	if (startBtn.clicked(mouseX, mouseY)) {
			    		state = 0;
			    		bkmusic.pause();
			    		removeWin.setState(0);
						
					}
			    	else if (removeBtn.clicked(mouseX, mouseY)) {
			    		text.setState(5);
			    		found=true;
			    		removeWin.setState(0);
			    		state = 1;
					}
			    	break;
			    }
			    case -1: {
			    	if (startBtn.clicked(mouseX, mouseY)) {
						//click.play(0);
						//bkmusic.loop();
						//virus.setPos(W_WIDTH / 2+180, W_HEIGHT / 2 + 200);
						//for (Filetype file:fileList ) fileList.remove(file);
						fileList.clear();
						nSpiral = new NoisySpiral();
						square = new Square();
						angle=0;
						area=1;
						virus = new Virus(W_WIDTH / 2-80, W_HEIGHT / 2 - 200, 0.4, "DOOM");
						safe1 = new Safe(W_WIDTH / 2-160, W_HEIGHT / 2 - 100, 0.4, "LOVE");
						safe2 = new Safe(W_WIDTH / 2-260, W_HEIGHT / 2 + 100, 0.4, "DOLL");
						fileList.add(virus);
						fileList.add(safe1);
						fileList.add(safe2);
						room = null;
						found = false;
						cleared = false;
						text.setState(1);
						bkmusic.loop();
					    state = 1;
			    	}
			    	break;
			    }
			}
			/*
			if (state == 0 && startBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.loop();
				text.setState(1);
				state = 1;
				
			} else if(state == 1 && startBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.pause();
				text.setState(0);
				state = 0;
			} else if(state == 2 && startBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.loop();
			    state = 3;
		    } else if(state == 3 && startBtn.clicked(mouseX, mouseY)) {
		    	//click.play(0);
				//bkmusic.pause();
		    	text.setState(2);
		    	state = 2;
		    } else if(state == 1 && removeBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.pause();
		    	text.setState(4);
				state = 4;
			} else if(state == 4 && removeBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.loop();
				text.setState(5);
			    state = 1;
		    } 
			else if(state == 2 && endBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.loop();
				text.setState(-1);
			    state = -1;
		    } 
			else if(state == -1 && startBtn.clicked(mouseX, mouseY)) {
				//click.play(0);
				//bkmusic.loop();
				//virus.setPos(W_WIDTH / 2+180, W_HEIGHT / 2 + 200);
				//for (Filetype file:fileList ) fileList.remove(file);
				fileList.clear();
				virus = new Virus(W_WIDTH / 2+180, W_HEIGHT / 2 + 200, 0.4, "DOOM");
				safe1 = new Safe(W_WIDTH / 2+100, W_HEIGHT / 2 + 200, 0.4, "LOVE");
				safe2 = new Safe(W_WIDTH / 2+260, W_HEIGHT / 2 + 200, 0.4, "DOLL");
				fileList.add(virus);
				fileList.add(safe1);
				fileList.add(safe2);
				text.setState(1);
			    state = 1;
		    }
			*/

		} 
	}
	
	public class MyMouseMotionListener extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			for (Filetype file:fileList ) {
				if (state == 3 && file.dragged(mouseX, mouseY)) {
					if(file.getHit()==false) {
						file.setPos(mouseX, mouseY);
					}
					//file.setPos(mouseX, mouseY);
					if (file.hit(trash)) {
						if(file instanceof Virus) {
							text.setState(2);
							if(cleared==false) {
								trace.play(0);
								bkmusic.pause();
							}
							cleared = true;
							//state = 2;
						}
						file.setHit(true);
						//fileList.remove(file);
					}
				}
				//file.draw(g2);
			}
			/*
			if (state == 1 && virus.dragged(mouseX, mouseY)) {
				virus.setPos(mouseX, mouseY);
				if (virus.hit(trash)) {
					text.setState(2);
					state = 2;
				}
			}
			*/
			repaint();
		}
	}
}

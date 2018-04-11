package game;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.magiclen.media.AudioPlayer;

/**
 * 建構視窗與畫面的Class
 * @author Takus
 *
 */

public class WindowsUI extends JFrame implements GameConfig{
	JPanel panel;
	Character ch;
	Map map;
	AudioPlayer audioPlayer;
	AudioPlayer titleAP;
	AudioPlayer clearAP;
	AudioPlayer gameOverAP;
	
	public static enum STATE{
		MENU,
		GUIDE,
		TURN2GAME,
		GAME,
		GAMEOVER,
		WIN
	};
	
	public static STATE state = STATE.MENU;
	//private Menu menu;
	
	/**
	 * 建構子，設定Title
	 */
	public WindowsUI(){
		setTitle("The Pavane of Vatican");
	}
	
	/**
	 * 初始化方法
	 */
	public void Start(){
		
		//Frame設定
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameX,frameY);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(3);
	
		//載入地圖
		//mapSet(map, ch);
		
		//安裝Listener
		PanelListener plis = new PanelListener();
		this.addKeyListener(plis);
		
		//設定面板
		panel = setPanel();
		
		//安裝面板
		this.add(panel);
		this.addMouseListener(new MenuMouseInput());
		
		//載入音樂
		File file = new File("audio/dungeon01.wav");
		audioPlayer = new AudioPlayer(file);
		File title = new File("audio/Title_music.wav");
		titleAP = new AudioPlayer(title);
		File gameOver = new File("audio/GameOver.wav");
		gameOverAP = new AudioPlayer(gameOver);
		File clear = new File("audio/Win.wav");
		clearAP = new AudioPlayer(clear);
		
		//面板刷新
		UpdateThread ut = new UpdateThread(panel);
		ut.start();

	}
	
	/**
	 * 設定使用地圖
	 * @param map 使用的地圖
	 * @param ch 使用角色
	 */
	public void mapSet(Map map, Character ch){
		//載入地圖
		this.map=map;
						
		//啟動人物
		this.ch=new Sister(map);
		this.ch.start();
	}
	
	/**
	 * 設定JPanel
	 * @return 設定好的JPanel
	 */
	public JPanel setPanel(){  
		
		JPanel panel = new MyPanel();
        panel.setPreferredSize(new Dimension(panelX, panelY));  
        panel.setLayout(null);  
        panel.setBackground(Color.white);  
        //menu = new Menu();
    	
    	return panel; 
        
         
    }  
	
	/**
	 * 按鍵傾聽者的Class
	 * @author Takus
	 *
	 */
	//Listener
	class PanelListener extends KeyAdapter{
		
		/**
		 * 按下按鈕
		 */
		//當按鈕按下
		public void keyPressed(KeyEvent e){
			if(state==STATE.GAME){
				int code = e.getKeyCode();
				switch(code){
					case KeyEvent.VK_RIGHT:
						ch.right=true;
						break;
					case KeyEvent.VK_LEFT:
						ch.left=true;
						break;
					case KeyEvent.VK_UP:
						ch.up=true;
						break;
					case KeyEvent.VK_Z:
						ch.attack=true;
						break;
					
				}
			}
			
		}
		
		/**
		 * 放開按鈕
		 */
		public void keyReleased(KeyEvent e){
			
			if(state==STATE.GAME){
				int code = e.getKeyCode();
				switch(code){
					case KeyEvent.VK_RIGHT:
						ch.right=false;
						ch.rightCount=27;
						break;
					case KeyEvent.VK_LEFT:
						ch.left=false;
						ch.leftCount=27;
						break;
					case KeyEvent.VK_Z:
						ch.attack=false;
						break;
					
				}
			}
			
		}
		
	}
	
	/**
	 * 建構遊戲畫面的Class
	 * @author Takus
	 *
	 */
	//建構遊戲畫面
	class MyPanel extends JPanel{
		JLabel JHP = new JLabel();
		JLabel JEC = new JLabel();
		
		/**
		 * 建構子
		 */
		public MyPanel(){
			super();
			
			JHP.setFont(new Font("Bleeding Cowboys", Font.BOLD, 50));
			JHP.setForeground(new Color(182, 161, 213));
			JHP.setBounds(150, 0, 300, 100);
			add(JHP);
			
			JEC.setFont(new Font("Bleeding Cowboys", Font.BOLD, 50));
			JEC.setForeground(new Color(182, 161, 213));
			JEC.setBounds(925, 0, 400, 100);
			add(JEC);
		}
		
		/**
		 * 繪製畫面
		 */
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			//主選單
			if(state == STATE.MENU){
				clearAP.stop();
				gameOverAP.stop();
				titleAP.play();
				map=null;
				ch=null;
				g.drawImage(Title.getImage(), 0, 0, null);
			}
			
			//開始遊戲前
			else if(state == STATE.TURN2GAME){
				try{
					mapSet(new Dungeon01(), ch);
					g.setColor(Color.black);
					g.fillRect(0, 0, 1300, 750);
						
					TimeUnit.SECONDS.sleep(2);
					state=WindowsUI.STATE.GAME;
						
				}catch(InterruptedException e){
					e.printStackTrace();
				}	

			}
			
			//說明
			else if(state==STATE.GUIDE){
				g.drawImage(Title_Guide.getImage(), 0, 0, null);
			}
			
			//死亡
			else if(state==STATE.GAMEOVER){
				audioPlayer.stop();
				gameOverAP.play();
				Character.x=1000;
				JHP.setText("");
				JEC.setText("");
				map=null;
				ch=null;
				g.setColor(Color.black);
				g.fillRect(0, 0, 1300, 750);
				g.drawImage(GameOver.getImage(), 0, 0, null);
				
			}
			
			else if(state==STATE.WIN){
				audioPlayer.stop();
				clearAP.play();
				JHP.setText("");
				JEC.setText("");
				g.setColor(Color.black);
				g.fillRect(0, 0, 1300, 750);
				g.drawImage(Clear.getImage(), 0, 0, null);
				
			}
			
			//遊戲中
			else if(state==STATE.GAME){
				titleAP.stop();
				audioPlayer.play();
				
				for(int j=ch.getJ()-13;j<=ch.getJ()+13;j++){
					//畫背景
					int i=j%4;
                	ImageIcon icon = GetIcon.background2icon(map.background[i]); 
                	g.drawImage(icon.getImage(), (13+j-ch.getJ())*elesize*2, 0, null);
				}
				
				for(int i=ch.getI()-11;i<=ch.getI()+3;i++){  
	                for(int j=ch.getJ()-13;j<=ch.getJ()+13;j++){  
	                	
	                	 //畫地圖
	                	ImageIcon icon = GetIcon.int2icon(map.map1[i][j]);  
                        g.drawImage(icon.getImage(), (13+j-ch.getJ())*elesize, (11+i-ch.getI())*elesize, elesize, elesize, null);  
	                }  
			 }
				
			 //畫敵人
			 drawEnemy(g);
			 
			 //畫角色
			 ch.drawCharacter(g);  
			 
			 //畫發射物
			 if(ch.wp.attack){
				 g.drawImage(GetIcon.weapon2Icon(ch.wp.icon).getImage(), (ch.wp.getJ()-ch.getJ()+13)*elesize, (ch.wp.getI()-ch.getI()+11)*elesize, null);
			 }
			 
			 //畫遮罩
			 g.drawImage(Mask.getImage(), 0, 0, null);
			 JHP.setText("HP: "+ch.HP);	
			 JEC.setText("Enemys: "+map.getEnemyCount());
			}
		}	
		
		/**
		 * 繪製敵人
		 * @param g 系統參數Graphics
		 */
		public void drawEnemy(Graphics g){
			ArrayList ms = map.getEnemyList();

		    for (Object m1 : ms) {
		        Enemy m = (Enemy) m1;
		        if(m.visiable){
		        	m.drawEnemy(g);
		        }
		        
		        if(m.visiable&&m.enemyWp.attack==true){
		        	g.drawImage(GetIcon.weapon2Icon(m.enemyWp.icon).getImage(), (m.enemyWp.getJ()-ch.getJ()+13)*elesize, (m.enemyWp.getI()-ch.getI()+11)*elesize-25, null);
		        }
		        
		    }
		}
	}
	
	/**
	 * 刷新畫面的Class
	 * @author Takus
	 *
	 */
	class UpdateThread extends Thread{
		JPanel panel;
		
		/**
		 * 建構子
		 * @param panel 要刷新的Panel
		 */
		public UpdateThread(JPanel panel){
			this.panel=panel;
		}
		
		/**
		 * 執行緒
		 */
		@Override
		public void run(){
			while(true){
				panel.repaint();
				try{
					Thread.sleep(10);
				}	catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 用於Menu的滑鼠傾聽者
	 * @author Takus
	 *
	 */
	class MenuMouseInput implements MouseListener {
		File SE = new File("audio/Title_SE.wav");
		AudioPlayer[] AP = new AudioPlayer[4];
		/**
		 * 建構子
		 */
		public MenuMouseInput(){
			super();
			for(int i=0;i<4;i++){
				AP[i] = new AudioPlayer(SE);
			}
		}
		
		/**
		 * 滑鼠按下
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
			int mx = e.getX();
			int my = e.getY();
			//System.out.println(mx+" "+my);
			
			
			if(mx>=940 && mx<= 1160&&WindowsUI.state==WindowsUI.STATE.MENU){
				if(my>=420 && my<=500){
					AP[0].play();
					//titleAP.stop();
					//titleAP.close();
					WindowsUI.state=WindowsUI.STATE.TURN2GAME;
					//panel.repaint();
				}
				
				else if(my>=525 && my<= 595){
					AP[1].play();
					WindowsUI.state=WindowsUI.STATE.GUIDE;
				}
				
				else if(my>=625 && my<= 695){
					AP[2].play();
					try{
						TimeUnit.SECONDS.sleep(1);
					}catch(InterruptedException E){
						E.printStackTrace();
					}
					System.exit(1);
				}
			}
			
			else if(mx>=1045 && mx<=1310 && WindowsUI.state==WindowsUI.STATE.GUIDE){
				if(my>=660 && my<= 720){
					AP[3].play();
					WindowsUI.state=WindowsUI.STATE.MENU;
				}
			}
			
			else if(mx>=1085 && mx<=1345 && WindowsUI.state==WindowsUI.STATE.GAMEOVER){
				if(my>=90 && my<=140){
					AP[3].play();
					try{
						TimeUnit.SECONDS.sleep(1);
					}catch(InterruptedException E){
						E.printStackTrace();
					}
					WindowsUI.state=WindowsUI.STATE.MENU;
					
				}
			}
			
			else if(mx>=1085 && mx<=1345 && WindowsUI.state==WindowsUI.STATE.WIN){
				if(my>=690 && my<=740){
					AP[3].play();
					try{
						TimeUnit.SECONDS.sleep(1);
					}catch(InterruptedException E){
						E.printStackTrace();
					}
					WindowsUI.state=WindowsUI.STATE.MENU;
					
				}
			}
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	
}

package game;
import javax.swing.*;
import java.awt.*;

/**
 * 定義遊戲設定檔案
 * @author Takus
 *
 */
public interface GameConfig {
	
	int frameX=1500;	//frame長
	int frameY=810;		//frame高
	int panelX=1300;	//畫面長
	int panelY=750;		//畫面高
	int elesize=50;		//素材大小
	int playersize=50;	//玩家大小
	//遊戲圖片
	ImageIcon blocks = new ImageIcon("images/blocks.png");	//地板
	ImageIcon none = new ImageIcon("images/test8.png");	//透明
	ImageIcon BGicon01 = new ImageIcon("images/background01_fix.png");	//背景
	ImageIcon BGicon02 = new ImageIcon("images/background02_fix.png");	//背景
	ImageIcon BGicon03 = new ImageIcon("images/background03_fix.png");	//背景
	ImageIcon BGicon04 = new ImageIcon("images/background04_fix.png");	//背景
	ImageIcon sister_right1 = new ImageIcon("images/sister_right1.png");
	ImageIcon sister_right2 = new ImageIcon("images/sister_right2.png");
	ImageIcon sister_right3 = new ImageIcon("images/sister_right3.png");
	ImageIcon sister_left1 = new ImageIcon("images/sister_left1.png");
	ImageIcon sister_left2 = new ImageIcon("images/sister_left2.png");
	ImageIcon sister_left3 = new ImageIcon("images/sister_left3.png");
	ImageIcon DGicon1 = new ImageIcon("images/Dagger1_right.png");		//飛刀
	ImageIcon DGicon2 = new	ImageIcon("images/Dagger2_right.png");
	ImageIcon DGicon3 = new	ImageIcon("images/Dagger3_right.png");
	ImageIcon DGicon4 = new	ImageIcon("images/Dagger4_right.png");
	ImageIcon DGicon5 = new	ImageIcon("images/Dagger1_left.png");
	ImageIcon DGicon6 = new	ImageIcon("images/Dagger2_left.png");
	ImageIcon DGicon7 = new	ImageIcon("images/Dagger3_left.png");
	ImageIcon DGicon8 = new	ImageIcon("images/Dagger4_left.png");
	ImageIcon ENicon_right1 = new ImageIcon("images/enemy_right1.png");	//敵人
	ImageIcon ENicon_right2 = new ImageIcon("images/enemy_right2.png");
	ImageIcon ENicon_right3 = new ImageIcon("images/enemy_right3.png");
	ImageIcon ENicon_left1 = new ImageIcon("images/enemy_left1.png");
	ImageIcon ENicon_left2 = new ImageIcon("images/enemy_left2.png");
	ImageIcon ENicon_left3 = new ImageIcon("images/enemy_left3.png");
	ImageIcon GNicon1 = new ImageIcon("images/Gun_right.png");	//	弩槍
	ImageIcon GNicon2 = new ImageIcon("images/Gun_left.png");
	ImageIcon Title = new ImageIcon("images/Title.jpg");	//標題
	ImageIcon TitleBlack = new ImageIcon("images/black.png");	//轉場用
	ImageIcon Title_Guide = new ImageIcon("images/Guide.jpg");	//說明
	ImageIcon GameOver = new ImageIcon("images/gameover.jpg");//	死亡
	ImageIcon Clear = new ImageIcon("images/clear.jpg");	//勝利
	ImageIcon Mask = new ImageIcon("images/mask2.png");	//遮罩
	
}

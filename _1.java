
package soju_marble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 	* this code is made by SouthKorea student in Kyungpook national university(KNU), so base comment or image is also Korean. 
 	* Because of my English Level, translation is little awkward. sorry about it. Think you! 
 
	class : Character 	- Player
						- AI
			Slot
			MainScreen
			
 */

// for Player & AI
// �÷��̾�� AI�� ���� ���� Ŭ����
class Character {
	public String characterName;
	public int characterNum;
	public int characterReplace = 0;
	public int characterDrink;
	public boolean characterIsIsland = false;
}

// User class
// ����� Ŭ����
class Player extends Character {
	public Player(String playerName){
		this.characterName = playerName;
	}
	public void SetName(String playerName) {
		this.characterName = playerName;
	}
}

// AI player class
// AI Ŭ����
class Ai extends Character {
	public void firstAICreate() {
		this.characterName = "AI1";
	}
	public void secondAICreate() {
		this.characterName = "AI2";
	}
}

// for in-game field
// ���� Ŭ����
class Slot {
	String penaltyContent;
	int addGlassNum;
	
	public Slot(String penaltyContent, int addGlassNum) {
		this.penaltyContent = penaltyContent ;
		this.addGlassNum = addGlassNum;
	}		
}

// JFrame class for GUI
// GUI ������ ���� JFrame Ŭ����
@SuppressWarnings("serial")
class MainScreen extends JFrame {
	static int BottonPushNum = 0;
	static int PlayerNum;
	static int AINum;
	static int gameMode;
	// 1 : give-up on,  2 : give-up off		1 : �׺� ����,  2 : �׺� ����
	static boolean firstButtonFlag = false, secondButtonFlag = false, thirdButtonFlag = false;
	static boolean nextButtonSetFlag = false;
	static String[] characterName = {null, null, null, null} ;
	static int[] characterMaxGlass = {0, 0, 0, 0};
	
	static JLabel mainLabel = new JLabel();   
	static JButton gameStartButton = new JButton(new ImageIcon("./Image/�� ��ŸƮ ��ư.jpg")); // JButton image setting, jButton �̹��� ����
	static JButton seeRankingButton = new JButton(new ImageIcon("./Image/��ŷ ��ư.jpg"));
	static JButton seehowtoplayImageLabellayButton = new JButton(new ImageIcon("./Image/�Ͽ��� �÷��� ��ư.jpg"));
	static JButton exitButton = new JButton(new ImageIcon("./Image/���� ��ư.jpg"));
	static JLabel titleImageLabel = new JLabel(new ImageIcon("./Image/Ÿ��Ʋ 1.jpg"));  // JLabel image setting, jLabel �̹��� ����
	static JLabel bottleImageLabel = new JLabel(new ImageIcon("./Image/�� �̹���.jpg"));
	static JLabel bambooImageLabel = new JLabel(new ImageIcon("./Image/�볪��.jpg")); 
	static JTextField seeRankingErrorTextField = new JTextField("���� ������ �����ϴ�");
	static Font defaultFont = new Font("����ü",0,45); 
	static Color defalutBackgroundColor = new Color(83,125,16);
	
	static int turn = 1;
	static Slot[] Slots = new Slot[12];
	static int randomNum = 0;
	
	static JLabel Gamebord = new JLabel(new ImageIcon("./Image/bord.jpg")); 
	static JLabel diceImageLabel = new JLabel(new ImageIcon("./Image/�⺻�ֻ���.jpg"));
	static JLabel diceNumOneLabel = new JLabel(new ImageIcon("./Image/�ֻ���1.jpg"));
	static JLabel diceNumTwoLabel = new JLabel(new ImageIcon("./Image/�ֻ���2.jpg"));
	static JLabel diceNumThreeLabel = new JLabel(new ImageIcon("./Image/�ֻ���3.jpg"));
	static JLabel diceNumFourLabel = new JLabel(new ImageIcon("./Image/�ֻ���4.jpg"));
	static JLabel diceNumFiveLabel = new JLabel(new ImageIcon("./Image/�ֻ���5.jpg"));
	static JLabel diceNumSixLabel = new JLabel(new ImageIcon("./Image/�ֻ���6.jpg"));
	static JLabel player1IconLabel = new JLabel(new ImageIcon("./Image/p1������.jpg"));
	static JLabel player2IconLabel = new JLabel(new ImageIcon("./Image/p2������.jpg"));
	static JLabel player3IconLabel = new JLabel(new ImageIcon("./Image/p3������.jpg"));
	static JLabel player4IconLabel = new JLabel(new ImageIcon("./Image/p4������.jpg"));
	static JLabel AI1IconLabel = new JLabel(new ImageIcon("./Image/ai1������.jpg"));
	static JLabel AI2IconLabel = new JLabel(new ImageIcon("./Image/ai2������.jpg"));
	static JButton diceRollingButton = new JButton(new ImageIcon("./Image/�ֻ����������ư.jpg"));
	static JButton giveUpButton = new JButton(new ImageIcon("./Image/�׺���ư1.jpg"));
	static JButton exitButton2 = new JButton(new ImageIcon("./Image/���� ��ư.jpg"));
	
	static FileWriter out = null;
	static FileReader in = null;
	
	public MainScreen() {
	
		setSize(1000, 800); // setting Frame size, ���� â ũ�⼳��
		setTitle("�ַ縶�� ����"); // setting Frame name, ���� �̸� ����
		setLocation(100,100); // setting Frame location in display, ���� â �ߴ� ��ġ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // process end setting,  �� ������ ���μ����� ����
		// if this code if gone, although JFrame end, background process is remind
		// �� �ڵ尡 ������, ���α׷��� ����Ǿ ��׶��忡 ���μ����� ������� ����
	
		mainLabel.setOpaque(true);  // background on setting, ���ȭ�� ��� ����
		mainLabel.setBackground(defalutBackgroundColor); // background color setting,  ��׶��� �� ����
	
		titleImageLabel.setBounds(200,50,600,300); // label location setting, label ��ġ ����
		bottleImageLabel.setBounds(730, 350,200, 400);
		bambooImageLabel.setBounds(1, 280,330, 500);
		
		gameStartButton.setRolloverIcon(new ImageIcon("./Image/�� ��ŸƮ ��ư2.jpg")); // button RolloverIcon setting, ��ư �������� �̹��� ����
		gameStartButton.setBorderPainted(false); // button border setting, ��ư �׵θ� ���ֱ�
		gameStartButton.setBounds(350, 300, 280, 80); // location setting, ��ư ��ġ ����
			gameStartButton.addActionListener(new ActionListener() { // button activation setting by ActionListener , ��ư  ���� ����
				public void actionPerformed(ActionEvent e) {
					mainLabel.setVisible(false);
					titleImageLabel.setVisible(false);
					seeRankingErrorTextField.setVisible(false);
					gameSetting();
				}
			});
			
		seeRankingButton.setRolloverIcon(new ImageIcon("./Image/��ŷ ��ư2.jpg"));
		seeRankingButton.setBorderPainted(false);
		seeRankingButton.setBounds(380, 400, 220, 80);
			seeRankingButton.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					mainLabel.setVisible(false);
					titleImageLabel.setVisible(false);
					seeRankingErrorTextField.setVisible(false);
					seeRanking();
				}
			});
			seehowtoplayImageLabellayButton.setRolloverIcon(new ImageIcon("./Image/�Ͽ��� �÷��� ��ư2.jpg"));
			seehowtoplayImageLabellayButton.setBorderPainted(false);
			seehowtoplayImageLabellayButton.setBounds(340, 500, 300, 80);
			seehowtoplayImageLabellayButton.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					mainLabel.setVisible(false);
					titleImageLabel.setVisible(false);
					bottleImageLabel.setVisible(false);
					bambooImageLabel.setVisible(false);
					seeRankingErrorTextField.setVisible(false);
					explainGame();
				}
			});
			exitButton.setRolloverIcon(new ImageIcon("./Image/���� ��ư2.jpg"));
			exitButton.setBorderPainted(false);
			exitButton.setBounds(425, 600, 125, 70);
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0); // program end code, JFrame ���α׷� ���� �ڵ�
				}
			});
			
			mainLabel.add(gameStartButton);  // add buttons at mainLabel, ��ư�� �󺧿� �߰�
			mainLabel.add(seeRankingButton); 
			mainLabel.add(seehowtoplayImageLabellayButton);
			mainLabel.add(exitButton);
	
			add(titleImageLabel);	// add labels at Frame, ���� �����ӿ� �߰�
			add(bottleImageLabel);
			add(bambooImageLabel);
			add(mainLabel);
			
			setVisible(true); 
			// this code is for output JFrame. if this code is gone, mainFrame is also gone, ȭ�鿡 JFrame ���!! ������ â �ȶ�
	}
	
	// seeRanking button activation method, seeRanking ��ư ���� �żҵ�
	public void seeRanking() {	
		JLabel seeRankingMainLabel = new JLabel();
		JTextField Rank1 = new JTextField();
		JTextField Rank2 = new JTextField();
		JTextField Rank3 = new JTextField();
		
		seeRankingMainLabel.setOpaque(true);
		seeRankingMainLabel.setBackground(defalutBackgroundColor);
		 
		Player[] playerBuffer = null;
		int intBuffer;
		String strBuffer = "";
		String[] strArrBuffer = null;
		
		JButton backButton = new JButton(new ImageIcon("./Image/�� ��ư.jpg"));
		
		backButton.setRolloverIcon(new ImageIcon("./Image/�� ��ư2.jpg")); 
		backButton.setBorderPainted(false); 
		backButton.setBounds(400, 600, 150, 70); 
		backButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				seeRankingMainLabel.setVisible(false);
				mainLabel.setVisible(true);
				titleImageLabel.setVisible(true);
			}
		});
		
		try {
			in = new FileReader("score.txt");
			while((intBuffer = in.read()) != -1) 
				strBuffer += (char)intBuffer;
            in.close();	
		}catch(IOException e) {
			System.out.println("IOException");
		}
		strArrBuffer = strBuffer.split(" ");
		playerBuffer = new Player[strArrBuffer.length];
		for(int i=0;i<strArrBuffer.length;i++) {
			playerBuffer[i] = new Player(strArrBuffer[i].split(":")[0]);
			playerBuffer[i].characterDrink = Integer.parseInt(strArrBuffer[i].split(":")[1]);
		}
		//for First
		int buffer1 = 0;
		int buffer2 = 0;
		for(int i=0;i<playerBuffer.length;i++) {
			if(buffer1 < playerBuffer[i].characterDrink) {
				buffer1 = playerBuffer[i].characterDrink;
				buffer2 = i;
			}
			if(i == (playerBuffer.length-1)) {
				Rank1.setText("1st : "+playerBuffer[buffer2].characterName + " --- " + Integer.toString(playerBuffer[buffer2].characterDrink)+"�� ����!");
				playerBuffer[buffer2].characterDrink = 0;
				buffer1 = 0;
				buffer2 = 0;
			}
		}
		// for Second
		for(int i=0;i<playerBuffer.length;i++) {
			if(buffer1 <= playerBuffer[i].characterDrink) {
				buffer1 = playerBuffer[i].characterDrink;
				buffer2 = i;
			}
			if(i == (playerBuffer.length-1)) {
				Rank2.setText("2nd : "+playerBuffer[buffer2].characterName + " --- " + Integer.toString(playerBuffer[buffer2].characterDrink)+"�� ����!");
				playerBuffer[buffer2].characterDrink = 0;
				buffer1 = 0;
				buffer2 = 0;
			}
		}
		// for Thrid 
		for(int i=0;i<playerBuffer.length;i++) {
			if(buffer1 <= playerBuffer[i].characterDrink) {
				buffer1 = playerBuffer[i].characterDrink;
				buffer2 = i;
			}
			if(i == (playerBuffer.length-1)) {
				Rank3.setText("3rd : "+playerBuffer[buffer2].characterName + " --- " + Integer.toString(playerBuffer[buffer2].characterDrink)+"�� ����!");			}
		}
		Rank1.setBounds(210, 200, 570, 70);
		Rank2.setBounds(210, 350, 570, 70);
		Rank3.setBounds(210, 500, 570, 70);
		Rank1.setBackground(Color.WHITE);
		Rank2.setBackground(Color.WHITE);
		Rank3.setBackground(Color.WHITE);
		Rank1.setFont(defaultFont);
		Rank2.setFont(defaultFont);
		Rank3.setFont(defaultFont);
		seeRankingMainLabel.add(Rank1);
		seeRankingMainLabel.add(Rank2);
		seeRankingMainLabel.add(Rank3);
		seeRankingMainLabel.add(backButton);
		add(seeRankingMainLabel);
		
	}
	
	// How to play button activation method, How to play ��ư ���� �żҵ�
	public void explainGame() {
		
		JLabel howtoplayImageLabel = new JLabel(new ImageIcon("./Image/�Ͽ��� �÷��� �̹���.jpg")); 
		JButton backButton = new JButton(new ImageIcon("./Image/�� ��ư.jpg"));
		
		howtoplayImageLabel.setBounds(1,1,990,790);
		backButton.setRolloverIcon(new ImageIcon("./Image/�� ��ư2.jpg")); 
		backButton.setBorderPainted(false);
		backButton.setBounds(650, 630, 150, 70); 
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				howtoplayImageLabel.setVisible(false);
				mainLabel.setVisible(true);
				titleImageLabel.setVisible(true);
				bottleImageLabel.setVisible(true);
				bambooImageLabel.setVisible(true);
			}
		});
		
		howtoplayImageLabel.add(backButton);
		add(howtoplayImageLabel);	
	}
	
	// game start button activation method, game start ��ư ���� �żҵ�
	// first display after choose game start button, ���ӽ��� ��ư �̷� ù��° ȭ��
	public void gameSetting() {
		
		JLabel gameSettingMainLabel = new JLabel(); //
		JLabel gameSettingImageLabel1 = new JLabel(new ImageIcon("./Image/�÷��̾� �� ���� �̹���.jpg"));
		JLabel gameSettingImageLabel2 = new JLabel(new ImageIcon("./Image/ai �� ���� �̹���.jpg"));
		JLabel gameSettingImageLabel3 = new JLabel(new ImageIcon("./Image/���� ��� ���� �̹���.jpg"));
		
		JButton player1Button = new JButton(new ImageIcon("./Image/1��ư.jpg")); // player number setting button, �÷��̾� �� ���� ��ư
		JLabel player1Label = new JLabel(new ImageIcon("./Image/1��ư2.jpg"));  // button choice image, ��ư ������ �̹���
		JButton player2Button = new JButton(new ImageIcon("./Image/2��ư.jpg"));
		JLabel player2Label = new JLabel(new ImageIcon("./Image/2��ư2.jpg"));
		JButton player3Button = new JButton(new ImageIcon("./Image/3��ư.jpg"));
		JLabel player3Label = new JLabel(new ImageIcon("./Image/3��ư2.jpg"));
		JButton player4Button = new JButton(new ImageIcon("./Image/4��ư.jpg"));
		JLabel player4Label = new JLabel(new ImageIcon("./Image/4��ư2.jpg"));
		JButton AI0Button = new JButton(new ImageIcon("./Image/0��ư.jpg")); // AI number setting button, AI �� ���� ��ư
		JLabel AI0Label = new JLabel(new ImageIcon("./Image/0��ư2.jpg")); // button choice image, ��ư ������ �̹���
		JButton AI1Button = new JButton(new ImageIcon("./Image/1��ư.jpg")); 
		JLabel AI1Label = new JLabel(new ImageIcon("./Image/1��ư2.jpg")); 
		JButton AI2Button = new JButton(new ImageIcon("./Image/2��ư.jpg"));
		JLabel AI2Label = new JLabel(new ImageIcon("./Image/2��ư2.jpg"));
		JButton giveupOnButton = new JButton(new ImageIcon("./Image/�׺��� ��ư.jpg")); // game mode setting button, ���Ӹ�� ���� ��ư
		JLabel giveupOnLabel = new JLabel(new ImageIcon("./Image/�׺��� ��ư2.jpg")); // button choice image, ��ư ������ �̹���
		JButton giveupOffButton = new JButton(new ImageIcon("./Image/�׺����� ��ư.jpg"));
		JLabel giveupOffLabel = new JLabel(new ImageIcon("./Image/�׺����� ��ư2.jpg"));
		
		JButton nextButton = new JButton(new ImageIcon("./Image/���� ��ư.jpg")); 
		// next button. when game setting done, button display, ���� ��ư���� �ʿ��� ��ư���� ��� �������� ȭ�鿡 ǥ��
		
		gameSettingMainLabel.setOpaque(true); 
		gameSettingMainLabel.setBackground(defalutBackgroundColor); 
		
		gameSettingImageLabel1.setBounds(300, 50, 400, 100); // Label or Button location setting, ��,��ư ��ġ����
		gameSettingImageLabel2.setBounds(300, 250, 400, 100);
		gameSettingImageLabel3.setBounds(300, 450, 400, 100);
		player1Button.setBounds(250, 150, 85, 65);player1Label.setBounds(250, 150, 85, 65); 
		player2Button.setBounds(390, 150, 85, 65);player2Label.setBounds(390, 150, 85, 65);
		player3Button.setBounds(530, 150, 85, 65);player3Label.setBounds(530, 150, 85, 65);
		player4Button.setBounds(670, 150, 85, 65);player4Label.setBounds(670, 150, 85, 65);
		AI0Button.setBounds(360, 350, 85, 65);AI0Label.setBounds(360, 350, 85, 65);
		AI1Button.setBounds(460, 350, 85, 65);AI1Label.setBounds(460, 350, 85, 65);
		AI2Button.setBounds(560, 350, 85, 65);AI2Label.setBounds(560, 350, 85, 65);
		giveupOnButton.setBounds(400, 550, 190, 75);giveupOnLabel.setBounds(400, 550, 190, 75);
		giveupOffButton.setBounds(400, 650, 190, 75);giveupOffLabel.setBounds(400, 650, 190, 75);
		nextButton.setBounds(850, 20, 100, 40);
		
		player1Label.setVisible(false); // first display setting, ó�� ȭ�� ����
		player2Label.setVisible(false);
		player3Label.setVisible(false);
		player4Label.setVisible(false);
		AI0Label.setVisible(false);
		AI1Label.setVisible(false);
		AI2Label.setVisible(false);
		giveupOnLabel.setVisible(false);
		giveupOffLabel.setVisible(false);
		nextButton.setVisible(false); 
		
		// button activation setting, ��ư ���� ����
		// 1 line, 1 choice setting, �� ���ο��� �� ��ư�� ���� �����ϵ��� ������
		player1Button.setRolloverIcon(new ImageIcon("./Image/1��ư2.jpg")); 
		player1Button.setBorderPainted(false); 
		player1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player2Label.setVisible(false);
				player3Label.setVisible(false);
				player4Label.setVisible(false);
				player1Button.setVisible(false);
				player2Button.setVisible(true);
				player3Button.setVisible(true);
				player4Button.setVisible(true);
				player1Label.setVisible(true);
				// by button, setting player number, ��ư�� ���� �÷��̼� �� ����
				PlayerNum = 1;
				// first line button check flag, ù��° ���� ��ư�� ������������ Ȯ��
				firstButtonFlag = true;
				// if, 3 line button chick is done, next button display, ���� 3���� ��ư�� ��� Ŭ���Ǹ�, ���� ��ư ǥ����
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		player2Button.setRolloverIcon(new ImageIcon("./Image/2��ư2.jpg")); 
		player2Button.setBorderPainted(false); 
		player2Button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				player1Label.setVisible(false);
				player3Label.setVisible(false);
				player4Label.setVisible(false);
				player2Button.setVisible(false);
				player1Button.setVisible(true);
				player3Button.setVisible(true);
				player4Button.setVisible(true);
				player2Label.setVisible(true);
				PlayerNum = 2;
				firstButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		player3Button.setRolloverIcon(new ImageIcon("./Image/3��ư2.jpg"));
		player3Button.setBorderPainted(false); 
		player3Button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				player1Label.setVisible(false);
				player2Label.setVisible(false);
				player4Label.setVisible(false);
				player3Button.setVisible(false);
				player1Button.setVisible(true);
				player2Button.setVisible(true);
				player4Button.setVisible(true);
				player3Label.setVisible(true);
				PlayerNum = 3;
				firstButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		player4Button.setRolloverIcon(new ImageIcon("./Image/4��ư2.jpg"));
		player4Button.setBorderPainted(false); 
		player4Button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				player1Label.setVisible(false);
				player2Label.setVisible(false);
				player3Label.setVisible(false);
				player4Button.setVisible(false);
				player1Button.setVisible(true);
				player2Button.setVisible(true);
				player3Button.setVisible(true);
				player4Label.setVisible(true);
				PlayerNum = 4;
				firstButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		AI0Button.setRolloverIcon(new ImageIcon("./Image/0��ư2.jpg")); 
		AI0Button.setBorderPainted(false);
		AI0Button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {		
				AI1Label.setVisible(false);
				AI2Label.setVisible(false);
				AI2Button.setVisible(true);
				AI1Button.setVisible(true);
				AI0Button.setVisible(false);
				AI0Label.setVisible(true);
				bambooImageLabel.setVisible(false); // for error correct, ���� ������ 
				bambooImageLabel.setVisible(true);  // if this code is gone, bamboo is cutting..., ������ ���� �볪�� ©������
				// by button, setting player number, ��ư�� ���� �÷��̼� �� ����
				AINum = 0;
				// first line button check flag, ù��° ���� ��ư�� ������������ Ȯ��
				secondButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		AI1Button.setRolloverIcon(new ImageIcon("./Image/1��ư2.jpg"));
		AI1Button.setBorderPainted(false);
		AI1Button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {		
				AI0Label.setVisible(false);
				AI2Label.setVisible(false);
				AI2Button.setVisible(true);
				AI0Button.setVisible(true);
				AI1Button.setVisible(false);
				AI1Label.setVisible(true);
				bambooImageLabel.setVisible(false); 
				bambooImageLabel.setVisible(true); 
				AINum = 1;
				secondButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		AI2Button.setRolloverIcon(new ImageIcon("./Image/2��ư2.jpg"));
		AI2Button.setBorderPainted(false); 
		AI2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				AI0Label.setVisible(false);
				AI1Label.setVisible(false);
				AI1Button.setVisible(true);
				AI0Button.setVisible(true);
				AI2Button.setVisible(false);
				AI2Label.setVisible(true);
				bambooImageLabel.setVisible(false);
				bambooImageLabel.setVisible(true); 
				AINum = 2;
				secondButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		giveupOnButton.setRolloverIcon(new ImageIcon("./Image/�׺��� ��ư2.jpg"));
		giveupOnButton.setBorderPainted(false); 
		giveupOnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				giveupOffLabel.setVisible(false);
				giveupOffButton.setVisible(true);
				giveupOnButton.setVisible(false);
				giveupOnLabel.setVisible(true);
				bambooImageLabel.setVisible(false); 
				bambooImageLabel.setVisible(true);  
				gameMode = 1; 
				thirdButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		giveupOffButton.setRolloverIcon(new ImageIcon("./Image/�׺����� ��ư2.jpg")); 
		giveupOffButton.setBorderPainted(false); 
		giveupOffButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {		
				giveupOnLabel.setVisible(false);
				giveupOnButton.setVisible(true);
				giveupOffButton.setVisible(false);
				giveupOffLabel.setVisible(true);
				bambooImageLabel.setVisible(false);
				bambooImageLabel.setVisible(true); 
				gameMode = 2; 
				thirdButtonFlag = true;
				if(firstButtonFlag == true && secondButtonFlag == true && thirdButtonFlag == true && nextButtonSetFlag == false) {
					nextButton.setVisible(true);
					nextButtonSetFlag = true;
				}
			}});
		// when choose all setting button, next button is appear
		// 3���� ��ư�� ��� �ϳ� �̻� ��������, ���� ��ư�� ������
		nextButton.setRolloverIcon(new ImageIcon("./Image/���� ��ư2.jpg")); 
		nextButton.setBorderPainted(false); 
		nextButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {	
				gameSettingMainLabel.setVisible(false);
				settingName();
				// when clicked button, display transition, ���� ��ư ��������, ȭ�� ��ȯ
			}});
		
		gameSettingMainLabel.add(gameSettingImageLabel1); // add element at Label, �󺧿� �󺧰� ��ư �߰�
		gameSettingMainLabel.add(gameSettingImageLabel2);
		gameSettingMainLabel.add(gameSettingImageLabel3);
		gameSettingMainLabel.add(nextButton);
		gameSettingMainLabel.add(player1Button);
		gameSettingMainLabel.add(player2Button);
		gameSettingMainLabel.add(player3Button);
		gameSettingMainLabel.add(player4Button);
		gameSettingMainLabel.add(AI1Button);
		gameSettingMainLabel.add(AI2Button);
		gameSettingMainLabel.add(giveupOnButton);
		gameSettingMainLabel.add(giveupOffButton);
		gameSettingMainLabel.add(player1Label);
		gameSettingMainLabel.add(player2Label);
		gameSettingMainLabel.add(player3Label);
		gameSettingMainLabel.add(player4Label);
		gameSettingMainLabel.add(AI1Label);
		gameSettingMainLabel.add(AI2Label);
		gameSettingMainLabel.add(giveupOnLabel);
		gameSettingMainLabel.add(giveupOffLabel);
		gameSettingMainLabel.add(AI0Button);
		gameSettingMainLabel.add(AI0Label);
		add(gameSettingMainLabel); // add Label at Frame, �����ӿ� �� �߰�
	}
	
	// second display after choose game start button, ���ӽ��� ��ư �̷� �ι�° ȭ��
	public void settingName() {
		JLabel settingNameMainLabel = new JLabel(); //	
		JLabel player1NameImageLabel = new JLabel(new ImageIcon("./Image/�÷��̾�1 �̸��Է� �̹���.jpg"));
		JLabel player2NameImageLabel = new JLabel(new ImageIcon("./Image/�÷��̾�2 �̸��Է� �̹���.jpg"));
		JLabel player3NameImageLabel = new JLabel(new ImageIcon("./Image/�÷��̾�3 �̸��Է� �̹���.jpg"));
		JLabel player4NameImageLabel = new JLabel(new ImageIcon("./Image/�÷��̾�4 �̸��Է� �̹���.jpg"));
		JLabel nameSettingImageLabel = new JLabel(new ImageIcon("./Image/�̸��Է� �̹���.jpg"));
		JLabel glassSettingImageLabel = new JLabel(new ImageIcon("./Image/�ַ��Է��̹���.jpg"));
		JTextField player1NameSettingTestField = new JTextField(); // using JTextField, get user input
		JTextField player1GlassSettingTestField = new JTextField();// JTextField�� �̿��� ����� �Է� ����
		JTextField player2NameSettingTestField = new JTextField();
		JTextField player2GlassSettingTestField = new JTextField();
		JTextField player3NameSettingTestField = new JTextField();
		JTextField player3GlassSettingTestField = new JTextField();
		JTextField player4NameSettingTestField = new JTextField();
		JTextField player4GlassSettingTestField = new JTextField();
		JButton settingDoneButton = new JButton(new ImageIcon("./Image/�Ϸ� ��ư.jpg"));
		
		// element location setting, ��ġ����
		player1NameImageLabel.setBounds(370, 100, 300, 100);
		player2NameImageLabel.setBounds(370, 100, 300, 100);
		player3NameImageLabel.setBounds(370, 100, 300, 100);
		player4NameImageLabel.setBounds(370, 100, 300, 100);
		nameSettingImageLabel.setBounds(390, 200, 250, 100);
		glassSettingImageLabel.setBounds(390, 350, 250, 100);
		player1NameSettingTestField.setBounds(440, 300, 150, 30);
		player2NameSettingTestField.setBounds(440, 300, 150, 30);
		player3NameSettingTestField.setBounds(440, 300, 150, 30);
		player4NameSettingTestField.setBounds(440, 300, 150, 30);
		player1GlassSettingTestField.setBounds(440, 450, 150, 30);
		player2GlassSettingTestField.setBounds(440, 450, 150, 30);
		player3GlassSettingTestField.setBounds(440, 450, 150, 30);
		player4GlassSettingTestField.setBounds(440, 450, 150, 30);
		settingDoneButton.setBounds(470, 500, 83, 40);
		
		// first display setting, ó�� ȭ�� ����
		settingNameMainLabel.setOpaque(true); 
		settingNameMainLabel.setBackground(defalutBackgroundColor); 
		
		player2NameImageLabel.setVisible(false);
		player3NameImageLabel.setVisible(false);
		player4NameImageLabel.setVisible(false);
		player2NameSettingTestField.setVisible(false);
		player3NameSettingTestField.setVisible(false);
		player4NameSettingTestField.setVisible(false);
		player2GlassSettingTestField.setVisible(false);
		player3GlassSettingTestField.setVisible(false);
		player4GlassSettingTestField.setVisible(false);
		
		// button activation setting, ��ư ���� ����
		settingDoneButton.setRolloverIcon(new ImageIcon("./Image/�Ϸ� ��ư2.jpg")); 
		settingDoneButton.setBorderPainted(false);
		settingDoneButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {		
				if(PlayerNum != 1 && BottonPushNum == 0) {  
					player1NameImageLabel.setVisible(false);
					player2NameImageLabel.setVisible(true);
					characterName[0] = player1NameSettingTestField.getText();
					characterMaxGlass[0] = Integer.parseInt(player1GlassSettingTestField.getText());
					player1NameSettingTestField.setVisible(false);
					player1GlassSettingTestField.setVisible(false);
					player2NameSettingTestField.setVisible(true);	
					player2GlassSettingTestField.setVisible(true);	
					BottonPushNum ++;
					bambooImageLabel.setVisible(false); 
					bambooImageLabel.setVisible(true); 
				}
				else if(PlayerNum == 1 && BottonPushNum == 0) { 
					characterName[0] = player1NameSettingTestField.getText();
					characterMaxGlass[0] = Integer.parseInt(player1GlassSettingTestField.getText());
					settingNameMainLabel.setVisible(false);
					mainGame();
				}
				
				else if(PlayerNum != 2 && BottonPushNum == 1) { 
					player2NameImageLabel.setVisible(false);
					player3NameImageLabel.setVisible(true);
					characterName[1] = player2NameSettingTestField.getText();
					characterMaxGlass[1] = Integer.parseInt(player2GlassSettingTestField.getText());
					player2NameSettingTestField.setVisible(false);
					player2GlassSettingTestField.setVisible(false);
					player3NameSettingTestField.setVisible(true);	
					player3GlassSettingTestField.setVisible(true);
					BottonPushNum ++;
					bambooImageLabel.setVisible(false);
					bambooImageLabel.setVisible(true);  
				}
				else if(PlayerNum == 2 && BottonPushNum == 1) { 
					characterName[1] = player2NameSettingTestField.getText();
					characterMaxGlass[1] = Integer.parseInt(player2GlassSettingTestField.getText());
					settingNameMainLabel.setVisible(false);
					mainGame();
				}
				else if(PlayerNum != 3 && BottonPushNum == 2) { 
					player3NameImageLabel.setVisible(false);
					player4NameImageLabel.setVisible(true);
					characterName[2] = player3NameSettingTestField.getText();
					characterMaxGlass[2] = Integer.parseInt(player3GlassSettingTestField.getText());
					player3NameSettingTestField.setVisible(false);
					player3GlassSettingTestField.setVisible(false);
					player4NameSettingTestField.setVisible(true);	
					player4GlassSettingTestField.setVisible(true);
					BottonPushNum ++;
					bambooImageLabel.setVisible(false); 
					bambooImageLabel.setVisible(true); 
				}
				else if(PlayerNum == 3 && BottonPushNum == 2) {
					characterName[2] = player3NameSettingTestField.getText();
					characterMaxGlass[2] = Integer.parseInt(player3GlassSettingTestField.getText());
					settingNameMainLabel.setVisible(false);
					mainGame();
				}
				else if(PlayerNum == 4 && BottonPushNum == 3) {
					characterName[3] = player4NameSettingTestField.getText();
					characterMaxGlass[3] = Integer.parseInt(player4GlassSettingTestField.getText());
					settingNameMainLabel.setVisible(false);
					mainGame();
				}
			}});
		
		settingNameMainLabel.add(player1NameImageLabel);
		settingNameMainLabel.add(player2NameImageLabel);
		settingNameMainLabel.add(player3NameImageLabel);
		settingNameMainLabel.add(player4NameImageLabel);
		settingNameMainLabel.add(player1NameSettingTestField);
		settingNameMainLabel.add(player2NameSettingTestField);
		settingNameMainLabel.add(player3NameSettingTestField);
		settingNameMainLabel.add(player4NameSettingTestField);
		settingNameMainLabel.add(player1GlassSettingTestField);
		settingNameMainLabel.add(player2GlassSettingTestField);
		settingNameMainLabel.add(player3GlassSettingTestField);
		settingNameMainLabel.add(player4GlassSettingTestField);
		settingNameMainLabel.add(settingDoneButton);
		settingNameMainLabel.add(nameSettingImageLabel);
		settingNameMainLabel.add(glassSettingImageLabel);
		add(settingNameMainLabel);
		
	}
	
	public void mainGame() {
		//create Slots with String setting, ���� ������ ���ڿ� ����
		Slots[0] = new Slot("�ѹ��� ���ҽ��ϴ�",0); 
		Slots[1] = new Slot("2���Դϴ�",2);
		Slots[2] = new Slot("�ֻ�������ƴ �弼��",0);
		Slots[3] = new Slot("���ε��Դϴ�. 1�� ������",0);
		Slots[4] = new Slot("��ҽ��ϴ�",0);
		Slots[5] = new Slot("1���Դϴ�",1);
		Slots[6] = new Slot("2���Դϴ�",2);
		Slots[7] = new Slot("�ֻ�������ƴ �弼��",0);
		Slots[8] = new Slot("��ҽ��ϴ�",0);
		Slots[9] = new Slot("���ּ��Դϴ�. �����̵��մϴ�",0);
		Slots[10] = new Slot("�ֻ�������ƴ �弼��",0);
		Slots[11] = new Slot("��ҽ��ϴ�",0);
		
		Gamebord.setOpaque(true);  
		Gamebord.setBackground(defalutBackgroundColor); 

		
		
		bottleImageLabel.setVisible(false);
		bambooImageLabel.setVisible(false);



		diceRollingButton.setBounds(325, 450, 211, 63);
		giveUpButton.setBounds(830, 655, 140, 51);
		diceImageLabel.setBounds(330, 240, 200, 200);
		diceNumOneLabel.setBounds(330, 240, 200, 200);
		diceNumTwoLabel.setBounds(330, 240, 200, 200);
		diceNumThreeLabel.setBounds(330, 240, 200, 200);
		diceNumFourLabel.setBounds(330, 240, 200, 200);
		diceNumFiveLabel.setBounds(330, 240, 200, 200);
		diceNumSixLabel.setBounds(330, 240, 200, 200);
		player1IconLabel.setBounds(65, 175, 20, 20);
		player2IconLabel.setBounds(90, 175, 20, 20);
		player3IconLabel.setBounds(115, 175, 20, 20);
		player4IconLabel.setBounds(140, 175, 20, 20);
		AI1IconLabel.setBounds(165, 175, 20, 20);
		AI2IconLabel.setBounds(190, 175, 20, 20);
		exitButton.setBounds(835, 550, 125, 70);
	
		diceNumOneLabel.setVisible(false);
		diceNumTwoLabel.setVisible(false);
		diceNumThreeLabel.setVisible(false);
		diceNumFourLabel.setVisible(false);
		diceNumFiveLabel.setVisible(false);
		diceNumSixLabel.setVisible(false);
		exitButton.setVisible(false);
	
		diceRollingButton.setRolloverIcon(new ImageIcon("./Image/�ֻ����������ư2.jpg")); // ��ư ��������
		diceRollingButton.setBorderPainted(false); // ��ư �׵θ� ���ֱ�
		giveUpButton.setRolloverIcon(new ImageIcon("./Image/�׺���ư2.jpg")); // ��ư ��������
		giveUpButton.setBorderPainted(false); // ��ư �׵θ� ���ֱ�
	
		player1IconLabel.setVisible(false);
		player2IconLabel.setVisible(false);
		player3IconLabel.setVisible(false);
		player4IconLabel.setVisible(false);
		AI1IconLabel.setVisible(false);
		AI2IconLabel.setVisible(false);
		player1IconLabel.setVisible(true);
		player2IconLabel.setVisible(true);
		player3IconLabel.setVisible(true);
		player4IconLabel.setVisible(true);
		AI1IconLabel.setVisible(true);
		AI2IconLabel.setVisible(true);
		
		Player[] players = new Player[PlayerNum]; // �÷��̾� ��ü ����
		Ai[] ais = new Ai[AINum];
		
		for(int i = 0; i< PlayerNum ; i++) {  // �÷��̾� ����ŭ �÷��̾� ��ü ����
			players[i] = new Player(characterName[i]);
			players[i].characterNum = i+1;
		}
		
		JTextField statusOfPlayer1TextField = new JTextField(characterName[0]+" : ���� 0 �� "); 
		JTextField statusOfPlayer2TextField = new JTextField(characterName[1]+" : ���� 0 �� "); 
		JTextField statusOfPlayer3TextField = new JTextField(characterName[2]+" : ���� 0 �� "); 
		JTextField statusOfPlayer4TextField = new JTextField(characterName[3]+" : ���� 0 �� "); 
		JTextField statusOfAI1TextField = new JTextField(" AI1 : ���� 0 �� "); 
		JTextField statusOfAI2TextField = new JTextField(" AI2 : ���� 0 �� "); 
		JTextField gamestatusTextField1 = new JTextField(" ���ӽ���!, p1�� �����Դϴ�. "); 
		JTextField gamestatusTextField2 = new JTextField("�ֻ����� �����ּ���"); 
		
		statusOfPlayer1TextField.setBackground(Color.WHITE);
		statusOfPlayer2TextField.setBackground(Color.WHITE);
		statusOfPlayer3TextField.setBackground(Color.WHITE);
		statusOfPlayer4TextField.setBackground(Color.WHITE);
		statusOfPlayer1TextField.setBounds(840, 50, 100, 30);
		statusOfPlayer2TextField.setBounds(840, 90, 100, 30);
		statusOfPlayer3TextField.setBounds(840, 130, 100, 30);
		statusOfPlayer4TextField.setBounds(840, 170, 100, 30);
		statusOfAI1TextField.setBounds(840, 210, 100, 30);
		statusOfAI2TextField.setBounds(840, 250, 100, 30);
		gamestatusTextField1.setBounds(810, 300, 175, 40);
		gamestatusTextField2.setBounds(810, 350, 175, 40);
		statusOfAI2TextField.setBackground(Color.WHITE);
		statusOfAI1TextField.setBackground(Color.WHITE);
		
		Gamebord.add(player1IconLabel);
		Gamebord.add(player2IconLabel);
		Gamebord.add(player3IconLabel);
		Gamebord.add(player4IconLabel);
		Gamebord.add(AI1IconLabel);
		Gamebord.add(AI2IconLabel);
		Gamebord.add(diceRollingButton);
		Gamebord.add(giveUpButton);
		Gamebord.add(statusOfPlayer1TextField);
		Gamebord.add(statusOfPlayer2TextField);
		Gamebord.add(statusOfPlayer3TextField);
		Gamebord.add(statusOfPlayer4TextField);
		Gamebord.add(statusOfAI1TextField);
		Gamebord.add(statusOfAI2TextField);
		Gamebord.add(diceNumOneLabel);
		Gamebord.add(diceNumTwoLabel);
		Gamebord.add(diceNumThreeLabel);
		Gamebord.add(diceNumFourLabel);
		Gamebord.add(diceNumFiveLabel);
		Gamebord.add(diceNumSixLabel);
		Gamebord.add(gamestatusTextField1);
		Gamebord.add(gamestatusTextField2);
		Gamebord.add(diceImageLabel);
		Gamebord.add(exitButton);
		add(Gamebord);
		
		switch(PlayerNum) {
		case 1: 
			statusOfPlayer2TextField.setVisible(false);statusOfPlayer3TextField.setVisible(false);statusOfPlayer4TextField.setVisible(false);
			player2IconLabel.setVisible(false);player3IconLabel.setVisible(false);player4IconLabel.setVisible(false);
			break;
		case 2: 
			statusOfPlayer3TextField.setVisible(false);statusOfPlayer4TextField.setVisible(false); 
			player3IconLabel.setVisible(false);player4IconLabel.setVisible(false);
			break;
		case 3: statusOfPlayer4TextField.setVisible(false);
			player4IconLabel.setVisible(false);break;
		case 4: break;
		default: break;
		}
		
		switch(AINum) {
		case 0: 
			statusOfAI2TextField.setVisible(false);statusOfAI1TextField.setVisible(false);
			AI1IconLabel.setVisible(false);AI2IconLabel.setVisible(false);
			break;
		case 1: 
			statusOfAI1TextField.setVisible(false);
			ais[0] = new Ai();
			ais[0].firstAICreate();
			ais[0].characterNum = PlayerNum+1;
			AI2IconLabel.setVisible(false);
			break;
		case 2: 
			ais[0] = new Ai();
			ais[1] = new Ai();
			ais[0].firstAICreate();
			ais[1].secondAICreate();
			ais[0].characterNum = PlayerNum+1;
			ais[1].characterNum = PlayerNum+2;
			break;
		default: break;
		}
		giveUpButton.addActionListener(new ActionListener() { // �� ��ư  ���� ����
			public void actionPerformed(ActionEvent e) {		
				switch(turn) {
				case 1: 
					gamestatusTextField1.setText("�׺��� �������ϴ�.");					
					gamestatusTextField2.setText("p1�� �й��Դϴ�.");
					exitButton.setVisible(true);
					saveGameResult(players);
					break;
				case 2: 
					gamestatusTextField1.setText("�׺��� �������ϴ�."); 
					gamestatusTextField2.setText("p2�� �й��Դϴ�."); 
					exitButton.setVisible(true);
					saveGameResult(players);
					break;
				case 3: 
					gamestatusTextField1.setText("�׺��� �������ϴ�."); 
					gamestatusTextField2.setText("p3�� �й��Դϴ�."); 
					exitButton.setVisible(true);
					saveGameResult(players);
					break;
				case 4: 
					gamestatusTextField1.setText("�׺��� �������ϴ�."); 
					gamestatusTextField2.setText("p4�� �й��Դϴ�."); 
					exitButton.setVisible(true);
					saveGameResult(players);
					break;
				default: 
					gamestatusTextField1.setText("�׺��� �������ϴ�."); 
					exitButton.setVisible(true);
					saveGameResult(players);
					break;
				}
			}});
		diceRollingButton.addActionListener(new ActionListener() { // �� ��ư  ���� ����
			public void actionPerformed(ActionEvent e) {		
				int diceNum = dice();
				switch(turn) {
				case 1: 
					players[0].characterReplace = setPlayerLocation(players[0].characterNum, players[0].characterReplace, diceNum);
					gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[players[0].characterReplace].penaltyContent);
					if(players[0].characterReplace == 2 || players[0].characterReplace == 7)
						players[0].characterDrink += diceNum;
					else if(players[0].characterReplace == 3) 
						players[0].characterIsIsland = true;
					else if(players[0].characterReplace == 9) {
						randomNum = (int)(Math.random()*11);
						players[0].characterReplace = randomNum;
						setPlayerLocation(players[0].characterNum, players[0].characterReplace, 0);
					}
					else
						players[0].characterDrink += Slots[players[0].characterReplace].addGlassNum;
					statusOfPlayer1TextField.setText(characterName[0]+" : ���� "+players[0].characterDrink+" �� ");
					
					
					if(PlayerNum > 1) {
						if(players[1].characterIsIsland == true) {
							gamestatusTextField2.setText(players[1].characterName+" ���ε��� ���� ���� ���ϴ�.");
							if(turn == (PlayerNum + AINum)) 
								turn = 1;
							else
								turn += 1;
							players[1].characterIsIsland = false;
						}
						else
							gamestatusTextField2.setText("p2 ����, �ֻ����� �����ּ���");
					}
					else if(PlayerNum == 1 && AINum > 0)
						if(ais[0].characterIsIsland == true) {
							gamestatusTextField2.setText("Ai1"+" ���ε��� ���� ���� ���ϴ�.");
							if(turn == (PlayerNum + AINum)) 
								turn = 1;
							else
								turn += 1;
							ais[0].characterIsIsland = false;
						}
						else
							gamestatusTextField2.setText("ai1 ����, �ֻ����� �����ּ���");
					else {
						if(players[0].characterIsIsland == true) {
							gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
							if(turn == (PlayerNum + AINum)) 
								turn = 1;
							else
								turn += 1;
							players[0].characterIsIsland = false;
						}
						else
							gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
					}
					if(turn == (PlayerNum + AINum)) 
						turn = 1;
					else
						turn += 1;
					if(loseTest(players)) {
						gamestatusTextField1.setVisible(false);
						gamestatusTextField2.setVisible(false);
					}
					break;
				case 2: 
					if(PlayerNum > 1) {
						players[1].characterReplace = setPlayerLocation(players[1].characterNum, players[1].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[players[1].characterReplace].penaltyContent);
						if(players[1].characterReplace == 2 || players[1].characterReplace == 7)
							players[1].characterDrink += diceNum;
						else if(players[1].characterReplace == 3) 
							players[1].characterIsIsland = true;
						else if(players[1].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							players[1].characterReplace = randomNum;
							setPlayerLocation(players[1].characterNum, players[1].characterReplace, 0);
						}
						else
							players[1].characterDrink += Slots[players[1].characterReplace].addGlassNum;
						statusOfPlayer2TextField.setText(characterName[1]+" : ���� "+players[1].characterDrink+" �� ");
						
						if(PlayerNum > 2 )
							if(players[2].characterIsIsland == true) {
								gamestatusTextField2.setText(players[2].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[2].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p3 ����, �ֻ����� �����ּ���");
						else if(PlayerNum == 2 && AINum > 0)
							if(ais[0].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai1"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("ai1 ����, �ֻ����� �����ּ���");
						else if(PlayerNum == 2 && AINum == 0)
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						}
					else {
						ais[0].characterReplace = setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[0].characterReplace].penaltyContent);
						if(ais[0].characterReplace == 2 || ais[0].characterReplace == 7)
							ais[0].characterDrink += diceNum;
						else if(ais[0].characterReplace == 3) 
							ais[0].characterIsIsland = true;
						else if(ais[0].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[0].characterReplace = randomNum;
							setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, 0);
						}
						else
							ais[0].characterDrink += Slots[ais[0].characterReplace].addGlassNum;
						statusOfAI1TextField.setText("Ai1 : ���� "+ais[0].characterDrink+" �� ");
						
						if(AINum == 1) 
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						else {
							if(ais[1].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai2"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[1].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("ai2 ����, �ֻ����� �����ּ���");
						}
					}
					if(turn == (PlayerNum + AINum)) 
						turn = 1;
					else
						turn += 1;
					if(loseTest(players)) {
						gamestatusTextField1.setVisible(false);
						gamestatusTextField2.setVisible(false);
					}
					break;
				case 3:
					if(PlayerNum > 2) {
						players[2].characterReplace = setPlayerLocation(players[2].characterNum, players[2].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[players[2].characterReplace].penaltyContent);
						if(players[2].characterReplace == 2 || players[2].characterReplace == 7)
							players[2].characterDrink += diceNum;
						else if(players[2].characterReplace == 3) 
							players[2].characterIsIsland = true;
						else if(players[2].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							players[2].characterReplace = randomNum;
							setPlayerLocation(players[2].characterNum, players[2].characterReplace, 0);						
							}
						else
							players[2].characterDrink += Slots[players[2].characterReplace].addGlassNum;
						statusOfPlayer3TextField.setText(characterName[2]+" : ���� "+players[2].characterDrink+" �� ");
						
						if(PlayerNum > 3) {
							if(players[3].characterIsIsland == true) {
								gamestatusTextField2.setText(players[3].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[3].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p4 ����, �ֻ����� �����ּ���");
						}
						else if(AINum > 0) {
							if(ais[0].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai1"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("ai1 ����, �ֻ����� �����ּ���");
						}
						else if(AINum == 0) {
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						}
					}
					else if(PlayerNum == 2) {
						ais[0].characterReplace = setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[0].characterReplace].penaltyContent);
						if(ais[0].characterReplace == 2 || ais[0].characterReplace == 7)
							ais[0].characterDrink += diceNum;
						else if(ais[0].characterReplace == 3) 
							ais[0].characterIsIsland = true;
						else if(ais[0].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[0].characterReplace = randomNum;
							setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, 0);
						}
						else
							ais[0].characterDrink += Slots[ais[0].characterReplace].addGlassNum;
						statusOfAI1TextField.setText("Ai1 : ���� "+ais[0].characterDrink+" �� ");
						
						if(AINum == 1) {
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						}
						else {
							if(ais[1].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai2"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[1].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("ai2 ����, �ֻ����� �����ּ���");
						}
					}
					else if(PlayerNum == 1) {
						ais[1].characterReplace = setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[1].characterReplace].penaltyContent);
						if(ais[1].characterReplace == 2 || ais[1].characterReplace == 7)
							ais[1].characterDrink += diceNum;
						else if(ais[1].characterReplace == 3) 
							ais[1].characterIsIsland = true;
						else if(ais[1].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[1].characterReplace = randomNum;
							setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, 0);						}
						else
							ais[1].characterDrink += Slots[ais[1].characterReplace].addGlassNum;
						statusOfAI2TextField.setText("Ai2 : ���� "+ais[1].characterDrink+" �� ");
						if(players[0].characterIsIsland == true) {
							gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
							if(turn == (PlayerNum + AINum)) 
								turn = 1;
							else
								turn += 1;
							players[0].characterIsIsland = false;
						}
						else
							gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
					}
					if(turn == (PlayerNum + AINum)) 
						turn = 1;
					else
						turn += 1;
					if(loseTest(players)) {
						gamestatusTextField1.setVisible(false);
						gamestatusTextField2.setVisible(false);
					}
					break;
				case 4: 
					if(PlayerNum > 3) {
						players[3].characterReplace = setPlayerLocation(players[3].characterNum, players[3].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[players[3].characterReplace].penaltyContent);
						if(players[3].characterReplace == 2 || players[3].characterReplace == 7)
							players[3].characterDrink += diceNum;
						else if(players[3].characterReplace == 3) 
							players[3].characterIsIsland = true;
						else if(players[3].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							players[3].characterReplace = randomNum;
							setPlayerLocation(players[3].characterNum, players[3].characterReplace, 0);
						}
						else
							players[3].characterDrink += Slots[players[3].characterReplace].addGlassNum;
						statusOfPlayer4TextField.setText(characterName[3]+" : ���� "+players[3].characterDrink+" �� ");
						if(AINum > 0) {
							if(ais[0].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai1"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("ai1 ����, �ֻ����� �����ּ���");
						}
						else {
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						}
					}
					else if(PlayerNum == 3) {
						ais[0].characterReplace = setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[0].characterReplace].penaltyContent);
						if(ais[0].characterReplace == 2 || ais[0].characterReplace == 7)
							ais[0].characterDrink += diceNum;
						else if(ais[0].characterReplace == 3) 
							ais[0].characterIsIsland = true;
						else if(ais[0].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[0].characterReplace = randomNum;
							setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, 0);
						}
						else
							ais[0].characterDrink += Slots[ais[0].characterReplace].addGlassNum;
						statusOfAI1TextField.setText("Ai1 : ���� "+ais[0].characterDrink+" �� ");
						if(AINum == 1) {
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						}
						else {
							if(ais[1].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai2"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[1].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("ai2 ����, �ֻ����� �����ּ���");
						}
					}
					else if(PlayerNum == 2) {
						ais[1].characterReplace = setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[1].characterReplace].penaltyContent);
						if(ais[1].characterReplace == 2 || ais[1].characterReplace == 7)
							ais[1].characterDrink += diceNum;
						else if(ais[1].characterReplace == 3) 
							ais[1].characterIsIsland = true;
						else if(ais[1].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[1].characterReplace = randomNum;
							setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, 0);
						}
						else
							ais[1].characterDrink += Slots[ais[1].characterReplace].addGlassNum;
						statusOfAI2TextField.setText("Ai2 : ���� "+ais[1].characterDrink+" �� ");
						if(players[0].characterIsIsland == true) {
							gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
							if(turn == (PlayerNum + AINum)) 
								turn = 1;
							else
								turn += 1;
							players[0].characterIsIsland = false;
						}
						else
							gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
					}
					if(turn == (PlayerNum + AINum)) 
						turn = 1;
					else
						turn += 1;
					if(loseTest(players)) {
						gamestatusTextField1.setVisible(false);
						gamestatusTextField2.setVisible(false);
					}
					break;
				case 5: 
					if(PlayerNum == 4) {
						ais[0].characterReplace = setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[0].characterReplace].penaltyContent);
						if(ais[0].characterReplace == 2 || ais[0].characterReplace == 7)
							ais[0].characterDrink += diceNum;
						else if(ais[0].characterReplace == 3) 
							ais[0].characterIsIsland = true;
						else if(ais[0].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[0].characterReplace = randomNum;
							setPlayerLocation(ais[0].characterNum, ais[0].characterReplace, 0);
						}
						else
							ais[0].characterDrink += Slots[ais[0].characterReplace].addGlassNum;
						statusOfAI1TextField.setText("Ai1 : ���� "+ais[0].characterDrink+" �� ");
						if(AINum == 2) {
							if(ais[1].characterIsIsland == true) {
								gamestatusTextField2.setText("Ai2"+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								ais[1].characterIsIsland = false;
							}
							else	
								gamestatusTextField2.setText("ai2 ����, �ֻ����� �����ּ���");
						}
						else {
							if(players[0].characterIsIsland == true) {
								gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
								if(turn == (PlayerNum + AINum)) 
									turn = 1;
								else
									turn += 1;
								players[0].characterIsIsland = false;
							}
							else
								gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
						}
					}
					else if(PlayerNum == 3) {
						ais[1].characterReplace = setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, diceNum);
						gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[1].characterReplace].penaltyContent);
						if(ais[1].characterReplace == 2 || ais[1].characterReplace == 7)
							ais[1].characterDrink += diceNum;
						else if(ais[1].characterReplace == 3) 
							ais[1].characterIsIsland = true;
						else if(ais[1].characterReplace == 9) {
							randomNum = (int)(Math.random()*11);
							ais[1].characterReplace = randomNum;
							setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, 0);
						}
						else
							ais[1].characterDrink += Slots[ais[1].characterReplace].addGlassNum;
						statusOfAI2TextField.setText("Ai2 : ���� "+ais[1].characterDrink+" �� ");
						if(players[0].characterIsIsland == true) {
							gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
							if(turn == (PlayerNum + AINum)) 
								turn = 1;
							else
								turn += 1;
							players[0].characterIsIsland = false;
						}
						else
							gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
					}
					if(turn == (PlayerNum + AINum)) 
						turn = 1;
					else
						turn += 1;
					break;
				case 6:
					ais[1].characterReplace = setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, diceNum);
					gamestatusTextField1.setText(Integer.toString(diceNum)+"! "+Slots[ais[1].characterReplace].penaltyContent);
					if(ais[1].characterReplace == 2 || ais[1].characterReplace == 7)
						ais[1].characterDrink += diceNum;
					else if(ais[1].characterReplace == 3) 
						ais[1].characterIsIsland = true;
					else if(ais[1].characterReplace == 9) {
						randomNum = (int)(Math.random()*11);
						ais[1].characterReplace = randomNum;
						setPlayerLocation(ais[1].characterNum, ais[1].characterReplace, 0);
					}
					else
						ais[1].characterDrink += Slots[ais[1].characterReplace].addGlassNum;
					statusOfAI2TextField.setText("Ai2 : ���� "+ais[1].characterDrink+" �� ");
					if(players[0].characterIsIsland == true) {
						gamestatusTextField2.setText(players[0].characterName+" ���ε��� ���� ���� ���ϴ�.");
						if(turn == (PlayerNum + AINum)) 
							turn = 1;
						else
							turn += 1;
						players[0].characterIsIsland = false;
					}
					else
						gamestatusTextField2.setText("p1 ����, �ֻ����� �����ּ���");
					if(turn == (PlayerNum + AINum)) 
						turn = 1;
					else
						turn += 1;
					break;
				default: break;
				}
				
			}
		});
		exitButton.addActionListener(new ActionListener() { // �� ��ư  ���� ����
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});
	}	

	public int dice() {
		diceNumOneLabel.setVisible(false);
		diceNumTwoLabel.setVisible(false);
		diceNumThreeLabel.setVisible(false);
		diceNumFourLabel.setVisible(false);
		diceNumFiveLabel.setVisible(false);
		diceNumSixLabel.setVisible(false);
		diceImageLabel.setVisible(false);
		
	
		int random = (int)(Math.random()*5+1);
		switch(random) {
		case 1: diceNumOneLabel.setVisible(true); break;
		case 2: diceNumTwoLabel.setVisible(true); break;
		case 3: diceNumThreeLabel.setVisible(true); break;
		case 4: diceNumFourLabel.setVisible(true); break;
		case 5: diceNumFiveLabel.setVisible(true); break;
		case 6: diceNumSixLabel.setVisible(true); break;
		default : break;
		}
		return random;
	}

	int setPlayerLocation(int PlayerNum,int characterReplace,int diceNum) {
		int characterNaxtReplace = characterReplace+diceNum;
		if(characterNaxtReplace>11)
			characterNaxtReplace -= 12;
		switch(PlayerNum) {
		case 1:
			switch(characterNaxtReplace) {
			case 0: player1IconLabel.setBounds(65, 175, 20, 20); Gamebord.add(player1IconLabel); 
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true);break;
			case 1: player1IconLabel.setBounds(255, 175, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 2: player1IconLabel.setBounds(445, 175, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true);  break;
			case 3: player1IconLabel.setBounds(635, 175, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 4: player1IconLabel.setBounds(635, 340, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 5: player1IconLabel.setBounds(635, 505, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 6: player1IconLabel.setBounds(635, 670, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 7: player1IconLabel.setBounds(445, 670, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 8: player1IconLabel.setBounds(255, 670, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 9: player1IconLabel.setBounds(65, 670, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 10: player1IconLabel.setBounds(65, 505, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			case 11: player1IconLabel.setBounds(65, 340, 20, 20); Gamebord.add(player1IconLabel );
			player1IconLabel.setVisible(false);player1IconLabel.setVisible(true); break;
			default : break;
			}
			break;
		case 2: 
			switch(characterNaxtReplace) {
			case 0: player2IconLabel.setBounds(90, 175, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 1: player2IconLabel.setBounds(280, 175, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 2: player2IconLabel.setBounds(470, 175, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true);  break;
			case 3: player2IconLabel.setBounds(660, 175, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 4: player2IconLabel.setBounds(660, 340, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 5: player2IconLabel.setBounds(660, 505, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 6: player2IconLabel.setBounds(660, 670, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 7: player2IconLabel.setBounds(470, 670, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 8: player2IconLabel.setBounds(280, 670, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 9: player2IconLabel.setBounds(90, 670, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			case 10: player2IconLabel.setBounds(90, 505, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true);break;
			case 11: player2IconLabel.setBounds(90, 340, 20, 20);Gamebord.add(player2IconLabel);
			player2IconLabel.setVisible(false);player2IconLabel.setVisible(true); break;
			default : break;
			}
			break;
		case 3:
			switch(characterNaxtReplace) {
			case 0: player3IconLabel.setBounds(115, 175, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 1: player3IconLabel.setBounds(305, 175, 20, 20);Gamebord.add(player3IconLabel); 
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true);break;
			case 2: player3IconLabel.setBounds(495, 175, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true);  break;
			case 3: player3IconLabel.setBounds(685, 175, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 4: player3IconLabel.setBounds(685, 340, 20, 20);Gamebord.add(player3IconLabel); 
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true);break;
			case 5: player3IconLabel.setBounds(685, 505, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 6: player3IconLabel.setBounds(685, 670, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 7: player3IconLabel.setBounds(495, 670, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 8: player3IconLabel.setBounds(305, 670, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 9: player3IconLabel.setBounds(115, 670, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 10: player3IconLabel.setBounds(115, 505, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			case 11: player3IconLabel.setBounds(115, 340, 20, 20);Gamebord.add(player3IconLabel);
			player3IconLabel.setVisible(false);player3IconLabel.setVisible(true); break;
			default : break;
			}
			break;
		case 4: 
			switch(characterNaxtReplace) {
			case 0: player4IconLabel.setBounds(140, 175, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 1: player4IconLabel.setBounds(330, 175, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 2: player4IconLabel.setBounds(520, 175, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 3: player4IconLabel.setBounds(710, 175, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 4: player4IconLabel.setBounds(710, 340, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 5: player4IconLabel.setBounds(710, 505, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 6: player4IconLabel.setBounds(710, 670, 20, 20);Gamebord.add(player4IconLabel);  
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true);break;
			case 7: player4IconLabel.setBounds(520, 670, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 8: player4IconLabel.setBounds(330, 670, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 9: player4IconLabel.setBounds(140, 670, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 10: player4IconLabel.setBounds(140, 505, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			case 11: player4IconLabel.setBounds(140, 340, 20, 20);Gamebord.add(player4IconLabel); 
			player4IconLabel.setVisible(false);player4IconLabel.setVisible(true); break;
			default : break;
			}
			break;
		case 5: 
			switch(characterNaxtReplace) {
			case 0: AI1IconLabel.setBounds(165, 175, 20, 20);Gamebord.add(AI1IconLabel); 
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true); break;
			case 1: AI1IconLabel.setBounds(355, 175, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 2: AI1IconLabel.setBounds(545, 175, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 3: AI1IconLabel.setBounds(735, 175, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 4: AI1IconLabel.setBounds(735, 340, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 5: AI1IconLabel.setBounds(735, 505, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 6: AI1IconLabel.setBounds(735, 670, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 7: AI1IconLabel.setBounds(545, 670, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 8: AI1IconLabel.setBounds(355, 670, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 9: AI1IconLabel.setBounds(165, 670, 20, 20);Gamebord.add(AI1IconLabel);
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true);  break;
			case 10: AI1IconLabel.setBounds(165, 505, 20, 20);Gamebord.add(AI1IconLabel); 
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true); break;
			case 11: AI1IconLabel.setBounds(165, 340, 20, 20);Gamebord.add(AI1IconLabel); 
			AI1IconLabel.setVisible(false);AI1IconLabel.setVisible(true); ;break;
			default : break;
			}
			break;
		case 6:
			switch(characterNaxtReplace) {
			case 0: AI2IconLabel.setBounds(190, 175, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true);   break;
			case 1: AI2IconLabel.setBounds(380, 175, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 2: AI2IconLabel.setBounds(570, 175, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 3: AI2IconLabel.setBounds(760, 175, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 4: AI2IconLabel.setBounds(760, 340, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 5: AI2IconLabel.setBounds(760, 505, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 6: AI2IconLabel.setBounds(760, 670, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 7: AI2IconLabel.setBounds(570, 670, 20, 20);Gamebord.add(AI2IconLabel); 
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true);break;
			case 8: AI2IconLabel.setBounds(380, 670, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 9: AI2IconLabel.setBounds(190, 670, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 10: AI2IconLabel.setBounds(190, 505, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			case 11: AI2IconLabel.setBounds(190, 340, 20, 20);Gamebord.add(AI2IconLabel);
			AI2IconLabel.setVisible(false);AI2IconLabel.setVisible(true); break;
			default : break;
			}
			break;
		default: break;
		}
		return characterNaxtReplace;
		
	}
	public Boolean loseTest(Player[] players) {
		Boolean flag = false;
		for(int i = 0; i< PlayerNum ; i++) {
			if(players[i].characterDrink > characterMaxGlass[i]) {
				JTextField gamestatusTextField = new JTextField(players[i].characterName+"�� �й��Դϴ�");
				gamestatusTextField.setBounds(810, 350, 175, 40);
				Gamebord.add(gamestatusTextField);
				diceRollingButton.setVisible(false);
				exitButton.setVisible(true);
				saveGameResult(players);
				flag = true;
			}
		}
		return flag;
	}
	public static void saveGameResult(Player[] players) {
		try {
			out = new FileWriter("score.txt", true);
			for(int i=0;i<PlayerNum;i++) {
				out.write(players[i].characterName);
				out.write(":");
				out.write(Integer.toString(players[i].characterDrink));
				out.write(" ");
			}
            out.close();	
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
}

public class _1 {
	
	public static void main(String[] args) {
		new MainScreen();
	}
		
}
	


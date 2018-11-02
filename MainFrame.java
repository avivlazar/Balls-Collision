import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;


public class MainFrame extends JFrame{
	
	public static final Font EXTERNAL_FONT = new Font("Arial", Font.BOLD, 15);
	public static final Font EXTERNAL_FONT_2 = new Font("Arial", Font.BOLD, 20);
	
	/*public static final JButton[] DOUBLE_MASS_ARRAY = {new JButton("1"), new JButton("2"), new JButton("4"),
		new JButton("10"), new JButton("25"), new JButton("50"), new JButton("100"), new JButton("200"),
		new JButton("500"), new JButton("1000")}; */
	private JLabel smallBallLabel = new JLabel("Small: (" + RButtonPanel.All_Colors_Names[0] + ")", JLabel.CENTER);
	private JLabel mediumBallLabel = new JLabel("Medium: (" + RButtonPanel.All_Colors_Names[1] + ")", JLabel.CENTER);
	private JLabel bigBallLabel = new JLabel("Big: (" + RButtonPanel.All_Colors_Names[2] + ")", JLabel.CENTER);
	private JLabel veryBigBallLabel = new JLabel("Very Big: (" + RButtonPanel.All_Colors_Names[3] + ")");
	private JLabel myFrictionLabel = new JLabel("My Friction:  ", JLabel.CENTER);
	private JLabel percentWastedEnergyLabel = new JLabel("Wasted Energy:", JLabel.CENTER);
	private JTextField smallBallMassText;
	private JTextField mediumBallMassText;
	private JTextField bigBallMassText;
	private JTextField veryBigBallMassText;
	private JTextField myFrictionTextField;
	private JTextField percentWastedEnergyTextField;
	private JButton OK = new JButton("OK");  
	private JRadioButton elasticButton = new JRadioButton("Elastic");
	private JRadioButton nonElasticButton = new JRadioButton("Non-Elastic");
	private JRadioButton plasticButton = new JRadioButton("Palstic");
	
	public static final Dimension MAIN_FRAME_DIMENTION = new Dimension(700, 500);           //Control Frame dimension
	private JPanel mainPanel = new JPanel();
	private JPanel massesPanel = new JPanel();
	private JPanel frictionPanel = new JPanel();
	private JPanel collisionTypePanel = new JPanel();
	private JPanel percentOfWastedEnergyPanel = new JPanel();
	private JPanel OK_Panel = new JPanel();
	private BallControl ballControl;
	
	/**Constructor**/
	public MainFrame(BallControl ballControl) {
		setTitle("My Main Frame");
		setSize(MAIN_FRAME_DIMENTION);
		setLayout(new FlowLayout());
		this.ballControl = ballControl;
		
		
		//***Masses Panel Setting**//
		smallBallLabel.setFont(EXTERNAL_FONT);
		mediumBallLabel.setFont(EXTERNAL_FONT);
		bigBallLabel.setFont(EXTERNAL_FONT);
		veryBigBallLabel.setFont(EXTERNAL_FONT);
		smallBallMassText = new JTextField (ballControl.getRBPanel().getMassByIndex(0) + "", JTextField.CENTER);
		mediumBallMassText = new JTextField (ballControl.getRBPanel().getMassByIndex(1) + "", JTextField.CENTER);
		bigBallMassText = new JTextField (ballControl.getRBPanel().getMassByIndex(2) + "", JTextField.CENTER);
		veryBigBallMassText = new JTextField (ballControl.getRBPanel().getMassByIndex(3) + "", JTextField.CENTER);
		
		massesPanel.setLayout(new GridLayout(2, 4, 20, 10));
		massesPanel.setBorder(new javax.swing.border.TitledBorder("Masses of Balls"));
		massesPanel.add(smallBallLabel);
		massesPanel.add(mediumBallLabel);
		massesPanel.add(bigBallLabel);
		massesPanel.add(veryBigBallLabel);
		massesPanel.add(smallBallMassText);
		massesPanel.add(mediumBallMassText);
		massesPanel.add(bigBallMassText);
		massesPanel.add(veryBigBallMassText);
		
		//***FrictionPanel Panel Setting**//
		myFrictionTextField = new JTextField(ballControl.getBallPanel().getFriction() + "");
		myFrictionLabel.setFont(new Font("Arial", Font.BOLD, 25));
		myFrictionTextField.setFont(new Font("Arial", Font.BOLD, 25));
		frictionPanel.setLayout(new GridLayout(1, 3, 10, 5));
		frictionPanel.setBorder(new javax.swing.border.TitledBorder("Friction"));
		frictionPanel.add(myFrictionLabel);
		frictionPanel.add(myFrictionTextField);
		frictionPanel.add(new JLabel("(must be positive number)", JLabel.CENTER));
		
		//***Collision Type Panel Panel Setting**//
		collisionTypePanel.setLayout(new GridLayout(1, 3, 10, 5));
		GridBagConstraints gridBag = new GridBagConstraints();
		ButtonGroup groupOfButtons = new ButtonGroup();
		collisionTypePanel.setBorder(new javax.swing.border.TitledBorder("Collision Type"));
		elasticButton.setFont(EXTERNAL_FONT_2);
		nonElasticButton.setFont(EXTERNAL_FONT_2);
		plasticButton.setFont(EXTERNAL_FONT_2);
		elasticButton.setMnemonic('E');
		nonElasticButton.setMnemonic('N');
		plasticButton.setMnemonic('P');
	//	collisionTypePanel.add(new JLabel());
		collisionTypePanel.add(elasticButton, gridBag);
		collisionTypePanel.add(nonElasticButton, gridBag);
		collisionTypePanel.add(plasticButton, gridBag);
		groupOfButtons.add(elasticButton);
		groupOfButtons.add(nonElasticButton);
		groupOfButtons.add(plasticButton);
		
		//***Percent of Wasted Energy Panel Setting**//
		percentOfWastedEnergyPanel.setLayout(new GridLayout(1, 3, 30, 5));
		percentOfWastedEnergyPanel.setBorder(new javax.swing.border.TitledBorder("*Percent of Wasted Energy"));
		percentWastedEnergyLabel.setFont(EXTERNAL_FONT_2);
		percentOfWastedEnergyPanel.add(percentWastedEnergyLabel);
		percentWastedEnergyTextField = new JTextField(ballControl.getBallPanel().getPercentWstedEnergy() + "");
		percentWastedEnergyTextField.setFont(EXTERNAL_FONT_2);
		percentOfWastedEnergyPanel.add(percentWastedEnergyTextField);
		percentOfWastedEnergyPanel.add(new JLabel("for non and plastic collision"));
		
		
		//***OK Panel Setting**//
		OK_Panel.add(OK);
		
		mainPanel.setLayout(new GridLayout(5, 1, 20, 20));
		mainPanel.add(massesPanel);
		mainPanel.add(frictionPanel);
		mainPanel.add(collisionTypePanel);
		mainPanel.add(percentOfWastedEnergyPanel);
		mainPanel.add(OK_Panel);
		
		
		add(mainPanel);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(ballControl);
		setAlwaysOnTop(true);
		setVisible(true);
	}

}

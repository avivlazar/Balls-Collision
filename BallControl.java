

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.awt.*;

public class BallControl extends JPanel {
	
	private BallControl ballControl = this;
	private BallPanel ballPanel = new BallPanel(this);
	private JPanel energySpeedPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton jbtSuspend = new JButton("Suspend");
	private JButton jbtResume = new JButton("Resume");
	private JButton jbtAdding = new JButton("Add");
	private JButton jbtRemove = new JButton("Remove");
	private JButton jbtMain = new JButton("Main");
	private JTextField speedTextNumber = new JTextField(ballPanel.getSpeedMessage());
	private JTextField avgSpeedTextNumber = new JTextField(ballPanel.getAvgSpeedMessage());
	private JTextField energyTextNumber = new JTextField(ballPanel.getEnergyMessage());
	private JTextField avgEnergyTextNumber = new JTextField(ballPanel.getAvgEnergyMessage());
	
	private JTextField KmSpeedTextNumber = new JTextField(ballPanel.getKmSpeedMessage());
	private JTextField avgKmSpeedTextNumber = new JTextField(ballPanel.getAvgKmSpeedMessage());
	
	private RButtonPanel rbp = new RButtonPanel();
	//private boolean isElasticCollision;
	
	private boolean poopo = false; 

	public BallControl() {
		buttonPanel.add(jbtSuspend);   // Group buttons in buttonPanel
		buttonPanel.add(jbtResume);
		buttonPanel.add(jbtAdding);
		buttonPanel.add(jbtRemove);
		buttonPanel.add(jbtMain);
		
		energySpeedPanel.setLayout(new GridLayout(2, 6, 10, 5));
		energySpeedPanel.add(new JLabel("Total Energy:"));
		energySpeedPanel.add(energyTextNumber);
		energySpeedPanel.add(new JLabel(""));
		
		
		energySpeedPanel.add(new JLabel("Total Speed:"));
		energySpeedPanel.add(speedTextNumber);
		energySpeedPanel.add(KmSpeedTextNumber);
		
		
		energySpeedPanel.add(new JLabel("Average Energy: "));
		energySpeedPanel.add(avgEnergyTextNumber);
		energySpeedPanel.add(new JLabel(""));
		
		
		energySpeedPanel.add(new JLabel("Average Speed"));
		energySpeedPanel.add(avgSpeedTextNumber);
		energySpeedPanel.add(avgKmSpeedTextNumber);
		
		
		// Add ball and buttons to the panel
		ballPanel.setBorder(new javax.swing.border.LineBorder(Color.red));
		setLayout(new BorderLayout());
		add(energySpeedPanel, BorderLayout.NORTH);
		add(ballPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		add(rbp, BorderLayout.WEST);
		
		
		// Register listeners
		jbtSuspend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!poopo){
					ballPanel.suspend();
					poopo = true;
				}
			}
		});
		jbtResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(poopo){
					ballPanel.resume();
					poopo = false;
				}
				
			}
		});
		jbtAdding.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ballPanel.ballAdding();
				energyTextNumber.setText(ballPanel.getEnergyMessage());
				speedTextNumber.setText(ballPanel.getSpeedMessage());
			}
		});
		jbtRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballPanel.ballRemoving();
				energyTextNumber.setText(ballPanel.getEnergyMessage());
				speedTextNumber.setText(ballPanel.getSpeedMessage());
			}
		});
		jbtMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(ballPanel.getNumOdBalls() <= 0){
					MainFrame Main = new MainFrame(ballControl);
				}
				else{
					final JFrame jf = new JFrame();
					JPanel p = new JPanel();
					JLabel lab = new JLabel("Remove All Balls Before Main");
					JButton jbt = new JButton("Ok");
					jf.setTitle("Introduction");
					jf.setSize(250, 80);
					p.setLayout(new FlowLayout());
					p.add(lab);
					p.add(jbt);
					jf.add(p);
					jbt.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							jf.dispose();
						}
					});
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jf.setLocationRelativeTo(ballControl);
					jf.setAlwaysOnTop(true);
					jf.setVisible(true);
				}
			}
		});
	}

	
	//Get methods
	public BallPanel getBallPanel() {return ballPanel;}
	public RButtonPanel getRBPanel() {return rbp;}
	public JTextField getSpeedTextNumber() {return speedTextNumber;}
	public JTextField getAvgSpeedTextNumber() {return avgSpeedTextNumber;}
	public JTextField getEnergyTextNumber() {return energyTextNumber;}
	public JTextField getAvgEnergyTextNumber() {return avgEnergyTextNumber;}
	public JTextField getKmSpeedTextNumber() {return KmSpeedTextNumber;}
	public JTextField getAvgKmSpeedTextNumber() {return avgKmSpeedTextNumber;}
	 //Set methods
	public void setSpeedText(String Text){speedTextNumber.setText(Text);}
    public void setAvgSpeedText(String Text){avgSpeedTextNumber.setText(Text);}
	public void setEnergyText(String Text) {energyTextNumber.setText(Text);}
	public void setAvgEnergyText(String Text){avgEnergyTextNumber.setText(Text);}
	public void setKmSpeedText(String Text){KmSpeedTextNumber.setText(Text);}
    public void setAvgKmSpeedText(String Text){avgKmSpeedTextNumber.setText(Text);}


	


	
	
}

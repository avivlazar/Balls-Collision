import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class RButtonPanel extends JPanel{
	
	public static final Color[] All_Colors= {Color.RED, Color.GREEN, Color.BLACK, Color.BLUE};
	public static final String[] All_Colors_Names = {"Red", "Green", "Black", "Blue)"};
	private double[] All_Masses = {1, 2, 3, 4};  // in kilo-gram
	private static final int[] ARRAY_OF_RADIUS = {15, 20, 25, 30};
	
	private int selectIndex;  //Helper number
	
	private JRadioButton[] buttonsArray;
	
	public RButtonPanel() {
		setLayout(new GridLayout(All_Colors.length, 1));
		//setLayout(new GridBagLayout());
		GridBagConstraints gridBag = new GridBagConstraints();
		ButtonGroup groupOfButtons = new ButtonGroup();
		buttonsArray = new JRadioButton[All_Colors.length];
		
		for(int i = 0; i < All_Colors.length; i++){
			buttonsArray[i] = new JRadioButton(All_Colors_Names[i] + "  (" + All_Masses[i] + " kg)");
			add(buttonsArray[i], gridBag);
			groupOfButtons.add(buttonsArray[i]);
		}
		
		buttonsArray[0].setSelected(true);
		
		buttonsArray[0].setMnemonic('R');
		buttonsArray[1].setMnemonic('G');
		buttonsArray[2].setMnemonic('B');
		buttonsArray[3].setMnemonic('l');
		
		setBorder(BorderFactory.createTitledBorder("Balls Type"));
		setOpaque(true);
		setVisible(true);
	}

	public double getMassByIndex(int i) {
		try{
			return All_Masses[i];
		}
		catch(IndexOutOfBoundsException e){
			return 0;
		}
		
	}
	
	//Get Methods
	public int getSelectedRadius() {return ARRAY_OF_RADIUS[getSelectedIndex()];}
	public Color getSelectedColor() {return All_Colors[getSelectedIndex()];}
	public double getSelectedMass() {return All_Masses[getSelectedIndex()];}
	public int getSelectedIndex(){
		for(int j = 0; j < All_Colors.length; j++){
			if(buttonsArray[j].isSelected()){
				return j;
			}
		}
		return 1;  //default
	}


	

	


    

}

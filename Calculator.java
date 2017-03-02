import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Calculator extends JFrame 
{
    //================================================================ constants
    private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);   
    
    //... Component referenced during execution
    private JTextField displayField;       // display result / input.
    //... Variables representing state of the calculator
    private boolean   startNumber = true;      // true: num key next
    private String    previousOp  = "=";       // previous operation
    private CalcLogic logic = new CalcLogic(); // The internal calculator.
    
    //============================================================== method main
   
    public static void main(String[] args)
    {
        //... Set the Look and Feel to that of system we're running on.
    	try 
    	{
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} 
       catch   (Exception unused)
	        {
	            ; // Ignore exception because we can't do anything.  Will use default.
	        }
	        
        //... Create the window.
        Calculator window = new Calculator();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    //============================================================== constructor
    public Calculator()
    {
        //... Set attributes of the display field
        displayField = new JTextField("0", 12);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(BIGGER_FONT);
        
        //... Create and set attributes of clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(BIGGER_FONT);
        clearButton.addActionListener(new ClearListener());
        
        //... Use one listener for all numeric keys.
        ActionListener numListener = new NumListener();
        
        //... Layout numeric keys in a grid.  Generate the buttons
        //    in a loop from the chars in a string.
        String buttonOrder = "789456123 0 ";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 3, 2, 2));
        for (int i = 0; i < buttonOrder.length(); i++)
        	{
	            String keyTop = buttonOrder.substring(i, i+1);
	            JButton b = new JButton(keyTop);
	            if (keyTop.equals(" ")) 
		            {
		                //... Put a dummy button in this position.
		                b.setEnabled(false);
		            }
		        else 
			        {
		                //... Put a digit button in the interface.
		                b.addActionListener(numListener);
		                b.setFont(BIGGER_FONT);
		            }
	            	buttonPanel.add(b);
        }
        
        //... One ActionListener to use for all operator buttons.
        ActionListener opListener = new OpListener();
        
        //... Create panel with grid layout to hold operator buttons.
        //    Use array of button names to create buttons in a loop.
        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(5, 1, 2, 2));
        String[] opOrder = {"+", "-", "*", "/", "="};
        for (int i = 0; i < opOrder.length; i++)
        {
            JButton b = new JButton(opOrder[i]);
            b.addActionListener(opListener);
            b.setFont(BIGGER_FONT);
            opPanel.add(b);
        }
        
        //Put Clear button in flow layout to keep from expanding.
        JPanel clearPanel = new JPanel();
        clearPanel.setLayout(new FlowLayout());
        clearPanel.add(clearButton);
        
        // Layout the top-level content panel.
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(5, 5));
        content.add(displayField, BorderLayout.NORTH );
        content.add(buttonPanel   , BorderLayout.CENTER);
        content.add(opPanel       , BorderLayout.EAST  );
        content.add(clearPanel    , BorderLayout.SOUTH );
        
        content.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        //... Finish building the window (JFrame)
        this.setContentPane(content);
        this.pack();
        this.setTitle("C00202493 Calculator");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }//end constructor
    
    
    //=== actionClear
    /*		 Called by Clear button action listener and elsewhere							*/
    private void actionClear() 
    {
        startNumber = true;         // Expecting number, not op.
        displayField.setText("0");
        previousOp  = "=";
        logic.setTotal("0");
    }
    
    //////////////////////////////////////////// inner listener class OpListener
    /** Listener for all op buttons. */
    class OpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            // The calculator is always in one of two states.
            // 1. A number must be entered -- an operator is wrong.
            // 2. An operator must be entered.
        	// if you enter an operator first the system will out put that there is and error
        	// 
            if (startNumber)
            { // Error: needed number, not operator
                //... In this state we're expecting a number, but got an operator.
                actionClear();
                displayField.setText("ERROR - No operator");
            }
            else
            {
                //... We're expecting an operator.
                startNumber = true;  // Next thing must be a number
                try
	                {
	                    // Get value from display field, convert, do prev op
	                    // If this is the first op, previousOp will be =.
	                    String displayText = displayField.getText();
	                    
	                    if (previousOp.equals("="))
		                    {
		                        logic.setTotal(displayText);
		                    } 
	                    else if (previousOp.equals("+"))
		                    {
		                        logic.add(displayText);
		                    } 
	                    else if (previousOp.equals("-")) 
		                    {
		                        logic.subtract(displayText);
		                    }
	                    else if (previousOp.equals("*")) 
		                    {
		                        logic.multiply(displayText);
		                    } 
	                    else if (previousOp.equals("/")) 
		                    {
		                        logic.divide(displayText);
		                    }
	                    else if (previousOp.equals("^")) 
	                    	{
	                    		logic.square(displayText);
	                    	}
	                    
	                    displayField.setText("" + logic.getTotalString());
	                    
	                } 
	                catch (NumberFormatException ex)
		                {
		                    actionClear();
		                    displayField.setText("Error");
		                }
	                
	                //... set previousOp for the next operator.
	                previousOp = e.getActionCommand();
	            }//endif startNumber
	        }//endmethod
	    }//end class
	    
	    
	    // inner listener class ClearListener
	    /** Action listener for numeric keys */
	    class NumListener implements ActionListener 
		    {
		        public void actionPerformed(ActionEvent e) 
		        {
		            String digit = e.getActionCommand(); // Get text from button
		            if (startNumber) 
			            {
			                //... This is the first digit, clear field and set
			                displayField.setText(digit);
			                startNumber = false;
			            } 
		            else
			            {
			                //... Add this digit to the end of the display field
			                displayField.setText(displayField.getText() + digit);
			            }
		        }
		    }
	    
    //// inner listener class ClearListener
    class ClearListener implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e) 
		        {
		            actionClear();
		        }
	    }
}

 


	import acm.gui.*;
import acm.program.*;
import java.awt.event.*;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
	public class Main  extends Program{

		String s="";						//a string that holds the digits in a single token
		String result="";					// a string holding the expression to be printed in text field 1
		Queue infix= new Queue();			//creating 2 queues and stack objects
		Queue pofx= new Queue();			
		Stack buffer= new Stack();
		JTextField field2=new JTextField();		//defining fields 1, 2, decValue and the JSlider globally to edit them in different methods in the code
		JTextField field1=new JTextField("",JTextField.CENTER);
		JTextField decValue= new JTextField("6");
		JSlider precision = new JSlider(JSlider.HORIZONTAL, 1, 11, 6);
		int decimal=6;		//setting the default for decimal places to be 6
		
			
			
			public void algr()
			{
			
			while(infix.index()>0)
			{
				String ch= infix.dequeue(); 	//dequeueing from infix into ch string 
				if(ch.equals("+") || ch.equals("-") || ch.equals("x") || ch.equals("/"))	//if ch is an operator
					{
					
					while(pr(buffer.peak())>=pr(ch))	//popping the stack while the top element has higher precedence
						{
						pofx.enqueue(buffer.pop());		//enqueueing the popped string into pofx queue
						}
					buffer.push(ch);
					}
				else if(ch.equals(")"))
				{
					
					while(!"(".equals(buffer.peak()))
					{
					
						pofx.enqueue(buffer.pop());
					}
					buffer.pop();
				}
				else if(ch.equals("("))
				{
					
					buffer.push(ch);
					
				}
				
				else {		//if ch is an operand, directly enqueue it to pofx
					
					pofx.enqueue(ch);
				}
				
			}
			while(buffer.peak()!="#")		//popping any elements left in the stack other than the # character and enqueueing them to pofx
			{
				pofx.enqueue(buffer.pop());
			}
			
			while(pofx.index()>0)		//dequeueing pofx until it is empty
			{
				String x= pofx.dequeue();
				infix.enqueue(x);
			}
			
			double answer;
			while(infix.index()>0)
			{
				String ch= infix.dequeue();		//dequeueing token into ch
				if(ch.equals("+") || ch.equals("-") || ch.equals("x") || ch.equals("/"))	//if operator
				{

					double op2=Double.parseDouble(buffer.pop());		//popping out the top 2 operands in stack
					double op1= Double.parseDouble(buffer.pop());
					if(ch.equals("+"))	{answer= op1+op2;}				//performing operation on them and the result stored in answer
					else if(ch.equals("-"))	{answer=op1-op2;}
					else if(ch.equals("x"))	{answer=op1*op2;}
					else	{answer=op1/op2;}
					
					buffer.push(String.valueOf(answer));			//answer pushed back to stack
				}
				else if(ch.equals(""))	{continue;}			//ignoring blank empty spaces tokens in between
				else
				{
					buffer.push(ch);						//push into stack if operand
				}
			}
			
			String x=buffer.pop();						//pop out remaining result in stack into string x after the loop
			double ch=Double.parseDouble(x);			//converting to double
			String formatted=String.format("%1$."+decimal+"f", ch);		//setting number of decimal places according to decimal value
			field2.setText(formatted);		//putting the answer into field 2

		}
			
		public static int pr(String ch)			//method returning values according to the precedence of the operators
		{
			if(ch.equals("+") || ch.equals("-")) return 2;
			else if (ch.equals("#")) return 0;
			else if (ch.equals("(")) return 1;
			else if (ch.equals("x") || ch.equals("/")) return 3;
			else return -1;
		}
		
		
		
		
		
		public void init() {					//the main method containg all the gui elements
buffer.push("#");	//pushing a non-null character to the stack
			
			String s="";
		      setLayout(new TableLayout(9, 4));		//creating a table of 9x4 entries
      
		      	add(field1, "gridwidth=4");			//field 1 and 2 spanning four columns are added
				add(field2, "gridwidth=4");
		      	add(new JButton("7"));   	// buttons added
		      	add(new JButton("8"));
		      	add(new JButton("9"));
		      	add(new JButton("+"));
		      	
				add(new JButton("4"));
				add(new JButton("5"));				
				add(new JButton("6"));
				add(new JButton("-"));
				add(new JButton("1"));
				add(new JButton("2"));
				add(new JButton("3"));
				add(new JButton("x"));
				add(new JButton("C"));
				add(new JButton("0"));
				add(new JButton("."));
				add(new JButton("/"));
				add(new JButton("("));
				add(new JButton(")"));
				add(new JButton("="), "gridwidth=2");
				add(new JTextField("Precision"));
				
				add(precision);
				addActionListeners();		//activated when a button is pressed
				
				add(decValue);
				
				precision.addChangeListener(new ChangeListener() {		//slider change listener activated when slider is changed

					public void stateChanged(ChangeEvent event) {		//method called by change listener
						JSlider source = (JSlider)event.getSource();
						decimal=source.getValue();
						decValue.setText("" +decimal);
						
						
					}
				});
					
		      
		   }

		 
		 
		   public void actionPerformed(ActionEvent e) {			// method called by action listener
		      String cmd = e.getActionCommand();
		     
		      result=result+cmd;
		      field1.setText(result);
		      if(cmd.equals("+") || cmd.equals("-") || cmd.equals("x") || cmd.equals("/") || cmd.equals("(") || cmd.equals(")"))		//if not operand or =
		      {
		    	  if(!s.equals(""))		//if operator or ")"
		    	  {
		    		  infix.enqueue(s);	   //enqueuing previous concatinated token
		    		  infix.enqueue(cmd);			//enqueuing current token
		    		  s="";
		    	  
		    	  }
		    	  else if(s.equals("") & !cmd.equals("(") & (cmd.equals("-") || cmd.equals("+")))	//if token is a unary sign then add it to string token
					{
						s+=cmd;
					}
		    	  else		//if open brackets
					{
						infix.enqueue(cmd);
					}
		    	  
		      }
		      else if(cmd.equals("="))
		      {
		    	  infix.enqueue(s);
		    	  algr();
		    	  field1.setText(result);
		      }
		      else if(cmd.equals("C"))		//clear all
		      {
		    	  
		    	  result="";
		    	  s="";
		    	  field1.setText(result);
		    	  field2.setText("");
		    	  precision.setValue(6);
		    	  decValue.setText("6");
		    	  decimal=6;
		    
		      }
		      
		      else		//if token is number then add it to string token
				{
		    	  s+=cmd;
				}
		
		   }
		

	}



import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalcLogic
	{
		//-- Instance variables.
		// The current total is all we need to remember.
		private double StoredVal = 0.0; 
		/** Constructor */
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    
	public String Evaluate(String sum)
		{
			try 
				{
					return engine.eval(sum) + "" ;
				} 
			catch (ScriptException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();		
				}
			return "";
		}
	
	//-----------------CONVERT ALL THE STRINGS ENTER ON THE PAD TO NUMERIC DOUBLES-------------------------------------------*/
		private double convertToNumber(String n)
			{
				return Double.parseDouble(n);
			}
		public void Square(Double n) 
		{	
				//Double.parseDouble(n);
				Double square = Math.pow(n * n, n);
				Double.toString( n );
		}
		
		
	//----------------------------------MEMORY FUNCTIONS FOR THE CALCULATOR STARTS --------------------------------------------------------///////////////////////////////////////////////////
		public void Store(String displayText) 
			{
				StoredVal = Double.parseDouble(displayText);
			}
		
		public double GetStoredVaL()
			{
				return StoredVal;
			}
		
		public void ResetStoredVal()
			{
				StoredVal = 0.0;
			}
		
		public void PlusStoredVal(double n) 
			{
					StoredVal += n;
			}
		
		public void MinusStoredVal(double n) 
			{
					StoredVal -= n;
			}
///-----------------------------MEMORY FUNTIONS FOR THE CALCULATOR ENDS-------------------------------------------------------------/////
	}

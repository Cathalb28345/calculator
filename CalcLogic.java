import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Cathal Brady C00202493
 * @version 2.0
 * @since 03/04/2017
 */
public class CalcLogic {
	// -- Instance variables.
	// The current total is all we need to remember.
	private double StoredVal = 0.0;
	/* script engine manager array */
	ScriptEngineManager mgr = new ScriptEngineManager();
	ScriptEngine engine = mgr.getEngineByName("JavaScript");
	/**
	 * 
	 * @param sum to be evaluated 
	 * takes in the result then parses the result into the script engine to be evaluated and then 
	 * if the result is == to a type double out of  bounds error that they tried to divide by zero and throws the DivideByZeroException
	 * and returns an error 
	 * else takes the ScriptException and returns error 
	 * @throws DivideByZeroException checks that you havent divided by 0
	 * @return sum
	 */
	
	public String Evaluate(String sum) throws DivideByZeroException {
		try

		{
			double result = Double.parseDouble(engine.eval(sum) + "");

			if (result == Double.POSITIVE_INFINITY) {
				throw new DivideByZeroException();
			} else {
				return engine.eval(sum) + "";
			}
		} catch (ScriptException e) {
			return "ERROR";
		}

	}
	

	// -- ENDS CONVERT ALL THE STRINGS ENTER ON THE PAD TO NUMERIC DOUBLES-------------------------------------------*/
	// -----------------BEGINS EXTRA FUNCTIONS FOR THE CALCULATOR-------------------------------------------*/
	/**
	 * 
	 * @param n to be squared
	 * takes in n turns it into a double from a string  
	 *squares n using Math.pow(square ,2) so it takes the inputed number and squares it by itself
	 *then reparses the double to a string and then returns it to a string
	 *@return squared number 
	 */
	public String Square(String n) {
		double square = Double.parseDouble(n);
		square = Math.pow(square, 2);
		return Double.toString(square);
	}
	/**
	 * 
	 * @param n return to its square root
	 * takes in n turns it into a double from a string  
	 *gets the square root of n using Math.sqrt(square) so it takes the inputed number and gets the sqaure root
	 *then reparses the double to a string and then returns it to a string
	 *@return SquareRoot 
	 */
	public String SquareRoot(String n) {
		double square = Double.parseDouble(n);
		square = Math.sqrt(square);
		return Double.toString(square);
	}

	// -----------------ENDS EXTRA FUNCTIONS FOR THE CALCULATOR-------------------------------------------*/
	// ----------------------------------MEMORY FUNCTIONS FOR THE CALCULATORSTARTS --------------------------------------------------------///////////////////////////////////////////////////
	/**
	 * 
	 * @param displayText stores the value 
	 * takes in displayText turns it into a double from a string  
	 *gets the total on the displayText and saves it
	 *then reparses the double to a string and then returns it to a string
	 *
	 */
	public void Store(String displayText) {
		StoredVal = Double.parseDouble(displayText);
	}
	/**
	 * 
	 * sends out the StoredVal thats saved and then prints it on screen  
	 *@return StoredVal
	 *
	 */
	public double GetStoredVaL() {
		return StoredVal;
	}
	/**
	 * 
	 * resets the StoredVal to 0.0
	 *
	 */
	public void ResetStoredVal() {
		StoredVal = 0.0;
	}
	/**
	 *@param n 
	 * adds to  StoredVal 
	 *
	 */
	public void PlusStoredVal(double n) {
		StoredVal += n;
	}
	/**
	 *@param n 
	 * subtracts from StoredVal 
	 *
	 *
	 */
	public void MinusStoredVal(double n) {
		StoredVal -= n;
	}
	/// -----------------------------MEMORY FUNTIONS FOR THE CALCULATOR
	/// ENDS-------------------------------------------------------------

}

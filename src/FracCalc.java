import java.util.*;
public class FracCalc {
	
    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
    	Scanner key=new Scanner(System.in);
    	String stop="stop";
    	String input=" ";
    	while (!input.equals(stop)) {
    		System.out.print("Please input fractions:");
    		input=key.nextLine();
    		System.out.println(produceAnswer(input));
    	}
    	
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 	
    	int wholeF=0;
    	int numeratorF=0;
    	int denominatorF=0;
    	int wholeS=0;
    	int numeratorS=0;
    	int denominatorS=0;
    	for(int i=0; i<input.length();i++) {
    		char y=input.charAt(i);
    		if(y=='+'||y=='-'||y=='*'||( y=='/'&& input.charAt(i+1)==' ')) {
    			String second=input.substring(i+1,input.length());
    			int wholeEnd=second.indexOf("_");
    			int numeratorEnd= second.indexOf("/");
    			String whole= "whole: ";
				String numerator= "numerator: ";
				String denominator= "denominator: ";
    			if (wholeEnd<0 && numeratorEnd <0) {
    				wholeEnd=i;
    				whole= "whole: "+second.substring(0,i);
    				numerator= "numerator: 0";
    				denominator=" denominator: 0";
    			}
    			else if (wholeEnd<0) {
    				numerator= "numerator: "+second.substring(0,numeratorEnd);
    				whole ="whole: 0";
    				denominator= "denominator: "+ second.substring(numeratorEnd+1, second.length());
    			}
    			else {
    				whole= "whole: "+second.substring(0,wholeEnd);
    				numerator= "numerator: "+ second.substring(wholeEnd+1,numeratorEnd);
    				denominator= "denominator: "+second.substring(numeratorEnd+1, second.length());
    			}
    			return whole +" "+ numerator+ " "+denominator; 

    			//return whole +" "+ numerator+ " "+denominator;
        			
    		}
    		
    	}
    	
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        
    	return input;
    }
    public static String Add(int one, int two, int num1, int num2, int den1, int den2) {
    	int totalWho=0;
    	int totalNum=0;
    	int totalDen=0;
    	totalWho=one+two;
    	if (den1==den2) {
    		totalNum=num1+num2;
    		totalDen=den1;
    	}
    	else {
    		int holderDen=den1;
    		int holderNum=num1;
    		num1=num1*den2;
    		den1=den1*den2;
    		num2=holderNum*num2;
    		den2=holderDen*den2;
    		totalNum=num1+num2;
    		totalDen=den1;
    	}
    	//String total= "total: "+ totalWho+"_"+totalNum+"/"+totalDen;
    	return Simplify(totalWho, totalNum, totalDen);
    }
    public static String Subtract(int one, int two, int num1, int num2, int den1, int den2) {
    	int totalWho=0;
    	int totalNum=0;
    	int totalDen=0;
    	totalWho=one-two;
    	if (den1==den2) {
    		totalNum=num1-num2;
    		totalDen=den1;
    	}
    	else {
    		int holderDen=den1;
    		int holderNum=num1;
    		num1=num1*den2;
    		den1=den1*den2;
    		num2=holderNum*num2;
    		den2=holderDen*den2;
    		totalNum=num1-num2;
    		totalDen=den1;
    	}
    	//String total="total: "+totalWho+"_"+totalNum+"/"+totalDen;
    	return Simplify(totalWho, totalNum, totalDen); 
    }
    public static String  Multiply(int one, int two, int num1, int num2, int den1, int den2) {
    	int totalWho=0;
    	int totalNum=0;
    	int totalDen=0;
    	int oneW=one*den1;
    	int twoW=two*den2;
    	num1+=oneW;
    	num2+=twoW;
    	totalNum=num1*num2;
    	totalDen=den2*den1;
    	String total="total: "+totalNum+"/"+totalDen;
    	return Simplify(totalWho, totalNum, totalDen);
    }
    public static String Divide(int one, int two, int num1, int num2, int den1, int den2) {
    	int totalWho=0;
    	int totalNum=0;
    	int totalDen=0;
    	int oneW=one*den1;
    	int twoW=two*den2;
    	num1+=oneW;
    	num2+=twoW;
    	totalNum=num1*den2;
    	totalDen=num2*den1;
    	String total="total: "+totalWho+"_"+totalNum+"/"+totalDen;
    	return Simplify(totalWho, totalNum, totalDen);    
    }
    public static String Simplify(int whole,int num, int den) {
    	int endNum= num%den; 
    	String total=" ";
    	int addWhole=(num-endNum)/den; 
    	int endWhole= addWhole+whole; 
    	int common= greatestCommonDivisor(endNum, den);
    	endNum/=common; 
    	den/=common;
    	if (endNum==0) {
    		total= "total: "+ endWhole; 
    	}
    	else {
    		total= "total: "+ endWhole + "_"+endNum+"/"+den;
    	}
    	return total;
    }
    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}

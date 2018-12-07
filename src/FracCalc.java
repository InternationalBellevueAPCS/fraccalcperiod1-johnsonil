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
    	System.out.println("RULES FOR INPUT: ");
    	System.out.println("1. input fractions as normal ex. 2/3 ");
    	System.out.println("2. inout mixed numbers using the following format 1_1/2 ");
    	System.out.println("3. negatives can be used  ");
    	System.out.println("4. space parts appropriatly 1_1/2 + 2_3/2 the space between opperand and numbers is important ");
    	System.out.println("5. Math only works if you use one operand NO 1 + 1 + 1 etc. ");
    	while (!input.equals(stop)) {
    		System.out.print("Please input equation:");
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
    		if(y=='+'||(y=='-'&& input.charAt(i+1)==' ')||y=='*'||( y=='/'&& input.charAt(i+1)==' ')) {
    			String second=input.substring(i+2,input.length());
    			int wholeEnd=second.indexOf("_");
    			int numeratorEnd= second.indexOf("/");
    			if (wholeEnd<0 && numeratorEnd <0) {
    				wholeS= Integer.parseInt(second);
    				numeratorS= 0;
    				denominatorS=0;
    			}
    			else if (wholeEnd<0) {
    				numeratorS= Integer.parseInt(second.substring(0,numeratorEnd));
    				wholeS =0;
    				denominatorS= Integer.parseInt(second.substring(numeratorEnd+1, second.length()));
    			}
    			else {
    				wholeS= Integer.parseInt(second.substring(0,wholeEnd));
    				numeratorS=Integer.parseInt( second.substring(wholeEnd+1,numeratorEnd));
    				denominatorS= Integer.parseInt(second.substring(numeratorEnd+1, second.length()));
    			}
    			String first= input.substring(0,i-1);
        		int wholeEndF=first.indexOf("_");
    			int numeratorEndF= first.indexOf("/");
        		
    			if (wholeEndF<0 && numeratorEndF<0) {
    				wholeF= Integer.parseInt(first);
    				numeratorF= 0;
    				denominatorF=0;
    			}
    			else if (wholeEndF<0) {
    				numeratorF= Integer.parseInt(first.substring(0,numeratorEndF));
    				wholeF =0;
    				denominatorF= Integer.parseInt(first.substring(numeratorEndF+1, first.length()));
    			}
    			else {
    				wholeF= Integer.parseInt(first.substring(0,wholeEndF));
    				numeratorF=Integer.parseInt( first.substring(wholeEndF+1,numeratorEndF));
    				denominatorF= Integer.parseInt(first.substring(numeratorEndF+1, first.length()));
    			}
    			if (y=='+') {
    				String result=Add(wholeF,wholeS,numeratorF,numeratorS,denominatorF, denominatorS);
    				return result;
    			}
    			else if (y== '-') {
    				String result=Subtract(wholeF,wholeS,numeratorF,numeratorS,denominatorF, denominatorS);
    				return result;
    			}
    			else if (y== '/') {
    				String result=Divide(wholeF,wholeS,numeratorF,numeratorS,denominatorF, denominatorS);
    				return result;
    			}
    			else if (y== '*') {
    				String result=Multiply(wholeF,wholeS,numeratorF,numeratorS,denominatorF, denominatorS);
    				return result;
    			}

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
    	if (one<0) {
    		num1*=-1;
    	}
    	if (two<0) {
    		num2*=-1;
    	}
    	if( one ==0 && den1==0) {
    		return Simplify(two, num2, den2) ;
    	}
    	else if( two ==0 && den2==0) {
    		return Simplify(one, num1, den1) ;
    	}
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
    		num2=holderDen*num2;
    		den2=holderDen*den2;
    		totalNum=num1+num2;
    		totalDen=den1;
    	}
    	//String total= "total: "+ totalWho+"_"+totalNum+"/"+totalDen;
    	return Simplify(totalWho, totalNum, totalDen);
    }
    public static String Subtract(int one, int two, int num1, int num2, int den1, int den2) {
    	if (one<0) {
    		num1*=-1;
    	}
    	if (two<0) {
    		num2*=-1;
    	}
    	if( one ==0 && den1==0) {
    		return Simplify(two, num2, den2) ;
    	}
    	else if( two ==0 && den2==0) {
    		return Simplify(one, num1, den1) ;
    	}
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
    		num1=num1*den2;
    		den1=den1*den2;
    		num2=holderDen*num2;
    		den2=holderDen*den2;
    		totalNum=num1-num2;
    		totalDen=den1;
    	}
    	//String total="total: "+totalWho+"_"+totalNum+"/"+totalDen;
    	return Simplify(totalWho, totalNum, totalDen); 
    }
    public static String  Multiply(int one, int two, int num1, int num2, int den1, int den2) {
    	if (den1==0) {
    		den1=1;
    	}
    	if (den2==0) {
    		den2=1;
    	}
    	if (one<0) {
    		num1*=-1;
    	}
    	if (two<0) {
    		num2*=-1;
    	}
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
    	if (den1==0) {
    		den1=1;
    	}
    	if (den2==0) {
    		den2=1;
    	}
    	if (one<0) {
    		num1*=-1;
    	}
    	if (two<0) {
    		num2*=-1;
    	}
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
    	if( den ==0) {
    		return whole+""; 
    	}
    	int endNum= num%den;
    	String total=" ";
    	int addWhole=(num-endNum)/den; 
    	int endWhole= addWhole+whole; 
    	int common= greatestCommonDivisor(endNum, den);
    	endNum/=common; 
    	den/=common;
    	
    	if (endNum<0 && den<0) {
    		endNum*=-1;
    		den*=-1;
    		
    	}
    	if( endNum>0 && den<0) {
    		endNum*=-1;
    		den*=-1;
    		
    	}
    	if(endWhole <0 && endNum<0 ) {
    		endNum=Math.abs(endNum);
    		den=Math.abs(den);
    	}
    	
    	if (endNum==0) {
    		total= endWhole+"";   	
    	}
    	else if (endWhole==0 && den>0) {
    		total= endNum+"/"+den;
    		
    	}
    	else {
    		total=  endWhole + "_"+endNum+"/"+den;
    		
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

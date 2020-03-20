package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import java.util.InputMismatchException;
import java.awt.*;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;
public class UIApplication {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		IPolynomialSolver solver = new PolynomialSolver();
		char poly1='\0',poly2='\0';
		float value=0.0f;
		String line ="";
		boolean wantingToExit=false;//a boolean to control some loops
		System.out.println("------------------------------------------------------------------------\n"+
							"           --------------------------------------------------\n"+
							"           -------------    Polynomial Solver   -------------\n"+
							"           --------------------------------------------------\n");
		while(true) {
			try {
				mainMenuUI();
				byte action = input.nextByte();
				if (action<1 || action>8) throw new InputMismatchException();
					actionDoingLabel:{
						try {
							if(action==1) {
								System.out.println("	Insert the variable name: A, B or C:\n"+
													"	Or enter M for Main Menu");
								poly1 = input.next().trim().charAt(0);
								if(poly1=='M') continue;
								if (poly1<'A' || poly1>'C') throw new InputMismatchException();
								System.out.print("	Insert the polynomial terms in the form\n" + 
													"	 (coeff1, exponent1), (coeff2, exponent2), ..\n"+
													"	Please enter them in decreasing order of the exponent value.\n"+
													"Enter your input :");
								inputLineLabel:
									try {
										line ="";
										while( line.contentEquals("")) line = input.nextLine();//removing unwanted empty line
										if (line.length()<5) throw new InputMismatchException(); // for example (1,1) is 5 letters
										solver.setPolynomial(poly1, getTerms(line));
										System.out.println("		" + poly1 + " is set now.");
									}catch(RuntimeException ex) {
										System.out.println("	Invalid terms input. Please enter a valid terms:");
										break inputLineLabel;
									}
							}else if(action==2) {
									System.out.println("	Insert the variable name: A, B, C or R:"+
														"	Or enter M for Main Menu");
									poly1 = input.next().trim().charAt(0);
									if(poly1=='M') continue;
									if ( !( (poly1>='A' && poly1<='C') || poly1=='R') ) throw new InputMismatchException();
									String polynomial = solver.print(poly1);
									if(polynomial == null) throw new RuntimeException();
									System.out.println("Variable "+poly1+" is "+polynomial);
							}else if(action == 6) {
								System.out.println("	Insert the variable name: A, B or C:"+
													"	Or enter M for Main Menu");
								try{
									poly1 = input.next().trim().charAt(0);
									if(poly1=='M') continue;
									if (poly1<'A' || poly1>'C') throw new InputMismatchException();
								}catch(InputMismatchException ex) {
									System.out.println("	Invalid input.");
									break actionDoingLabel;
								}
								solver.evaluatePolynomial(poly1, 1); // check is it is set
								System.out.print("	Enter the value of the varible in the polynomial:\n		");
								inputValueLabel:
									try {
										value = input.nextFloat();
										System.out.println("	Result = "+solver.evaluatePolynomial(poly1, value));
									}catch(InputMismatchException ex){
										System.out.println("	Invalid input. Please enter a valid input:");
										break inputValueLabel;
									}
							}else if(action==7) {
								System.out.println("	Insert the variable name: A, B or C:"+
													"	Or enter M for Main Menu");
								poly1 = input.next().trim().charAt(0);
								if(poly1=='M') continue;
								if (poly1<'A' || poly1>'C') throw new InputMismatchException();
								solver.clearPolynomial(poly1);
								System.out.println("	"+poly1+" is cleared.");
								continue;
							}else if(action==8) {
								input.close();
								System.out.println("		Exit was done successfully.");
								System.exit(0); // No need for break as the JVM will shutdown.
							}else {
								wantingToExit = false;
								while(true) {
									System.out.println("Insert first operand variable name: A, B or C"+
														"	Or enter M for Main Menu");
									poly1 = input.next().trim().charAt(0);
									if(poly1=='M') {wantingToExit=true; break;}
									if ( poly1<'A' || poly1>'C' ) throw new InputMismatchException();
									try{
										solver.evaluatePolynomial(poly1, 1);// Checking if poly1 is set
									}catch(RuntimeException ex) {
										System.out.println("		"+poly1+" isn't set yet.");
										continue;
									}
									break;
								}
								if(wantingToExit) continue;
								while(true) {
									try {
										System.out.println("Insert second operand variable name: A, B or C"+
															"	Or enter M for Main Menu");
										poly2 = input.next().trim().charAt(0);
										if(poly2=='M') {wantingToExit=true; break;}
										if ( poly2<'A' || poly2>'C' ) throw new InputMismatchException();
										solver.evaluatePolynomial(poly2, 1);// Checking if poly1 is set
									}catch (InputMismatchException ex) {
										System.out.println("	Invalid input.");
										continue;
									}catch(RuntimeException ex) {
										System.out.println("		"+poly2+" isn't set yet.");
										continue;
									}
									break;
								}
								if(wantingToExit) continue;
									try {
										switch(action) {
										case 3:
											solver.add(poly1, poly2);
											break;
										case 4:
											solver.subtract(poly1, poly2);
											break;
										case 5:
											solver.multiply(poly1, poly2);
										}
										System.out.println("	R is "+solver.print('R'));
									}catch(RuntimeException ex) {
										System.out.println("	Operand variable isn't set yet.");
									}
							}
						}catch (InputMismatchException ex) {
							System.out.println("	Invalid input. Please enter a valid input");
							break actionDoingLabel;
						} catch(RuntimeException ex) {
							System.out.println("		"+poly1+" isn't set yet.");
							break actionDoingLabel;
						}
				}
			}catch (InputMismatchException ex) { // catching action wrong input
				input.nextLine();
				System.out.println("	Invalid input. Please enter a valid input:");
			}
		}
	}
	public static void mainMenuUI() {
		System.out.println("------------------------------------------------------------------------\n" +
							"		Choose an action:\n" +
							"		1- Set a polynomial variable.\n"+
							"		2- Print the value of a polynomial variable.\n"+
							"		3- Add two polynomials.\n"+
							"		4- Subtract two polynomials.\n"+
							"		5- Multiply two polynomials.\n"+
							"		6- Evaluate a polynomial at some point.\n"+ 
							"		7- Clear a polynomial variable.\n"+
							"		8- Exit.");
	}
	public static int[][] getTerms(String line){
		Slinkedlist list = new Slinkedlist();
		int length = line.length();
		String num1 = "",num2 = "";
		for(int i=0; i<length; i++) {
			if(line.charAt(i)==' ' || line.charAt(i)==',') continue;
			if(line.charAt(i) == '(') {
				i++; // Start taking num1
				while(Character.isDigit(line.charAt(i)) || line.charAt(i)==' ') num1 += line.charAt(i++);
				if(num1.length()==0 || line.charAt(i) != ',') throw new InputMismatchException();
				i++;// Start taking num2
				while(Character.isDigit(line.charAt(i)) || line.charAt(i)==' ') num2 += line.charAt(i++);
				if(num2.length()==0 || line.charAt(i) != ')') throw new InputMismatchException();
				list.add(new Point(Integer.parseInt(num1),Integer.parseInt(num2)));
				num1 = "";
				num2 = "";
				continue;
			}
			//will reach here only when an invalid input
			throw new InputMismatchException();
		}
		int[][] terms = new int[list.size()][2];
		for(int i=0;i<list.size();i++) {
			terms[i][0]= ((Point)list.get(i)).x;
			terms[i][1]= ((Point)list.get(i)).y;
		}
		return terms;
	}
}
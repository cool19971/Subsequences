/**
 * @author Damon McClellan
 * Subsequence Checker
 * CS215 Data Structures  
 */

import java.util.*;
import java.io.*;

public class Application {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);
		boolean loop = true; 
	
		while (loop == true){
			String sequence1 ="abc";
			String sequence2 ="aaqbzcw";
			String mf=""; 
			String continueLoop = "";
			boolean isSubSequence = false;
			int isSubSequenceCheck = 0;
			int m = 0;
			int numberFound = 0;
			
			System.out.println("Would you like enter sequences manually or from a file? (M/F):");
			mf = scan.nextLine();
			
			if (mf.equals("M") == true) {
				System.out.println("\nEnter the first sequence:");
				sequence1 = scan.nextLine().toLowerCase();
				System.out.println("Enter the second sequence:");
				sequence2 = scan.nextLine().toLowerCase();
				char[] array1 = sequence1.toCharArray();
				char[] array2 = sequence2.toCharArray();
				char[] order = new char [array1.length];
				int s1Length = array1.length;
				int s2Length = array2.length;
				
				if(s2Length >= s1Length) {
					for (int i = s1Length -1; i >=0; i--) {
						boolean isFound = (sequence2.contains(Character.toString(array1 [i])));
						if (isFound == true) {
							numberFound ++;
						}//end if
						
						if(numberFound == s1Length) {
							StackInterface<Character> s2Stack = new ArrayStack<>();
							for (int k = s2Length - 1; k > 0 - 1; k--) {
								s2Stack.push(array2 [k]);
							}//end for
							for (int j = 0; j <= s2Length - 1; j ++) {
								for (int l = 0 ; l < s1Length; l ++ ) {
									if(m != s1Length  && s2Stack.isEmpty() != true) {
										if (s2Stack.peek() == array1 [m] && s2Stack.peek() != order [l] && s2Stack.isEmpty() != true) {
											order [m] = s2Stack.pop();
											s2Stack.push(order [m]);
											m ++ ;
											l = s1Length + 1;
										}else {
											if(s2Stack.isEmpty() != true) {
												s2Stack.pop();
											}//end if 
										}//end else
									}//end if
								}//end for
							}//end for
							
							for (int n = 0; n < s1Length; n++) {
								if (order[n] == array1 [n]) {
									isSubSequenceCheck ++ ;
									if (isSubSequenceCheck == s1Length) {
										isSubSequence = true;
									}//end if
								}//end if
							}//end for
						}//end if
					}//end for
					
					if (isSubSequence == true) {
						System.out.println("\n" + sequence1 + " IS A SUBSEQUENCE of " + sequence2 + ".");
					}else System.out.println("\n" + sequence1 + " IS NOT A SUBSEQUENCE of " + sequence2 + ".");
				}else System.out.println("\n " + sequence1 + " IS NOT A SUBSEQUENCE of " + sequence2 + ".");
				
				System.out.println("\nWould you like to enter more sequences? (Y/N):");
				continueLoop = scan.nextLine();
				
				if (continueLoop.equals("Y") == true) {
					loop = true;
				}else if (continueLoop.contentEquals("N")) {
					loop = false;
				}else {System.out.println("Inccorrect format"); loop = false; }
				
			}else if (mf.contentEquals("F") == true) {
				System.out.println("Enter the name of the file to process:");
				String fileName = scan.nextLine();
				FileReader fr=new FileReader(fileName);
				
				try {
					BufferedReader br = new BufferedReader(fr);
					String line = null;
					StringBuffer stringBuffer = new StringBuffer("");
					while ((line = br.readLine()) != null) {
						stringBuffer.append(line + ", ");
					}//end while
					
					String lines = stringBuffer.toString();
					String [] arrayLines = lines.split(", ");
					int arrayLinesSize = arrayLines.length;
					for(int o = 0; o <= ((arrayLinesSize + (arrayLinesSize/2))/ 2); o = o + 2) {
						sequence1 = arrayLines[o].toLowerCase();
						sequence2 = arrayLines[o + 1].toLowerCase();
						char[] array1 = sequence1.toCharArray();
						char[] array2 = sequence2.toCharArray();
						char[] order = new char [array1.length];
						int s1Length = array1.length;
						int s2Length = array2.length;
						isSubSequence = false;
						isSubSequenceCheck = 0;
						m = 0;
						numberFound = 0;
						
						if(s2Length >= s1Length) {
							for (int i = s1Length -1; i >=0; i--) {
								boolean isFound = (sequence2.contains(Character.toString(array1 [i])));
								if (isFound == true) {
									numberFound ++;
								}//end if
								
								if(numberFound == s1Length) {
									StackInterface<Character> s2Stack = new ArrayStack<>();
									for (int k = s2Length - 1; k > 0 - 1; k--) {
										s2Stack.push(array2 [k]);
									}//end for
									
									for (int j = 0; j <= s2Length - 1; j ++) {
										for (int l = 0 ; l < s1Length; l ++ ) {
											if(m != s1Length  && s2Stack.isEmpty() != true) {
												if (s2Stack.peek() == array1 [m] && s2Stack.peek() != order [l]  && s2Stack.isEmpty() != true) {
													order [m] = s2Stack.pop();
													s2Stack.push(order [m]);
													m ++ ;
													l = s1Length + 1;
												}else {
													if (s2Stack.isEmpty() != true) {
														s2Stack.pop(); 
													}//end if
												}//end else
											}//end if
										}//end for
									}//end for
									for (int n = 0; n < s1Length; n++) {
										if (order[n] == array1 [n]) {
											isSubSequenceCheck ++ ;
											if (isSubSequenceCheck == s1Length) {
												isSubSequence = true;
											}//end if
										}//end if
									}//end for
								}//end if
							}//end for
							
							if (isSubSequence == true) {
								System.out.println("\n" + sequence1 + " IS A SUBSEQUENCE of " + sequence2 + ".");
							}else System.out.println("\n" + sequence1 + " IS NOT A SUBSEQUENCE of " + sequence2 + ".");
						}else System.out.println("\n " + sequence1 + " IS NOT A SUBSEQUENCE of " + sequence2 + ".");
					}//end for
					System.out.println("\nWould you like to enter more sequences? (Y/N):");
					continueLoop = scan.nextLine();
					
					if (continueLoop.equals("Y") == true) {
						loop = true;
						}else {loop = false;}
				}//try
				catch (IOException e) {}

			}else {System.out.println("Inccorect format");}
		}//end while
		System.out.println("<END RUN>");
	}//end main
}//end application

// Created by: Ahmed Elehwany
// this code is a quick solver for scrabble game. Basically you enter the set of letters you have and the code generates the longest word present in dictionary file.txt that is made up of these letters or some of them.
// You can initialize an existing string as argument to the method and then the method adds letters to this string to form a longer valid word.

import java.util.*;
import java.io.*;



public class Scrabble{

    static HashSet<String> myDictionary; // this is where the words of the dictionary are stored

    // Reads dictionary from file
    public static void readDictionaryFromFile(String fileName) throws Exception {
        myDictionary=new HashSet<String>();

        BufferedReader myFileReader = new BufferedReader(new FileReader(fileName) );

        String word;
        while ((word=myFileReader.readLine())!=null) myDictionary.add(word);

	myFileReader.close();
    }


    
    

    /* Arguments: 
        char availableLetters[] : array of characters containing the letters that remain available
        String prefix : Word assembled to date
        Returns: String corresponding to the longest English word starting with prefix, completed with zero or more letters from availableLetters. 
	         If no such word exists, it returns the String ""
     */
     public static String longestWord(char availableLetters[], String prefix) {
    	 
    	 String longest="";
     if(availableLetters.length==0)		//termination step for recursion
    	 {
    	 return "";
    	}
     
	 for(int i=0; i<availableLetters.length;i++)	//to add different letters of the array to the same index position in prefix
	 {
		
		 String word=prefix+availableLetters[i];	//saving updated string in a variable other than the original prefix

		 if (myDictionary.contains(word)) 
		 {
			 //System.out.println("The word " + "'"+ word+ "'" + " is in the dictionary");
			 if(word.length()>longest.length())	//if true update string longest with the word found
			 {
			  longest=word;
			 }
			 
		 }
		
			 char[] newavailableLetters = new char[availableLetters.length-1];	//new array without the letter already been used
			 
			 int m=0;
			 
			 for(int j=0; j<availableLetters.length; j++ )
			 {
				 if(j==i)	continue;
				 
				 newavailableLetters[m]=availableLetters[j];
				 
				 m++;
			 }
			 
		     String result=longestWord(newavailableLetters, word);	//saving the return value from the recursive call
		     if(result.length()>longest.length())	{longest=result;}	//if return value fulfills the condition, update string longest
		     
	 }
	 
	 return longest;


    }

    
    
    /* main method
        You should not need to change anything here.
     */
    public static void main (String args[]) throws Exception {
       
	// First, read the dictionary
	try {
	    readDictionaryFromFile("englishDictionary.txt");
        }
        catch(Exception e) {
            System.out.println("Error reading the dictionary: "+e);
        }
        
        
        // Ask user to type in letters
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in) );
        char letters[]; 
        do {
            System.out.println("Enter your letters (no spaces or commas):");
            
            letters = keyboard.readLine().toCharArray();

	    // now, enumerate the words that can be formed
          
            String longest = longestWord(letters, "");
	    System.out.println("The longest word is "+"'"+longest+"'");
        } while (letters.length!=0);

        keyboard.close();
        
    }
}

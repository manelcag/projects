/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  WordCounter
*****************************************************/

import java.io.*;

public class WordCounter {
	private final static int KEYWORDS_MAX = 1000;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("WordCounter use: WordCounter" +
            "keywords_file file_to_analyze result_file");
			System.out.println("ie: WordCounter keywords.txt"+ 
            "WordCounter.java result.txt");
		} else {
			String keywords[];
			int keywordsOcc[];
			
			keywords = loadKeyWords(args[0]);
			
			keywordsOcc = countKeywords(args[1], keywords);
			
			saveToFile(args[2], keywords, keywordsOcc);
		}
	}
	
	private static String[] loadKeyWords(String fileName) {
		BufferedReader inputFile  = null;
		String line;
		int keywordsNumber = 0;
		String result[] = new String[KEYWORDS_MAX];
		String[] splittedLine;

		System.out.print("Loading keywords from ");
		System.out.println(fileName);
		
		try {
			inputFile = new BufferedReader(new FileReader(fileName));
			
			line = inputFile.readLine();
			while (line != null) {
				// Add line to result array
				System.out.print("Adding: ");
				System.out.println(line);
				
				splittedLine = line.split(" ");
				
				for (int keywordNum= 0; keywordNum < 
                splittedLine.length; keywordNum++) {
					result[keywordsNumber] = splittedLine[keywordNum];
					keywordsNumber++;
				}

				// Next line
				line = inputFile.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.print("File ");
			System.err.print(fileName);
			System.err.println(" not found.");
		} catch (IOException e) {
			System.err.println("Error reading keywords.");
		} finally {
			try {
				if (inputFile != null) {
					inputFile.close();
				}
			} catch (IOException e) {
				System.err.print("Can not close file!");
			}
		}
		
		System.out.print("Loaded ");
		System.out.print(keywordsNumber);
		System.out.println(" keywords");
		
		return result;
	}

	
	private static int[] countKeywords(String fileName, 
    String keywords[]) {
		BufferedReader inputFile  = null;
		String line;
		int result[] = new int[KEYWORDS_MAX];
        
		System.out.print("Analyzing: ");
		System.out.println(fileName);
		
		try {
			inputFile = new BufferedReader(new FileReader(fileName));
			
			line = inputFile.readLine();
			while (line != null) {
				System.out.println(line);
				
				// Analyze line to find keyword and store its number 
                // in result
				countKeywords(keywords, line, result);
				
				// Next line
				line = inputFile.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.print("File ");
			System.err.print(fileName);
			System.err.print(" not found.");
		} catch (IOException e) {
			System.err.println("Error reading keywords.");
		} finally {
			try {
				if (inputFile != null) {
					inputFile.close();
				}
			} catch (IOException e) {
				System.err.print("Can not close file!");
			}
		}
		
		return result;
	}
	
	// In the beginning I used a function to find the keyword
	// but maybe there is more than one keyword in a line
	// that's why I implemented countKeyword directy, instead
	// of using a function returning an array with keywords indexes
	private static void countKeywords(String[] keywords, String line, 
    int[] result) {
		int keywordsNumber;
		String preparedLine;
		
        // convert all to upper case to ignore differences between
        // upper and lower case
		keywordsNumber = countKeyWords(keywords);
		preparedLine = line.toUpperCase().trim();
		
		// Analyze preparedLine to find keyword occurrences
		for (int idx= 0; idx < keywordsNumber; idx++) {
			// There is no need to initialize to zero
			// because java initializes automatically int arrays as 0
			if (preparedLine.indexOf(keywords[idx].toUpperCase().trim
            ()) >= 0) {
				System.out.println("Found a keyword occurrence");
				result[idx] = result[idx] + 1;
			}
		}
	}
	
	private static int countKeyWords(String[] keywords) {
		int keywordsNumber = 0;
		
		while(keywords[keywordsNumber] != null
				&& keywordsNumber < KEYWORDS_MAX) {
			keywordsNumber++;
		}
		
		return keywordsNumber;
	}
	
	private static void saveToFile(String filename, String keywords[], 
    int keywordsOcc[]) {
		int keywordsNumber;
		PrintWriter outputFile = null;
		
		keywordsNumber = countKeyWords(keywords);

		try {
			outputFile = new PrintWriter(new FileOutputStream(filename));
			
			for (int idx= 0; idx < keywordsNumber; idx++) {
				outputFile.print(keywordsOcc[idx]);
				outputFile.print("\t");
				outputFile.println(keywords[idx]);
			}
			
		} catch (FileNotFoundException e) {
			System.err.print("An error has occurred creating result"+
            "file [");
			System.err.print("filename]. ");
			System.err.print("Error was: ");
			System.err.println(e.getCause());
		} finally {
			if (outputFile != null) {
				outputFile.close();
			}
		}
	}
}

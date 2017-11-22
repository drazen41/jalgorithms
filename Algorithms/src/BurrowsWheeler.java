import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BurrowsWheeler {
	private static boolean DEBUG = false;
	// apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {

    	String input = "";
    	int originalInd = 0;
//    	if (DEBUG) {
////    		In in = new In("aesop.txt");
////        	input = in.readAll();	
//		} else {
//	    	 	
//		}
    	input = BinaryStdIn.readString();

    	char[] characters = input.toCharArray();
    	CircularSuffixArray circularSuffixArray = new CircularSuffixArray(input);
    	for (int i = 0; i < circularSuffixArray.length(); i++) {
			if (circularSuffixArray.index(i) == 0) {
				originalInd = i;
//				if (!DEBUG) {
//					
//				}
				BinaryStdOut.write(i);
				break;
			}
		}
    	
    	StringBuilder sBuilder = new StringBuilder();
    	sBuilder.append(originalInd);
    	for (int i = 0; i < characters.length; i++) {
			
    		int letterIndex = circularSuffixArray.index(i) - 1;
    		if (letterIndex < 0) {
				letterIndex = input.length()-1;
			}
    		char letter = characters[letterIndex];
//			if (DEBUG) {
//				sBuilder.append(letter);
//			} else {
//				
//			}
			BinaryStdOut.write(letter);
			
		}
    	
//    	if (DEBUG) {
//			StdOut.println(sBuilder.toString());
//		}
    	
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
    	int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        char[] sorted = new char[input.length];

        for (int i = 0; i < input.length; i++)
            sorted[i] = input[i];
        
        Arrays.sort(sorted);
        
        int []baseIndex = new int[256];
        int []next = new int[input.length];

        // First, construct the next array...
        for (int i = 0; i < input.length; i++) {
            next[i] = getNextForChar(sorted[i], input, baseIndex);
        }

        if (DEBUG)
            for (int i = 0; i < next.length; i++)
                StdOut.printf("Next: i: %d   %d\n", i, next[i]);

        // show the string.
        int i, ptr;
        for (i = 0, ptr = first; i < next.length; i++, ptr = next[ptr]) {
            BinaryStdOut.write(sorted[ptr], 8); 
        }
            
        BinaryStdOut.close();
    }
    private static int getNextForChar(char c, char[] input, int []baseIndex) {
        for (int i = baseIndex[c]; i < input.length; i++) {
            if (input[i] == c) {
                baseIndex[c] = i+1;
                return i;
            }
        }

        // should not reach here....
        assert false;
        return 0;
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
    	BurrowsWheeler.encode();
    }
}

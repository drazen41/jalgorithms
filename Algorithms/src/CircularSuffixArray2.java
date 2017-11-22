import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class CircularSuffixArray2 {
	private char[] cSuffixes;
	
	private Hashtable<Integer,Integer> indexSet;
	private int stringLength;
	public CircularSuffixArray2(String s) {  // circular suffix array of s
		//suffixes = new char[s.length()];
		this.stringLength = s.length();
		this.indexSet = new Hashtable<Integer,Integer>();
		Hashtable<String, Integer> original = new Hashtable<String,Integer>();
		Hashtable<Integer, String> sorted = new Hashtable<Integer,String>();
		
		String[] oSuffixes = new String[stringLength];
		String[] sSuffixes = new String[stringLength];
		char[] characters = s.toCharArray();
		for (int i = 0; i < characters.length; i++) {			
			String suffix = s.substring(i) + s.substring(0, i);
			
			oSuffixes[i] = suffix;
			//original.put(suffix, i);
		}
		sSuffixes = oSuffixes.clone();
		Arrays.sort(sSuffixes);
		int i = 0;
		for (String string : sSuffixes) {
			indexSet.put(i, original.get(string));
		//	indexes[i] = tst.get(string);
			i++;
		}
		
	}
    public int length() {                   // length of s
    	return stringLength;
    }
    public int index(int i) {              // returns index of ith sorted suffix
    	return indexSet.get(i);
    }
    public static void main(String[] args) {  // unit testing of the methods (optional)
    	In in = new In("aesop.txt");
    	String input = in.readAll();
    	//CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABRACADABRA!");
    	CircularSuffixArray2 circularSuffixArray = new CircularSuffixArray2(input);
    	StdOut.println(circularSuffixArray.length());
    	StdOut.println(circularSuffixArray.index(11));
    }
}

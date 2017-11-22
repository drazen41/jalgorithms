import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;


public class CircularSuffixArray3 {
	private int stringLength ;
	private String string;
    private Integer[]  sortSuffixes;
    private HashMap<Integer, Integer> indices;
    private class SuffixesOrder implements Comparator<Integer> {
        public int compare(Integer i, Integer j) {
            if      ((length() - 1) < i) return 1;
            else if ((length() - 1) < j) return -1;
            if (string.charAt(i) != string.charAt(j))
                return compare(string.charAt(i), string.charAt(j));
            else
                return compare(i+1, j+1);
        }

        private int compare(char a, char b) {
            return b - a;
        }
    }   

    private Comparator<Integer> suffixesOrder() {
        return new SuffixesOrder();
    }

    // circular suffix array of s
    public CircularSuffixArray3(String s) {
        this.stringLength = s.length();
        this.indices = new HashMap<Integer,Integer>();
    	if (s == null) throw new IllegalArgumentException();
        string = s;
        sortSuffixes = new Integer[length()];
        for (int i = 0; i < length(); i++)
            //sortSuffixes[i] = (length() - 1) - i;
        	sortSuffixes[i] = i;
        //Arrays.sort(sortSuffixes, suffixesOrder());
        Arrays.sort(sortSuffixes,suffixesOrder());
        for (int i = 0; i < sortSuffixes.length; i++) {
		//	indices.put(sortSuffixes[sortSuffixes.length-1-i],i);	
			indices.put((sortSuffixes.length-1)-i,sortSuffixes[i]);
		}
        
       // StdOut.println(sortSuffixes.toString());
    }
    public int length() {                   // length of s
    	return stringLength;
    }
    public int index(int i) {              // returns index of ith sorted suffix
    	if (i < 0 || i > this.stringLength-1) {
			throw new IllegalArgumentException();
		}
    	return indices.get(i);
    }
    public static void main(String[] args) {  // unit testing of the methods (optional)
//    	In in = new In("aesop.txt");
//    	String input = in.readAll();
    	CircularSuffixArray3 circularSuffixArray = new CircularSuffixArray3("BABABBAAAA");
    	//CircularSuffixArray circularSuffixArray = new CircularSuffixArray(input);
    	StdOut.println(circularSuffixArray.length());
    	StdOut.println(circularSuffixArray.index(0));
    }
}

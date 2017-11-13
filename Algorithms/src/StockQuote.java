import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class StockQuote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name  = "https://finance.yahoo.com/quote/";
		String stock = "msft";
		In in = new In(name+stock);
		String text = in.readAll();
		int start = text.indexOf("Last Trade:",0);
		int from = text.indexOf("<b>", start);
		int to = text.indexOf("</b>", from);
		String price = text.substring(from+3,to);
		StdOut.println(price);
	}

}

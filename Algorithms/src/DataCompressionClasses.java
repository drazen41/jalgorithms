import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import edu.princeton.cs.algs4.BinaryStdIn;

public class DataCompressionClasses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BinaryDump.main(args);
//		HexDump.main(args);
//		PictureDump.main(args);
//		RunLength.compress();
		String text = "ABRACADABRA!";
		BinaryOut binaryOut = new BinaryOut("abraBin.data");
		binaryOut.write(text);
		binaryOut.close();
//		FileWriter outFile = null;
//        try {
//            outFile = new FileWriter("member.txt");
//            outFile.write("Test");
//            outFile.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
		
		BinaryStdIn.readString();
	}

}

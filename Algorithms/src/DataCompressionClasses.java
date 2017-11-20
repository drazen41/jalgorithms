


public class DataCompressionClasses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BinaryDump.main(args);
//		HexDump.main(args);
//		PictureDump.main(args);
//		RunLength.compress();
		String text = "ABRACADABRA!";
		BinaryOut binaryOut = new BinaryOut();
		for (Character character  : text.toCharArray()) {
			binaryOut.write(character);
		}
		
	}

}

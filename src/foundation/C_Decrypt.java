package foundation;
import util.CryptoTools;

public class C_Decrypt {

	
	public static byte[] CaeserDencrypt(byte[] ct, Integer keyShift )
	{
						
		byte pt[] = new byte[ct.length];
		
		for (int i = 0; i != ct.length; i++) {
									
			pt[i] = (byte) (((char)(ct[i] + 'A') - keyShift) % 26 + 'A');	
					
		}
		return pt; 
	}
	
	
	public static void main(String[] args) throws Exception { 
		//Do Caesar decryption using a key of 19
		
		byte[] raw = CryptoTools.fileToBytes("data/MSG1.ct.txt"); //read from text file
		byte[] ct = CryptoTools.clean(raw); //Clean up the cipher text, remove spaces, etc.

		//CryptoTools.bytesToFile(CaeserDencrypt(ct, 19), "data/Trial.txt"); //output to a text file OR
		System.out.print(new String(CaeserDencrypt(ct, 19))); //output the plaintext to the console
	}
	
	
	
	
}

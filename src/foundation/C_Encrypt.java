package foundation;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import javax.naming.spi.DirStateFactory.Result;

import util.CryptoTools;

public class C_Encrypt {
	
     //Takes the cleaned up plain text file in Bytes from CryptoTools.clean and Integer key value, output is the Cipher text
	public static byte[] CaeserEncrypt(byte[] pt, Integer keyShift )
	{
						
		byte ct[] = new byte[pt.length]; 
		
		for (int i = 0; i != pt.length; i++) {
									
			ct[i] = (byte) (((char)(pt[i] - 'A') + keyShift) % 26 + 'A');	
					
		}
		return ct; 
	}
	

	public static void main(String[] args) throws Exception {
		
		byte[] raw = CryptoTools.fileToBytes("data/MSG1.txt"); 
		byte[] pt = CryptoTools.clean(raw);
		
		CryptoTools.bytesToFile(pt, "data/MSG1.clean.txt"); //calling a void method to persist clean plaintext to new text note
		
		
		//CryptoTools.bytesToFile(CaeserEncrypt(pt, 19), "data/MSG1.ct.txt"); // encrypt plaintext to get ciphertext
		System.out.print(new String(CaeserEncrypt(pt, 19))); //output the ciphertext to the console 

	}
	
	
}

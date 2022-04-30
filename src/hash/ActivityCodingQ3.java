package hash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;

import javax.print.attribute.Size2DSyntax;

import util.CryptoTools;

public class ActivityCodingQ3 {

	public static byte[] bitwiseXOR(byte[] b1, byte b2){ //XOR two given byte arrays
		
		byte[] bt = new byte[b1.length]; 
		for(int i=0; i< b1.length; i++ ){ //length of b1 is 64 bytes for SHA1
				
					bt[i] = (byte) (b1[i] ^ b2);
		}
		return bt;
		
	}	
	
	
	
	public static void main(String[] args) throws Exception {

		//3 a. padding constants are:
		byte opad = 0x5c;
		byte ipad = 0x36;
		
		//Possible block sizes
		byte SHA1_blocksize_bytes = 64; //512 bits

		//message and key
		byte[] m = "The quick brown fox jumps over the lazy dog".getBytes();
		byte[] key = "key".getBytes();		
	
		
		//Type of hash we're using
		MessageDigest md = MessageDigest.getInstance("SHA-1"); //type of hash used, blocksize VARIES!
				
		
		if (key.length > SHA1_blocksize_bytes) {
            
            byte[] hashedKey = md.digest(key);
            byte[] paddedKey = Arrays.copyOf(hashedKey, SHA1_blocksize_bytes);
            
            //K' XOR ipad
            byte[] ipad_XOR = bitwiseXOR(paddedKey, ipad);  
            //(K' XOR ipad)||m concatenate ipad_XOR with m
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
            out1.write(ipad_XOR);
            out1.write(m);
            //H((K' XOR ipad)||m) create the hash
            byte[] inner_Hash = md.digest(out1.toByteArray()); 
            
            
            //K' XOR opad
            byte[] opad_XOR = bitwiseXOR(paddedKey, opad);
            //(K' XOR opad) || H((K' XOR ipad)||m) concatenate
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            out2.write(opad_XOR);
            out2.write(inner_Hash);
            //H( (K' XOR opad) || H((K' XOR ipad)||m) )  
            //make final Hash encapsulating everything
            byte[] outer_Hash = md.digest(out2.toByteArray());
			
			System.out.print(CryptoTools.bytesToHex(outer_Hash));
		}
		
		else if (key.length < SHA1_blocksize_bytes) {
            
            //Creates a new array for the key of the block size with padding for the key consisting of 0s
            byte[] paddedKey = Arrays.copyOf(key, SHA1_blocksize_bytes);    
            
            //H((K' XOR ipad)||m)
            byte[] ipad_XOR = bitwiseXOR(paddedKey, ipad);        
            //Concatenate ipad_XOR with m
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
            out1.write(ipad_XOR);
            out1.write(m);
            byte[] inner_Hash = md.digest(out1.toByteArray()); 
            
            
            //H( (K' XOR opad) || H((K' XOR ipad)||m) )
            byte[] opad_XOR = bitwiseXOR(paddedKey, opad);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            out2.write(opad_XOR);
            out2.write(inner_Hash);
            byte[] outer_Hash = md.digest(out2.toByteArray());
			
			System.out.print(CryptoTools.bytesToHex(outer_Hash));

		}
	
	}
	
	
	
	
	
	
	
	
	
	
}

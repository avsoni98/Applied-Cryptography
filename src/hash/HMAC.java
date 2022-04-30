package hash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.print.attribute.Size2DSyntax;


import util.CryptoTools;

public class HMAC {
    
    
    public static void main(String[] args) throws Exception {
        
    //check the OPAD and IPAD values unless specified otherwise
    
    System.out.print(HMAC_function("Mainly cloudy with 40 percent chance of showers", "This is my secret key", 64, "SHA-1"));
    
    }
    
    
    
    
    
    public static byte[] bitwiseXOR(byte[] b1, byte b2){ //XOR two given byte arrays
        
        byte[] bt = new byte[b1.length]; 
        for(int i=0; i< b1.length; i++ ){ //length of b1 is 64 bytes for SHA1
                
                    bt[i] = (byte) (b1[i] ^ b2);
        }
        return bt;
        
    }    
    

public static String HMAC_function(String m, String k, int blockSize_bytes, String hashingMethod) throws IOException, NoSuchAlgorithmException {
    
            //UNLESS SPECIFIED OTHERWISE
            byte opad = 0x5c; 
            byte ipad = 0x36;
            
    
            //message and key
            byte[] message = m.getBytes();
            byte[] key = k.getBytes();        
            

            String HMAC_resultString = "";
            
            //Type of hash we're using
            MessageDigest md = MessageDigest.getInstance(hashingMethod); //type of hash used, blocksize VARIES!
                    
            
            if (key.length > blockSize_bytes) {
            
                byte[] hashedKey = md.digest(key);
                byte[] paddedKey = Arrays.copyOf(hashedKey, blockSize_bytes);
                
                //K' XOR ipad
                byte[] ipad_XOR = bitwiseXOR(paddedKey, ipad);  
                //(K' XOR ipad)||m concatenate ipad_XOR with m
                ByteArrayOutputStream out1 = new ByteArrayOutputStream();
                out1.write(ipad_XOR);
                out1.write(message);
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
                
                return HMAC_resultString = new String(CryptoTools.bytesToHex(outer_Hash));
                
            }
            
            else if (key.length < blockSize_bytes) {
            
                //Creates a new array for the key of the block size with padding for the key consisting of 0s
                byte[] paddedKey = Arrays.copyOf(key, blockSize_bytes);    
                
                //H((K' XOR ipad)||m)
                byte[] ipad_XOR = bitwiseXOR(paddedKey, ipad);        
                //Concatenate ipad_XOR with m
                ByteArrayOutputStream out1 = new ByteArrayOutputStream();
                out1.write(ipad_XOR);
                out1.write(message);
                byte[] inner_Hash = md.digest(out1.toByteArray()); 
                
                
                //H( (K' XOR opad) || H((K' XOR ipad)||m) )
                byte[] opad_XOR = bitwiseXOR(paddedKey, opad);
                ByteArrayOutputStream out2 = new ByteArrayOutputStream();
                out2.write(opad_XOR);
                out2.write(inner_Hash);
                byte[] outer_Hash = md.digest(out2.toByteArray());
    
                return HMAC_resultString = new String(CryptoTools.bytesToHex(outer_Hash));
            }
   
            return HMAC_resultString;



}    


}


//end of class
    
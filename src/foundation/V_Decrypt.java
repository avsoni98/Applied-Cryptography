package foundation;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import util.CryptoTools;

//requires you to know the KEY from its key length
public class V_Decrypt {

	static String decrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

	
	
	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/QQ1V.txt");
		String cipherString = new String(ct, StandardCharsets.UTF_8); //convert ciphertext file to bytes then to string
		String keyString = "HANNAH"; //input the keystring
		String answerString = decrypt(cipherString, keyString);
		System.out.print(answerString); //output as a string in the console
		
		//byte[] b = answerString.getBytes();
		//CryptoTools.bytesToFile(b, "data/check.txt");
	}
	
}

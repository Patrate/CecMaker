package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Communicator {
	private static final String folder = "profiles";
	
	public static void saveProfile(String key, String data) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String fname = hash(key) + ".json";
		writeJson(folder + "/" + fname, data);
	}
	
	public static String loadProfile(String key) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String fname = hash(key) + ".json";
		return readJson(folder + "/" + fname);
	}
	
	public static File getDossier(String key) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String fname = hash(key) + ".json";
		Dossier dossier = Dossier.fromJson(readJson(folder + "/" + fname));
		return new File(FileMaker.buildDossier(dossier));
	}
	
	public static String hash(String data) throws NoSuchAlgorithmException, InvalidKeySpecException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(data.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}
	
	public static String readJson(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String data = "";
		String line = reader.readLine();
		while (line != null){
		    data +=line;
		    line = reader.readLine();
		}
		reader.close();
		return data;
	}
	
	public static void writeJson(String path, String data) throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
	    writer.write(data);
	    writer.close();
	}
}

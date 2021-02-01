package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Communicator {
	private static final byte[] salt = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
		KeySpec spec = new PBEKeySpec("data".toCharArray(), salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(hash);
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

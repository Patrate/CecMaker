package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletResponse;

import spark.Request;
import spark.Response;

public class REST {

	private static String auth(Request request) throws MissingParamException {
		String name = request.queryParams("name");
		String key = request.queryParams("key");
		if (name == null || key == null) {
			throw new MissingParamException();
		}
		return name + key;
	}

	/**
	 * Charger le dossier
	 */
	public static String getUser(Request request, Response response) {
		String key;
		try {
			key = auth(request);
		} catch (MissingParamException e) {
			e.printStackTrace();
			response.status(500);
			response.body("Error: " + e.getMessage());
			return response.body();
		}

		String json;
		try {
			json = Communicator.loadProfile(key);
			if (json != null) {
				response.status(200);
				response.type("application/json");
				response.body(json);
			} else {
				response.status(404);
				response.body("Introuvable");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.status(500);
			response.body("Error: " + e.getMessage());
			return response.body();
		}
		return response.body();
	}

	/**
	 * Sauvegarder le dossier
	 */
	public static String saveUser(Request request, Response response) {
		String key;
		try {
			key = auth(request);
		} catch (MissingParamException e) {
			e.printStackTrace();
			response.status(500);
			response.body("Error: " + e.getMessage());
			return response.body();
		}

		String json = request.body();
		System.out.println(json);
		try {
			Communicator.saveProfile(key, json);
			response.status(200);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
			e.printStackTrace();
			response.status(500);
			response.body("Error: " + e.getMessage());
			return response.body();
		}
		return response.body();
	}

	/**
	 * Générer le dossier
	 */
	public static HttpServletResponse getDossier(Request request, Response response) {
		String key;
		try {
			key = auth(request);
		} catch (MissingParamException e) {
			e.printStackTrace();
			response.status(500);
			response.body("Error: " + e.getMessage());
			return null;
		}

		String json = request.body();
		if(json == null || json.isEmpty()) {
			response.status(500);
			return null;
		}
		try {
			Communicator.saveProfile(key, json);
			String path = FileMaker.buildDossier(Dossier.fromJson(json));

			byte[] data = null;
			data = Files.readAllBytes(Paths.get(path));
			HttpServletResponse raw = response.raw();
			response.header("Content-Disposition", "attachment; filename=dossier.docx");
			response.type("application/force-download");
			response.status(200);
			raw.getOutputStream().write(data);
			raw.getOutputStream().flush();
			raw.getOutputStream().close();
			return raw;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
			response.status(500);
			e.printStackTrace();
		}
		return null;
	}
}

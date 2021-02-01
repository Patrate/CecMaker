package main;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

	public static void main(String[] args) {
        /*after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });*/
		
		get("/", (request, response) -> {
			System.out.println("Loading user");
			return REST.getUser(request, response);
		});
		post("/", (request, response) -> {
			System.out.println("Saving user");
			return REST.saveUser(request, response);
		});
		post("/download", (request, response) -> {
			System.out.println("Downloading dossier");
			return REST.getDossier(request, response);
		});
		// TODO clean régulièrement des docx générés
	}

}

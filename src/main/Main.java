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
			System.out.println("GET /");
			return REST.getUser(request, response);
		});
		post("/", (request, response) -> {
			System.out.println("POST /");
			return REST.saveUser(request, response);
		});
		post("/download", (request, response) -> {
			System.out.println("GET /download");
			return REST.getDossier(request, response);
		});
		// TODO clean régulièrement des docx générés
	}

}

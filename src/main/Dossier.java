package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Dossier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1991442006268631859L;
	
	private boolean changementPrenom;
	
	private String tribunal_ville; // TGI_VILLE
	private String tribunal_addresse; //TGI_ADDR
	
	private String nom_EC; //NOM_EC
	private String prenom_EC; //PRENOM_EC
	private Gender genre_EC; //MrMme_EC MascFem_EC GENRE_EC
	private String prenom_reel; //PRENOM_REEL
	private Gender genre_reel; //MrMme_REEL MascFem_REEL IlElle_REEL MascuFemu_REEL GENRE_REEL et ISFEM
	private String dob; //DOB
	private String pob; //POB
	
	private String situation; //SITUATION_JOB
	private String situation_familiale; //SITUATION_FAM
	private String adresse; //ADDR
	private String telephone; //PHONE
	
	private String parcours; // 1 page minimum, 2 pages maximum PARCOURS
	private String reconnaissance_sociale; // Environ 1 page RECO_SOCIALE
	
	private String lieu; //POW
	private String date; //DOW
	
	private List<String> pieces;
	
	
	
	public Dossier() {
		pieces = new ArrayList<String>();
		pieces.add("Copie intégrale de l'acte de naissance de [PRENOM_EC] [NOM_EC]");
		pieces.add("Carte d'identité de [PRENOM_EC] [NOM_EC]");
		pieces.add("Consentement libre et éclairé pour la modification de l'acte de naissance");
		pieces.add("Justificatif de domicile");
	}
	
	public static Dossier fromJson(String json) {
		Gson g = new Gson();

		Dossier dossier = g.fromJson(json, Dossier.class);
		
		return dossier;
	}
	
	public static String toJson(Dossier dossier) {
		Gson g = new Gson();

		String json = g.toJson(dossier);
		
		return json;
	}
	
	public static Dossier example() {
		Dossier retour = new Dossier();
		
		retour.changementPrenom = false;
		retour.tribunal_ville = "Lille";
		retour.tribunal_addresse = "13  Avenue du Peuple Belge, 59800 Lille";
		retour.nom_EC = "Cousin";
		retour.prenom_EC = "Alice Sérana Hope";
		retour.genre_EC = Gender.MASC;
		retour.prenom_reel = "Alice Serana Hope Chloe";
		retour.genre_reel = Gender.FEM;
		retour.dob = "02/07/1998";
		retour.pob = "Seclin";
		retour.situation = "Sans emploi";
		retour.situation_familiale = "Célibataire";
		retour.adresse = "102 rue des Stations, Bât D2 Apt 242, 59800 Lille";
		retour.telephone = "06.67.16.21.17";
		retour.lieu = "Lille";
		retour.date = "26/01/2021";
		retour.parcours = "LOREM IPSUM";
		retour.reconnaissance_sociale = "LORUM IPSEM";
		
		return retour;
	}

	public boolean isChangementPrenom() {
		return changementPrenom;
	}

	public void setChangementPrenom(boolean changementPrenom) {
		this.changementPrenom = changementPrenom;
	}

	public String getTribunal_ville() {
		return tribunal_ville;
	}

	public void setTribunal_ville(String tribunal_ville) {
		this.tribunal_ville = tribunal_ville;
	}

	public String getTribunal_addresse() {
		return tribunal_addresse;
	}

	public void setTribunal_addresse(String tribunal_addresse) {
		this.tribunal_addresse = tribunal_addresse;
	}

	public String getNom_EC() {
		return nom_EC;
	}

	public void setNom_EC(String nom_EC) {
		this.nom_EC = nom_EC;
	}

	public String getPrenom_EC() {
		return prenom_EC;
	}

	public void setPrenom_EC(String prenom_EC) {
		this.prenom_EC = prenom_EC;
	}

	public Gender getGenre_EC() {
		return genre_EC;
	}

	public void setGenre_EC(Gender genre_EC) {
		this.genre_EC = genre_EC;
	}

	public String getPrenom_reel() {
		return prenom_reel;
	}

	public void setPrenom_reel(String prenom_reel) {
		this.prenom_reel = prenom_reel;
	}

	public Gender getGenre_reel() {
		return genre_reel;
	}

	public void setGenre_reel(Gender genre_reel) {
		this.genre_reel = genre_reel;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getSituation_familiale() {
		return situation_familiale;
	}

	public void setSituation_familiale(String situation_familiale) {
		this.situation_familiale = situation_familiale;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getParcours() {
		return parcours;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}

	public String getReconnaissance_sociale() {
		return reconnaissance_sociale;
	}

	public void setReconnaissance_sociale(String reconnaissance_sociale) {
		this.reconnaissance_sociale = reconnaissance_sociale;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getPieces() {
		return pieces;
	}

	public void setPieces(List<String> pieces) {
		this.pieces = pieces;
	}
}

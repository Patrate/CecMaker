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
	
	private String tribunal_adresse; //TGI_ADDR
	private String tribunal_adresse_complement; //TGI_ADDR
	private String tribunal_adresse_ville; //TGI_ADDR
	private String tribunal_adresse_cp; //TGI_ADDR
	
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
	private String adresse_complement; //ADDR
	private String adresse_ville; //ADDR
	private String adresse_cp; //ADDR
	private String telephone; //PHONE
	
	private String parcours; // 1 page minimum, 2 pages maximum PARCOURS
	private String reconnaissance_sociale; // Environ 1 page RECO_SOCIALE
	
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
		retour.tribunal_adresse = "13  Avenue du Peuple Belge";
		retour.tribunal_adresse_complement = "";
		retour.tribunal_adresse_ville = "Lille";
		retour.tribunal_adresse_cp = "59800";
		retour.nom_EC = "Cousin";
		retour.prenom_EC = "Alice Sérana Hope";
		retour.genre_EC = Gender.MASC;
		retour.prenom_reel = "Alice Serana Hope Chloe";
		retour.genre_reel = Gender.FEM;
		retour.dob = "02/07/1998";
		retour.pob = "Seclin";
		retour.situation = "Sans emploi";
		retour.situation_familiale = "Célibataire";
		retour.adresse = "102 rue des Stations";
		retour.adresse_complement = "Bâtiment D2 Appartement 242";
		retour.adresse_ville = "Lille";
		retour.adresse_cp = "59800";
		retour.telephone = "06.67.16.21.17";
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

	public String getTribunal_adresse() {
		return tribunal_adresse;
	}

	public void setTribunal_adresse(String tribunal_adresse) {
		this.tribunal_adresse = tribunal_adresse;
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

	public String getadresse() {
		return adresse;
	}

	public void setadresse(String adresse) {
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

	public String getTribunal_adresse_complement() {
		return tribunal_adresse_complement;
	}

	public void setTribunal_adresse_complement(String tribunal_adresse_complement) {
		this.tribunal_adresse_complement = tribunal_adresse_complement;
	}

	public String getTribunal_adresse_ville() {
		return tribunal_adresse_ville;
	}

	public void setTribunal_adresse_ville(String tribunal_adresse_ville) {
		this.tribunal_adresse_ville = tribunal_adresse_ville;
	}

	public String getTribunal_adresse_cp() {
		return tribunal_adresse_cp;
	}

	public void setTribunal_adresse_cp(String tribunal_adresse_cp) {
		this.tribunal_adresse_cp = tribunal_adresse_cp;
	}

	public String getadresse_complement() {
		return adresse_complement;
	}

	public void setadresse_complement(String adresse_complement) {
		this.adresse_complement = adresse_complement;
	}

	public String getadresse_ville() {
		return adresse_ville;
	}

	public void setadresse_ville(String adresse_ville) {
		this.adresse_ville = adresse_ville;
	}

	public String getadresse_cp() {
		return adresse_cp;
	}

	public void setadresse_cp(String adresse_cp) {
		this.adresse_cp = adresse_cp;
	}
	
	public String getFulladress() {
		return this.adresse + ((this.adresse_complement != null && !this.adresse_complement.isEmpty())?", " + this.adresse_complement:"")
			+ ", " + this.adresse_cp + " " + this.adresse_ville;
	}
	
	public String getFullTJadress() {
		return this.tribunal_adresse + ((this.tribunal_adresse_complement != null && !this.tribunal_adresse_complement.isEmpty())?", " + this.tribunal_adresse_complement:"")
			+ ", " + this.tribunal_adresse_cp + " " + this.tribunal_adresse_ville;
	}
}

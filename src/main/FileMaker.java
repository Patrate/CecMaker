package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class FileMaker {

	public static final String FROM_NAME = "template/template-requete.docx";
	public static final String FROM_NO_NAME = "template/template-requete-no-name.docx";
	public static final String TO = "results";
	
	public static void main(String[] args) { 
		/*try {
			Communicator.writeJson("results/test2.json", Dossier.toJson(Dossier.example()));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		try {
			buildDossier(Dossier.example());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String buildDossier(Dossier dossier) throws IOException {
		String filename = (dossier.isChangementPrenom())?FROM_NAME:FROM_NO_NAME;
		try(FileInputStream fis = new FileInputStream(filename)){
			XWPFDocument doc = new XWPFDocument(OPCPackage.open(fis));
			
			int i = 1;
			for(String piece : dossier.getPieces()) {
				XWPFParagraph newParagraph = doc.createParagraph();
				newParagraph.setSpacingAfter(0);
				XWPFRun run = newParagraph.createRun();
				run.setText("      " + i + ".   " + piece);
				run.setFontFamily("Palatino Linotype");
				run.setFontSize(12);
				i += 1;
			}
			
			for (XWPFParagraph p : doc.getParagraphs()) {
				List<XWPFRun> runs = p.getRuns();
				if (runs != null) {
					for (XWPFRun r : runs) {
						String text = r.getText(0);
						if(text != null) {
							text = replaceAll(text, dossier);
							r.setText(text, 0);
						}
					}
				}
			}
			for (XWPFTable tbl : doc.getTables()) {
				for (XWPFTableRow row : tbl.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								if(text != null) {
									text = replaceAll(text, dossier);
									r.setText(text, 0);
								}
							}
						}
					}
				}
			}
			
			String filePath = TO + "/" + dossier.getPrenom_reel().replaceAll(" ", "") + "_" + dossier.getNom_EC().replaceAll(" ", "") + ".docx";
			doc.write(new FileOutputStream(filePath));
			doc.close();
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ERROR";
	}
	
	private static String replaceAll(String text, Dossier dossier) {
		String[] wordsList = new String[] {"[TGI_VILLE]", "[TGI_ADDR]", "[NOM_EC]", "[PRENOM_EC]", "[MRMME_EC]", "[MrMme_EC]", "[MascFem_EC]", "[PRENOM_REEL]", "[MrMme_REEL]", "[MascFem_REEL]", "[IlElle_REEL]",
				"[MascuFemu_REEL]", "[!ISFEM]", "[ISFEM]", "[DOB]", "[POB]", "[SITUATION_JOB]", "[SITUATION_FAM]", "[ADDR]", "[PHONE]", "[PARCOURS]", "[SOCIALE]", "[POW]", "[DOW]"};
		String[] replaceList = new String[] {
				dossier.getTribunal_ville(), dossier.getTribunal_addresse(), dossier.getNom_EC(), dossier.getPrenom_EC(), (dossier.getGenre_EC() == Gender.MASC)?"Monsieur":"Madame", (dossier.getGenre_EC() == Gender.MASC)?"monsieur":"madame",
						(dossier.getGenre_EC() == Gender.MASC)?"masculin":"féminin", dossier.getPrenom_reel(), (dossier.getGenre_reel() == Gender.MASC)?"monsieur":"madame",
						(dossier.getGenre_reel() == Gender.MASC)?"masculin":"féminin", (dossier.getGenre_reel() == Gender.MASC)?"il":"elle",
						(dossier.getGenre_reel() == Gender.MASC)?"masculine":"féminine",
						(dossier.getGenre_reel() == Gender.MASC)?"e":"", (dossier.getGenre_reel() == Gender.MASC)?"":"e",
						dossier.getDob(), dossier.getPob(), dossier.getSituation(), dossier.getSituation_familiale(), dossier.getAdresse(), dossier.getTelephone(),
						dossier.getParcours(), dossier.getReconnaissance_sociale(), dossier.getLieu(), dossier.getDate()
		};
		
		for(int i = 0; i < wordsList.length; i++) {
			text = text.replace(wordsList[i], replaceList[i]);
		}
		
		return text;
	}

	

}

API_url = "//mon-cec.emmathie.fr/api/"
//API_url = "https://mon-cec.emmathie.fr/api/"
//API_url = "localhost:4567/"
var pjNumber = 0;
var pjList = [];
var pjDivList = [];

function addPJ() {
  var container = document.getElementById("pjList");
  var theSuperDiv = document.createElement("div");
  var theDiv = document.createElement("div");
  theDiv.appendChild(document.createTextNode("" + (5 + pjNumber) + ": "));
  var theInput = document.createElement("input");
  theInput.type = "text";
  theDiv.appendChild(theInput)
  pjList.push(theInput);
  pjDivList.push(theSuperDiv);
  pjNumber += 1

  container.appendChild(theDiv);
  container.appendChild(document.createElement("br"));

  container.appendChild(theSuperDiv);
  return theInput;
}

function removePJ(index) {
  var container = document.getElementById("pjList");
  container.removeChild(pjDivList[index])
  pjList.splice(index, 1);
  pjDivList.splice(index, 1);
}

function updateChangementPrenom() {
  if (document.getElementById("changementPrenom").checked) {
    document.getElementById("div_prenom_EC").style.visibility = "visible";
  } else {
    document.getElementById("div_prenom_EC").style.visibility = "hidden";
    document.getElementById("prenom_EC").value = document.getElementById("prenom_reel").value;
    updatePJ();
  }
}
updateChangementPrenom()

document.getElementById("changementPrenom").addEventListener("change",function () {
  updateChangementPrenom()
});

document.getElementById("prenom_reel").addEventListener("change",function () {
  if (!document.getElementById("changementPrenom").checked) {
    document.getElementById("prenom_EC").value = document.getElementById("prenom_reel").value;
    updatePJ();
  }
});

document.getElementById("nom_EC").addEventListener("change",function () {
  updatePJ();
});

document.getElementById("prenom_EC").addEventListener("change",function () {
  updatePJ();
});

function updatePJ() {
  var nom = document.getElementById("nom_EC").value;
  var prenom = document.getElementById("prenom_EC").value;
  document.getElementById("PJ1").value = "Copie intégrale de l'acte de naissance de " + prenom + " " + nom;
  document.getElementById("PJ2").value = "Carte d'identité de " + prenom + " " + nom;
}

function getCredentials() {
    var credentials = {
      "pseudo": document.getElementById("pseudo").value,
      "pwd": document.getElementById("pwd").value
    }
    if(!credentials["pseudo"] || !credentials["pwd"]){
      console.log("Choisissez un pseudo et un mot de passe");
      return;
    } else {
      return credentials;
    }
}

function getDossier(){
    var prenom_reel = document.getElementById("prenom_reel").value;
    var changementPrenom = document.getElementById("changementPrenom").checked;
    var prenom_EC = "";
    if (changementPrenom){
       prenom_EC = document.getElementById("prenom_EC").value;
    } else {
      prenom_EC = prenom_reel;
    }

    var genre_reel;
    var genre_EC;
    if(document.getElementById("MASC").checked) {
      genre_reel = "MASC";
      genre_EC = "FEM";
    } else if(document.getElementById("FEM").checked) {
      genre_reel = "FEM";
      genre_EC = "MASC";
    } else {
      console.log("Vous avez oublié de choisir un genre");
      return null;
    }

    var dossier = {
      "prenom_reel": prenom_reel,
      "changementPrenom": changementPrenom,
      "prenom_EC": prenom_EC,
      "genre_reel": genre_reel,
      "genre_EC": genre_EC,
      "nom_EC": document.getElementById("nom_EC").value,
      "dob": document.getElementById("dob").value,
      "pob": document.getElementById("pob").value,
      "situation": document.getElementById("situation").value,
      "situation_familiale": document.getElementById("situation_familiale").value,
      "adresse": document.getElementById("adresse").value,
      "adresse_complement": document.getElementById("adresse_complement").value,
      "adresse_ville": document.getElementById("adresse_ville").value,
      "adresse_cp": document.getElementById("adresse_cp").value,
      "telephone": document.getElementById("telephone").value,
      "date": new Date().getFullYear() + "-" + new Date().getMonth() + "-" + new Date().getDay(),
      "tribunal_adresse": document.getElementById("tribunal_adresse").value,
      "tribunal_adresse_complement": document.getElementById("tribunal_adresse_complement").value,
      "tribunal_adresse_ville": document.getElementById("tribunal_adresse_ville").value,
      "tribunal_adresse_cp": document.getElementById("tribunal_adresse_cp").value,
      "parcours": document.getElementById("parcours").value,
      "reconnaissance_sociale": document.getElementById("reconnaissance_sociale").value,
      "pieces": []
    }

    var i;
    for (i = 0; i < pjList.length; ++i) {
      if(pjList[i].value) {
        dossier["pieces"].push(pjList[i].value)
      }
    }

    return dossier;
}

function fillDossier(dossier){
    document.getElementById("prenom_reel").value = dossier["prenom_reel"]
    document.getElementById("changementPrenom").checked = dossier["changementPrenom"]
    document.getElementById("prenom_EC").value = dossier["prenom_EC"]
    document.getElementById("MASC").checked = dossier["genre_reel"] == "MASC"
    document.getElementById("FEM").checked = dossier["genre_reel"] == "FEM"
    document.getElementById("nom_EC").value = dossier["nom_EC"]
    document.getElementById("dob").value = dossier["dob"]
    document.getElementById("pob").value = dossier["pob"]
    document.getElementById("situation").value = dossier["situation"]
    document.getElementById("situation_familiale").value = dossier["situation_familiale"]
    document.getElementById("adresse").value = dossier["adresse"]
    document.getElementById("adresse_complement").value = dossier["adresse_complement"]
    document.getElementById("adresse_ville").value = dossier["adresse_ville"]
    document.getElementById("adresse_cp").value = dossier["adresse_cp"]
    document.getElementById("telephone").value = dossier["telephone"]
    document.getElementById("tribunal_adresse").value = dossier["tribunal_adresse"]
    document.getElementById("tribunal_adresse_complement").value = dossier["tribunal_adresse_complement"]
    document.getElementById("tribunal_adresse_ville").value = dossier["tribunal_adresse_ville"]
    document.getElementById("tribunal_adresse_cp").value = dossier["tribunal_adresse_cp"]
    document.getElementById("parcours").value = dossier["parcours"]
    document.getElementById("reconnaissance_sociale").value = dossier["reconnaissance_sociale"]
    var i;
    for(i = 3; i < dossier["pieces"].length; ++i) {
      var newPJ = addPJ();
      newPJ.value = dossier["pieces"][i]
    }
    updatePJ();
}

function save() {
  cred = getCredentials();
  dossier = getDossier();
  if(cred == null || dossier == null){
    return;
  }
  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
       if (this.readyState == 4) {
           if (this.status == 200) {
             alert("Sauvegarde ok");
           } else {
             alert("Erreur lors de la sauvegarde");
             console.log(this.responseText);
           }
           document.getElementById("saveButton").disabled = false
           document.getElementById("saveButton").value = "Sauvegarder le dossier"
       }
  };
  xhttp.open("POST", API_url + '?name=' + cred["pseudo"] + '&key=' + cred["pwd"], true);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.send(JSON.stringify(dossier));
}

function load() {
  cred = getCredentials();
  if(cred == null){
    return;
  }
  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
       if (this.readyState == 4) {
          if (this.status == 200) {
           json = this.responseText;
           fillDossier(JSON.parse(json))
         } else {
           alert("Erreur lors du chargement")
         }

         document.getElementById("loadButton").disabled = false
         document.getElementById("loadButton").value = "Charger le dossier"
       }
  };
  xhttp.open("GET", API_url + '?name=' + cred["pseudo"] + '&key=' + cred["pwd"], true);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.send()
}

function requestDossier() {
  cred = getCredentials();
  dossier = getDossier();
  if(cred == null || dossier == null){
    return;
  }
  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
       if (this.readyState == 4) {
         if (this.status == 200) {
            // Trick for making downloadable link
            a = document.createElement('a');
            a.href = window.URL.createObjectURL(xhttp.response);
            // Give filename you wish to download
            a.download = "dossier.docx";
            a.style.display = 'none';
            document.body.appendChild(a);
            a.click();
            a.delete
          }
          document.getElementById("downloadDossierButton").disabled = false
          document.getElementById("downloadDossierButton").value = "Télécharger le dossier"
       }
  };
  xhttp.open("POST", API_url + 'download?name=' + cred["pseudo"] + '&key=' + cred["pwd"], true);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.responseType = 'blob';
  xhttp.send(JSON.stringify(dossier));
}

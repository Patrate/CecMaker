//API_url = "//mon-cec.emmathie.fr/api/"
API_url = "https://mon-cec.emmathie.fr/api/"
//API_url = "localhost:4567/"
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
       prenom_EC = document.getElementById("pseudo").value;
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

    var addresse = document.getElementById("addresse").value;
    if(document.getElementById("addresse_complement").value) {
      addresse += ", " + document.getElementById("addresse_complement").value
    }
    addresse += ", " + document.getElementById("addresse_cp").value + " " + document.getElementById("addresse_ville").value;


    var lieu = document.getElementById("addresse_ville").value;

    var addresse_tribunal = document.getElementById("tribunal_addresse").value;
    if(document.getElementById("tribunal_addresse_complement").value) {
      addresse_tribunal += ", " + document.getElementById("tribunal_addresse_complement").value
    }
    addresse_tribunal += ", " + document.getElementById("tribunal_addresse_cp").value + " " + document.getElementById("tribunal_addresse_ville").value;

    var tribunal_ville = document.getElementById("tribunal_addresse_ville").value;

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
      "addresse": addresse,
      "telephone": document.getElementById("telephone").value,
      "lieu": lieu,
      "date": document.getElementById("date").value,
      "tribunal_addresse": addresse_tribunal,
      "tribunal_ville": tribunal_ville,
      "parcours": document.getElementById("parcours").value,
      "reconnaissance_sociale": document.getElementById("reconnaissance_sociale").value,
      "pieces": []
    }
    var i;

    for (i = 1; i < 5; i++) {
      dossier["pieces"].push(document.getElementById("PJ" + i).value)
    }

    return dossier;
}

function sendRequest(type, urlExt, dossier){
  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
       if (this.readyState == 4 && this.status == 200) {
           console.log(this.responseText);
       }
  };
  xhttp.open(type, API_url + urlExt, true);
  xhttp.setRequestHeader("Content-type", "application/json");
  if(dossier != null){
    xhttp.send(JSON.stringify(dossier));
  } else {
    xhttp.send()
  }
}

function save() {
  cred = getCredentials();
  dossier = getDossier();
  if(cred == null || dossier == null){
    return;
  }
  sendRequest("POST", '?name=' + cred["pseudo"] + '&key=' + cred["pwd"], dossier)
}

function load() {
  cred = getCredentials();
  if(cred == null){
    return;
  }
  sendRequest("GET", '?name=' + cred["pseudo"] + '&key=' + cred["pwd"], null)
}

function requestDossier() {
  cred = getCredentials();
  dossier = getDossier();
  if(cred == null || dossier == null){
    return;
  }
  sendRequest("GET", 'download?name="' + cred["pseudo"] + '"&key="' + cred["pwd"] +'"', dossier)
}

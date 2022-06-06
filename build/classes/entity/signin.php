<?php session_start(); ?>

<?php 
                            //page qui vérifie la validité de l'inscription de l'utilisateur

    include('mySQL.php'); //on récupère la connexion
    include('Client.php'); //on récupère tous les clients

    $inputMail = $_POST['mail'];
    $inputName = $_POST['nom'];
    $inputFirstname = $_POST['prenom'];
    $inputPassword = $_POST['psw'];

    if (in_array($inputMail, array_column($users,'mail'))){ //si l'adresse mail existe déjà en BD, l'utilisateur est déjà inscrit
        header('Location: http://localhost/inscription.php?errorMail='.$inputMail);
    }
    else{
        $sqlQuery = 'INSERT INTO client(nom, mail, mdp, prenom) VALUES (:nom, :mail, :mdp, :prenom)';

        $insertUser = $mySqlClient->prepare($sqlQuery);
        $insertUser->execute([
            'nom' => $inputName,
            'mail' => $inputMail,
            'mdp' => $inputPassword,
            'prenom' => $inputFirstname,
        ]);
        $_SESSION['LOGGED_USER']= $inputMail;
        header('Location: http://localhost/connection.php');
    }
    

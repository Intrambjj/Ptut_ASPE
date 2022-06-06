<?php session_start(); ?>

<!-- on récupère la connexion à la base de donnée -->
<?php include('src\entity\mySQL.php');?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/inscription.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"/>
    <title>Page d'inscription</title>
</head>
<body>
    <!-- début inclusion du header -->
    <?php include('header.php'); ?> 
    <!-- fin inclusion du header -->
    <div id="container">
        <?php if (isset($_GET['errorMail'])){
            echo 'Un compte existe déjà avec cette adresse mail : ' . $_GET['errorMail'];
        }
        ?>
        <form action="src\entity\signin.php" method="POST">
            <h1>Inscription</h1>

            <label><b>Nom</b></label>
            <input type="text" id="input_nom" placeholder="Entrer votre nom" name="nom" required>

            <label><b>Prénom</b></label>
            <input type="text" id="input_prenom" placeholder="Entrer votre prénom" name="prenom" required>
                    
            <label><b>Adresse mail</b></label>
            <input type="email" id="input_mail" placeholder="You@exemple.com" name="mail" required>

            <label><b>Mot de passe</b></label>
            <input type="password" id="input_password" placeholder="Entrer le mot de passe" name="psw" required>

            <input type="submit" id='submit' value='LOGIN' >
            </form>
    </div>  

</body>
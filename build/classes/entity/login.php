<?php 

if (isset($_POST['mail']) && isset($_POST['psw'])){ //si les variables existent

    include('src\entity\Client.php'); // on récupère tous les clients

    foreach($users as $user){

         // on verifie que les données saisies soit correctes
        if ($user['mail'] === $_POST['mail'] && $user['mdp'] === $_POST['psw']){
               $_SESSION['LOGGED_USER'] = $user['mail'];
        }
        else { // champs saisis non valide, on demande de recomencer
            $errorMessage = sprintf('les informations envoyées ne permettent pas de vous idenfier : %s / %s', $_POST['mail'], $_POST['psw']);  
        }
    }
} 
?>

<?php if(!isset($_SESSION['LOGGED_USER'])): ?>
    <div id="container">
            <!-- zone de connexion -->

                <!-- on verifie si il n'y a pas eu une erreur dans la saisie des champs -->
            <?php if (isset($errorMessage)): ?>
                <?php echo htmlspecialchars($errorMessage); ?>
            <?php else: ?>
                <form action="connection.php" method="POST">
                    <h1>Connexion</h1>
                    
                    <label><b>Adresse mail</b></label>
                    <input type="email" id="input_mail" placeholder="You@exemple.com" name="mail" required>

                    <label><b>Mot de passe</b></label>
                    <input type="password" id="input_password" placeholder="Entrer le mot de passe" name="psw" required>

                    <input type="submit" id='submit' value='LOGIN' >
                    <p>Nouveau client? <a href=inscription.php>Cliquez ici</a>
                </form>
            <?php endif; ?>
    </div>
<?php else: ?>
    <div id="logSuccess" role="alert">
        <!-- souhaiter la bienvenu à l'utilisateur qui se connecte -->
        <p>Bonjour <strong><?php echo $_SESSION['LOGGED_USER'];?></strong> et bienvenue sur le site !</p>   
    </div>
    <a href="disconnection.php"><span class="logoff">Se déconnecter</span></a>           
<?php endif; ?>
<?php

 // on récupère depuis la connection mySQL du fichier DBConnection.php
 include('mySQL.php');

 $usersStatement = $mySqlClient->prepare("SELECT * FROM client ");
 $usersStatement->execute();
 $users = $usersStatement->fetchAll();
?>
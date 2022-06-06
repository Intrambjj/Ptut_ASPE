<?php 

   // on récupère depuis la connection mySQL du fichier DBConnection.php
    include('mySQL.php');

    // on récupère tout le contenu de la table product
    

    $produitsStatement = $mySqlClient->prepare("SELECT * FROM product");
    $produitsStatement->execute();
    $produits = $produitsStatement->fetchAll();

?>
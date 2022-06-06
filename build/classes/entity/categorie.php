<?php 
    include('mySQL.php');

    // on veut récuperer toutes les catégories 
    $categoryQuery = 'SELECT * FROM categorie';

    $categoryStatement = $mySqlClient->prepare($categoryQuery);
    $categoryStatement->execute();
    $category = $categoryStatement->fetchAll();
?>
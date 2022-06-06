<?php 
try{
    $mySqlClient = new PDO(
        'mysql:host=localhost;dbname=projet_tut;charset=utf8',
        'root',
        '',
        [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION],
     );
}
catch(Exception $e){
    die('Erreur : ' . $e->getMessage());
}

?>
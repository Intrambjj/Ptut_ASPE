<?php session_start(); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/connection.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"/>
    <title>Page de connection</title>
</head>
<body>
    <!-- début inclusion du header -->
    <?php include('header.php'); ?> 
    <!-- fin inclusion du header -->

    <!-- inclusion du formulaire de connection -->
    <?php include('src\entity\login.php'); ?> 
    <!-- fin inclusion du formulaire de connection -->

    
</body>
</html>
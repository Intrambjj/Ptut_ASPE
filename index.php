<?php session_start() ?>
<?php include('src\entity\mySQL.php');?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"/>
    <title>Page principal</title>
</head>
<body>
                
     <!-- début inclusion du header -->
     <?php include('header.php'); ?> 
    <!-- fin inclusion du header -->
    
    <section class="hero">
        <img src="image/logo.png" alt="logo2" class="hero-logo"/>
        <h2 class="hero-title">SkateShop</h2>
    </section>

    <section class="collection-container">
        <?php foreach($category as $item): ?>
            <?php if(isset($item['image'])): ?>
                <a href="resultat.php?categorie=<?php echo $item['nom'] ?>" class="collection">
                    <img src="<?php echo $item['image']?>" alt="collection"/>
                </a>   
            <?php endif; ?>
        <?php endforeach; ?>    
    </section>

</body>
</html>
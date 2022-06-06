<?php session_start();?>
<?php include('src\entity\produit.php');?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/produit.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
    <title>Les produits</title>
</head>
<body>
    <!-- début inclusion du header -->
    <?php include('header.php'); ?> 
    <!-- fin inclusion du header -->
    <?php if(isset($_SESSION['LOGGED_USER'])): ?>
        <div id="logSuccess" role="alert">
            <!-- souhaiter la bienvenu à l'utilisateur qui se connecte -->
            <p>Bonjour <strong><?php echo $_SESSION['LOGGED_USER'];?></strong> et bienvenue sur le site !</p>   
        </div>
        
    <?php endif; ?>

    <?php 
    $idParam =  htmlspecialchars($_GET['id']); // on se protège d'un utilisateur qui saisi un script comme paramètre
    // on vérifie que le paramètre est connu en BD et si le paramètre est non nul pour afficher la page
    if(isset($idParam) && in_array($idParam, array_column($produits, 'id'))): ?>
        <!-- on récupère le produit concerné grâce à l'ID -->
        <?php 
            $itemQuery = 'SELECT * from product where id=:id';
            $itemStatement = $mySqlClient->prepare($itemQuery);
            $itemStatement->execute(['id' => $idParam]);
            $item = $itemStatement->fetch();
        ?>
        <section class="product-detail">
            <div class="image-slider">
                <img class="image1" src="<?php echo $item['image1']?>" alt="image principale">
                <span class="product-images">
                    <?php if($item['image2']!==""):?>
                        <img src="<?php echo $item['image2']?>" alt="image secondaire">
                    <?php endif; ?>
                    <?php if($item['image3']!==""):?>
                        <img src="<?php echo $item['image3']?>" alt="troisième image">
                    <?php endif; ?>
                    </span>
            </div>
            <div class="details">
                <h2 class="product-brand"><?php echo $item['marque']?></h2>
                <p class="product-name"><?php echo $item['nom']?></p>
                <p class="product-price"><?php echo $item['prix']?> $</p>
                <p class="product-quantity">Quantité restante: <?php echo $item['stock']?></p>
                <!-- si l'utilisateur est connecté, en appuyant sur le bouton, on renvoit vers le panier -->
                <?php if(isset($_SESSION['LOGGED_USER'])): ?>
                    <form action="panier.php" method="post">
                <!-- si il n'est pas connecté, il doit d'abord ce connecter, on le renvoit donc vers la page de connection -->
                <?php else: ?>
                    <form action="connection.php" method="post">
                <?php endif; ?>
                        <label for="quantity">Selectionnez votre quantité : </label>
                        <input type="number" id="quantity" name="quantity" min="1" max="<?php echo $item['stock']?>">
                        <input type="hidden" name="id" value=<?php echo $idParam?>/>
                        <button class="btn cart-btn" type="submit">ajouter au panier</button>
                    </form>
            </div>
        </section>
        <section class="description-container">
            <h2 class="title-desc">Description</h2>
            <div class="description"><?php echo $item['description']?></div>
        </section>
    <?php else: ?>
        <h2>URL non valide</h2>
    <?php endif; ?>



</body>
<?php session_start(); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/resultat.css">
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

    <section class="search-result"> 

            <?php if (isset($_POST['search-value'])): ?>
                <?php 
                    $productsStatement = $mySqlClient->prepare("SELECT nom, marque, prix, image1, id FROM product WHERE marque LIKE '%{$_POST['search-value']}%' OR nom LIKE '%{$_POST['search-value']}%' ");
                    $productsStatement->execute();
                    $products = $productsStatement->fetchAll();
                ?>
            <?php elseif (isset($_GET['categorie'])): ?>
                <!-- on s'assure que le paramètre envoyé n'a pas de code script -->
                <?php $categoryParam = htmlspecialchars($_GET['categorie']); ?>
                <!-- on vérifie que la variable contenant la catégorie existe -->
                <h2 class="title-category">Catalogue de nos <?php echo $categoryParam;?></h2>
                <?php
                // on récupère tous les produits qui correspondent à la catégorie. 
                $productsQuery = 'SELECT product.nom, product.marque, product.prix, product.image1, product.id from product, produitcategorie, categorie where categorie.nom=:nom and product.id=produitcategorie.id_produit and categorie.id=produitcategorie.id_categorie';
                $productsStatement = $mySqlClient->prepare($productsQuery);
                $productsStatement->execute(['nom' => $categoryParam,]);
                $products = $productsStatement->fetchAll(); ?>
            <?php else: ?>
                <h2> URL non valide </h2>
            <?php endif; ?> 
                <!-- Bloc contenant tous les produits -->
            <?php if(isset($products)): ?>
                <div class="product-container">
                    <?php foreach ($products as $product): ?>
                        <div class="product-card">
                            <div class="product-image">
                                <img class="image-item" src="<?php echo $product['image1']?>" alt="image principale">
                                <a href="produit-detail.php?id=<?php echo $product['id']?>" class="card-btn">
                                    <span>voir le produit</span>
                                </a>          
                            </div>
                            <div class="product-info">
                                <h2 class="name-product"><?php echo $product['marque']; ?></h2>
                                <p><?php echo $product['nom']; ?></p>
                                <span class="price"><?php echo $product['prix']?> $</span>
                            </div>
                        </div>
                    <?php endforeach; ?>
                </div>   
            <?php endif; ?>       
    </section>

</body>
</html>


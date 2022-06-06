<?php session_start();?>
<?php include('src\entity\mySQL.php')?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/panier.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"/>
    <title>Panier</title>
</head>
<body>
    <!-- début inclusion du header -->
    <?php include('header.php'); ?> 
    <!-- fin inclusion du header -->

    <!-- on vérifie que l'utilisateur soit connecté -->
    <?php if(isset($_SESSION['LOGGED_USER'])): ?>
        
        <div id="logSuccess" role="alert">
            <!-- souhaiter la bienvenu à l'utilisateur qui se connecte -->
            <p>Bonjour <strong><?php echo $_SESSION['LOGGED_USER'];?></strong> et bienvenue sur le site !</p>   
        </div>

        <!-- on récupère l'id item et la quantité que l'utilisateur à passé et on crée un variable session et on ajoute dans le tableau une entrée-->
        <?php if(isset($_POST['quantity']) && isset($_POST['id'])){
            $_SESSION['cartSession'][] = ['id' => $_POST['id'], 'quantity' => $_POST['quantity']];
        }
        ?>
        <!-- il faut maintenant récuperer de la BD tous les produits qui ont le même ID que les ID de SESSION['cartSession] -->
        <?php if (isset($_SESSION['cartSession'])){
            foreach($_SESSION['cartSession'] as $item){
                $sqlQuery = 'SELECT * FROM product where id=:id';
                $productStatement = $mySqlClient->prepare($sqlQuery);
                $productStatement->execute(['id' => $item['id']],);
                $productLine = $productStatement->fetch();
                // j'ai récupéré ma ligne en BD qui correspond au produit, et j'ajoute une clé "quantité" avec la valeur
                $productLine['quantity'] = $item['quantity'];
                $cartProducts[] = $productLine;
                 // le tableau cartProducts contient les articles de la sessions
                 
            }
        }?> 
        <div class="cart-section">
        <div class="product-list">
            <p class="section-heading">Ton panier</p>
            <div class="cart">
                <?php $total_cart = 0; ?>
                <!-- On vérifie que des produits on été ajoutés dans la session, sinon panier vide -->
                <?php if(isset($cartProducts)): ?>
                    <?php foreach($cartProducts as $product): ?>
                        <?php $total_cart = $total_cart + $product['prix']*$product['quantity']; ?>
                        <div class="sm-product">
                            <img src="<?php echo $product['image1']?>" class="sm-product-img" alt="">
                            <div class="sm-text">
                                <p class="sm-product-name"><?php echo $product['marque']?></p>
                                <p class="sm-des"><?php echo $product['nom']?></p>
                            </div>
                            <div class="item-counter">
                                <button class="counter-btn decrement">-</button>
                                <p class="item-count"><?php echo $product['quantity']?></p>
                                <button class="counter-btn increment">+</button>
                            </div>
                            <p class="sm-price"><?php echo $product['prix']*$product['quantity']?> $</p>
                            <form action="panier.php">
                                <button type="submit" class="sm-delete-btn"><img src="image/close.png" alt=""></button>
                            </form>
                        </div>
                    <?php endforeach; ?>

                <!-- On rendre dans le else si le panier est vide -->
                <?php else: ?>
                    <strong> ton panier est vide </strong>
                <?php endif; ?>
                
                <form action="panier.php" method="post">
                    <button type="submit" class="vider-panier" name="delete" value=1>Vider le panier</button>
                </form>
            </div>
        </div>
        <div class="checkout-section">
            <p class="text">Total</p>
            <h1 class="bill"><?php echo $total_cart ?> $</h1>
            <form action="test.html">
                <button class="checkout-btn">Acheter</button>
            </form>   
        </div>
        <!-- si il n'est pas connecté on lui demande de se connecter -->
    <?php else: ?>
        <div class="panier-inf">
            <p>Pour accéder à votre panier, veuillez vous connecter. <a href="connection.php">cliquez ici</a>
        </div>
    <?php endif; ?>
    <script src="js\panier.js"></script> 
</body>
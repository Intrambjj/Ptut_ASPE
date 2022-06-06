<nav>
            <div class="navbar">
                <a href="index.php" class="navbar-ref"><img src="image/logo.png"class="navbar-logo" alt="logo"></a>
                <div class="navbar-content">
                    <form action="resultat.php" method="post" class="search-container">
                        <input type="text" placeholder="Search..." class="search-box" name="search-value"/>
                        <button class="search-button" type="submit"><i class="fas fa-search"></i></button> 
                    </form>
                    <a href="connection.php"><i class="fas fa-user"></i></a>
                    <a href="panier.php"><i class="fas fa-wallet"></i></a> 
                </div>
            </div>
            <ul class="links-container">
                <!-- inclusion de 'categorie.php' contenant mes catégorie en BD -->
                <?php include('src\entity\categorie.php'); ?>
                <!-- fin inclusion de 'categorie.php' -->
                <?php foreach($category as $item): ?>
                    <li class="link-item"><a href="resultat.php?categorie=<?php echo $item['nom'] ?>" class="link"><?php echo $item['nom']; ?></a></li>
                <?php endforeach; ?>       
            </ul>           
</nav>
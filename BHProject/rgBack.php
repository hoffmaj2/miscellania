Loading... <?php
require 'db.php';
?>
<?php
    $temp = $pdo -> query("INSERT INTO BH_User (username, password, email, name, telephone, street, zip, city, state, country) VALUES 
    ('".$_POST["uname"]."','".sha1($_POST["pwd"])."','".$_POST["email"]."','".$_POST["name"]."','".$_POST["phone"]."',
    '".$_POST["st"]."','".$_POST["zip"]."','".$_POST["city"]."','".$_POST["state"]."','".$_POST["country"]."');");
    header("Location: BHP.php?ctg=1&msg="."You have registered successfully!"."");
                exit();
?>
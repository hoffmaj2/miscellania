Loading... <?php
require 'db.php';
?>
<?php
$temp = $pdo -> query("SELECT user_id FROM BH_User WHERE username = '" . $_POST["uname"] . "' AND password = '" . sha1($_POST["pwd"]) . "'");
$nlc = $temp -> fetchAll();
$id = $nlc[0][0];
$length = count($nlc);
if ($length != 0) {
    setcookie("BHlin4", $id, time() + 1800,'/');
    header("Location: BHP.php?ctg=1&msg=" . "You have logged in successfully!" . "");
    exit();
} else {
    header("Location: BHP.php?ctg=1&msg=" . "Error: username and password combination does not exist!" . "");
    exit();
}
?>
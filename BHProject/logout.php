Loading... <?php
require 'db.php';
?>
<?php
require 'validate.php';
?>
<?php
unset($_COOKIE['BHlin4']);
    setcookie('BHlin4', '', time() - 3600, '/');
header("Location: BHP.php?ctg=1&msg=" . "You have logged out successfully!" . "");
exit();
?>
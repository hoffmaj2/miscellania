<?php

if ((isset($_COOKIE["BHlin4"]))) {
   header("Location: BHP.php?ctg=1&msg=" . "You are already logged in!" . "");
   exit();
}

?>

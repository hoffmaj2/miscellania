<?php

if (!(isset($_COOKIE["BHlin4"]))) {
   header("Location: BHP.php?ctg=1&msg=" . "You have to be logged in first!" . "");
   exit();
}

?>

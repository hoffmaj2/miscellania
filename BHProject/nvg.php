<?php
require "db.php";
?>
<?php
echo("<div id=\"frm\"><img id=\"cchest\" src=\"cchest.png\" alt=\"Treasure Chest\"></div>");
echo("<ul id=\"nvg\">");
if(isset($_COOKIE["BHlin4"])){
    $temp = $pdo -> query("SELECT username AS data FROM BH_User WHERE user_id = " . $_COOKIE["BHlin4"] . "");
                $nlc = $temp -> fetch(PDO::FETCH_ASSOC);
                $dn = $nlc["data"];
                echo("<li id=\"welcome\">Welcome ".$dn."</li>");
}
echo("
  <li><a href=\"BHP.php\">Home</a></li>
  <li><a href=\"BHRegister.php#signup\">Sign Up</a></li>
  <li><a href=\"BHRegister.php#login\">Log In</a></li>
  <li><a href=\"logout.php\">Log Out</a></li>
  <li><a href=\"chout.php\">Checked Out Items</a></li>
  <li><a href=\"orders.php\">Ordered Items</a></li>
  <li><a href=\"payment.php\">Pay Fines</a></li>
</ul>");
?>
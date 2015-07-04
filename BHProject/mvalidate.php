<?php
$temp = $pdo -> query("SELECT user_id FROM BH_User WHERE user_id = '" . $_COOKIE["BHlin4"] . "' AND user_owes != 0");
$nlc = $temp -> fetchAll();
//$id = $nlc[0][0];
$length = count($nlc);
if ($length == 0) {
    //do nothing
} else {
    header("Location: BHP.php?ctg=1&msg=" . "Error: You owe a fine for previous delayed or failed returns:\n you will not be allowed to checkout new items until it is payed!" . "");
    exit();
}
?>
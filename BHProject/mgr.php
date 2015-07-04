<?php
require 'db.php';
?>

<?php
require 'PHPMailer/PHPMailerAutoload.php';

if (ob_get_level() == 0)
    ob_start();

//while (TRUE) {

//deal with overdue items
$temp = $pdo -> query("SELECT i.item_id, i.item_code, i.checkoutuser_id, i.requestuser_id1, u.email, u.username, i.due_date FROM BH_Item AS i, BH_User AS u WHERE i.checkoutuser_id = u.user_id AND u.user_id != 1 AND i.item_status < 2 AND due_date < '" . date("Y-m-d H:i:s") . "'");
if ($temp -> rowCount() > 0) {
    $nlc = $temp -> fetchAll();
    $length = count($nlc);
    for ($x = 0; $x < $length; $x++) {
        //echo("item: " . $nlc[$x][1] . " is overdue!<br>");
        smailfn($nlc[$x][4],"Dear ".$nlc[$x][5].",\r\nYour checkout of item: ".$nlc[$x][1]." has expired.\r\nPlease be prepared to give this item to the next borrower.\r\nThank you for helping to make Community Chest what it is.\r\n\r\n     -Community Chest");
    }
    $temp = $pdo -> query("UPDATE BH_Item AS i, BH_User AS u SET i.item_status = 2 WHERE i.checkoutuser_id = u.user_id AND u.user_id != 1 AND i.item_status < 2 AND due_date < '" . date("Y-m-d H:i:s") . "'");
} else {
    //do nothing
}

//deal with near due items
$temp = $pdo -> query("SELECT i.item_id, i.item_code, i.checkoutuser_id, i.requestuser_id1, u.email, u.username, i.due_date FROM BH_Item AS i, BH_User AS u WHERE i.checkoutuser_id = u.user_id AND u.user_id != 1 AND i.item_status < 1 AND due_date < '" . (date_format((new DateTime('tomorrow')),'Y-m-d H:i:s')) . "'");
if ($temp -> rowCount() > 0) {
    $nlc = $temp -> fetchAll();
    $length = count($nlc);
    for ($x = 0; $x < $length; $x++) {
        //echo("item: " . $nlc[$x][1] . " is nearly overdue!<br>");
        smailfn($nlc[$x][4],"Dear ".$nlc[$x][5].",\r\nYour checkout of item: ".$nlc[$x][1]." will expire within one day (".$nlc[$x][6].").\r\nPlease be prepared to give this item to the next borrower.\r\nThank you.\r\n\r\n     -Community Chest");
    }
    $temp = $pdo -> query("UPDATE BH_Item AS i, BH_User AS u SET i.item_status = 1 WHERE i.checkoutuser_id = u.user_id AND u.user_id != 1 AND i.item_status < 1 AND due_date < '" . date("Y-m-d H:i:s") . "'");
} else {
    //do nothing
}

//twil("1-269-359-0601", "this is a test message from time: " . date("Y-m-d H:i:s") . "!");
sleep(1);
ob_flush();
flush();
//}

ob_end_flush();

//+12693590601", 'From' => "+12034028138"


    function smailfn($addr,$msg){

    $mail = new PHPMailer();

    $mail->IsSMTP();  // telling the class to use SMTP
    $mail->SMTPAuth   = TRUE; // SMTP authentication
    $mail->Host       = "smtp.gmail.com"; // SMTP server
    $mail->Port       = 587; // SMTP Port
    $mail->Username   = "BHCommunityChest@gmail.com"; // SMTP account username
    $mail->Password   = "BHCC1871";        // SMTP account password

    $mail->SetFrom('BHCommunityChest@gmail.com', 'Community Chest'); // FROM

    $mail->AddAddress($addr);
    //$mail->AddAddress("BHCommunityChest@gmail.com"); // recipient email

    $mail->Subject    = "Community Chest Notice"; // email subject
    $mail->Body       = $msg;

    $mail->SMTPDebug = 1;
    
    if(!$mail->Send()) {
      echo 'Message was not sent.';
      echo 'Mailer error: ' . $mail->ErrorInfo;
    } else {
      echo 'Message has been sent.';
    }
    }


?>
<html>
<script language="JavaScript" src="mgr.js"></script>
</html>
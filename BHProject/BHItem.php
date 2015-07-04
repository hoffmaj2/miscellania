<!DOCTYPE HTML>
<html>
	<head>
		<title>Community Chest</title>
		<link href="bootstrap-3.3.5-dist/css/bootstrap.css" type="test/css" rel="stylesheet">
		<link href="BHP.css" type="text/css" rel="stylesheet" >
		<link href="css/hover.css" type="text/css" rel="stylesheet" >
		<link href="nvg.css" type="text/css" rel="stylesheet" >
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script language="JavaScript" src="BHItem.js"></script>
	</head>
	<body id="front-page">
		<?php
        require 'db.php';
		?>
		<div class="banner">
			<h1>Community Chest</h1>
			<?php
            require 'nvg.php';
			?>
			<?php
			require 'PHPMailer/PHPMailerAutoload.php';
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
			
                if (isset($_GET["ct"])) {
                    require 'validate.php';
                    require 'mvalidate.php';
                    $temp = $pdo -> query("UPDATE BH_item SET due_date = '".date("Y-m-d",strtotime("+1 week"))."', item_status=4,checkoutuser_id = " . $_COOKIE["BHlin4"] . " WHERE item_status = 3 AND item_id = " . $_GET["iid"] . "");
                } else if (isset($_GET["rm"])) {
                    require 'validate.php';
                    $temp = $pdo -> query("UPDATE BH_item SET requestuser_id1 = 1 WHERE requestuser_id1 =" . $_COOKIE["BHlin4"] . "  AND item_id = " . $_GET["iid"] . "");
                    $temp = $pdo -> query("UPDATE BH_item SET requestuser_id2 = 1 WHERE requestuser_id2 =" . $_COOKIE["BHlin4"] . "  AND item_id = " . $_GET["iid"] . "");
                } else if (isset($_GET["rt"])) {
                    require 'validate.php';
                    $temp = $pdo -> query("UPDATE BH_item SET due_date = '".date("Y-m-d",strtotime("+1 week"))."', checkoutuser_id = requestuser_id1, requestuser_id1 = requestuser_id2, requestuser_id2 = 1 WHERE checkoutuser_id =" . $_COOKIE["BHlin4"] . "  AND item_id = " . $_GET["iid"] . "");
                    $temp = $pdo -> query("UPDATE BH_Item SET item_status = 3 WHERE checkoutuser_id = 1");
                    $temp = $pdo -> query("SELECT u.email, i.item_code, u.username  FROM BH_Item AS i, BH_User AS u WHERE u.user_id = i.checkoutuser_id AND i.checkoutuser_id = " . $_GET["iid"] . " AND i.checkoutuser_id != 1");
                    $nlc = $temp -> fetchAll();
                    $length = count($nlc);
                    if($length>0){
                        $addr = $nlc[0][0];
                        smailfn($addr,"Dear ".$nlc[0][2].",\r\n\r\nThe ".$nlc[0][1]." you ordered is now available. It will be exclusivly yours until a week from today.\r\n     -Community Chest");
                           }
                        
                    //$addr = 
                } else if (isset($_GET["ad"])) {
                    require 'validate.php';
                    $temp = $pdo -> query("UPDATE BH_item SET requestuser_id1 = " . $_COOKIE["BHlin4"] . ", requestuser_id2 = " . $_COOKIE["BHlin4"] . " WHERE (requestuser_id2 = 1 OR requestuser_id1 = 1) AND item_id = " . $_GET["iid"] . "");
                    $temp = $pdo -> query("UPDATE BH_item SET requestuser_id2 = 1 WHERE requestuser_id2 = " . $_COOKIE["BHlin4"] . " AND requestuser_id1 = " . $_COOKIE["BHlin4"] . "");
                }

                $temp = $pdo -> query("SELECT * FROM BH_item WHERE item_id = " . $_GET["iid"] . "");
                $nlc = $temp -> fetch(PDO::FETCH_ASSOC);
                $ic = $nlc["item_code"];
                $d = $nlc["description"];
                $ld = $nlc["long_description"];
                $in = $nlc["image_name"];
                $dd = $nlc["due_date"];
                $di = $nlc["donor_id"];
                $i1 = $nlc["checkoutuser_id"];
                $i2 = $nlc["requestuser_id1"];
                $i3 = $nlc["requestuser_id2"];
                $wlen = 0;
                if ($i1 != "1")
                    $wlen += 1;
                if ($i2 != "1")
                    $wlen += 1;
                if ($i3 != "1")
                    $wlen += 1;
                $temp = $pdo -> query("SELECT username AS data FROM BH_User WHERE user_id = " . $di . "");
                $nlc = $temp -> fetch(PDO::FETCH_ASSOC);
                $dn = $nlc["data"];

			?>
		</div>
		<div class="banner" style="position:static"></div>

		<div class="itembox">
			<div class="tpb">
				<div class="uib">
					<?php echo("<h2>" . $d . "</h2><h3>Donated by " . $dn . "</h3>"); ?>
				</div>
				<div class="mib">
					<?php echo("<table><td id=\"td1\"><img id=\"iim\" src=\"" . $in . "\"></td><td><p id=\"lld\">" . $ld . "</p></td></table>"); ?>
				</div>
			</div>
			<div class="lib">
				<?php

                if (isset($_COOKIE["BHlin4"]) && $i1 == $_COOKIE["BHlin4"])
                    echo("<ul><li><p>You currently hold this item.</p><button onclick=\"ret()\">Return this item</button></li></ul>");
                else if (isset($_COOKIE["BHlin4"]) && ($i2 == $_COOKIE["BHlin4"] || $i3 == $_COOKIE["BHlin4"]))
                    echo("<ul><li><p>You are already on the wait list for this item.</p><button onclick=\"rem()\">Remove yourself from the waitlist</button></li></ul>");
                else {
                    if ($wlen != 0) {echo("<ul><li><p>There are currently <b>" . ($wlen - 1) . "</b> people on the wait list for this item.</p>");
                        if ($wlen < 3)
                            echo("<button onclick=\"wlistadd()\">Add yourself to the wait list</button>");
                        echo("</li></ul>");
                    } else
                        echo("<ul><li><p>This item is currently availabe for checkout.</p><button onclick=\"checkout()\">Checkout this item</button></li></ul>");
                }
				?>
			</div>
		</div>

	</body>
</html>
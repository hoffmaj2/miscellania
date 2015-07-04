<!DOCTYPE HTML>
<html>
	<head>
		<title>Community Chest</title>
		<link href="bootstrap-3.3.5-dist/css/bootstrap.css" type="test/css" rel="stylesheet">
		<link href="BHP.css" type="text/css" rel="stylesheet" >
		<link href="css/hover.css" type="text/css" rel="stylesheet" >
		<link href="nvg.css" type="text/css" rel="stylesheet" >
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script language="JavaScript" src="BHRegister.js"></script>
	</head>

	<body>
<?php
require "db.php";
?>
		<?php

        require_once '/lib/Braintree.php';

        Braintree_Configuration::environment('sandbox');
        Braintree_Configuration::merchantId('pbw4xz8ktdr7qswn');
        Braintree_Configuration::publicKey('vgkv8s4nfvgn9486');
        Braintree_Configuration::privateKey('9085b22ae725191162acd7da0e4b9536');

        //$result = Braintree_Transaction::sale(array('amount' => '10.00', 'creditCard' => array('number' => $_POST["cnb"], 'expirationDate' => $_POST["expd"])));

        
        $result = Braintree_Transaction::sale([
  'amount' => '10.00',
  'paymentMethodNonce' => "fake-valid-nonce"//$_POST["payment_method_nonce"]
]);
        if ($result -> success) {
            //print_r("success!: " . $result -> transaction -> id);
            $temp = $pdo -> query("UPDATE BH_User SET user_owes = 0 WHERE user_id = ". $_COOKIE["BHlin4"] ."");
            header("Location: BHP.php?ctg=1&msg=" . "Payment successfull!" . "");
    exit();
        } else if ($result -> transaction) {
            print_r("Error processing transaction:");
            print_r("\n  code: " . $result -> transaction -> processorResponseCode);
            print_r("\n  text: " . $result -> transaction -> processorResponseText);
        } else {
            print_r("Validation errors: \n");
            print_r($result -> errors -> deepAll());
        }
    ?>
	</body>
</html>
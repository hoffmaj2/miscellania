<?php/*
    require 'PHPMailer/PHPMailerAutoload.php';

    smailfn("jdhscripts@gmail.com", "test email type 5-a");
    
    function smailfn($addr,$msg){

    $mail = new PHPMailer();

    $mail->IsSMTP();  // telling the class to use SMTP
    $mail->SMTPAuth   = TRUE; // SMTP authentication
    $mail->Host       = "smtp.gmail.com"; // SMTP server
    $mail->Port       = 587; // SMTP Port
    $mail->Username   = "BHCommunityChest@gmail.com"; // SMTP account username
    $mail->Password   = "BHCC1871";        // SMTP account password

    $mail->SetFrom('BHCommunityChest@gmail.com', 'Community Chest'); // FROM

    $mail->AddAddress($addr); // recipient email

    $mail->Subject    = "Community Chest Notice"; // email subject
    $mail->Body       = $msg;

    $mail->SMTPDebug = 1;
    
    if(!$mail->Send()) {
      echo 'Message was not sent.';
      echo 'Mailer error: ' . $mail->ErrorInfo;
    } else {
      echo 'Message has been sent.';
    }
    }*/
?>
<?php $servername = "localhost";
$username = "dbuser";
$password = "123";
$dbname = "MBDB";
try{$pdo = new PDO("mysql:host=$servername;dbname=$dbname;charset=utf8", "$username", "$password");}
catch(PDOException $e){
    die("An erroe has occured when connecting to the database: <br><br>".$e);
}
?>

<?php $servername = "localhost";
$username = "root";
$password = "";
$dbname = "BH2015";
try{$pdo = new PDO("mysql:host=$servername;dbname=$dbname;charset=utf8", "$username", "$password");}
catch(PDOException $e){
    die("An error has occured when connecting to the database: <br><br>".$e);
}
?>

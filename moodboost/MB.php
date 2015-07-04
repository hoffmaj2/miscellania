<!DOCTYPE HTML>
<html>
	<head>
		<title>Mood-Boost</title>
		<link href="bootstrap-3.3.5-dist/css/bootstrap.css" type="test/css" rel="stylesheet">
		<link href="MB.css" type="text/css" rel="stylesheet" >
		<link href="css/hover.css" type="text/css" rel="stylesheet" >
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script language="JavaScript" src="MB.js"></script>
	</head>
	<body id="front-page">
		<?php require 'db.php';?>
		<div class="banner">
			<img src="https://openclipart.org/download/192852/thumbs-up-right.svg">
			<h1>Project Mood Boost</h1>
		</div>
		<div class="banner" style="position:static"></div>

		<div id="buttons">
			<?php
				$temp = $pdo->query("SELECT name,img FROM Mood");
				$emotions = $temp->fetchAll();
				$length = count($emotions);
				for ($x = 0; $x < $length; $x++){
					echo("<button style=\"background: url(");
					echo($emotions[$x][1]);
					echo(") no-repeat; background-size:cover; font: 55pt san-sarif; color: #4D0000;\" id=\"");
					echo($emotions[$x][0]);
					echo("\" class=\"btn btn-default sqr1 hvr-grow\" type=\"submit\">");
					echo($emotions[$x][0]);
					echo("</button>");
				}
			?>
		</div>
	</body>
</html>
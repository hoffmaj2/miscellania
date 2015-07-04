<!DOCTYPE HTML>
<html>
	<head>
		<title>Community Chest</title>
		<link href="bootstrap-3.3.5-dist/css/bootstrap.css" type="test/css" rel="stylesheet">
		<link href="BHP.css" type="text/css" rel="stylesheet" >
		<link href="css/hover.css" type="text/css" rel="stylesheet" >
		<link href="nvg.css" type="text/css" rel="stylesheet" >
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script language="JavaScript" src="BHP.js"></script>
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
		</div>
		<div class="banner" style="position:static"></div>

		<div id="buttons">
			<?php

            if (isset($_GET['msg'])) {
                echo '<script type="text/javascript">alert("' . $_GET['msg'] . '");window.location.replace("BHP.php?ctg=' . $_GET["ctg"] . '");</script>';
            }

            $ctg = 1;
            if (isset($_GET['ctg'])) {
                $ctg = $_GET['ctg'];
            }
            $temp = $pdo -> query("SELECT leaf_category FROM BH_Category WHERE category_id = '" . $ctg . "'");
            $nlcc = $temp -> fetch(PDO::FETCH_ASSOC);
            if ($nlcc["leaf_category"] == TRUE) {
                header("Location: BHSearch.php?ctg=" . $ctg . "");
                exit();
            }
            $temp = $pdo -> query("SELECT category_name,image_name,category_id FROM BH_Category WHERE parent_category_id = '" . $ctg . "' AND category_id !=1 
            ORDER BY category_name");
            $nlc = $temp -> fetchAll();
            $length = count($nlc);
            for ($x = 0; $x < $length; $x++) {
                echo("<button style=\"background: url(");
                echo($nlc[$x][1]);
                echo(") no-repeat; background-size:cover; font: 48pt san-sarif; color: #000000;\" id=\"");
                echo($nlc[$x][2]);
                echo("\" class=\"btn btn-default sqr1 srcbtn hvr-grow\" type=\"submit\">");
                echo("<span class=\"btntxt\">");
                echo($nlc[$x][0]);
                echo("</span>");
                echo("</button>");
            }
			?>
		</div>
	</body>
</html>
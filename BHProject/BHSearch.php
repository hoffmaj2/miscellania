<!DOCTYPE HTML>
<html>
	<head>
		<title>Community Chest</title>
		<link href="bootstrap-3.3.5-dist/css/bootstrap.css" type="test/css" rel="stylesheet">
		<link href="css/hover.css" type="text/css" rel="stylesheet" >
		<link href="BHP.css" type="text/css" rel="stylesheet" >
		<link href="nvg.css" type="text/css" rel="stylesheet" >
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script language="JavaScript" src="BHSearch.js"></script>
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
		<div id="searchBar">
			<form onsubmit="smt(); return false;">
				<br>
				Search:
				<br>
				<input type="text" name="search" value="" id="txtare">
			</form>
		</div>
			<?php
			//WHERE category_id = ".$_GET["ctg"]."
			$qry = "SELECT description, image_name, item_id FROM BH_Item WHERE category_id = ".$_GET["ctg"]." ORDER BY due_date";
            if (isset($_GET['search'])) {
                $qry = "SELECT description, image_name, item_id FROM BH_Item WHERE category_id = ".$_GET["ctg"]." AND description LIKE '%".$_GET['search']."%' ORDER BY due_date";
            }
            $temp = $pdo -> query($qry);
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
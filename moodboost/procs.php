

		<?php
        require 'db.php';
		?>
		<?php

        $proc = $_POST["p"];
        

        if ($proc == "0") {
            $mood = $_POST["m"];
            unset($_COOKIE["mbctag"]);
            $pdo -> query("INSERT INTO Tags (mood) VALUES ('" . $mood . "');");
            $temp = $pdo -> query("SELECT MAX(tag) FROM Tags;");
            $ndata = $temp -> fetchAll();
            $tag = $ndata[0][0];
            $temp = $pdo -> query("SELECT MIN(tag), COUNT(*) FROM Tags WHERE tag != " . $tag . " AND mood = '" . $mood . "';");
            $ndata = $temp -> fetchAll();
            $tag2 = $ndata[0][0];
            $chk = $ndata[0][1];
            if (($chk . "") != "0") {
                $pdo -> query("DELETE FROM Tags WHERE tag = " . $tag . " OR tag = " . $tag2 . ";");
                $pdo -> query("INSERT INTO Chats VALUES(" . $tag . "," . $tag2 . ");");
                setcookie("mbctag", $tag, time() + 600,'/');
                $pdo -> query("INSERT INTO Chats VALUES(" . $_COOKIE["mbctag"] . "," . $tag2 . ");");
            }
        } else if ($proc == "1") {
            $pdo -> query("DELETE FROM Chats WHERE taga = " . $_COOKIE["mbctag"] . " OR tagb = " . $_COOKIE["mbctag"] . ";");
            $pdo -> query("DELETE FROM Msgs WHERE pfrm = " . $_COOKIE["mbctag"] . " OR pto = " . $_COOKIE["mbctag"] . ";");
            unset($_COOKIE["mbctag"]);
        } else if ($proc == "2") {
            if(isset($_COOKIE["mbctag"])){
                setcookie("mbctag", $_COOKIE["mbctag"], time() + 600,'/');
            $txt = $_POST["t"];
            $temp = $pdo -> query("SELECT taga FROM Chats WHERE tagb = ". $_COOKIE["mbctag"] ." UNION SELECT tagb FROM Chats WHERE taga = ". $_COOKIE["mbctag"] .";");
            $ndata = $temp -> fetchAll();
            $tag = $ndata[0][0];
            $pdo -> query("INSERT INTO Msgs VALUES (". $_COOKIE["mbctag"] .",".$tag.",'".$txt."','".date("Y-m-d H:i:s")."');");
            }
        }
        else if ($proc == "3") {
            if(isset($_COOKIE["mbctag"])){
            $temp = $pdo -> query("SELECT * FROM Msgs WHERE pfrm = " . $_COOKIE["mbctag"] . " OR pto = " . $_COOKIE["mbctag"] . " ORDER BY tme;");
            $ndata = $temp -> fetchAll();
            $x = 0;
            $len = count($ndata);
            for($x=0;$x<$len;$x++){
                echo($ndata[$x][0]."_*459".$ndata[$x][1]."_*459".$ndata[$x][2]."_*459".$ndata[$x][3]."_*459");
                }
            }
        }
		?>
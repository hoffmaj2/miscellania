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
            require 'antivalidate.php';
            ?>
		</div>
		<div class="banner" style="position:static"></div>

		<!--div id="regfrm">

		</div-->
		<div id="buttons">
		<div class="form" id="fm">

			<div class="tab-content">
				<div id="signup">
					<h2>Sign Up for Free</h2><hr><br>

					<form action="rgBack.php" method="post">

						<div class="top-row">
							<div class="field-wrap">
								<label> Full Name<span class="req">*</span> </label>
								<input name="name" type="text" required autocomplete="on" />
							</div>

							<div class="field-wrap">
								<label> Email Address<span class="req">*</span> </label>
								<input name="email" type="email"required autocomplete="on"/>
							</div>

							<div class="field-wrap">
								<label> Username<span class="req">*</span> </label>
								<input name="uname" type="username"required autocomplete="off"/>
							</div>

							<div class="field-wrap">
								<label> Set A Password<span class="req">*</span> </label>
								<input name="pwd" type="password"required autocomplete="off"/>
							</div>

							<div class="field-wrap">
								<label> Phone #<span class="req">*</span> </label>
								<input name="phone" type="tel"required autocomplete="on"/>
							</div>

							<div class="field-wrap">
								<label> Street<span class="req">*</span> </label>
								<input name="st" type="text"required autocomplete="on"/>
							</div>

							<div class="field-wrap">
								<label> Zip<span class="req">*</span> </label>
								<input name="zip" type="text"required autocomplete="on"/>
							</div>

							<div class="field-wrap">
								<label> City<span class="req">*</span> </label>
								<input name="city" type="text"required autocomplete="on"/>
							</div>

							<div class="field-wrap">
								<label> State<span class="req">*</span> </label>
								<input name="state" type="text"required autocomplete="on"/>
							</div>

							<div class="field-wrap">
								<label> Country<span class="req">*</span> </label>
								<input name="country" type="text"required autocomplete="on"/>
							</div>

							<button type="submit" class="button button-block"/>
							Get Started</button>

					</form>
					</div>

				</div>
			</div>

			<div id="login">
				<h2>Log in to a pre-existing account:</h2><hr><br>

				<form action="lgBack.php" method="post">

					<div class="field-wrap">
						<label> Username<span class="req">*</span> </label>
						<input name="uname" type="username"required autocomplete="off"/>
					</div>

					<div class="field-wrap">
						<label> Password<span class="req">*</span> </label>
						<input name="pwd" type="password"required autocomplete="off"/>
					</div>

					<button class="button button-block"/>
					Log In</button>

				</form>

			</div>

		</div><!-- tab-content -->

		</div> <!-- /form -->
	</body>
</html>
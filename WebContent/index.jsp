<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head profile="http://www.w3.org/2005/10/profile">
 <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>CSS Style Sorter - Beta</title>
<link type="text/css" href="css/redmond/jquery-ui-1.8.12.custom.css" rel="stylesheet" /> 
<link type="text/css" href="stylesheets/general-styles.css" rel="stylesheet" /> 
		<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>

		<script type="text/javascript" src="js/jquery-ui-1.8.12.custom.min.js"></script>


    <script type="text/javascript" src="functions.js"></script>

		<script type="text/javascript">
			$(function(){


				// Tabs
				$('#tabs').tabs();


});
		</script>
		<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; margin: 50px;}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
		</style>

		<style type="text/css">

		/* tabs */

		div#tabs {
		margin-left:auto;
		margin-right:auto;
		width:700px;
		}

		div#tabs-1 {
		height: 450px;
		}

		div#tabs-2 {
		height: 450px;
		}

		div#tabs-3 {
		height: 450px;
		}

                div#tabs-4 {
		height: 450px;
		}


		/* forms and selectors */

		form#code {
		clear:both;
		}

		form#code textarea {
		width:49%;
		}

		div.methods {
		float:left;
		width:290px;
		}

		div.m_options {
		margin-left:10px;
		width: 180px;
		float:left;
		}

                div#wrap_all {
                    width:700px;
                    margin-left:auto;
                    margin-right:auto;
}


		</style>
	</head>
	<body>
            <div id="wrap_all">

<img src="images/sorter_logo7.png" style="float:left; margin-bottom:5px;" />

		<div id="tabs" style="clear:both">
			<ul>
				<li><a href="#tabs-1">Copy & Paste</a></li>
				<li><a href="#tabs-2">File Upload</a></li>
				<li><a href="#tabs-3">From URL</a></li>
                                <li><a href="#tabs-4">Feedback</a></li>
			</ul>
			<div id="tabs-1">
			<div class="logintxt" style="float:right; margin:5px 0;"><!-- <a href="#" onmouseover="descrip(this)">Login</a><span> | </span><a href="#">Sign up</a> --></div>



			<form action="SorterWebApp" accept-charset="utf-8" method="post" id="code">


        <div><a class="options_titles" style="margin-right:260px;">Enter your CSS:</a><a class="options_titles" id="url_title" style="color:#C0C0C0;">Enter your HTML:</a></div>
        <div><textarea name="content" rows="10" cols="40"></textarea><textarea id="html_url"  name="html" rows="10" cols="40" disabled="true"></textarea></div>
    <div><input type="submit" value="Sort CSS" name="Copy&Paste" /></div>

<br />

<div class="methods">

    <a class="options_titles">Sort Method:</a>
    <br />
    <input type="checkbox" onclick="indenter(this.checked)" name="option" value="indent" id="indent"  onmouseover="descrip(this)" checked="true"/><a id="indent_title"> Sort indenting sub-selectors</a><br />
    <input type="checkbox" onclick="sequn(this.checked)" name="option" value="seq" id="seq" disabled="true"  /><a id="seq_title"> Sort selectors in the order they appear in HTML</a><br />
    <input  type="checkbox" onclick="alphaer(this.checked)" name="option" value="alpha" id="alpha" disabled="true"/><a id="alpha_title" > Sort alphabetically</a><br />

    <input type="checkbox" onclick="nicely(this.checked)" name="option" value="nice" id="nice" disabled="true"/><a id="nice_title"> Format nicely</a><br />
    



</div>

<div class="m_options">
    <a class="options_titles">Options:</a>

    <br />
    <input type="checkbox" name="option" value="comments" id="comments" /><a id="comments_title"> Remove all comments</a><br />
    <input type="checkbox" name="option" value="safesort" id="safesort" checked="true"/><a id="safesort_title"> Sort Safely</a><br />
    <input type="checkbox" name="option" value="indenta" id="indenta" checked="true"/><a id="indenta_title"> Indent sub-selectors</a><br />



</div>






<script>
$(function() {
  $("div#info").hide();
})


<!-- <button id="showr">Show</button> <button id="hidr">Hide</button> -->



$("#showr").click(function () {
  $("div#info:eq(0)").show("fast", function () {
    /* use callee so don't have to name the function */
    $(this).next("div#info").show("fast", arguments.callee);
  });
});
$("#hidr").click(function () {
  $("div#info").hide(2000);
});

</script>

<div id="info" style="float:left; width: 100px; height:100px; background-color: #70a8d2;"></div>


<div style="clear:both;"></div>





    </form>




			</div> <!-- close tabs-1 -->
			<div id="tabs-2">
<div class="logintxt" style="float:right; margin:5px 0;"><!-- <a href="#" onmouseover="descrip(this)">Login</a><span> | </span><a href="#">Sign up</a> --></div>




			<form style="margin-top:30px;" accept-charset="utf-8"  ENCTYPE='multipart/form-data'
            method='POST' action='SorterWebApp'>

            <a class="options_titles">CSS: </a><br /><input type="file" name="upload"/>
            <br />
            <a class="options_titles" id="url_title">HTML:</a><br /><input id="html_url1" type="file" name="html" disabled="true"/>
            <br />
            <br />
            <input TYPE='submit' VALUE='Sort CSS' name="FileUpload"/>

            <br />

            <br />
            <br />


    <div class="methods">

    <a class="options_titles">Sort Method:</a>
    <br />
    <input type="checkbox" onclick="indenter1(this.checked)" name="option" value="indent" id="indent1" checked="true"/><a id="indent_title1"> Sort indenting sub-selectors</a><br />

    <input type="checkbox" onclick="sequn1(this.checked)" name="option" value="seq" id="seq1" disabled="true" /><a id="seq_title1"> Sort selectors in the order they appear in HTML</a><br />
    <input type="checkbox" onclick="alphaer1(this.checked)" name="option" value="alpha" id="alpha1" disabled="true"/><a id="alpha_title1" > Sort alphabetically</a><br />
    <input type="checkbox" onclick="nicely1(this.checked)" name="option" value="nice" id="nice1" disabled="true"/><a id="nice_title1"> Format nicely</a><br />
   
</div>

<div class="m_options">
    <a class="options_titles">Options:</a>
    <br />
    <input type="checkbox" name="option" value="comments" id="comments1"/><a id="comments_title1"> Remove all comments</a><br />
    <input type="checkbox" name="option" value="safesort" id="safesort1" checked="true"/><a id="safesort_title1" > Sort Safely</a><br />

    <input type="checkbox" name="option" value="indenta" id="indenta1" checked="true"/><a id="indenta_title1" > Indent sub-selectors</a><br />


</div>

<div class="new_space"></div>

    </form>



</div> <!-- close tab 2 -->
			<div id="tabs-3">
<div class="logintxt" style="float:right; margin:5px 0;"><!-- <a href="#" onmouseover="descrip(this)">Login</a><span> | </span><a href="#">Sign up</a> --></div>
<form action="SorterWebApp" accept-charset="utf-8"  method="post" style="margin-top:30px;" >
        <span>CSS URL:</span><br /> <input  type="text" name="css_url" size="40"/>

        <br />

        <a id="url_title">HTML URL:</a><br /><input  type="text" name="html_url" size="40"  id="html_url2" disabled="true"/>
        <br />
        <br />

    <div><input type="submit" value="Sort CSS" name="ByUrl" /></div>
<br />

<div class="methods">
    <a class="options_titles">Sort Method:</a>
    <br />

    <input type="checkbox" onclick="indenter2(this.checked)" name="option" value="indent" id="indent2" checked="true"/><a id="indent_title2"> Sort indenting sub-selectors</a><br />
    <input type="checkbox" onclick="sequn2(this.checked)" name="option" value="seq" id="seq2" disabled="true"/><a id="seq_title2"> Sort selectors in the order they appear in HTML</a><br />
    <input type="checkbox" onclick="alphaer2(this.checked)" name="option" value="alpha" id="alpha2" disabled="true"/><a id="alpha_title2" > Sort alphabetically</a><br />
    <input type="checkbox" onclick="nicely2(this.checked)" name="option" value="nice" id="nice2" disabled="true"/><a id="nice_title2"> Format nicely</a><br />
  

</div>

<div class="m_options">
    <a class="options_titles">Options:</a>
    <br />
    <input type="checkbox" name="option" value="comments" id="comments2"/><a id="comments_title2"> Remove all comments</a><br />
    <input type="checkbox" name="option" value="safesort" id="safesort2" checked="true"/><a id="safesort_title2"> Sort Safely</a><br />

    <input type="checkbox" name="option" value="indenta" id="indenta2" checked="true"/><a id="indenta_title2"> Indent sub-selectors</a><br />


</div>

<div class="new_space"></div>



    </form>










			</div> <!-- close tab 3 -->

  <div id="tabs-4">




      <div id="feedback">


        <form action="SorterWebApp" accept-charset="utf-8" method="post" id="feedback"  >
            <input  type="text" name="fullname" size="40"  id="emailaddress"/><span class="user_info">Name (required)</span>
            <br />
                <input  type="text" name="email" size="40"  id="emailaddress"/><span class="user_info">Email address (required)</span>
            <br />
            </br>
            <input  type="text" name="website" size="40"  id="emailaddress"/><span class="user_info">Website (optional)</span>
            <br />




            <p>If you are reporting a bug please include a link to the CSS/HTML. If the code is not online, upload to <a href="http://pastebin.com/" target="_blank">pastebin</a> and include the link. Please do not include the code in the message form submission. Emails should be answered within 48 hours.</p>

            <br /><textarea name="feedback" rows="10" cols="40"></textarea>
            <br>

            <input type="submit" value="Submit feedback" name="email" />
        </form>





    </body>
</html>


</div>

  </div> <!-- close tab 4 -->












		</div>
<div id="footer">&copy; 2011 Chris & Brian Scott. All rights reserved.</div>

</div> <!-- close div wrap all -->
	</body>
</html>
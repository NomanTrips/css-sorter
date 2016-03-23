<%--
    Document   : answer
    Created on : May 15, 2011, 12:57:22 PM
    Author     : Owner
--%>
<!--pageEncoding="ascii"-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<!--xmlns="http://www.w3.org/1999/xhtml"-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>CSS Style Sorter - Beta</title>
		<link type="text/css" href="css/redmond/jquery-ui-1.8.12.custom.css" rel="stylesheet" />
<script type="text/javascript" src="functions.js"></script>
		<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<link type="text/css" href="stylesheets/answer.css" rel="stylesheet" />
<link type="text/css" href="stylesheets/general-styles.css" rel="stylesheet" />
		<script type="text/javascript" src="js/jquery-ui-1.8.12.custom.min.js"></script>






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
                    width: 700px;
                    margin-left:auto;
                    margin-right:auto;
}




		</style>



	</head>
	<body>

<%String output="";%>
<!--<div id="d_clip_button" style="border:1px solid black; padding:20px;">Copy To Clipboard</div>-->





            <div id="wrap_all" >
	<img src="images/sorter_logo7.png" style="float:left; margin-bottom:5px;" />
		<div id="tabs" style="clear:both;">
			<ul style="height: 28px;">

			</ul>



<div id="tabs-4" style="padding:10px;">


			<div style="clear:both"></div>
                        <div class="sub-menu">
                            <!--<div class="logintxt" style="float:right; margin:5px 0;"><a href="https://iussumbeta.appspot.com/login.jsp" onmouseover="descrip(this)">Login</a><span> | </span><a href="signup.jsp">Sign up</a></div>-->
                        <div class="sub-menu-items"><a id="return" href="index.jsp">Return</a></div>
                        <div id="d_clip_button" class="sub-menu-copy"><button type="button" id="copy-button">Copy to clipboard</button></div>
                        <div class="sub-menu-text"><span>Backup you're CSS before using the output below</span></div>
                        </div>
			<div class="answer">
                                              <div class="ans_main">
                      <div class="ans_text">




                          <pre>
<%

output="";
/*String[] css_id_real_c2=(String[])session.getAttribute("css_id_real");
int x;
for(x=0; x<css_id_real_c2.length; x++){
    out.println(css_id_real_c2[x]);
    }*/
/*
Integer method_c_obj=(Integer)session.getAttribute("method");
int method_c = method_c_obj.intValue();
if(method_c==5){
Integer x_obj=(Integer)session.getAttribute("zr");
int x = x_obj.intValue();
out.println(x);
              }
*/
    //else {
      //  out.println("Something is seriously wrong here!");
        //}

              //System.out.println("method 5 passed");
              String[] value_c=new String[1];
              String[] property_c=new String[1];
              //Integer messen_obj=(Integer)session.getAttribute("messen");
              //int messen_c=messen_obj.intValue();
              //out.println("well we got tht much");
             /*String[] css_id_real_c=(String[])session.getAttribute("css_id_real");
              int p;
              for(p=0; p<css_id_real_c.length; p++){
                  out.println(css_id_real_c[p]);
                  //out.println(messen_c);
                  }*/

              //build vars

              String[] css_id_real_c=(String[])session.getAttribute("css_id_real");
              String[] css_properties_c=(String[])session.getAttribute("css_properties");
              int[] indent_amt_c=(int[])session.getAttribute("indent_amt");
              int b1_c = css_id_real_c.length;
              //System.out.println("427");
              //end build vars

                 int z1;
                 int y1;
                 int v;
                 for (z1=0; z1<b1_c;z1++){

                    String indent_temp= new String("");
                    for(v=0; v<indent_amt_c[z1]; v++){
                    indent_temp=(indent_temp+'\t');
                    }
                    //iussum_main.f1=z;
                    int f1_c=z1;
                    session.setAttribute("f1", f1_c);
         //begin formatter
         int bad_css_c=0;
         String archon=new String("zealots");
         session.setAttribute("zealots", archon);
         //make method vars
         Integer f1_obj;
         f1_obj=(Integer)session.getAttribute("f1");
         //int f1_c=f1_obj.intValue();
         //String[] css_id_real_c=(String[])session.getAttribute("css_id_real");
         //String[] css_properties_c=(String[])session.getAttribute("css_properties");
         //end make method vars

        String proper="";
        //prop_amt=0;
        int prop_amt_c=0;

        char[] prop_char;
        String prop_temp="";
        int a =0;
        int z;
        int y;
        int m;

        //selector= css_id_real[f1];
        String selector_c=css_id_real_c[f1_c];
        selector_c = selector_c.replaceAll("\\s+$", "");
        selector_c = selector_c.replaceAll("^\\s+", "");
        selector_c=(selector_c+' ');
        //selector sub routine to retreive headings if their are any.
        char[] sel_array=selector_c.toCharArray();
        String heading="";
        int isheading=0;
        for(z=0; z<sel_array.length; z++){
            if(sel_array[z]=='/'&& sel_array[z+1]=='*'){
                z++;
                z++;
                heading="/*";
                /*while(sel_array[z] != '*'|| sel_array[z+1]!='/'){
                    heading=(heading+sel_array[z] );
                    z++;
                    }*/
                int w;
                for(w=z; w<sel_array.length; w++){
                    if(sel_array[w]=='*' && sel_array[w+1]=='/'){
                        break;
                        }
                    else{
                        heading=(heading+sel_array[w] );
                        }
                    }
                z=w;
                z++;
                z++;
                heading=(heading+"*/");
                selector_c="";
                while(z<sel_array.length){
                    selector_c=(selector_c+sel_array[z]);
                    z++;
                    }
                isheading=1;
            }
            else{
                continue;
                }
            }
        //end selector sub
        //System.out.println(selector);
        proper=css_properties_c[f1_c].replaceAll("\\{", " ");
        proper=proper.replaceAll("\\}", " ");
//System.out.println("478");
        prop_char=proper.toCharArray();
        m=proper.length();
        int semi_amt=0;//fix for properties w less : than ;

        for(y=0; y<m; y++){
            if(prop_char[y]==':'){  //fix from ';' to ':'
               prop_amt_c++;
            }
            else{
                continue;
            }
        }
        for(y=0; y<m; y++){
            if(prop_char[y]==';'){  //fix from ';' to ':'
               semi_amt++;
            }
            else{
                continue;
            }
        }
        //System.out.println("499");
        if(semi_amt>prop_amt_c){
            //bad_css=1;
            bad_css_c=1;
            session.setAttribute("bad_css", bad_css_c);
            prop_amt_c=semi_amt;
        }
        Integer bad_css_obj;
         //bad_css_obj=(Integer)session.getAttribute("bad_css");
         //int bad_css_c=bad_css_obj.intValue();
         //System.out.println("508");
        if(bad_css_c==1){
            semi_amt=0;
            //something wrong with the css;

        }

        else{
            a=0;
        semi_amt=0;
        //end semi & colon fix
        //properties= new String[prop_amt];
        String[] properties_c=new String[prop_amt_c];
        //property=new String[prop_amt];
        property_c=new String[prop_amt_c];
        //value=new String[prop_amt];
        value_c=new String[prop_amt_c];

//System.out.println("523");
        for(z=0; z<prop_amt_c; z++){
            value_c[z]="bears_in_the_css";
            properties_c[z]=("\t");
            property_c[z]=("\t");
        }
        for(z=0; z<m; z++){
            if(prop_char[z]==';'){
                prop_temp = prop_temp.replaceAll("\\s+$", "");
                prop_temp = prop_temp.replaceAll("^\\s+", "");
                properties_c[a]=(properties_c[a]+prop_temp+';');
                a++;
                prop_temp="";
            }
            else{
                prop_temp=(prop_temp+prop_char[z]);
            }
        }
        //build property & value
        prop_temp="";
        a=0;
        String value_temp;
        for(z=0; z<m; z++){
            if(prop_char[z]==':'){
                prop_temp = prop_temp.replaceAll("\\s+$", "");
                prop_temp = prop_temp.replaceAll("^\\s+", "");
                property_c[a]=(property_c[a]+prop_temp);
                z++;
                value_temp="";
                while(z<m && prop_char[z] !=';'){//fix while(prop_char[z] !=';' && z<m)
                    value_temp=(value_temp+prop_char[z]);
                    z++;
                }
                value_temp = value_temp.replaceAll("\\s+$", "");
                value_temp = value_temp.replaceAll("^\\s+", "");
                value_c[a]=value_temp;
                a++;
                prop_temp="";
            }
            else{
                prop_temp=(prop_temp+prop_char[z]);
            }
        }
         //iussum_main.session.setAttribute("property", property_c);
         //iussum_main.session.setAttribute("value", value_c);
         //iussum_main.session.setAttribute("selector", selector_c);
         //iussum_main.session.setAttribute("bad_css", bad_css_c);
         //iussum_main.session.setAttribute("prop_amt", prop_amt_c);
        }
                    //end formatter
                    //iussum_main.formatter();
                    //String archon_c=(String)iussum_main.session.getAttribute("zealots");
                    //out.println(archon_c);
                    //build vars from formatter
                    //String[] property_c=(String[])iussum_main.session.getAttribute("property");
                    //String[] value_c=(String[])iussum_main.session.getAttribute("value");
                    //String selector_c=(String)iussum_main.session.getAttribute("selector");

                    //Integer bad_css_obj=(Integer)session.getAttribute("bad_css");
                    //int bad_css_c = bad_css_obj.intValue();
                    //out.println("bad_css: "+bad_css_obj);
                    //out.println(selector_c);
                    //Integer prop_amt_obj=(Integer)iussum_main.session.getAttribute("prop_amt");
                    //out.println(prop_amt_obj);

                    //int prop_amt_c = prop_amt_obj.intValue();

                    //end build
                    //System.out.println("591");
                    if(bad_css_c==1){
                        %><span class="badcss"><i><%out.print(css_id_real_c[z1]);%></i></span><%
                        %><span class="badcss"><i><%out.print(css_properties_c[z1]);%></i></span><%
                        out.println();
                        bad_css_c=0;
                        }
                    else{
                    if(isheading==1){
                        %><i><span class="heading"><%out.print(heading);out.println();%></span></i><%
                        output=(output+heading+"\\u000D\\u000A");
                        }
                    //\\u000D\\u000A
                    %><span class="selector"><%out.print(indent_temp+selector_c);%><span class="begbracket">{</span></span><%out.println();%><%
                    output=(output+indent_temp+selector_c+"{"+"\\u000D\\u000A");
                    for(y1=0; y1<prop_amt_c; y1++){
                        if(value_c[y1]=="bears_in_the_css"){
                            continue;
                            }
                        %><span class="property"><%out.print(indent_temp+property_c[y1]);%></span><span class="twodots">:</span><span class="value"><%out.print(value_c[y1]);%></span><span class="semicolon">;</span><%out.println();%><%
                        output=(output+indent_temp+property_c[y1]+":"+value_c[y1]+";"+"\\u000D\\u000A");
                        //output=(output+property_c[y1]);
                    //out.println(indent_temp+iussum_main.properties[y]);
                    }%><span class="endbracket"><%out.println(indent_temp+"}");%></span><%
                        output=(output+indent_temp+"}"+"\\u000D\\u000A");
                    }

                    }
                //iussum_main.initilize();

//System.out.println(output);
//output=("{thisisbad};"+"\\u000D\\u000A"+"ok");
//output="\\u0027";
//output="'Lucida Grande'";
//byte[] utf8 = spec.getBytes("UTF-8");

//byte[] utf8 = output.getBytes("UTF-8");
//int z;
//String spec = new String(utf8, "UTF-8");
//System.out.println(spec);
//for(z=0; z<utf8.length; z++){
  //  System.out.println(utf8[z]);
    //}
                // output = new String(utf8, "UTF-8");
                 //output="cats";
output=output.replaceAll("'","\\\\u0027");
//output=output.replaceAll("\\f","\\u000D\\u000A");
//output = output.replaceAll("\\p{Cntrl}", "");
output=output.replaceAll("\\r\\n", "\\\\u000D\\\\u000A");
						  %><!--</textarea>-->


                      </pre>


                      </div>
                  </div>
                        </div>
			</div>
          <script type="text/javascript" src="ZeroClipboard.js"></script>

<!--var s='<%=output%>'; -->
                <script language="JavaScript">



                        var clip = new ZeroClipboard.Client();

                        var s='<%=output%>';
                        clip.setText(s);
                        clip.glue( 'copy-button' );
                </script>




		</div>


                      <div id="footer">&copy; 2011 Chris & Brian Scott. All rights reserved.</div>



            </div> <!-- close div wrap all -->
    </body>
</html>

package com.stylesorter.servlet;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.InputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.io.DataInputStream;

import org.apache.commons.io.IOUtils;

import java.net.*;

import com.stylesorter.*;

@WebServlet("/SorterWebApp")
public class SorterWebApp extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse   response) throws ServletException, IOException {

		doPost(request, response);
}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
		System.out.println("line 36");
    	ArrayList<String> list=new ArrayList<String>();
    	String css_data="";
        String input="";
        HttpSession session;
        session = req.getSession(true);

        String[] options;
        options= req.getParameterValues("option");
    if(req.getParameter("email")!=null){

    	//System.out.println("jubba");
        String email;
        String feedback;
        email = req.getParameter("email");
        feedback = req.getParameter("feedback");
        //System.out.println(feedback);
        Properties props = new Properties();
        //Session session1 = Session.getInstance(props, null);

        String msgBody = feedback;
        /**
        try {
            Message msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress("19scott19@gmail.com", "gmail.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("brianscott0017@yahoo.com", "cssformater.com"));
            msg.addRecipient(Message.RecipientType.TO,
                    		 new InternetAddress("chrisscott11us@yahoo.com", "cssformatter.com"));
            msg.setSubject(email+" Feedback regarding css-sorter");
            msg.setText(msgBody);
            Transport.send(msg);

        } catch (Exception e) {
            // ...
        }
         */
            //resp.sendRedirect("/index.jsp");
        	
        }
        
    if(req.getParameter("Copy&Paste")!=null){

    css_data=req.getParameter("content");
    input=req.getParameter("html");
    list=new ArrayList<String>(Arrays.asList(options));
    }

    else if(req.getParameter("ByUrl")!=null){
                //get css_data
                String css_add=req.getParameter("css_url");
                URL css = new URL(css_add);/* http://www.yahoo.com/ */
    	    DataInputStream dis;
    	    String css_input;
    	    dis = new DataInputStream(css.openStream());
                css_input= IOUtils.toString(dis, "UTF-8");
                dis.close();
                css_data=css_input;

                //get html input if using HtmlSorter
                if(req.getParameter("html_url") !=null){
                String html_add=req.getParameter("html_url");
                URL html = new URL(html_add);/* http://www.yahoo.com/ */
    	    DataInputStream dis1;
    	    String html_input;
    	    dis1 = new DataInputStream(html.openStream());
                html_input= IOUtils.toString(dis1, "UTF-8");
                dis.close();
                input=html_input;
                }
                list=new ArrayList<String>(Arrays.asList(options));
    }

    else {
    list=new ArrayList<String>();
    String upload_input;
    String html_input;
    //Logger log =
    //Logger.getLogger(SorterWebApp.class.getName());
    ServletFileUpload params = new ServletFileUpload();
    try{
    FileItemIterator iterator1 = params.getItemIterator(req);
    while (iterator1.hasNext()) {
     FileItemStream item1 = iterator1.next();
        InputStream stream1 = item1.openStream();
        if (item1.isFormField()) {
            String cat=IOUtils.toString(stream1, "UTF-8");

            if(cat.equals("comments")){
             list.add("comments");
            }
            else if (cat.equals("safesort")){
             list.add("safesort");
            }
            else if (cat.equals("indenta")){
             list.add("indenta");
            }
            else if(cat.equals("alpha")){
                list.add("alpha");
            }
            else if(cat.equals("seq")){
                list.add("seq");
            }
             else if(cat.equals("nice")){
                 list.add("nice");
            }
             else if(cat.equals("cat")){
                 list.add("cat");
            }
            else if(cat.equals("indent")){
                list.add("indent");
            }
            else{
              continue;
            }
        }
        else{
            String file_id;
            file_id=item1.getFieldName();
      //      log.warning("Got an uploaded file: " + item1.getFieldName() +
        //                  ", name = " + item1.getName());
            if(file_id.equals("upload")){
                upload_input = IOUtils.toString(stream1, "UTF-8");
            css_data=upload_input;
            session.setAttribute("css_data", css_data);
            }
            else{
                html_input= IOUtils.toString(stream1, "UTF-8");
                input=html_input;
                session.setAttribute("input", input);
            }

        }
    }
    }
    catch(Exception e){

    }
    	
    }
    if(css_data.equals("") || list.size()<1){
    	resp.sendRedirect("index.jsp"); // No CSS! redirect back to main page
    	return;
    }
    System.out.println(css_data);
    StyleSheet css = new StyleSheet(css_data);
    int i;
    for(int x=0; x<list.size(); x++){
    	
    	if (list.get(x).equals("alpha")){ // Alphabetical Sort
    		/** Determines the program flow based of the provided list 'options'.
    		 *  Strings in this list are compared against hard coded values to see
    		 *  which code needs to be executed. I.E if the list contains the value
    		 *  'comments', removeComments() will execute.
    		 */
    			Boolean bool=false;
    			Boolean dool=false;
    			// Loop through passed in options to determine how to sort
    			for(i=0; i<list.size(); i++){
    				if(list.get(i).equals("comments")){
    					bool=true; // True: remove comments
    				}
    				if(list.get(i).equals("indenta")){
    					dool=true; // don't change cascading order
    				}
    			}
    				if(bool){ 
    					// Break the CSS rules into selectors and declarations
    					css.removeComments();
    					// Alphabetize the CSS rules
    					css.sortAlphabetically();
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    				}
    				else{ // Leave the comments in the CSS
    					css.removeComments(); // CSS rules without comments
    					StyleSheet commented= new StyleSheet(css_data); // CSS rules with comments
    					// Map the uncommented selectors and declarations to the commented ones
    					Map<String, String> selectorMap=css.map(css.styles.selectors, commented.styles.selectors);
    					Map<String, String> declarationMap=css.map(css.styles.declarations, commented.styles.declarations);
    					
    					// Alphabetize the uncommented CSS rules
    					css.sortAlphabetically();

    					// Retrieve the commented CSS rules for each sorted uncommented rule
    					for(int n=0; n<css.styles.selectors.size(); n++){
    						// Set the sorted selectors & declarations to the mapped commented values
    						css.styles.selectors.set(n, selectorMap.get(css.styles.selectors.get(n)));
    						css.styles.declarations.set(n, declarationMap.get(css.styles.declarations.get(n)));
    					}
    					
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    				}

    	}
    	if (list.get(x).equals("indent")){ // Sub-selector Sort
    			Boolean bool=false;
    			Boolean cool=false;
    			Boolean dool=false;
    			// Loop through passed in options to determine how to sort
    			for( i=0; i<list.size(); i++){
    				if(list.get(i).equals("comments")){
    					bool=true; // True: remove comments
    				}
    				if (list.get(i).equals("safesort")){
    					cool=true; // don't change cascading order
    				}
    				if(list.get(i).equals("indenta")){
    					System.out.println(dool);
    					dool=true; // don't change cascading order
    				}
    			}
    				if(bool){
    					// Break the CSS rules into selectors and declarations
    					css.removeComments();
    					if(cool == false){
    						// Sort by putting sub-selectors below main ones
    						css.sortByClass();
    					}
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    					
    				}
    				else{ // Leave the comments in the CSS
    					css.removeComments(); // CSS rules without comments
    					StyleSheet commented = new StyleSheet(css_data);
    					// Map the uncommented selectors and declarations to the commented ones
    					Map<String, String> selectorMap=css.map(css.styles.selectors, commented.styles.selectors);
    					Map<String, String> declarationMap=css.map(css.styles.declarations, commented.styles.declarations);
    					
    					if(cool == false){ // Not a safe-sort, group the selectors
    						// Sort by putting sub-selectors below main ones
    						css.sortByClass();
    					}
    					
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    					
    					// Retrieve the commented CSS rules for each sorted uncommented rule
    					for(int n=0; n<css.styles.selectors.size(); n++){
    						// Set the sorted selectors & declarations to the mapped commented values
    						css.styles.selectors.set(n, selectorMap.get(css.styles.selectors.get(n)));
    						css.styles.declarations.set(n, declarationMap.get(css.styles.declarations.get(n)));
    					}
    					
    				}
    				
    	}
    	if (list.get(x).equals("seq")){ // By HTML Sort
    			Boolean bool=false;
    			Boolean dool=false;
    			// Loop through passed in options to determine how to sort
    			for(i=0; i<list.size(); i++){
    				if(list.get(i).equals("comments")){
    					bool=true; // True: remove comments
    				}
    				if(list.get(i).equals("indenta")){
    					System.out.println(dool);
    					dool=true; // don't change cascading order
    				}
    			}
    				if(bool){
    					// Break the CSS rules into selectors and declarations
    					css.removeComments();
    					css.sortByHTMLclass(input);
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    					
    				}
    				else{ // Leave the comments in the CSS
    					css.removeComments();  // CSS rules without comments 
    					StyleSheet commented = new StyleSheet(css_data); // CSS rules with comments
    					// Map the uncommented selectors and declarations to the commented ones
    					Map<String, String> selectorMap=css.map(css.styles.selectors, commented.styles.selectors);
    					Map<String, String> declarationMap=css.map(css.styles.declarations, commented.styles.declarations);
    					css.sortByHTMLclass(input);
    					
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    					// Retrieve the commented CSS rules for each sorted uncommented rule
    					for(int n=0; n<css.styles.selectors.size(); n++){
    						// Set the sorted selectors & declarations to the mapped commented values
    						css.styles.selectors.set(n, selectorMap.get(css.styles.selectors.get(n)));
    						css.styles.declarations.set(n, declarationMap.get(css.styles.declarations.get(n)));
    					}

    					
    				}	
    	}
    	if (list.get(x).equals("nice")){ 
    			Boolean bool=false;
    			Boolean dool=false;
    			// Loop through passed in options to determine how to sort
    			for(i=0; i<list.size(); i++){
    				if(list.get(i).equals("comments")){
    					bool=true; // True: remove comments
    				}
    				if(list.get(i).equals("indenta")){
    					dool=true; // don't change cascading order
    				}
    			}
    				if(bool){
    					// Break the CSS rules into selectors and declarations
    					css.removeComments();
    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    					
    				}
    				else{ // Leave the comments in the CSS
    					css.removeComments(); // CSS rules without comments
    					StyleSheet commented = new StyleSheet(css_data); // CSS rules with comments
    					// Map the uncommented selectors and declarations to the commented ones
    					Map<String, String> selectorMap=css.map(css.styles.selectors, commented.styles.selectors);
    					Map<String, String> declarationMap=css.map(css.styles.declarations, commented.styles.declarations);

    					if(dool){
    						css.indentSubSelectors();
    					}
    					else{
    						css.styles.indent=new int[css.styles.selectors.size()];
    						for(i=0; i<css.styles.selectors.size(); i++){
    							css.styles.indent[i]=0;
    						}
    					}
    					
    					// Retrieve the commented CSS rules for each sorted uncommented rule
    					for(int n=0; n<css.styles.selectors.size(); n++){
    						// Set the sorted selectors & declarations to the mapped commented values
    						css.styles.selectors.set(n, selectorMap.get(css.styles.selectors.get(n)));
    						css.styles.declarations.set(n, declarationMap.get(css.styles.declarations.get(n)));
    					}		
    				}
    			
    	}
    	
    	/**catch(IndexOutOfBoundsException e){
    		System.out.println("exception caught");
    		resp.sendRedirect("/error.jsp");
    		
    	}**/
    }

    String[] s = css.styles.selectors.toArray(new String[list.size()]);
    String[] d = css.styles.declarations.toArray(new String[list.size()]);
    session.setAttribute("css_id_real", s);
    session.setAttribute("css_properties", d);    
    session.setAttribute("indent_amt", css.styles.indent);
    resp.sendRedirect("answer.jsp");
  
    }
}














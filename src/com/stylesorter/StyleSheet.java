package com.stylesorter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.*;
import com.stylesorter.Styles;

/** Class containing methods for manipulating Cascading Style Sheets (CSS).
 * 
 * @author Brian Scott
 *
 */
public class StyleSheet {
	public Styles styles;
	// Instance Variables
	String stylesheet;
	File stylesheetFile;
	
	// Constructors
	public StyleSheet(){
		
	}
	public StyleSheet(String stylesheet){
		this.stylesheet= stylesheet;
		styles=new Styles(stylesheet);
	}
	
	// Acessor Methods
	
	// Sort Methods
	
	// Sorts stylesheet by class
	public void sortByClass(){
		ClassSorter sorter = new ClassSorter();
		sorter.groupByClass(styles); // Sorts the stylesheet's styles by class 
		stylesheet= styles.toString(); // Set a sorted string
	}
	// Sorts stlyesheet into alphabetical order
	public void sortAlphabetically(){		
		AlphaSorter sorter=new AlphaSorter(); // Create new AlphaSorter		
		sorter.alphabatize(styles); // Alphabetize styles
		stylesheet= styles.toString(); // Set the stylesheet string to the sorted styles
	}
	// Sorts stylesheet by the order in which classes appear in an html string
	public void sortByHTMLclass(String html){
		HtmlSorter sorter = new HtmlSorter();
		sorter.sortClasses(sorter.getHtmlClasses(html), styles); // First argument is html classes
		stylesheet= styles.toString();
	}
	
	// Format Methods
	
	public void indentSubSelectors(){
		Formatter format = new Formatter();
		styles.indent = format.indentSubSelectors(styles.selectors); // Set this styles object indent array to indent values
	}
	public void removeComments(){
	    stylesheet = stylesheet.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)", "");
	     }
	public void stringToFile(){
		try{
		FileUtils.writeStringToFile(stylesheetFile, stylesheet);
		}
		catch(Exception e){
			System.out.println("Error writing string to file");
		}
	}
	
	// Utility Methods
	
	/** Checks a string at passed in index for a delimiter. Use to ensure that
	 *  a selector like 'navbar' isn't grouped as a sub-selector of 'nav'
	 *  becuase of it's similarity.
	 *  
	 *  @param string you want to check
	 *  @param index of the string where you want to check
	 *  @return true=delimiter, false=no delimiter
	 */
	public boolean delimiterCheck(int index, String selector){
		
		char[] delimiters= {'.',' ',':','#','['};
		boolean delimiter=false;
		// Check to make sure selector is long enough to look at index
		if(selector.length()<=index){
			delimiter=true; // Delimiter check passed by default because not long enough
		}
		else{
	    // Look at index for a delimiter		
		for(int i=0; i<delimiters.length; i++){
			if(selector.charAt(index)==delimiters[i]){			
				delimiter=true;
			}
		 }
		}
		return delimiter;
	}
	
	/** Loops through the passed in list of selectors to see if the passed in
	 *  selector is a sub-selector to any in the list.
	 * 
	 *  @param selector the selector we are checking
	 *  @param index of the selector in the list of selectors
	 *  @param selectors the list of selectors we are checking against
	 *  @return True if is sub-selector, False if isn't
	 */
	public boolean subselectorCheck(String selector, int index, ArrayList<String> selectors){
		boolean subSelector=false;
		for(int i=0; i<selectors.size(); i++){
			if(i != index // Make sure we arn't checking against ourself
				 // Check for selector as substring of the selector at i
				 && selector.indexOf(selectors.get(i)) ==0
				 // Make sure we are longer(a sub-selector will always be longer than a selector!)
			     && selector.length()>selectors.get(i).length()){
				
				/* Do a delimiter check. A sub-selector will have a delimiter
				 * at the index of the selector's length we are checking. i.e 
				 * 'nav.ul' is a sub-selector of 'nav' because '.' appears at
				 * index 3(the length of the selector) of 'nav.ul'.
				 */
				// Pass check() the length of selector at i
				if(delimiterCheck(selectors.get(i).length(), selector)){
					
					subSelector=true; // delimiter test passed, we have a sub-selector
				}
			}
			
		}
		return subSelector;
	}
	
	/** Maps keys to values (may NOT contain duplicate keys)
	 * 
	 *  @param keys
	 *  @param values
	 *  @return map 
	 */
	public Map<String, String> map(ArrayList<String> keys, ArrayList<String> values){
    	Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
		return map;
	}
	

}

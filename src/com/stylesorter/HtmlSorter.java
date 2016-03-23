package com.stylesorter;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class HtmlSorter extends StyleSheet {
	
	// Local variables
	private ArrayList<String> classes=new ArrayList<String>(); // HTML classes
	private ArrayList<Integer> classesOrder=new ArrayList<Integer>(); // Sequential order of HTML classes
	
	public ArrayList<String> getHtmlClasses(String html){

		// Add all HTML classes to the list
		findString(("class=\""), false, html);
		// Add all id's to the list
		findString("id=\"", false, html);		
		//Now we need to add all tags to the list
		// A hard-coded list of special tags which can be classes in a CSS page
		String[] htmlTags= {"<body","<pre","<abbr","<img","<input"};
		// Loop to check for each of the above tags in the HTML string
		for(int i=0; i<htmlTags.length; i++){
			findString(htmlTags[i], true, html);
		}
		
		// Map the classes to the indexes then sort the indexes and sort the classes in same order
        SortedMap<Integer, String> pairs = new TreeMap<Integer, String>();
        for (int i = 0; i < classesOrder.size(); i++) {
            pairs.put(classesOrder.get(i), classes.get(i));
        }      
        // Sorts the index sequentially
        java.util.Collections.sort(classesOrder);

        // Rearrange the classes by how the indexes were just sorted
        int i = 0;
        for (Map.Entry<Integer, String> e : pairs.entrySet()) {
            classes.set(i++, e.getValue());
        }
		return classes;
	}
	
	private void findString(String identifier, Boolean isTag, String html){
		int i=0;
		while(i<html.length()){
			int j=0;
			// If there are no more occurrences of the substring then break
			if(html.indexOf(identifier, i) == -1){
				break;
			}
			else{
				String htmlClass; // Temporary holder for the found class or id
				// Start of the class or id name '"' put this in int j
				j=(html.indexOf(identifier, i))+identifier.length();
				// If the identifier is a tag add the tag to the list
			if(isTag){
				htmlClass=(identifier.replaceAll("<", "")); // turns '<body' to <body>
				classes.add(htmlClass); // Add tag to list
				classesOrder.add(j); // Add index of tag to the index list
			}
			// Else the identifier is a class or id, add the name to the list
			else{
				// Cut out name between the quotes i.e class="nav"	
				htmlClass=(html.substring((j), (html.indexOf('"', j)))); 
				classes.add(htmlClass); // Add the class or id to the list
				// Add the location in the string where the class was found(for sorting purposes)
				classesOrder.add(j); 
			}
			i=j; // increment the loop by 'j' the location of the last found class or id
			}
		}
	}
	
	/** Looks for HTML classes, id's and unique tags in the CSS selectors. 
	 *  Attempts to sort the CSS rules(selectors & declarations) by the order
	 *  of the HTML classes.
	 * 
	 *  @param classes  These are the HTML style classes, id's and tags
	 *  @param selectors 
	 *  @param declarations
	 *  @return data object containing the sorted selectors and declarations
	 */
	public void sortClasses(ArrayList<String> classes, Styles styles){
		ArrayList<String> selectorsSorted=new ArrayList<String>();
		ArrayList<String> declarationsSorted=new ArrayList<String>();
		int i=0;
		// Loop through HTML classes, id's and unique tags
		for(i=0; i<classes.size(); i++){
			// Loop through CSS selectors to search for HTML classes etc.
			for(int n=0; n<styles.selectors.size(); n++){
				
				// Find a match by seeing if HTML class found in selector string
				if(styles.selectors.get(n).indexOf(classes.get(i))==0 
				|| styles.selectors.get(n).indexOf(classes.get(i))==1 
				&& subselectorCheck(styles.selectors.get(n), n, styles.selectors) !=true){
					//System.out.println("n "+selectors.get(n));
					// Found an HTML class or id in the CSS
					// Get all that selector's sub-selectors and group them
					ClassSorter sorter = new ClassSorter();
					Styles stylesheetClass = sorter.getClass(styles, i);
					// Set selectors and declarations without the group 
					styles.selectors=stylesheetClass.getSelectors();
					styles.declarations=stylesheetClass.getDeclarations();
					// Add the grouped selectors and declarations to the sorted list
					for(int j=0; j<stylesheetClass.selectors.size(); j++){
						selectorsSorted.add(stylesheetClass.selectors.get(j)); // Adding selector
						declarationsSorted.add(stylesheetClass.declarations.get(j));  
					}
				}
			}
			
		}
		// Now put all the CSS classes not found at the bottom of the sorted list
		for(i=0; i<styles.selectors.size(); i++){
			selectorsSorted.add(styles.selectors.get(i)); // Adding selector
			declarationsSorted.add(styles.declarations.get(i)); // Adding declaration	
		}
		styles.setSelectors(selectorsSorted);
		styles.setDeclarations(declarationsSorted);	
	}

}

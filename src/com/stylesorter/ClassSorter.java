package com.stylesorter;

import java.util.ArrayList;

public class ClassSorter extends StyleSheet {
	
	/** Sorts a list of selectors along with it's list of declarations
	 *  by putting sub-selectors below selectors. The first loop is the 
	 *  selector we are seeing if has sub-selectors. The second is the loop
	 *  through potential sub-selectors. First we check to see that the selector
	 *  in the i loop is NOT a sub-selector via 'SubSelectorChecker' then make
	 *  a new selector group(with it's respective declaration group) and start 
	 *  adding sub-selectors to it. Once the group is completed(after the n loop),
	 *  we add it to the sorted list of selectors. After every selector is
	 *  checked for sub-selectors we return the sorted lists in a data object.
	 * 
	 *  @param selectors
	 *  @param declarations
	 *  @return data object containing sorted selector and declaration lists
	 */
	public void groupByClass(Styles styles){
		ArrayList<String> selectorsSorted=new ArrayList<String>(); // List for sorted selectors
		ArrayList<String> declarationsSorted=new ArrayList<String>(); // List for sorted declarations
		// Check to see if selector at i has any sub-selectors	
		for(int i=0; i<styles.selectors.size(); i++){	
			// First check to see if selector is a sub-selector to something else					
			if(subselectorCheck(styles.selectors.get(i), i, styles.selectors)){ // True that we are a sub-selector
				continue; 
		  }
			else{				
				// False, selector must be a main selector with possible sub-selectors
				// Get all that selectors sub-selectors and group them
				//SelectorGroup grouper=new SelectorGroup();
				//CSSGroup group=grouper.group(i, styles.selectors, styles.declarations);
				Styles stylesheetClass = getClass(styles, i);
				// Set selector and declaration list without the group 
				if(stylesheetClass.selectors.size()>1){
					i=-1;
				}
				else{i=i-1;}
				// Add the grouped selectors and declarations to the sorted list		
				for(int j=0; j<stylesheetClass.selectors.size(); j++){
					selectorsSorted.add(stylesheetClass.selectors.get(j)); // Adding selector
					declarationsSorted.add(stylesheetClass.declarations.get(j));  
				}
			}
		}
		// Set the styles selectors and decs equal to their sorted lists.
		styles.selectors = selectorsSorted;
		styles.declarations = declarationsSorted;
	}
	public Styles getClass(Styles styles, int i){
		Styles stylesheetClass = new Styles(); // Make a new styles object to hold the class
		stylesheetClass.selectors.add(styles.selectors.get(i)); // Add main selector to the class
		stylesheetClass.declarations.add(styles.declarations.get(i)); // Add main selector's declaration
		styles.declarations.remove(i); // Remove declaration from styles
		styles.selectors.remove(i); // Remove selector from styles    	 	
		// Loop back through selectors to see if selector at n is a sub-selector
		for(int n=0; n<styles.selectors.size(); n++){
			// Make sure we don't make current selector a sub-selector to itself
			// Look for the selector as a substring at the start of the selector
			if (styles.selectors.get(n).indexOf(stylesheetClass.selectors.get(0))==0 
				// Also look one char ahead incase we have something like '.container'
				|| styles.selectors.get(n).indexOf(stylesheetClass.selectors.get(0))==1){
				
				// Do a delimiter check
				if(delimiterCheck(stylesheetClass.selectors.get(0).length(), styles.selectors.get(n))){
					stylesheetClass.selectors.add(styles.selectors.get(n)); // add sub-selector to group
					styles.selectors.remove(n); // Remove from list
					stylesheetClass.declarations.add(styles.declarations.get(n)); // Add declaration
					styles.declarations.remove(n); // Remove declaration from list
					n=-1;
				}	
				
			}
			
		}
		// Now alphabetize the selectors class(places sub-selectors below main ones).
		AlphaSorter alpha = new AlphaSorter(); // Create new AlphaSorter
		alpha.alphabatize(stylesheetClass); // alphabetize
		
		return stylesheetClass; // Return sorted stylesheet class
		
	}
	
}

package com.stylesorter;

import java.util.ArrayList;
import java.util.Map;
import com.google.common.collect.Multimap; // Generic friendly libraries
import com.google.common.collect.TreeMultimap;
import com.stylesorter.Styles;

public class AlphaSorter extends StyleSheet {

	/** Sorts a styles object into alphabetical order by the style's selectors.
	 *  
	 *  Uses two maps: One for retreival of selectors with non-sorting characters (i.e '#,: , .').
	 *  And another to alphabetically sort by google's Multimap methods.
	 * 
	 *  @param styles
	 */
	public void alphabatize(Styles styles){
		// Create a map for selectors to fix '#main, .div' throwing off the alphabetical order.	
		ArrayList<String> c=new ArrayList<String>();
		for(int i=0; i<styles.selectors.size(); i++){
			String str=styles.selectors.get(i).replace('#', ' '); // Remove bad characters for map
			str=str.replace(':', ' ');
			str=str.replace('.', ' ');
			str=str.trim();
			c.add(str);
		}
		Map<String, String> selectorMap=map(c, styles.selectors); // Selector map
		// Map the declarations to the selectors and sort naturally
		Multimap<String, String> map = TreeMultimap.create();
		for(int n=0; n<styles.selectors.size(); n++){
			// Putting into the map auto sorts by natural ordering (alphabetical)
			map.put(c.get(n), styles.declarations.get(n));
		}
		// Loop through the map's entries to get the alphabetized selectors & declarations
		@SuppressWarnings("unused")
		int i=0;
		styles=new Styles();
	    for (Map.Entry<String, String> e : map.entries()) {
	    	styles.selectors.add(e.getKey()); // Set a selector to the entry key
	    	styles.declarations.add(e.getValue()); // Set a declaration to the value
	        i++; // Increment the index of where we are setting
	    }
		for(int n=0; n<styles.selectors.size(); n++){
			// Retrieve original selectors from selectorMap
			styles.selectors.set(n, selectorMap.get(styles.selectors.get(n)));
		}
	} 
}

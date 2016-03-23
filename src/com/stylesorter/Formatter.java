package com.stylesorter;

import java.util.ArrayList;

public class Formatter extends StyleSheet {
	StyleRule rule=new StyleRule();
	
	/** Formats the parts of a CSS rule including the comment, selector,
	 *  property and value. Sets these values to the CssFormatter data object.
	 * 
	 *  @param selector
	 *  @param declaration
	 *  @param IndentAmount that the rule should be indented (0 if no indent)
	 */
	public void formatRule(String selector, String declaration, int IndentAmount){
		// Build the indent string (IndentAmount * tab char)
		String indent="";
		for(int i=0; i==IndentAmount; i++){
			indent=indent+'\t';
		}	
		// Format the selector
		formatSelector(selector, indent);	
		
		// Now format the declaration
		formatDeclaration(declaration, indent);
	}
	/** Formats a selector by adding indentation to it and separating any
	 *  comments which precede it.  
	 * 
	 *  @param selector
	 *  @param indent If there doesn't have to be indentation this could be ""
	 */
	private void formatSelector(String selector, String indent){
		// If there is a comment place it in it's own string
		rule.comment=indent;
		// First check to see if there is a comment at all
		if(selector.indexOf("/*") !=-1){
			// Found comment now copy it out into it's own string
			rule.comment=selector.substring(selector.indexOf("/*"), selector.lastIndexOf("*/"));
			// Make selector the string after the comment
			rule.selector=indent+selector.substring(selector.lastIndexOf("*/"));
		}
		else{ // No comment just a selector
			rule.selector=indent+selector;
		}
	}
	/** Formats a declaration by looping through a char array of it and 
	 *  separating the properties from the values. Adds indentation to these
	 *  if there is any and then adds them to property and value lists.
	 * 
	 *  @param declaration
	 *  @param indent
	 */
	private void formatDeclaration(String declaration, String indent){
		
		char[] chars=declaration.toCharArray();
		String substring=""; // Could be either a property or a value
		// Loop through the chars of declaration
		for(int i=0; i<chars.length; i++ ){
			if(chars[i]==':'){ // Reached end of property
				rule.values.add(indent+substring.trim()); // Add property to list
				substring=""; // Clear the substring
			}
			else if(chars[i]==';'){ // Reached end of value
				rule.properties.add(indent+substring.trim()); // Add to value list
				substring="";
			}
			else{
				// Add chars to substring which could be property chars or value chars
				substring=substring+chars[i]; 
			}
		}
	}
	
	/** Takes a group of selectors and assigns them each an indent amount
	 *  from the left margin. Loops through the selector list checking the 
	 *  selector at i against the previous selector. If it is a sub-selector
	 *  it's indent amount is set to 1+ the amount of the main/previous
	 *  selector. Also keeps a memory of the current selector group and checks
	 *  if it is a sub to any of those. If it is it again sets the indent amount
	 *  to 1+ indent amount of the main.
	 * 
	 *  @param selectors that you want to assign indent amounts to
	 *  @return indentAmount a corresponding list of indent amounts for 'selectors'
	 */
	public int[] indentSubSelectors(ArrayList<String> selectors){
		int[] indentAmount=new int[selectors.size()];
		String selector;
		// Initialize indentAmount elements to 0
		for(int i=0; i<indentAmount.length; i++){
			indentAmount[i]=0;
		}
		ArrayList<String> selectorGroup=new ArrayList<String>();
		ArrayList<Integer> indentGroup=new ArrayList<Integer>(); 
		ArrayList<String> mainSelector=new ArrayList<String>();	
		selectorGroup.add(selectors.get(0));
		indentGroup.add(indentAmount[0]);
		
		for(int i=1; i<selectors.size(); i++){
			selector=selectors.get(i);
			// Add the previous selector in the loop to mainSelector list
			mainSelector.clear();
			mainSelector.add(selectors.get(i-1));
			// Check selector at i against the previous selector to see if it's a sub-selector
			/* Pass check() a -1 as the index since we are only sending the
			 * previous selector to check and don't have to worry about
			 * checking against ourself.
			 */		
			if(subselectorCheck(selector, -1, mainSelector)){ // Is a sub-selector
				selectorGroup.add(selector); // add to the group
				// Add an indent amount of 1+ the indent amount of the main selector
				indentGroup.add((indentAmount[i-1]+1)); 
				indentAmount[i]=(indentAmount[i-1]+1); 
				/* Now a loop to check if we are a sub-selector to anything
				 * else in the group. i.e 'nav.ul.a' might be a sub to 'nav'
				 * but it is also a sub to 'nav.ul'. Then indent amount in this
				 * case should be 1+ that of 'nav.ul' not 'nav'.
				 */
				for(int n=0; n<selectorGroup.size(); n++){
					mainSelector.clear(); // Clear main so we can reuse
					mainSelector.add(selectorGroup.get(n)); // selector at n
					/* If selector is a sub to the selector at n and the indent
					 * amount of the selector at n is greater than selector's
					 * indent amount we need to make selector's indent amount=
					 * n(indent amount) +1.
					 */
					if(selectorGroup.get(n).equals(selector)){	
						continue; // continue if we are comparing against ourself
					}
					if(subselectorCheck(selector, -1, mainSelector) && indentGroup.get(n)>indentAmount[i]){
						indentAmount[i]=indentGroup.get(n)+1; // n(indent amount)+1
						indentGroup.set(indentGroup.size()-1, indentGroup.get(n)+1); // do for group
					}
				}
			}
			else{			
				// Search the group to see if we are a sub-selector to any of those
				Boolean bool=false;
				for(int n=0; n<selectorGroup.size(); n++){	
					mainSelector.clear(); // Clear main so we can reuse
					mainSelector.add(selectorGroup.get(n)); // selector at n
					/* If selector is a sub to the selector at n and the indent
					 * amount of the selector at n is greater than selector's
					 * indent amount we need to make selector's indent amount=
					 * n(indent amount) +1.
					 */
					if(selectorGroup.get(n).equals(selector)){	
						continue; // continue if we are comparing against ourself
					}
					if(subselectorCheck(selector, -1, mainSelector) ){
						indentAmount[i]=indentGroup.get(n)+1; // n(indent amount)+1
						indentGroup.add(indentGroup.get(n)+1);
						selectorGroup.add(selector); // Add the selector to the group
						bool=true;
					}
				}
				if(bool==false){
				selectorGroup.clear(); // Clear for reuse
				indentGroup.clear(); // Clear for reuse
				selectorGroup.add(selector); // Add selector at i to the group
				indentGroup.add(indentAmount[i]); // Add the indent amount
				}
			}
		}
		return indentAmount;		
	}

}

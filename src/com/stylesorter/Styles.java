package com.stylesorter;

import java.util.ArrayList;

/** Representation of the styles which make up a CSS. Each style has a selector 
 *  and declaration.Here all the styles of a CSS are represented by a list of 
 *  selectors with a corresponding list of declarations. 
 */
public class Styles {
	public ArrayList<String> selectors;
	public ArrayList<String> declarations;
	public int[] indent;
	
	// Constructors
	public Styles(){
		selectors=new ArrayList<String>();
		declarations=new ArrayList<String>();
	}
	public Styles(String stylesheet){
		selectors=new ArrayList<String>();
		declarations=new ArrayList<String>();
		getStyles(stylesheet);
	}
	public Styles(ArrayList<String> selectors,ArrayList<String> declarations){
		this.selectors=selectors;
		this.declarations=declarations;
	}
	public Styles(ArrayList<String> selectors,ArrayList<String> declarations, int[] indent){
		this.selectors=selectors;
		this.declarations=declarations;
		this.indent=indent;
	}
	
	// Setters
	public void setSelectors(ArrayList<String> selectors){
	    this.selectors=selectors;
	}
	public void setDeclarations(ArrayList<String> declarations){
	    this.declarations=declarations;
	}
	// Getters
	public ArrayList<String> getSelectors(){
	    return selectors;
	}
	public ArrayList<String> getDeclarations(){
	    return declarations;
	}
	
	public String toString(){
		String str="";
		for(int i=0; i<selectors.size(); i++){
			str= (str + selectors.get(i) +declarations.get(i)); 
		}
		return str;
	}
	
	/** Gets CSS styles and puts them into selector and declaration lists
	 * 
	 * @param css
	 * @return styles
	 */
	protected void getStyles(String css){
		String selector="";
		String declaration="";
		char[] rawCssChar = css.toCharArray(); // CSS code as char array
		int i;
		
		for(i=0; i < rawCssChar.length; i++){
			if(rawCssChar[i] == '{'){ // Found declaration!
				selectors.add(selector.trim()); // Add selector to ArrayList & trim whitespace
				selector=""; // Clear selector for reuse
				i++; // Move index past begin bracket '{' 
				while(rawCssChar[i] != '}' && i<rawCssChar.length){
					declaration = declaration + rawCssChar[i]; // Getting a declaration
					i++;
				}
				//i++; // Move index past end bracket '}'
				declarations.add(declaration); // Add declaration to ArrayList
				declaration=""; // Clear for reuse
			}
			else{
				selector = selector + rawCssChar[i]; // Getting a selector
			}
		}	
	}


}

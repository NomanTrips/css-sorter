package com.stylesorter;

import java.util.ArrayList;

/** Representation of a CSS rule including a selector, list of properties and
 *  corresponding values.
 */
public class StyleRule {
	// Formatting variables
	public String selector;
	public String comment;
	public ArrayList<String> properties=new ArrayList<String>();
	public ArrayList<String> values=new ArrayList<String>();

	// Setters
	public void setSelector(String selector){
	    this.selector=selector;
	}
	public void setComment(String comment){
	    this.comment=comment;
	}
	public void setProperties(ArrayList<String> properties){
	    this.properties=properties;
	}
	public void setValues(ArrayList<String> values){
	    this.values=values;
	}
	
	// Getters
	public String getSelector(){
	    return selector;
	}
	public String getComment(){
	    return comment;
	}
	public ArrayList<String> getProperties(){
	    return properties;
	}
	public ArrayList<String> getValues(){
	    return values;
	}

}

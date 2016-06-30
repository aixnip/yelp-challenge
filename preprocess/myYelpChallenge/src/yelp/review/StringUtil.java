/**
 * 
 */
package yelp.review;

/**
 * @author siliu
 *
 */
public class StringUtil {

	public static String reviseString(String oldstr){
		
		String newstr = oldstr;
		
		newstr = oldstr.replaceAll("[^a-zA-Z]+","");
//		newstr = newstr.replaceAll("\\/", "_");

		return newstr;
	}
}

/**
 * 
 */
package yelp.review;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author siliu
 *
 */
public class Business {
	
	private String businessfilepath;
	
	public Business(String businessfilepath){
		this.businessfilepath = businessfilepath;
	}
	
	public Map<String,JSONArray> extractCategories(){
		
		JSONParser parser = new JSONParser();
		Map<String, JSONArray> bc_map = new HashMap<String,JSONArray>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(businessfilepath));
			String line;
			while((line = br.readLine()) != null){
				//System.out.println("line: " + line);
				Object obj = parser.parse(line.trim());
				JSONObject jsonObject = (JSONObject) obj;

				String businessId = (String) jsonObject.get("business_id");
				//System.out.println(businessId);

				JSONArray categories = (JSONArray) jsonObject.get("categories");
				//System.out.println(categories);
				bc_map.put(businessId, categories);
			}
			//System.out.println(bc_map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return bc_map;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	String businessfilepath = "/Users/siliu/Desktop/yelp_dataset_challenge_academic_dataset/yelp_academic_dataset_business.json";
		//String jsonpath =  "/Users/siliu/Desktop/yelp_dataset_challenge_academic_dataset/test.json";
		Map<String, JSONArray> bc_map = new HashMap<String,JSONArray>();
		try {
			Business business = new Business(businessfilepath);
			bc_map = business.extractCategories();
			System.out.println(bc_map.size());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

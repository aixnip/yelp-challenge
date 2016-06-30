/**
 * 
 */
package yelp.review;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author siliu
 *
 */
public class Review {
	
	private String reviewfilepath;
	
	public Review(String reviewfilepath){
		this.reviewfilepath = reviewfilepath;
	}
	
	public Map<String,String> extractReview(){
		
		JSONParser parser = new JSONParser();
		Map<String, String> br_map = new HashMap<String,String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(reviewfilepath));
			String line;
			while((line = br.readLine()) != null){
				
				Object obj = parser.parse(line.trim());
				JSONObject jsonObject = (JSONObject) obj;

				String businessId = (String) jsonObject.get("business_id");
				String text = (String)jsonObject.get("text");
				
				br_map.put(businessId, text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return br_map;
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	String reviewfilepath =  "/Users/siliu/Desktop/yelp_dataset_challenge_academic_dataset/yelp_academic_dataset_review.json";
    	Map<String, String> br_map = new HashMap<String,String>();
		try {
			Review review = new Review(reviewfilepath);
			br_map = review.extractReview();
			System.out.println(br_map.size());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}

/**
 * 
 */
package yelp.review;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;

/**
 * @author siliu
 *
 */
public class Categorize {

	Business business;
	Review review;

	/**
	 * 
	 */
	public Categorize(Business business, Review review) {
		// TODO Auto-generated constructor stub
		this.business = business;
		this.review = review;
	}

	public void executeCategorize() {

		Map<String, JSONArray> bc_map = new HashMap<String, JSONArray>();
		Map<String, String> br_map = new HashMap<String, String>();

		bc_map = business.extractCategories();
		br_map = review.extractReview();

		// Store the [category, reviews] pair in HashMap cr_map
		// Map<String, ArrayList> cr_map = new HashMap<String, ArrayList>();

		// Iterate bc_map and br_map using businessId to map reviews to
		// different category
		int count = 0;
		for (String businessId : bc_map.keySet()) {
			count++;
			if (count % 5000 == 0) {
				System.out.println("------" + count + " records processed!" + "------");
			}
			JSONArray categories = bc_map.get(businessId);

			if (br_map.get(businessId) != null) {
				
				String text = br_map.get(businessId);

				// Iterate categories, gather all reviews for each category
				Iterator it = categories.iterator();
				while (it.hasNext()) {

					String category = (String) it.next();
					String cate = "reviewdata" + "/" + StringUtil.reviseString(category);
					File cateDir = new File(cate);
					int rank = 1;
					if (cateDir.exists()) {
						rank = cateDir.list().length + 1;
						String filename = cate + "/" + Integer.toString(rank);
						FileDir.createFile(filename, text);
					} else {
						cateDir.mkdir();
						rank = 1;
						String filename = cate + "/" + Integer.toString(rank);
						FileDir.createFile(filename, text);
					}

				}
			}

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String businessfilepath = "/Users/siliu/Desktop/yelp_dataset_challenge_academic_dataset/yelp_academic_dataset_business.json";
		String reviewfilepath = "/Users/siliu/Desktop/yelp_dataset_challenge_academic_dataset/yelp_academic_dataset_review.json";

		Business business = new Business(businessfilepath);
		Review review = new Review(reviewfilepath);

		Categorize cateObj = new Categorize(business, review);
		cateObj.executeCategorize();
	}

}

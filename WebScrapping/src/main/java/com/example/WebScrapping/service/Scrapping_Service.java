package com.example.WebScrapping.service;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class Scrapping_Service {
	
	public String CodechefRating(String url) throws IOException
	{
		String rating=null;
		Document doc=Jsoup.connect(url).get();
		
		Element ratingele=doc.selectFirst(".rating-number");
		if(ratingele!=null)
		{
			rating=ratingele.text();
		}
		else {
			rating="0";
		}
		
		return rating;
	}
	
	public String CodechefCC(String url) throws IOException
	{
		String cc=null;
		Document doc=Jsoup.connect(url).get();
		
		Element ccele=doc.selectFirst(".contest-participated-count b");
		if(ccele!=null)
		{
			cc=ccele.text();
		}
		else {
			cc="0";
		}
		
		return cc;
	}
	
	public String CodechefPSC(String url) throws IOException
	{
		String psc=null;
		Document doc=Jsoup.connect(url).get();
		
		Element pscelement=null;
		Elements pscele=doc.select("h3:contains(Total Problems Solved)");
		
		
		if(pscele!=null)
		{
			psc=pscele.text().replaceAll("\\D+", "");
		}
		else {
			psc="0";
		}
		
		return psc;
	}
	
	
	
	public String CodeForecesRating(String url) throws IOException
	{
		String rating=null;
		Document doc=Jsoup.connect(url).get();
		
		Elements liElements = doc.select("li:contains(Contest rating:)");

		if(liElements ==null)
		{
			return "0";
		}
        if (!liElements.isEmpty()) {
            Element liElement = liElements.first();
            Element ratingElement = liElement.selectFirst("span.user-gray");
            
            if (ratingElement != null) {
                 rating = ratingElement.text();
            } 
        }
		
		return rating;
	}
	
	public String CodeForcesSolvedCount(String url)throws IOException
	{
		String solvedCount=null;
		
		Document doc=Jsoup.connect(url).get();
		
		Element scele=doc.selectFirst("._UserActivityFrame_counterValue");
		
		if(scele==null)
		{
			return "0";
		}
		
		
		solvedCount=scele.text().replaceAll("\\D+", "");
		
		return solvedCount;
	}
	
	public String CodeForcesContestParticipated(String username) throws IOException
	{
		int cc=0;
		
		String contestUrl = "https://codeforces.com/contests/with";
		String url=contestUrl+"/"+username;
        
        
		
			
			Document doc=Jsoup.connect(url).get();
			
			
			Elements contestTable = doc.select("table.user-contests-table");
            
            if (!contestTable.isEmpty()) {
                Elements rows = contestTable.select("tr");
                 cc = rows.size() - 1; 
                
            } 
            
			
            return Integer.toString(cc);	
		
	}
	
	String leetcodeapi="https://alfa-leetcode-api.onrender.com/";
	
	public String LeetCodeRating(String username) throws IOException {
	    String rating = null;

	    if (username != null) {
	        String fullurl = leetcodeapi + username + "/contest";

	        try {
	            Document doc = Jsoup.connect(fullurl).ignoreContentType(true).get();
	            
	            if (doc != null) {
	                String ResponseString = doc.body().text();
	                JSONObject json = new JSONObject(ResponseString);
	                rating = Integer.toString(json.getInt("contestRating"));
	            } else {
	                rating = "n/a"; // Handle case where document is null or not found
	            }
	        } catch (HttpStatusException e) {
	            if (e.getStatusCode() == 404) {
	                rating = "0"; // Handle 404 error
	            } else {
	                throw e; // Rethrow other HTTP status exceptions
	            }
	        }
	    }
	    return rating;
	}
	
	public String LeetCodeSolvedCount(String username) throws IOException {
	    String solvedcount = null;

	    if (username != null) {
	        String fullurl = leetcodeapi + username + "/solved";

	        try {
	            Document doc = Jsoup.connect(fullurl).ignoreContentType(true).get();

	            if (doc != null) {
	                String ResponseString = doc.body().text();
	                JSONObject json = new JSONObject(ResponseString);
	                solvedcount = Integer.toString(json.getInt("solvedProblem"));
	            } else {
	                solvedcount = "n/a"; // Handle case where document is null or not found
	            }
	        } catch (HttpStatusException e) {
	            if (e.getStatusCode() == 404) {
	                solvedcount = "0"; // Handle 404 error
	            } else {
	                throw e; // Rethrow other HTTP status exceptions
	            }
	        }
	    }
	    return solvedcount;
	}
	
	public String LeetCodeContestParticipated(String username) throws IOException {
	    String cp = null;

	    if (username != null) {
	        String fullurl = leetcodeapi + username + "/contest";

	        try {
	            Document doc = Jsoup.connect(fullurl).ignoreContentType(true).get();

	            if (doc != null) {
	                String ResponseString = doc.body().text();
	                JSONObject json = new JSONObject(ResponseString);
	                cp = Integer.toString(json.getInt("contestAttend"));
	            } else {
	                cp = "n/a"; // Handle case where document is null or not found
	            }
	        } catch (HttpStatusException e) {
	            if (e.getStatusCode() == 404) {
	                cp = "0"; // Handle 404 error
	            } else {
	                throw e; // Rethrow other HTTP status exceptions
	            }
	        }
	    }
	    return cp;
	}
	
	
	
	

}

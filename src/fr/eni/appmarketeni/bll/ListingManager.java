package fr.eni.appmarketeni.bll;

import java.util.ArrayList;
import java.util.List;


import fr.eni.appmarketeni.bo.Article;
import fr.eni.appmarketeni.bo.Listing;
import fr.eni.appmarketeni.dal.dao.DAOFactory;
import fr.eni.appmarketeni.dal.dao.ListingDAO;
import fr.eni.appmarketeni.dal.exception.DALException;

public class ListingManager {

	private static ListingDAO daoListing;
	
	public ListingManager() {
		daoListing = DAOFactory.getListingDAO();
	}
	
	public void insertListing(String name, List<String> listeArticle) {
	
		try {

			
			Listing listing = new Listing();
			listing.setName(name);
			
			for (String liste : listeArticle) {
				Article article = new Article(liste);
				System.out.println(article.toString());
				System.out.println(listing.toString());
				listing.getListArticle().add(article);
			}
						
			daoListing.insert(listing);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Listing> selectAll(){
		List<Listing> liste = new ArrayList<Listing>();
		try {
			liste = daoListing.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
		
	}
	
	public Listing selectById(int id) {
		Listing liste = new Listing();
		try {
			liste = daoListing.selectById(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
		
	}
	
	public void updateListing(Listing listing){
		try {
			daoListing.updateListing(listing);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateArticle(Article article) {
		try {
			daoListing.updateArticle(article);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteListing (int id) {
		try {
			daoListing.deleteListing(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteArticle (int id) {
		try {
			daoListing.deleteArticle(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
} 

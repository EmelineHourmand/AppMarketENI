package fr.eni.appmarketeni.bo;

import java.util.List;

import lombok.Data;

@Data
public class Listing {
	
	int idList;
	String name;
	List<Article> listArticle;

}

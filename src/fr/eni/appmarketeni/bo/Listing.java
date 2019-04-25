package fr.eni.appmarketeni.bo;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author ehourman2019
 *
 */
@Data
public class Listing {

	private int idList;
	private String name;
	private List<Article> listArticle;

}

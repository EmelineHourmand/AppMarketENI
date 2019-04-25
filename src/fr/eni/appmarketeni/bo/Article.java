package fr.eni.appmarketeni.bo;

import lombok.Data;

/**
 * 
 * @author ehourman2019
 *
 */
@Data
public class Article {

	private int idArticle;
	private String name;
	private boolean checked;

	/**
	 * @param idArticle
	 * @param name
	 * @param checked
	 */
	public Article(int idArticle, String name, boolean checked) {
		this.idArticle = idArticle;
		this.name = name;
		this.checked = checked;
	}

	/**
	 * Empty constructor
	 */
	public Article() {
	}
}

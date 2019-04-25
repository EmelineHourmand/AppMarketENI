package fr.eni.appmarketeni.dal.dao;
/**
 * 
 * @author ehourman2019
 *
 */
public class DAOFactory {
	
	/**
	 * Get ListingDAO
	 * @return ListingDAOJdbcImpl
	 */
	public static ListingDAO getListingDAO() {
		return new ListingDAOJdbcImpl();
	}
}

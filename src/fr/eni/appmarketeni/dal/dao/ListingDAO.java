package fr.eni.appmarketeni.dal.dao;

import java.util.List;

import fr.eni.appmarketeni.bo.Listing;
import fr.eni.appmarketeni.dal.exception.DALException;

/**
 * 
 * @author ehourman2019
 *
 */
public interface ListingDAO {

	/**
	 * Insert a listing in bdd
	 * @param listing
	 * @throws DALException
	 */
	void insert(Listing listing) throws DALException;
	
	/**
	 * Select all listing element in bdd
	 * @return
	 * @throws DALException
	 */
	List<Listing> selectAll() throws DALException;
	
	/**
	 * Select a listing element in bdd
	 * @param id
	 * @return
	 * @throws DALException
	 */
	Listing selectById(int id) throws DALException;
	
	/**
	 * Upate a listing element in bdd
	 * @param listing
	 * @throws DALException
	 */
	void update(Listing listing) throws DALException;
	
	/**
	 * Delete a listing element in bdd
	 * @param id
	 * @throws DALException
	 */
	void delete(int id) throws DALException;
}

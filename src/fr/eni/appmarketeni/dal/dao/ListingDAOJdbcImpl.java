package fr.eni.appmarketeni.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.appmarketeni.bo.Article;
import fr.eni.appmarketeni.bo.Listing;
import fr.eni.appmarketeni.dal.ConnectionProvider;
import fr.eni.appmarketeni.dal.exception.DALException;
/**
 * 
 * @author ehourman2019
 *
 */
public class ListingDAOJdbcImpl implements ListingDAO{

	private static final String SQL_INSERT_LISTING = "INSERT INTO LISTES (nom) VALUES (?)";
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLE (nom, id_liste, coche)"
													+" VALUES (?,?,?)";
	private static final String SQL_SELECT_BY_ID = "";
	private static final String SQL_SELECT_ALL = "";
	private static final String SQL_UPDATE = "";
	private static final String SQL_REMOVE = "";
	
	private PreparedStatement psmt = null;
	
	@Override
	public void insert(Listing listing) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			psmt = cnx.prepareStatement(SQL_INSERT_LISTING, Statement.RETURN_GENERATED_KEYS);
			
			psmt.setString(1, listing.getName());
			psmt.executeUpdate();
			
			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				listing.setIdList(rs.getInt(1));
			}
			
			for (Article article : listing.getListArticle()) {
				psmt = cnx.prepareStatement(SQL_INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
				
				psmt.setString(1, article.getName());
				psmt.setInt(2, listing.getIdList());
				psmt.setBoolean(3, article.isChecked());
				psmt.executeUpdate();
				rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					article.setIdArticle(rs.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e.getMessage(), e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException(e.getMessage(), e);
			}
		}
		
	}

	@Override
	public List<Listing> selectAll() throws DALException {
		List<Listing> listListing = new ArrayList<Listing>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			Listing listing =new Listing();
			while(rs.next())
			{
				if(rs.getInt("id_repas")!=listing.getIdList())
				{
					listing = linstingBuilder(rs);
					listListing.add(listing);
				}
				Article aliment = articleBuilder(rs);
				listing.getListArticle().add(aliment);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listListing;
	}

	@Override
	public Listing selectById(int id) throws DALException {
		Listing listing =new Listing();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				listing = linstingBuilder(rs);
				Article aliment = articleBuilder(rs);
				listing.getListArticle().add(aliment);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return listing;
	}

	@Override
	public void update(Listing listing) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Build a Article
	 * @param rs
	 * @return article
	 * @throws SQLException
	 */
	private Article articleBuilder(ResultSet rs) throws SQLException {
		Article article = new Article(rs.getInt("id_article"), rs.getString("nom"), rs.getBoolean("coche"));
		return article;
	}
	
	/**
	 * Build a listing
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Listing linstingBuilder(ResultSet rs) throws SQLException {
		Listing listing;
		listing=new Listing();
		listing.setIdList(rs.getInt("id_listes"));
		listing.setName(rs.getString("nom"));
		return listing;
	}
}

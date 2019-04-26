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
public class ListingDAOJdbcImpl implements ListingDAO {

	private static final String SQL_INSERT_LISTING = "INSERT INTO LISTES (nom) VALUES (?)";
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLE (nom, id_liste, coche) VALUES (?,?,?)";
	private static final String SQL_SELECT_BY_ID = "SELECT LISTES.id as id_listes, LISTES.nom, ARTICLES.id as id_articles, ARTICLES.nom, ARTICLES.coche FROM LISTES INNER JOIN ARTICLES ON LISTES.id = ARTICLES.id_liste WHERE LISTES.id = ?";
	private static final String SQL_SELECT_ALL = "SELECT LISTES.id as id_listes, LISTES.nom, ARTICLES.id as id_articles, ARTICLES.nom, ARTICLES.coche FROM LISTES INNER JOIN ARTICLES ON LISTES.id = ARTICLES.id_liste";
	private static final String SQL_UPDATE_LISTING = "UPDATE LISTES SET nom=? WHERE id=?";
	private static final String SQL_UPDATE_ARTICLE = "UPDATE ARTICLES SET nom=?, coche=? WHERE id=?";
	private static final String SQL_REMOVE_LISTING = "DELETE FROM ARTICLES WHERE id_liste=?; DELETE FROM LISTES WHERE id=?";
	private static final String SQL_REMOVE_ARTICLE = "DELETE FROM ARTICLES WHERE id=?";

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
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			Listing listing = new Listing();
			while (rs.next()) {
				if (rs.getInt("id_repas") != listing.getIdList()) {
					listing = linstingBuilder(rs);
					listListing.add(listing);
				}
				Article aliment = articleBuilder(rs);
				listing.getListArticle().add(aliment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listListing;
	}

	@Override
	public Listing selectById(int id) throws DALException {
		Listing listing = new Listing();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
			psmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listing = linstingBuilder(rs);
				Article aliment = articleBuilder(rs);
				listing.getListArticle().add(aliment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listing;
	}

	@Override
	public void updateListing(Listing listing) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE_LISTING);
			psmt.setString(1, listing.getName());
			psmt.setInt(2, listing.getIdList());
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateArticle(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE_ARTICLE);
			psmt.setString(1, article.getName());
			psmt.setBoolean(2, article.isChecked());
			psmt.setInt(3, article.getIdArticle());
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteListing(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_REMOVE_LISTING);
			psmt.setInt(1, id);
			psmt.setInt(2, id);
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteArticle(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_REMOVE_ARTICLE);
			psmt.setInt(1, id);
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Build a Article
	 * 
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
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Listing linstingBuilder(ResultSet rs) throws SQLException {
		Listing listing;
		listing = new Listing();
		listing.setIdList(rs.getInt("id_listes"));
		listing.setName(rs.getString("nom"));
		return listing;
	}
}

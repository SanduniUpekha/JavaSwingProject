package guiprogramming.bookshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {
	
	private BookDatabase database;
	
	public BookDao() {
		database = new BookDatabase();
		
	}
	
	public void insertBook(Book book) {
		
		final String INSERT_BOOK_SQL="insert into tablebook"+"(authorname,bookname,edition,price) values"+"(?,?,?,?);";
		Connection con = database.getDatabaseConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_BOOK_SQL);
			ps.setString(1,book.getAuthorName());
			ps.setString(2,book.getBookName());
			ps.setString(3,book.getEditionYear());
			ps.setString(4,book.getBookPrice());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public ResultSet LoadTableData() {
		
		final String ALL_BOOKDETAILS_SQL="select * from tablebook";
		Connection con = database.getDatabaseConnection();
		
		PreparedStatement ps;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(ALL_BOOKDETAILS_SQL);
			rs = ps.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}
	
	public void updateBook(Book book) {
		
		final String UPDATE_BOOK_SQL="update tablebook set authorname=?, bookname=?, edition=?, price=? where id=? ";
		Connection con = database.getDatabaseConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(UPDATE_BOOK_SQL);
			ps.setString(1,book.getAuthorName());
			ps.setString(2,book.getBookName());
			ps.setString(3,book.getEditionYear());
			ps.setString(4,book.getBookPrice());
			ps.setInt(5,book.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

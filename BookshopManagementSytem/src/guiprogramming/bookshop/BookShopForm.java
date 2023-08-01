package guiprogramming.bookshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookShopForm {

	private JFrame frame;
	private JTextField txtAuthor; 
	private JTextField txtBookName;
	private JTextField txtEdition;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShopForm window = new BookShopForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private BookDao daoBook;
	private JTable table;
	private JTextField txtSearchId;
	
	public BookShopForm() {
		
		daoBook = new BookDao();
		initialize();
		loadTable();
	}
	
	public void clearForm() {
		txtAuthor.setText("");  
		txtBookName.setText("");   
		txtEdition.setText("");  
		txtPrice.setText("");
		txtSearchId.setText("");
	}

	public void loadTable() {
		
		ResultSet rs = daoBook.LoadTableData();
		table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1056, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("Book Shop Management");
		lblRegistration.setFont(new Font("SansSerif", Font.BOLD, 38));
		lblRegistration.setBounds(332, 34, 447, 47);
		frame.getContentPane().add(lblRegistration);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Book Registration Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 92, 433, 333);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAuthor = new JLabel("Author Name :");
		lblAuthor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblAuthor.setBounds(22, 61, 127, 26);
		panel.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtAuthor.setBounds(159, 61, 235, 27);
		panel.add(txtAuthor);
		txtAuthor.setColumns(10);
		
		JLabel lblBookName = new JLabel("Book Name :");
		lblBookName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblBookName.setBounds(22, 111, 127, 26);
		panel.add(lblBookName);
		
		txtBookName = new JTextField();
		txtBookName.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtBookName.setColumns(10);
		txtBookName.setBounds(159, 111, 235, 27);
		panel.add(txtBookName);
		
		JLabel lblEdition = new JLabel("Edition :");
		lblEdition.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblEdition.setBounds(22, 160, 127, 26);
		panel.add(lblEdition);
		
		txtEdition = new JTextField();
		txtEdition.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtEdition.setColumns(10);
		txtEdition.setBounds(159, 160, 235, 27);
		panel.add(txtEdition);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPrice.setBounds(22, 210, 127, 26);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtPrice.setColumns(10);
		txtPrice.setBounds(159, 210, 235, 27);
		panel.add(txtPrice);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String author = txtAuthor.getText();  
				String book = txtBookName.getText();   
				String edition = txtEdition.getText();  
				String price = txtPrice.getText(); 
				
				Book bookObject = new Book(author, book, edition, price); 
				daoBook.insertBook(bookObject);
				
				clearForm();
				loadTable();
				 
			}
		});
		btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnRegister.setBounds(159, 267, 117, 40);
		panel.add(btnRegister);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clearForm();
			}
		});
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnClear.setBounds(306, 267, 88, 40);
		panel.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(463, 100, 575, 318);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel model = table.getModel();
				int selectedRow = table.getSelectedRow();
				String id = model.getValueAt(selectedRow, 0).toString();
				String aName = model.getValueAt(selectedRow, 1).toString();
				String bName = model.getValueAt(selectedRow, 2).toString();
				String edition = model.getValueAt(selectedRow, 3).toString();
				String price = model.getValueAt(selectedRow, 4).toString();
				
				txtSearchId.setText(id);
				txtAuthor.setText(aName);  
				txtBookName.setText(bName);   
				txtEdition.setText(edition);  
				txtPrice.setText(price);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(582, 429, 352, 63);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(188, 11, 142, 40);
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookid = txtSearchId.getText();
				String author = txtAuthor.getText();  
				String book = txtBookName.getText();   
				String edition = txtEdition.getText();  
				String price = txtPrice.getText(); 
				int id = Integer.parseInt(bookid);
				
				Book bookObject = new Book(id, author, book, edition, price); 
				daoBook.updateBook(bookObject);
				
				clearForm();
				loadTable();
				
			}
		});
		btnUpdate.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnUpdate.setBounds(20, 11, 142, 40);
		panel_1.add(btnUpdate);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder(null, "Search Book Area", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchPanel.setBounds(20, 436, 422, 105);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		JLabel lblSearchId = new JLabel("Search by ID");
		lblSearchId.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSearchId.setBounds(26, 27, 137, 29);
		searchPanel.add(lblSearchId);
		
		txtSearchId = new JTextField();
		txtSearchId.setBounds(173, 31, 202, 28);
		searchPanel.add(txtSearchId);
		txtSearchId.setColumns(10);
	}
}


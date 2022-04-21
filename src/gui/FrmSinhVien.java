package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectSQL;
import dao.LopHoc_dao;
import dao.SinhVien_dao;
import entity.LopHoc;
import entity.SinhVien;

public class FrmSinhVien extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SinhVien_dao sinhVienDao;
	private DefaultTableModel dfTableQLSV;
	private JTable tableQLSV;
	private JButton btnLL;
	private JButton btnL;
	private JButton btnR;
	private JButton btnRR;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JTextField txtho;
	private JTextField txtTen;
	private JComboBox<String> ComboxPhai;
	private JTextField txtEmail;
	private JTextField txtDC;
	private JTextField txtMaSV;
	private JTextField txtMa;
	private ArrayList<SinhVien> listSinhVien;
	private JButton btnXoaTr;
	private LopHoc_dao lopHocDao;
	private SinhVien sv;

	public FrmSinhVien(String maLop) throws SQLException {

		execDB();
		listSinhVien = sinhVienDao.getDSSinhVienTheoMaLop(maLop);
		showWindow();
		addControl();
		txtMa.setText(maLop);
		addEvent();

//		System.out.println(maLop);
//		for (SinhVien sinhVien : listSinhVien) {
//			System.out.println(sinhVien + "/n");
//		}
	}

	private void execDB() {
		try {
			ConnectSQL.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sinhVienDao = new SinhVien_dao();
		lopHocDao = new LopHoc_dao();

	}

	private void showWindow() {
		setTitle("QUAN LY SINH VIEN");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void addControl() {
		// pnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));

		JPanel pnTitle = new JPanel();
		JLabel lblTitle = new JLabel("THONG TIN SINH VIEN");
		Font f = new Font("Arial", Font.BOLD, 22);
		lblTitle.setFont(f);
		lblTitle.setForeground(Color.blue);
		pnTitle.add(lblTitle);
		pnNorth.add(pnTitle);

		add(pnNorth, BorderLayout.NORTH);

		// table in center
		String[] header = { "Ma lop", "Ma sv", "Ho", "Ten", "Phai", "email", "dia chi" };
		dfTableQLSV = new DefaultTableModel(header, 0);
		tableQLSV = new JTable(dfTableQLSV);
		add(new JScrollPane(tableQLSV), BorderLayout.CENTER);
		tableQLSV.setRowHeight(20);

		// jpanel South
		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.Y_AXIS));
		JPanel pnDir = new JPanel();
		btnLL = new JButton("<<");
		btnL = new JButton("<");
		btnR = new JButton(">");
		btnRR = new JButton(">>");
		pnDir.add(btnLL);
		pnDir.add(Box.createRigidArea(new Dimension(10, 10)));
		pnDir.add(btnL);
		pnDir.add(Box.createRigidArea(new Dimension(10, 10)));
		pnDir.add(btnR);
		pnDir.add(Box.createRigidArea(new Dimension(10, 10)));
		pnDir.add(btnRR);
		pnDir.add(Box.createRigidArea(new Dimension(10, 10)));
		pnSouth.add(pnDir);

		JPanel pnMaten = new JPanel();
		pnMaten.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblMa = new JLabel("Ma lop");
		txtMa = new JTextField(30);

		pnMaten.add(lblMa);
		pnMaten.add(txtMa);
		pnSouth.add(pnMaten);

		JPanel pnMaSV = new JPanel();
		pnMaSV.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblMaSV = new JLabel("Ma sv");
		txtMaSV = new JTextField(30);
		pnMaSV.add(lblMaSV);
		pnMaSV.add(txtMaSV);
		pnSouth.add(pnMaSV);

		JPanel pnHT = new JPanel();
		pnHT.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblho = new JLabel("Ho");
		txtho = new JTextField(30);
		pnHT.add(lblho);
		pnHT.add(txtho);

		JLabel lblTen = new JLabel("Ten");
		txtTen = new JTextField(30);
		pnHT.add(lblTen);
		pnHT.add(txtTen);
		pnSouth.add(pnHT);

		JPanel pnPhai = new JPanel();
		pnPhai.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblPhai = new JLabel("Phai");
		String[] phaiCB = { "Nam", "Nữ" };
		ComboxPhai = new JComboBox<String>(phaiCB);

		ComboxPhai.setPreferredSize(new Dimension(100, 25));
		pnPhai.add(lblPhai);
		pnPhai.add(ComboxPhai);
		pnSouth.add(pnPhai);

		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblEmail = new JLabel("Email");
		txtEmail = new JTextField(30);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnSouth.add(pnEmail);

		JPanel pnDC = new JPanel();
		pnDC.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblDC = new JLabel("Địa chỉ");
		txtDC = new JTextField(30);
		pnDC.add(lblDC);
		pnDC.add(txtDC);
		pnSouth.add(pnDC);

		lblMa.setPreferredSize(new Dimension(60, 20));
		lblho.setPreferredSize(new Dimension(60, 20));
		lblTen.setPreferredSize(new Dimension(60, 20));
		lblPhai.setPreferredSize(new Dimension(60, 20));
		lblEmail.setPreferredSize(new Dimension(60, 20));
		lblDC.setPreferredSize(new Dimension(60, 20));
		lblMaSV.setPreferredSize(new Dimension(60, 20));

		JPanel pnBtn = new JPanel();
		btnThem = new JButton("Them");
		btnLuu = new JButton("Luu");
		btnSua = new JButton("Sua");
		btnXoa = new JButton("Xoa");
		btnXoaTr = new JButton("Xoa Trang");
		pnBtn.add(btnThem);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnLuu);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnSua);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnXoa);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnXoaTr);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnSouth.add(pnBtn);

		add(pnSouth, BorderLayout.SOUTH);

		docDLTuArrVaoTable();

		txtMa.setEditable(false);
		btnLuu.setEnabled(false);
	}

	private void addEvent() {
		tableQLSV.addMouseListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTr.addActionListener(this);
	}

	private String[] formatDL(SinhVien sv) {
		String gt = "";
		if (sv.getPhai()) {
			gt = "Nam";
		} else {
			gt = "Nữ";
		}
		String[] row = { sv.getLop().getMaLop(), sv.getMasv(), sv.getHo(), sv.getTen(), gt, sv.getEmail(),
				sv.getDiaChi() };
		return row;
	}

	private void docDLTuArrVaoTable() {
		for (int i = 0; i < listSinhVien.size(); i++) {
			String[] row = formatDL(listSinhVien.get(i));
			System.out.println(listSinhVien.get(i));
			dfTableQLSV.addRow(row);
		}
	}

	private void xoaTrang() {
		txtDC.setText("");

		txtEmail.setText("");
		txtho.setText("");
		txtTen.setText("");
		txtMaSV.setText("");
		

	}

	// them
	private SinhVien sinhvien() throws SQLException {
		boolean phai;
		if (ComboxPhai.getSelectedItem() == "Nam") {
			phai = true;
		} else {
			phai = false;
		}

		String maLop = txtMa.getText();
		String maSv = txtMaSV.getText();
		String ho = txtho.getText();
		String ten = txtTen.getText();

		String email = txtEmail.getText();
		String dc = txtDC.getText();
		sv = new SinhVien(maSv, ho, ten, phai, email, dc, new LopHoc(maLop));
		return sv;
	}

	private void themSV() throws SQLException {
		SinhVien sv = sinhvien();
		String[] row = { sv.getLop().getMaLop(), sv.getMasv(), sv.getHo(), sv.getTen(),
				ComboxPhai.getSelectedItem() + "", sv.getEmail(), sv.getDiaChi() };
		if (sinhVienDao.timSinhVienTheoMa(sv.getMasv()) == null) {
			dfTableQLSV.addRow(row);
			sinhVienDao.insertSinhVien(sv);
			JOptionPane.showMessageDialog(this, "Them thanh cong");
		} else {
			JOptionPane.showMessageDialog(this, "Trung ma");
		}
	}
	private boolean checkValidData() {
		String msv = txtMaSV.getText().trim();
		String ho = txtho.getText().trim();
		String ten = txtTen.getText().trim();

		String email = txtEmail.getText().trim();
		String dc = txtDC.getText();
		if(!(msv.length() > 0 && msv.matches("[0-9]{8}"))) {
			JOptionPane.showMessageDialog(this, "Ma chua hop le an co 8 chu so");
			txtMaSV.requestFocus();
			return false;
		}
		if(!(ho.length() > 0 && ho.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s]+$"))) {
			JOptionPane.showMessageDialog(this, "Ho bat đau bang chu hoa");
			txtho.requestFocus();
			return false;
		}
		if(!(ten.length() > 0 && ten.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s]+$"))) {
			JOptionPane.showMessageDialog(this, "Ten bat đau bang chu hoa");
			txtTen.requestFocus();
			return false;
		}
		if(!(email.length() > 0 && email.matches("^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,6}$"))) {
			JOptionPane.showMessageDialog(this, "Dia chi email chua hop le");
			txtEmail.requestFocus();
			return false;
		}
		if(!(dc.length() > 0 && dc.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s]+$"))) {
			JOptionPane.showMessageDialog(this, "Dia chi chua hop le");
			txtDC.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnXoaTr)) {
			xoaTrang();
		}
		if (obj.equals(btnThem)) {
			try {
				if(checkValidData()) {
					themSV();
					xoaTrang();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnSua)) {

			int vt = tableQLSV.getSelectedRow();
			dfTableQLSV.setValueAt(txtMa.getText(), vt, 0);
			dfTableQLSV.setValueAt(txtMaSV.getText(), vt, 1);
			dfTableQLSV.setValueAt(txtho.getText(), vt, 2);
			dfTableQLSV.setValueAt(txtTen.getText(), vt, 3);
			dfTableQLSV.setValueAt(ComboxPhai.getSelectedItem(), vt, 4);
			dfTableQLSV.setValueAt(txtEmail.getText(), vt, 5);
			dfTableQLSV.setValueAt(txtDC.getText(), vt, 6);
			JOptionPane.showMessageDialog(this, "Nhan nut Luu de luu ket qua");
			btnLuu.setEnabled(true);

		}
		if (obj.equals(btnLuu)) {
			String maSV = txtMaSV.getText();
			SinhVien sv;
			try {
				sv = sinhVienDao.timSinhVienTheoMa(maSV);
				boolean gt;
				if (ComboxPhai.getSelectedItem() == "Nam") {
					gt = true;
				} else {
					gt = false;
				}
				SinhVien sv1 = new SinhVien(sv.getMasv(), txtho.getText(), txtTen.getText(), gt, txtEmail.getText(),
						txtDC.getText(), sv.getLop());
				sinhVienDao.updateSinhVien(sv1);
				JOptionPane.showMessageDialog(this, "Sua thanh cong");
				System.out.println(sv1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (obj.equals(btnXoa)) {
			int vt = tableQLSV.getSelectedRow();
			String maSV = txtMaSV.getText();
			sinhVienDao.deleteSV(maSV);
			dfTableQLSV.removeRow(vt);
			JOptionPane.showMessageDialog(this, "Xoa thanh cong");
			xoaTrang();

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int vt = tableQLSV.getSelectedRow();
		txtMa.setText((String) tableQLSV.getValueAt(vt, 0));
		txtMaSV.setText((String) tableQLSV.getValueAt(vt, 1));
		txtho.setText((String) tableQLSV.getValueAt(vt, 2));
		txtTen.setText((String) tableQLSV.getValueAt(vt, 3));
		ComboxPhai.setSelectedItem((String) tableQLSV.getValueAt(vt, 4));
		txtEmail.setText((String) tableQLSV.getValueAt(vt, 5));
		txtDC.setText((String) tableQLSV.getValueAt(vt, 6));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

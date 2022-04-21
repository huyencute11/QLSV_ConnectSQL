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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class frmLopHoc extends JFrame implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dfTableQLSV;
	private JTable tableQLSV;
	private JButton btnLL;
	private JButton btnL;
	private JButton btnR;
	private JButton btnRR;
	private JTextField txtMa;
	private JTextField txtTL;
	private JTextField txtSS;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXem;
	private SinhVien_dao sinhVienDao;

	private LopHoc_dao lopHocDao;
	private ArrayList<LopHoc> listLopHoc;
	private FrmSinhVien frmSV;
	private JButton btnXoaTrang;

	public frmLopHoc() {
		execDB();
		showWindow();
		addControl();
		addEvent();

	}

	private void showWindow() {
		setTitle("QUAN LY SINH VIEN");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void execDB() {
		try {
			ConnectSQL.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sinhVienDao = new SinhVien_dao();
		lopHocDao = new LopHoc_dao();
//		siSoDao = new ThongTinSiSo_dao();
//		listSV = sinhVienDao.getAllSV();

	}

	private void addControl() {
		// pnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));

		JPanel pnTitle = new JPanel();
		JLabel lblTitle = new JLabel("THONG TIN LOP HOC");
		Font f = new Font("Arial", Font.BOLD, 22);
		lblTitle.setFont(f);
		lblTitle.setForeground(Color.blue);
		pnTitle.add(lblTitle);
		pnNorth.add(pnTitle);

		add(pnNorth, BorderLayout.NORTH);

		// table in center
		String[] header = { "Ma lop", "Ten lop", "Si so" };
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

		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblMa = new JLabel("Ma lop");
		txtMa = new JTextField(30);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnSouth.add(pnMa);

		JPanel pnTL = new JPanel();
		pnTL.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblTL = new JLabel("Ten lop");
		txtTL = new JTextField(70);
		pnTL.add(lblTL);
		pnTL.add(txtTL);
		pnSouth.add(pnTL);

		JPanel pnSS = new JPanel();
		pnSS.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblSS = new JLabel("Si so");
		txtSS = new JTextField(20);
		pnSS.add(lblSS);
		pnSS.add(txtSS);
		pnSouth.add(pnSS);

		lblMa.setPreferredSize(new Dimension(60, 20));
		lblTL.setPreferredSize(new Dimension(60, 20));
		lblSS.setPreferredSize(new Dimension(60, 20));

		JPanel pnBtn = new JPanel();
		btnThem = new JButton("Them");
		btnLuu = new JButton("Luu");
		btnSua = new JButton("Sua");
		btnXoa = new JButton("Xoa");
		btnXoaTrang = new JButton("Xoa trang");
		btnXem = new JButton("Xem thong tin sinh vien");
		pnBtn.add(btnThem);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnLuu);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnSua);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnXoa);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnXem);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnBtn.add(btnXoaTrang);
		pnBtn.add(Box.createRigidArea(new Dimension(10, 10)));
		pnSouth.add(pnBtn);

		add(pnSouth, BorderLayout.SOUTH);

		docDLTuArrVaoTable();
		tableQLSV.setRowSelectionInterval(0, 0);

		txtMa.setText((String) tableQLSV.getValueAt(0, 0));
		txtTL.setText((String) tableQLSV.getValueAt(0, 1));
		txtSS.setText((String) tableQLSV.getValueAt(0, 2));
		txtSS.setEditable(false);
		btnLuu.setEnabled(false);

	}

	private void addEvent() {
		tableQLSV.addMouseListener(this);
		btnLL.addActionListener(this);
		btnL.addActionListener(this);
		btnR.addActionListener(this);
		btnRR.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	// format data
	private String[] layDLSiSo(LopHoc l) {
		String[] row1 = { l.getMaLop(), l.getTenLop(), l.getSoLuongSinhVien() + "" };
		return row1;
	}

	// render data
	private void docDLTuArrVaoTable() {
		listLopHoc = lopHocDao.getAllLopHoc();
		for (int i = 0; i < listLopHoc.size(); i++) {
			String[] row = layDLSiSo(listLopHoc.get(i));
			dfTableQLSV.addRow(row);
		}
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtTL.setText("");
		txtSS.setText("");
		btnLuu.setEnabled(true);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		txtMa.setEditable(true);
		btnXem.setEnabled(false);
	}

	private LopHoc lop() {
		String ma = txtMa.getText();
		String ten = txtTL.getText();

		LopHoc l = new LopHoc(ma, ten);
		return l;
	}

	private boolean them() throws SQLException {
		LopHoc l = lop();
		String[] row1 = { l.getMaLop(), l.getTenLop(), 0 + "" };
		if (lopHocDao.timLopQuaMaLop(l.getMaLop()) !=null) {
			return false;
		} else {
			lopHocDao.insertLopHoc(l);
			dfTableQLSV.addRow(row1);
			return true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnXem)) {
			
			
			String maLop = txtMa.getText();
			try {
				frmSV = new FrmSinhVien(maLop);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frmSV.setVisible(true);
		}
		if (obj.equals(btnXoaTrang)) {
			txtMa.setEditable(false);
			xoaTrang();
			tableQLSV.clearSelection();
			btnThem.setEnabled(true);
		}
		if (obj.equals(btnThem)) {
			try {
				if(txtTL.getText().trim().length() == 0 || txtMa.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(this, "Can nhap day du ten lop va ma lop");
				}
				else {
					if (them()) {
						JOptionPane.showMessageDialog(this, "Them lop hoc thanh cong");
						xoaTrang();
					} else {
						JOptionPane.showMessageDialog(this, "Trung ma lop");
					}
				}
				

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			tableQLSV.setRowSelectionInterval(0, 0);
			btnXem.setEnabled(true);
			txtMa.setText((String) tableQLSV.getValueAt(0, 0));
			txtTL.setText((String) tableQLSV.getValueAt(0, 1));
			txtSS.setText((String) tableQLSV.getValueAt(0, 2));
		}
		if (obj.equals(btnXoa)) {
			int vt = tableQLSV.getSelectedRow();
			LopHoc l = lopHocDao.getAllLopHoc().get(vt);
			try {
				if (lopHocDao.timLopQuaMaLop(l.getMaLop()) !=null) {
					// before delete lophoc we must delete all sinhvien in lophoc because have
					// constrant foreign key malop
					ArrayList<SinhVien> listSv = sinhVienDao.getDSSinhVienTheoMaLop(l.getMaLop());
					for (SinhVien sinhVien : listSv) {
						System.out.println(sinhVien);
					}
					Connection con = ConnectSQL.getConnection();
					PreparedStatement stmt = null;
					for (SinhVien sinhVien : listSv) {
						String sql = "DELETE FROM sinhvien WHERE masv = ?";
						stmt = con.prepareStatement(sql);
						stmt.setString(1, sinhVien.getMasv());
						stmt.executeUpdate();
					}

					dfTableQLSV.removeRow(vt);
					lopHocDao.deleteLop(txtMa.getText());
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Xoa thanh cong");
					tableQLSV.setRowSelectionInterval(0, 0);
					btnSua.setEnabled(true);
					btnXoa.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this, "Xoa khong thanh cong");
				}
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}
		if (obj.equals(btnSua)) {
			int vt = tableQLSV.getSelectedRow();
			dfTableQLSV.setValueAt(txtMa.getText(), vt, 0);
			dfTableQLSV.setValueAt(txtTL.getText(), vt, 1);
			dfTableQLSV.setValueAt(txtSS.getText(), vt, 2);
			JOptionPane.showMessageDialog(this, "Vui long an Luu de luu thong tin");
			btnLuu.setEnabled(true);
		}
		if (obj.equals(btnLuu)) {
			String maLop = txtMa.getText();
			LopHoc l;
			try {
				l = lopHocDao.timLopQuaMaLop(maLop);
				LopHoc l1 = new LopHoc(l.getMaLop(), txtTL.getText(), l.getSoLuongSinhVien());
				lopHocDao.updateLop(l1);
				JOptionPane.showMessageDialog(this, "Update thanh cong lop");
				xoaTrang();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		txtMa.setEditable(false);
		int vt = tableQLSV.getSelectedRow();

		txtMa.setText((String) tableQLSV.getValueAt(vt, 0));
		txtTL.setText((String) tableQLSV.getValueAt(vt, 1));
		txtSS.setText((String) tableQLSV.getValueAt(vt, 2));
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);
		btnLuu.setEnabled(true);
		btnThem.setEnabled(true);
		btnXem.setEnabled(true);
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

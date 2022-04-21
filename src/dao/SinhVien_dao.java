package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectSQL;
import entity.LopHoc;
import entity.SinhVien;

public class SinhVien_dao {

	/**
	 * get list sinh vien
	 * 
	 * @return list sv
	 */
	public ArrayList<SinhVien> getAllSV() {

		ArrayList<SinhVien> listSV = new ArrayList<SinhVien>();
		try {
			ConnectSQL.getInstance();
			Connection con = ConnectSQL.getConnection();
			String sql = "select * from SinhVien";
			Statement statement = con.createStatement();
			ResultSet sv = statement.executeQuery(sql);
			while (sv.next()) {
				String maLop = sv.getString(1);
				String ma = sv.getString(2);
				String ho = sv.getNString(3);
				String ten = sv.getNString(4);
				boolean phai = sv.getBoolean(5);
				String email = sv.getString(6);
				String diachi = sv.getNString(7);
				
				LopHoc lop = new LopHoc(maLop);
				SinhVien s = new SinhVien(ma, ho, ten, phai, email, diachi, lop);
				listSV.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSV;
	}

	public int getCountOfClass(String maLop) {
		int count = 0;
		try {
			ConnectSQL.getInstance();
			Connection con = ConnectSQL.getConnection();
			String sql = "Select * from SinhVien where malop = '" + maLop + "'";
			Statement statement = con.createStatement();
			ResultSet sv = statement.executeQuery(sql);
			while (sv.next()) {
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}


	public ArrayList<SinhVien> getDSSinhVienTheoMaLop(String ma) throws SQLException {
		ArrayList<SinhVien> listSVTheoMa = new ArrayList<SinhVien>();
		ConnectSQL.getInstance();
		Connection con = ConnectSQL.getConnection();
		String sql = "select * from sinhVien" + "	where maLop = '" + ma + "'";
		Statement statement = con.createStatement();
		ResultSet sv = statement.executeQuery(sql);
		while (sv.next()) {
			String maLop = sv.getString(1);
			String maSV = sv.getString(2);
			String ho = sv.getNString(3);
			String ten = sv.getNString(4);
			boolean phai = sv.getBoolean(5);
			String email = sv.getString(6);
			String diachi = sv.getNString(7);

			LopHoc lop = new LopHoc(maLop);
			SinhVien s = new SinhVien(maSV, ho, ten, phai, email, diachi, lop);
			listSVTheoMa.add(s);
		}
		return listSVTheoMa;
	}

	// xoa sinh vien theo ma sinh viên
	public void deleteSV(String id) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from SinhVien where masv = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// update sinh vien
	public void updateSinhVien(SinhVien sv) throws SQLException {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update SinhVien set malop = ?, Ho = ?, Ten = ?, Phai = ?, email = ?, diachi =? where masv = ?";
		try {
			stmt = con.prepareStatement(sql);        
//			LopHoc l 
			stmt.setString(1, sv.getLop().getMaLop());
			stmt.setNString(2, sv.getHo());
			stmt.setString(3, sv.getTen());
			stmt.setBoolean(4, sv.getPhai());
			stmt.setString(5, sv.getEmail());
			stmt.setNString(6, sv.getDiaChi());
			stmt.setString(7, sv.getMasv());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//insert sinh vien
	public void insertSinhVien(SinhVien sv) {
		Connection con = ConnectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into SinhVien values (?, ?, ?, ?, ?, ?, ?) ");
			stmt.setString(1, sv.getLop().getMaLop());
			stmt.setString(2, sv.getMasv());
			stmt.setString(3, sv.getHo());
			stmt.setString(4, sv.getTen());
			stmt.setBoolean(5, sv.getPhai());
			stmt.setString(6, sv.getEmail());
			stmt.setString(7, sv.getDiaChi());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//tim sih vien theo ma sinh vien
	public SinhVien timSinhVienTheoMa(String id) throws SQLException {
		SinhVien sv = null;
		ConnectSQL.getInstance();
		Connection con = ConnectSQL.getConnection();
		String sql = "select * from SinhVien where masv = '"+id+"'";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()) {
			String maLop = rs.getString(1);
			LopHoc l = new LopHoc(maLop);
			String maSV = rs.getString(2);
			String ho = rs.getString(3);
			String ten = rs.getString(4);
			boolean phai = rs.getBoolean(5);
			String email = rs.getString(6);
			String dc = rs.getString(7);
			sv = new SinhVien(maSV, ho, ten, phai, email, dc, l);
		}
		return sv;
		
	}
	
}

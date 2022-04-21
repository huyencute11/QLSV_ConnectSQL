package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectSQL;
import entity.LopHoc;


public class LopHoc_dao {
	/**
	 * 
	 * @return list lop hoc
	 */
	public ArrayList<LopHoc> getAllLopHoc() {
		ArrayList<LopHoc> listLop = new ArrayList<LopHoc>();
		SinhVien_dao sinhVien = new SinhVien_dao();
		try {
			ConnectSQL.getInstance();
			Connection con = ConnectSQL.getConnection();
			String sql = "select * from LopHoc";
			Statement statement;
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maL = rs.getString(1);
				String tenL = rs.getString(2);

				LopHoc l = new LopHoc(maL, tenL);
				l.setSoLuongSinhVien(sinhVien.getCountOfClass(maL));
				listLop.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listLop;
	}
		public LopHoc timLopQuaMaLop(String id) throws SQLException {
			LopHoc l = null;
			ConnectSQL.getInstance();
			Connection con = ConnectSQL.getConnection();
			String sql = "select * from LopHoc where malop = '" +id+ "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				String maLop = rs.getString(1);
				String ten = rs.getNString(2);
				l = new LopHoc(maLop, ten);
			}
			return l;
			
		}
	/**
	 * them 1 lop hoc
	 * @param lop
	 */
		public void insertLopHoc(LopHoc lop) {
			ConnectSQL.getInstance();
			Connection con = ConnectSQL.getConnection();
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement("insert into Lophoc values (?, ?)");
				stmt.setString(1, lop.getMaLop());
				stmt.setString(2, lop.getTenLop());
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	/**
	 * delete lop theo ma
	 * @param id
	 */
		public void deleteLop(String id) {
			ConnectSQL.getInstance();
			Connection con = ConnectSQL.getConnection();
			PreparedStatement stmt = null;
			String sql = "DELETE FROM lophoc WHERE malop = ?";
			try {
				
				stmt = con.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void updateLop(LopHoc l) {
			Connection con = ConnectSQL.getConnection();
			PreparedStatement stmt = null;
			String sql = "update lophoc set tenlop = ? where malop=?";
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, l.getTenLop());
				stmt.setString(2, l.getMaLop());
				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
}

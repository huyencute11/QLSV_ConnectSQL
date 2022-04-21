package entity;

import java.util.Objects;

public class LopHoc {
	private String maLop;
	private String tenLop;
	private int soLuongSinhVien;
	
	public LopHoc(String maLop) {
		super();
		this.maLop = maLop;
	}
	
	public LopHoc(String maLop, String tenLop) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
	}
	public LopHoc(String maLop, String tenLop, int soLuongSinhVien) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.soLuongSinhVien = soLuongSinhVien;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public int getSoLuongSinhVien() {
		return soLuongSinhVien;
	}

	public void setSoLuongSinhVien(int soLuongSinhVien) {
		this.soLuongSinhVien = soLuongSinhVien;
	}

	@Override
	public String toString() {
		return "LopHoc [maLop=" + maLop + ", tenLop=" + tenLop + ", soLuongSinhVien=" + soLuongSinhVien + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLop, soLuongSinhVien, tenLop);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LopHoc other = (LopHoc) obj;
		return Objects.equals(maLop, other.maLop) && soLuongSinhVien == other.soLuongSinhVien
				&& Objects.equals(tenLop, other.tenLop);
	}
	
	
	
}

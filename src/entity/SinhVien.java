package entity;

import java.util.Objects;

public class SinhVien {
	private String masv;
	private String ho;
	private String ten;
	private boolean phai;
	private String email;
	private String diaChi;
	private LopHoc lop;
	
	public SinhVien(String masv) {
		super();
		this.masv = masv;
	}
	
	public SinhVien(String masv, String ho, String ten, boolean phai, String email, String diaChi, LopHoc lop) {
		super();
		this.masv = masv;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.email = email;
		this.diaChi = diaChi;
		this.lop = lop;
	}
	public SinhVien(LopHoc lop, String ho, String ten, boolean phai, String email, String diaChi, String masv) {
		super();
		this.lop = lop;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.email = email;
		this.diaChi = diaChi;
		this.masv = masv;
	}
	public String getMasv() {
		return masv;
	}

	public void setMasv(String masv) {
		this.masv = masv;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public boolean getPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public LopHoc getLop() {
		return lop;
	}

	public void setLop(LopHoc lop) {
		this.lop = lop;
	}

	@Override
	public String toString() {
		return "SinhVien [masv=" + masv + ", ho=" + ho + ", ten=" + ten + ", phai=" + phai + ", email=" + email
				+ ", diaChi=" + diaChi + ", lop=" + lop + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(masv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		return Objects.equals(masv, other.masv);
	}
	
	
}

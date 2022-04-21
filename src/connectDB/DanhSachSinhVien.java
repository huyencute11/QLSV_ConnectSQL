package connectDB;

import java.util.ArrayList;

import entity.SinhVien;

public class DanhSachSinhVien {
	private ArrayList<SinhVien> dssv;

	public DanhSachSinhVien() {
		super();
		this.dssv = new ArrayList<SinhVien>();
	}
	public boolean themSV(SinhVien sv) {
		if(dssv.contains(sv)) {
			return false;
		}else {
			dssv.add(sv);
			return true;
		}
	}
	public boolean xoa(String id) {
		for (SinhVien sinhVien : dssv) {
			if(sinhVien.getMasv().equalsIgnoreCase(id)) {
				dssv.remove(sinhVien);
				return true;
			}
		}
		return false;
	}
	public boolean sua(String id) {
		return true;
	}
}

create database QLSinhVien
on primary(
	name = 'Sach_data', 
	filename = 'D:\HuongSuKien_JAVA\QLSinhVien\QLSinhVien.mdf',
	size = 25mb,
	maxsize = 40mb,
	filegrowth = 1mb
)
log on
(
	name = 'Sach_log', 
	filename = 'D:\HuongSuKien_JAVA\QLSinhVien\QLSinhVien.ldf',
	size = 6mb,
	maxsize = 8mb,
	filegrowth = 1mb
)
go
use QLSinhVien
drop table lophoc
create table LopHoc (
	maLop varchar(20) not null primary key,
	tenLop nvarchar(100) not null
)
drop Table SinhVien
create table SinhVien (
	masv char(10) not null primary key,
	ho nvarchar(30),
	ten nvarchar(30) not null,
	phai bit,
	email nvarchar(50),
	diaChi nvarchar(100),
	maLop varchar(20) not null foreign key references LopHoc(maLop)
)


insert into LopHoc values ('DHKTPM16A', N'Đại học kỹ thuật phần mềm 16A'),
('DHKTPM16B', N'Đại học kỹ thuật phần mềm 16B'),
('DHHTTT16A', N'Đại học hệ thống thông tin 16A'),
('DHKHMT16A', N'Đại học khoa học máy tính 16A'),
('DHKHDL6A', N'Đại học khoa học dữ liệu 16A'),
('DHCNTT16A', N'Đại học công nghệ thông tin 16A')


insert into SinhVien values ('20105131', N'Trần Thị Minh', N'Huyền', 0, 'ttminhuyen1111@gmail.com', N'Quảng Trị', 'DHKTPM16A'),
('20101234', N'Phạm Thanh', N'Sơn', 1, 'sonpham@gmail.com', N'Vũng Tàu', 'DHKTPM16B'),
('20004987', N'Nguyễn Anh', N'Quân', 1, 'quan22@gmail.com', N'Đồng Nai', 'DHKHMT16A'),
('21004679', N'Nguyễn Hương', N'Ngân', 0, 'huongngan22@gmail.com', N'Sài Gòn', 'DHKHDL6A'),
('20109876', N'Lê Minh', N'Huy', 0, 'huyle@gmail.com', N'Bình Định', 'DHHTTT16A'),
('20102708', N'Trần Diệu', N'HChâu', 0, 'chau2708@gmail.com', N'Bình Phước', 'DHCNTT16A')

insert into SinhVien values ('20102313', N'Nguyễn Lan', N'Ngọc', 0, 'ngoc@gmail.com', N'Sài Gòn', 'DHKTPM16A'),
('20112458', N'Phung Thanh', N'Độ', 0, 'doPhung@gmail.com', N'Cao Bằng', 'DHKTPM16A')

insert into SinhVien values ('20001021', N'Hoàng', N'Phương', 0, 'phuonghoang@gmail.com', N'Bình Phước', 'DHKHDL6A')

SELECT LopHoc.maLop, SinhVien.masv, SinhVien.ten
FROM     LopHoc INNER JOIN
                  SinhVien ON LopHoc.maLop = SinhVien.maLop
where SinhVien.maLop = 'DHKTPM16B'
SELECT masv, ho, ten, email, diaChi, maLop,(
		case phai when 1 then N'Nam'
		when 0 then N'Nữ'
        end) as 'Giới tính'
FROM     SinhVien

select * from SinhVien

-- Create function demSiSo
GO
create function demSiSo()
returns table 
as
 return
	SELECT LopHoc.maLop, LopHoc.tenLop, siso = count(SinhVien.masv) 
	FROM     LopHoc INNER JOIN
                  SinhVien ON LopHoc.maLop = SinhVien.maLop

	group by LopHoc.maLop, LopHoc.tenLop


-- exec
go
select * from dbo.demSiSo()

--delete
delete from sinhvien where masv in(
	select masv from sinhVien
	where maLop = 'DHKHDL6A'
)
# JavaFX_AiLaTrieuPhu
Bài tập lớn môn Lập trình Java - Game Ai là triệu phú.
<br />
Sử dụng JavaFX, Hibernate và MySQL.

# Giảng viên hướng dẫn - Sinh viên thực hiện
Giảng viên hướng dẫn: Thầy Dương Hữu Thành
<br />Sinh viên thực hiện:
<ul>
  <li>1551010009 - Lê Hoàng Quốc Bảo (Back-end)</li>
  <li>1551010008 - Đỗ Huỳnh Gia Bảo (Database và Front-end)</li>
</ul>

# Bảng tiến độ dự án
Phiên bản | Nội dung | Ngày commit
----------|----------|-------------
Beta 1 | Demo chức năng đăng ký người dùng, đăng nhập. | 19/11/2018
Beta 2 | Liên kết scene Đăng nhập, đăng ký.<br />Thêm chức năng quản lý câu hỏi (Thêm, Cập nhật, Xoá, Phục hồi).<br />Tinh chỉnh chuỗi kết nối tới MySQL để có thể lưu chuỗi Unicode (UTF-8). | 20/11/2018
Beta 3 | Dùng <strong>StringBuilder</strong> thay cho phương thức .concat của msgErr và msgSucc (DangKyController, DangNhapController, QLCauHoiController).<br />Fix bug và cải tiến ở QLCauHoiController.<br />Đổi tên phương thức <strong>saveCauHoi</strong> thành <strong>saveOrUpdateCauHoi</strong>.<br />Thêm nút chức năng tải lại dữ liệu cho TableView ở QLCauHoi.<br />Thêm thông báo xác nhận trước khi thêm một câu hỏi vào CSDL.| 21/11/2018
Beta 4 | Thêm controller cho màn hình chính.<br />Sử dụng giao diện của DHGB.<br />Kiểm tra lựa chọn thêm câu hỏi hay chơi ở phần đăng nhập. Chỉ có admin mới có quyền thêm câu hỏi, còn người dùng sẽ được chuyển sang giao diện chơi game. | 30/11/2018
Beta 5 | Hoàn thiện ChoiGameController.<br />Liên kết hoàn chỉnh giữa các Scene.<br /> | 11/12/2018

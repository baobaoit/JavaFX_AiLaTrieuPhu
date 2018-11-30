package demoaltp;

import demoaltp.database.HibernateUtil;
import demoaltp.modal.NguoiDung;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author BaoBao
 */
public class DangKyController implements Initializable {

    private SessionFactory factory;
    @FXML
    private BorderPane bpDangKy;
    @FXML
    private TextField txtTaiKhoan;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtNamSinh;
    @FXML
    private TextField txtQueQuan;
    @FXML
    private PasswordField txtMatKhau;
    @FXML
    private RadioButton rdoNam;
    @FXML
    private RadioButton rdoNu;
    @FXML
    private RadioButton rdoKhac;
    private Alert msg;
    private Stage curWindow;

    @FXML
    private void troVeHandler(ActionEvent event) {
        try {
            curWindow = (Stage) bpDangKy.getScene().getWindow();

            curWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLLogin_DHGB.fxml"))));
            curWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dangKyHandler(ActionEvent event) {
        StringBuilder msgErr = new StringBuilder(""); // Noi dung thong bao loi
        StringBuilder msgSucc = new StringBuilder(""); // Noi dung thong bao thanh cong

        String taiKhoan = txtTaiKhoan.getText();
        if (taiKhoan.isEmpty()) {
            // Tai khoan rong
            msgErr.append("Tài khoản: Trống!\n");
        } else {
            // Tai khoan khong duoc chua khoan trang
            taiKhoan = taiKhoan.trim().replaceAll("\\s+", "");
            msgSucc.append(String.format("Tài khoản: %s\n", taiKhoan));
        }

        String matKhau = txtMatKhau.getText();
        if (matKhau.isEmpty()) {
            // Mat khau rong
            msgErr.append("Mật khẩu: Trống!\n");
        } else if (matKhau.length() < 6) {
            // Mat khau co do dai duoi 6 ky tu
            msgErr.append("Mật khẩu: Phải có tối thiểu 6 ký tự.\n");
        } else {
            msgSucc.append(String.format("Mật khẩu: %s\n", matKhau));
        }

        String hoTen = txtHoTen.getText();
        if (hoTen.isEmpty()) {
            // Ho ten rong
            msgErr.append("Họ tên: Trống!\n");
        } else {
            // Ho ten khong duoc chua khoan trang du thua
            hoTen = hoTen.trim().replaceAll("\\s+", " ");
            msgSucc.append(String.format("Họ tên: %s\n", hoTen));
        }

        String gioiTinh = "Nam";
        if (rdoNam.isSelected()) {
            gioiTinh = rdoNam.getText();
        } else if (rdoNu.isSelected()) {
            gioiTinh = rdoNu.getText();
        } else if (rdoKhac.isSelected()) {
            gioiTinh = rdoKhac.getText();
        }
        msgSucc.append(String.format("Giới tính: %s\n", gioiTinh));

        String queQuan = txtQueQuan.getText();
        if (queQuan.isEmpty()) {
            // Que quan rong
            msgErr.append("Quê quán: Trống!\n");
        } else {
            // Que quan khong duoc chua khoan trang du thua
            queQuan = queQuan.trim().replaceAll("\\s+", " ");
            msgSucc.append(String.format("Quê quán: %s\n", queQuan));
        }

        int namSinh = 0;
        if (txtNamSinh.getText().isEmpty()) {
            // Nam sinh rong
            msgErr.append("Năm sinh: Trống!");

            if (!msgErr.toString().isEmpty()) {
                // Neu co loi xay ra
                msg.setAlertType(Alert.AlertType.ERROR);
                msg.setHeaderText("Lỗi đăng ký");
                msg.setContentText(msgErr.toString());
                msg.show();
            }
        } else {
            try {
                namSinh = Integer.parseInt(txtNamSinh.getText());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                msgErr.append("Năm sinh: Không hợp lệ!");
            } finally {
                if (!msgErr.toString().isEmpty()) {
                    // Neu co loi xay ra
                    msg.setAlertType(Alert.AlertType.ERROR);
                    msg.setHeaderText("Lỗi đăng ký");
                    msg.setContentText(msgErr.toString());
                    msg.show();
                } else {
                    switch (saveNguoiDung(taiKhoan, matKhau, hoTen, gioiTinh, namSinh, queQuan)) {
                        case 0:
                            msg.setAlertType(Alert.AlertType.WARNING);
                            msg.setHeaderText("Cảnh báo");
                            msg.setContentText("Tài khoản này đã tồn tại. Vui lòng chọn tài khoản khác!\nXin cảm ơn.");
                            msg.show();
                            break;
                        case 1:
                            msg.setAlertType(Alert.AlertType.INFORMATION);
                            msg.setHeaderText("Đăng ký thành công");
                            msg.setContentText(msgSucc.toString());
                            msg.show();
                            break;
                    }
                }
            }
        }
    }

    private int saveNguoiDung(String taiKhoan, String matKhau, String hoTen, String gioiTinh, int namSinh, String queQuan) {
        int tinhTrangDK = 0; // 0: ton tai tai khoan nay, 1: dang ky thanh cong
        Session session = this.factory.openSession();
        NguoiDung nguoiDung = (NguoiDung) session.get(NguoiDung.class, taiKhoan);
        if (nguoiDung == null) {
            // Them moi
            tinhTrangDK = 1;
            Transaction trans = session.beginTransaction();
            nguoiDung = new NguoiDung(taiKhoan, matKhau, hoTen, gioiTinh, namSinh, queQuan);
            session.save(nguoiDung);
            try {
                trans.commit();
            } catch (Exception ex) {
                trans.rollback();
            }
        }
        session.close();
        return tinhTrangDK;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();
        msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Ai là triệu phú");
    }
}

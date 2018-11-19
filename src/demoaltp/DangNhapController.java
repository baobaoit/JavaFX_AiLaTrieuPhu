package demoaltp;

import demoaltp.database.HibernateUtil;
import demoaltp.modal.NguoiDung;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author BaoBao
 */
public class DangNhapController implements Initializable {

    private SessionFactory factory;
    private Alert msg;
    @FXML
    private TextField txtTaiKhoan;
    @FXML
    private PasswordField txtMatKhau;

    @FXML
    private void troVeHandler(ActionEvent event) {

    }

    @FXML
    private void dangKyHandler(ActionEvent event) {

    }

    @FXML
    private void dangNhapHandler(ActionEvent event) {
        String msgErr = ""; // Noi dung thong bao loi

        String taiKhoan = txtTaiKhoan.getText();
        if (taiKhoan.isEmpty()) {
            // Tai khoan rong
            msgErr = msgErr.concat("Tài khoản: Trống!\n");
        } else {
            // Tai khoan khong duoc chua khoan trang
            taiKhoan = taiKhoan.trim().replaceAll("\\s+", "");
        }

        String matKhau = txtMatKhau.getText();
        if (matKhau.isEmpty()) {
            // Mat khau rong
            msgErr = msgErr.concat("Mật khẩu: Trống!\n");
        } else if (matKhau.length() < 6) {
            // Mat khau co do dai duoi 6 ky tu
            msgErr = msgErr.concat("Mật khẩu: Phải có tối thiểu 6 ký tự.\n");
        }

        if (!msgErr.isEmpty()) {
            // Neu co loi xay ra
            msg.setAlertType(Alert.AlertType.ERROR);
            msg.setHeaderText("Lỗi đăng nhập");
            msg.setContentText(msgErr);
            msg.show();
        } else {
            if (isTonTaiNguoiDung(taiKhoan, matKhau)) {
                msg.setAlertType(Alert.AlertType.INFORMATION);
                msg.setHeaderText("Đăng nhập thành công");
                msg.setContentText("Chào mừng bạn quay trở lại! Chiến thôi!");
                msg.show();
            } else {
                msg.setAlertType(Alert.AlertType.ERROR);
                msg.setHeaderText("Lỗi đăng nhập");
                msg.setContentText("Tài khoản chưa tồn tại vui lòng đăng ký!");
                msg.show();
            }
        }
    }

    private boolean isTonTaiNguoiDung(String taiKhoan, String matKhau) {
        boolean isTonTai = false;
        Session session = factory.openSession();
        NguoiDung nguoiDung = (NguoiDung) session.get(NguoiDung.class, taiKhoan);
        if (nguoiDung != null) {
            // Kiem tra mat khau dung khong
            if (nguoiDung.getMatKhau().equals(matKhau)) {
                isTonTai = true;
            }
        }
        session.close();
        return isTonTai;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();
        msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Ai là triệu phú");
    }

}

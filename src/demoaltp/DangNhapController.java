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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private VBox vbDangNhap;
    @FXML
    private TextField txtTaiKhoan;
    @FXML
    private PasswordField txtMatKhau;
    private Stage curWindow;
    private boolean play;

    @FXML
    private void troVeHandler(ActionEvent event) {
        try {
            curWindow = (Stage) vbDangNhap.getScene().getWindow();

            curWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument_DHGB.fxml"))));
            curWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dangKyHandler(ActionEvent event) {
        try {
            curWindow = (Stage) vbDangNhap.getScene().getWindow();

            curWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLSignUp_DHGB.fxml"))));
            curWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dangNhapHandler(ActionEvent event) {
        StringBuilder msgErr = new StringBuilder(""); // Noi dung thong bao loi

        String taiKhoan = txtTaiKhoan.getText();
        if (taiKhoan.isEmpty()) {
            // Tai khoan rong
            msgErr.append("Tài khoản: Trống!\n");
        } else {
            // Tai khoan khong duoc chua khoan trang
            taiKhoan = taiKhoan.trim().replaceAll("\\s+", "");
        }

        String matKhau = txtMatKhau.getText();
        if (matKhau.isEmpty()) {
            // Mat khau rong
            msgErr.append("Mật khẩu: Trống!\n");
        } else if (matKhau.length() < 6) {
            // Mat khau co do dai duoi 6 ky tu
            msgErr.append("Mật khẩu: Phải có tối thiểu 6 ký tự.\n");
        }

        if (!msgErr.toString().isEmpty()) {
            // Neu co loi xay ra
            msg.setAlertType(Alert.AlertType.ERROR);
            msg.setHeaderText("Lỗi đăng nhập");
            msg.setContentText(msgErr.toString());
            msg.show();
        } else {
            if (isTonTaiNguoiDung(taiKhoan, matKhau)) {
                if (this.play) {
                    this.playAiLaTrieuPhu();
                } else {
                    if (taiKhoan.equals("admin")) {
                        try {
                            curWindow = (Stage) vbDangNhap.getScene().getWindow();

                            curWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("QLCauHoi.fxml"))));
                            curWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        this.playAiLaTrieuPhu();
                    }
                }
            } else {
                msg.setAlertType(Alert.AlertType.ERROR);
                msg.setHeaderText("Lỗi đăng nhập");
                msg.setContentText("Tài khoản chưa tồn tại vui lòng đăng ký!");
                msg.show();
            }
        }
    }

    private void playAiLaTrieuPhu() {
        msg.setAlertType(Alert.AlertType.INFORMATION);
        msg.setHeaderText("Đăng nhập thành công");
        msg.setContentText("Chào mừng bạn quay trở lại! Chiến thôi!");
        msg.show();
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

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }
}

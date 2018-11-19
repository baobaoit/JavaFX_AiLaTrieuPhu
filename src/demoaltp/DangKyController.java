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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private GridPane gpDangKy;
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
    
    @FXML
    private void troVeHandler(ActionEvent event) {
        
    }
    
    @FXML
    private void dangKyHandler(ActionEvent event) {
        String msgErr = ""; // Noi dung thong bao loi
        String msgSucc = ""; // Noi dung thong bao thanh cong
        
        String taiKhoan = txtTaiKhoan.getText();
        if (taiKhoan.isEmpty()) {
            // Tai khoan rong
            msgErr = msgErr.concat("Tài khoản: Trống!\n");
        } else {
            // Tai khoan khong duoc chua khoan trang
            taiKhoan = taiKhoan.trim().replaceAll("\\s+", "");
            msgSucc = msgSucc.concat(String.format("Tài khoản: %s\n", taiKhoan));
        }
        
        String matKhau = txtMatKhau.getText();
        if (matKhau.isEmpty()) {
            // Mat khau rong
            msgErr = msgErr.concat("Mật khẩu: Trống!\n");
        } else if (matKhau.length() < 6) {
            // Mat khau co do dai duoi 6 ky tu
            msgErr = msgErr.concat("Mật khẩu: Phải có tối thiểu 6 ký tự.\n");
        } else {
            msgSucc = msgSucc.concat(String.format("Mật khẩu: %s\n", matKhau));
        }
        
        String hoTen = txtHoTen.getText();
        if (hoTen.isEmpty()) {
            // Ho ten rong
            msgErr = msgErr.concat("Họ tên: Trống!\n");
        } else {
            // Ho ten khong duoc chua khoan trang du thua
            hoTen = hoTen.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Họ tên: %s\n", hoTen));
        }
        
        String gioiTinh = "Nam";
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdoNu.isSelected()) {
            gioiTinh = "Nu";
        } else if (rdoKhac.isSelected()) {
            gioiTinh = "Khac";
        }
        msgSucc = msgSucc.concat(String.format("Giới tính: %s\n", gioiTinh));
        
        String queQuan = txtQueQuan.getText();
        if (queQuan.isEmpty()) {
            // Que quan rong
            msgErr = msgErr.concat("Quê quán: Trống!\n");
        } else {
            // Que quan khong duoc chua khoan trang du thua
            queQuan = queQuan.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Quê quán: %s\n", queQuan));
        }
        
        int namSinh = 0;
        if (txtNamSinh.getText().isEmpty()) {
            // Nam sinh rong
            msgErr = msgErr.concat("Năm sinh: Trống!");
        } else {
            try {
                namSinh = Integer.parseInt(txtNamSinh.getText());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                msgErr = msgErr.concat("Năm sinh: Không hợp lệ!");
            } finally {
                if (!msgErr.isEmpty()) {
                    // Neu co loi xay ra
                    msg.setAlertType(Alert.AlertType.ERROR);
                    msg.setHeaderText("Lỗi đăng ký");
                    msg.setContentText(msgErr);
                    msg.show();
                } else {
                    saveNguoiDung(taiKhoan, matKhau, hoTen, gioiTinh, namSinh, queQuan);
                    msg.setAlertType(Alert.AlertType.INFORMATION);
                    msg.setHeaderText("Đăng ký thành công");
                    msg.setContentText(msgSucc);
                    msg.show();
                }
            }
        }
    }
    
    private void saveNguoiDung(String taiKhoan, String matKhau, String hoTen, String gioiTinh, int namSinh, String queQuan) {
        Session session = this.factory.openSession();
        NguoiDung nguoiDung = (NguoiDung)session.get(NguoiDung.class, taiKhoan);
        Transaction trans = session.beginTransaction();
        if (nguoiDung == null) {
            // Them moi
            nguoiDung = new NguoiDung();
            nguoiDung.setTaiKhoan(taiKhoan);
        }
        nguoiDung.setMatKhau(matKhau);
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setGioiTinh(gioiTinh);
        nguoiDung.setNamSinh(namSinh);
        nguoiDung.setQueQuan(queQuan);
        session.save(nguoiDung);
        trans.commit();
        session.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();
        msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Ai là triệu phú");
    }
}

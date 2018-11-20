package demoaltp;

import demoaltp.database.HibernateUtil;
import demoaltp.modal.CauHoi;
import demoaltp.modal.LinhVuc;
import demoaltp.modal.MucDo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author BaoBao
 */
public class QLCauHoiController implements Initializable {

    private SessionFactory factory;
    private Alert msg;
    @FXML
    private TextField txtNoiDung;
    @FXML
    private TextField txtDapAnA;
    @FXML
    private TextField txtDapAnB;
    @FXML
    private TextField txtDapAnC;
    @FXML
    private TextField txtDapAnD;
    @FXML
    private RadioButton rdoA;
    @FXML
    private RadioButton rdoB;
    @FXML
    private RadioButton rdoC;
    @FXML
    private RadioButton rdoD;
    @FXML
    private ComboBox<LinhVuc> cbLinhVuc;
    @FXML
    private ComboBox<MucDo> cbMucDo;
    @FXML
    private TextField txtNoiDungEdit;
    @FXML
    private TextField txtDapAnAEdit;
    @FXML
    private TextField txtDapAnBEdit;
    @FXML
    private TextField txtDapAnCEdit;
    @FXML
    private TextField txtDapAnDEdit;
    @FXML
    private RadioButton rdoAEdit;
    @FXML
    private RadioButton rdoBEdit;
    @FXML
    private RadioButton rdoCEdit;
    @FXML
    private RadioButton rdoDEdit;
    @FXML
    private ComboBox<LinhVuc> cbLinhVucEdit;
    @FXML
    private ComboBox<MucDo> cbMucDoEdit;
    @FXML
    private TableView<CauHoi> tbCauHoi;
    @FXML
    private Button btnXoaOrKhoiHoi;
    private CauHoi sltCauHoiEdit;

    @FXML
    private void troVeHandler(ActionEvent event) {

    }

    @FXML
    private void luuHandler(ActionEvent event) {
        String msgErr = ""; // Noi dung thong bao loi
        String msgSucc = ""; // Noi dung thong bao thanh cong

        String noiDung = txtNoiDungEdit.getText();
        if (noiDung.isEmpty()) {
            msgErr = msgErr.concat("Nội dung: Trống!\n");
        } else {
            noiDung = noiDung.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Nội dung: %s\n", noiDung));
        }

        String dapAnA = txtDapAnAEdit.getText();
        if (dapAnA.isEmpty()) {
            msgErr = msgErr.concat("Đáp án A: Trống!\n");
        } else {
            dapAnA = dapAnA.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án A: %s\n", dapAnA));
        }

        String dapAnB = txtDapAnBEdit.getText();
        if (dapAnB.isEmpty()) {
            msgErr = msgErr.concat("Đáp án B: Trống!\n");
        } else {
            dapAnB = dapAnB.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án B: %s\n", dapAnB));
        }

        String dapAnC = txtDapAnCEdit.getText();
        if (dapAnC.isEmpty()) {
            msgErr = msgErr.concat("Đáp án C: Trống!\n");
        } else {
            dapAnC = dapAnC.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án C: %s\n", dapAnC));
        }

        String dapAnD = txtDapAnDEdit.getText();
        if (dapAnD.isEmpty()) {
            msgErr = msgErr.concat("Đáp án D: Trống!\n");
        } else {
            dapAnD = dapAnD.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án D: %s\n", dapAnD));
        }

        String dapAn = "A";
        if (rdoAEdit.isSelected()) {
            dapAn = rdoAEdit.getText();
        } else if (rdoBEdit.isSelected()) {
            dapAn = rdoBEdit.getText();
        } else if (rdoCEdit.isSelected()) {
            dapAn = rdoCEdit.getText();
        } else if (rdoDEdit.isSelected()) {
            dapAn = rdoDEdit.getText();
        }
        msgSucc = msgSucc.concat(String.format("Đáp án: %s\n", dapAn));

        LinhVuc sltLinhVuc = cbLinhVucEdit.getSelectionModel().getSelectedItem();
        if (sltLinhVuc == null) {
            msgErr = msgErr.concat("Lĩnh vực: Chưa chọn!\n");
        } else {
            msgSucc = msgSucc.concat(String.format("Lĩnh vực: %s\n", sltLinhVuc));
        }

        MucDo sltMucDo = cbMucDoEdit.getSelectionModel().getSelectedItem();
        if (sltMucDo == null) {
            msgErr = msgErr.concat("Mức độ: Chưa chọn!\n");
        } else {
            msgSucc = msgSucc.concat(String.format("Mức độ: %s\n", sltMucDo));
        }

        if (!msgErr.isEmpty()) {
            // Neu co loi xay ra
            msg.setAlertType(Alert.AlertType.ERROR);
            msg.setHeaderText("Lỗi thêm câu hỏi");
            msg.setContentText(msgErr);
            msg.show();
        } else {
            if (saveCauHoi(noiDung, dapAnA, dapAnB, dapAnC, dapAnD, dapAn, sltLinhVuc, sltMucDo, true) == 0) {
                msg.setAlertType(Alert.AlertType.INFORMATION);
                msg.setHeaderText("Cập nhật câu hỏi thành công");
                msg.setContentText(msgSucc);
                msg.show();
                loadDataTableCauHoi();
            }
        }
    }

    @FXML
    private void xoaOrPhucHoiHandler(ActionEvent event) {
        String btnText = btnXoaOrKhoiHoi.getText();
        if (btnText.equalsIgnoreCase("xoá")) {
            xoaOrPhucHoiCauHoi(true);
            msg.setAlertType(Alert.AlertType.INFORMATION);
            msg.setHeaderText("Xoá câu hỏi thành công");
            msg.setContentText("Đã xoá câu hỏi này!\nBạn vẫn có thể khôi phục lại bằng nút Phục hồi.");
            msg.show();
        } else {
            xoaOrPhucHoiCauHoi(false);
            msg.setAlertType(Alert.AlertType.INFORMATION);
            msg.setHeaderText("Phục hồi câu hỏi thành công");
            msg.setContentText("Đã phục hồi lại câu hỏi này!");
            msg.show();
        }
        loadDataTableCauHoi();
    }

    private void xoaOrPhucHoiCauHoi(boolean isXoa) {
        Session session = factory.openSession();
        CauHoi cauHoi = (CauHoi) session.get(CauHoi.class, sltCauHoiEdit.getNoiDung());
        if (isXoa) {
            cauHoi.setXoa(1);
        } else {
            cauHoi.setXoa(0);
        }
        Transaction trans = session.beginTransaction();
        session.save(cauHoi);
        trans.commit();
        session.close();
    }

    @FXML
    private void themHandler(ActionEvent event) {
        String msgErr = ""; // Noi dung thong bao loi
        String msgSucc = ""; // Noi dung thong bao thanh cong

        String noiDung = txtNoiDung.getText();
        if (noiDung.isEmpty()) {
            msgErr = msgErr.concat("Nội dung: Trống!\n");
        } else {
            noiDung = noiDung.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Nội dung: %s\n", noiDung));
        }

        String dapAnA = txtDapAnA.getText();
        if (dapAnA.isEmpty()) {
            msgErr = msgErr.concat("Đáp án A: Trống!\n");
        } else {
            dapAnA = dapAnA.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án A: %s\n", dapAnA));
        }

        String dapAnB = txtDapAnB.getText();
        if (dapAnB.isEmpty()) {
            msgErr = msgErr.concat("Đáp án B: Trống!\n");
        } else {
            dapAnB = dapAnB.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án B: %s\n", dapAnB));
        }

        String dapAnC = txtDapAnC.getText();
        if (dapAnC.isEmpty()) {
            msgErr = msgErr.concat("Đáp án C: Trống!\n");
        } else {
            dapAnC = dapAnC.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án C: %s\n", dapAnC));
        }

        String dapAnD = txtDapAnD.getText();
        if (dapAnD.isEmpty()) {
            msgErr = msgErr.concat("Đáp án D: Trống!\n");
        } else {
            dapAnD = dapAnD.trim().replaceAll("\\s+", " ");
            msgSucc = msgSucc.concat(String.format("Đáp án D: %s\n", dapAnD));
        }

        String dapAn = "A";
        if (rdoA.isSelected()) {
            dapAn = rdoA.getText();
        } else if (rdoB.isSelected()) {
            dapAn = rdoB.getText();
        } else if (rdoC.isSelected()) {
            dapAn = rdoC.getText();
        } else if (rdoD.isSelected()) {
            dapAn = rdoD.getText();
        }
        msgSucc = msgSucc.concat(String.format("Đáp án: %s\n", dapAn));

        LinhVuc sltLinhVuc = cbLinhVuc.getSelectionModel().getSelectedItem();
        if (sltLinhVuc == null) {
            msgErr = msgErr.concat("Lĩnh vực: Chưa chọn!\n");
        } else {
            msgSucc = msgSucc.concat(String.format("Lĩnh vực: %s\n", sltLinhVuc));
        }

        MucDo sltMucDo = cbMucDo.getSelectionModel().getSelectedItem();
        if (sltMucDo == null) {
            msgErr = msgErr.concat("Mức độ: Chưa chọn!\n");
        } else {
            msgSucc = msgSucc.concat(String.format("Mức độ: %s\n", sltMucDo));
        }

        if (!msgErr.isEmpty()) {
            // Neu co loi xay ra
            msg.setAlertType(Alert.AlertType.ERROR);
            msg.setHeaderText("Lỗi thêm câu hỏi");
            msg.setContentText(msgErr);
            msg.show();
        } else {
            switch (saveCauHoi(noiDung, dapAnA, dapAnB, dapAnC, dapAnD, dapAn, sltLinhVuc, sltMucDo, false)) {
                case 0:
                    msg.setAlertType(Alert.AlertType.WARNING);
                    msg.setHeaderText("Cảnh báo");
                    msg.setContentText("Câu hỏi này đã tồn tại. Vui lòng nhập câu hỏi khác!\nXin cảm ơn.");
                    msg.show();
                    break;
                case 1:
                    msg.setAlertType(Alert.AlertType.INFORMATION);
                    msg.setHeaderText("Thêm câu hỏi thành công");
                    msg.setContentText(msgSucc);
                    msg.show();
                    break;
            }
        }
    }

    private int saveCauHoi(String noiDung, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAn, LinhVuc sltLinhVuc, MucDo sltMucDo, boolean isCapNhat) {
        int tinhTrangCH = 0; // 0: ton tai cau hoi nay, 1: them cau hoi thanh cong
        Session session = factory.openSession();
        CauHoi cauHoi = (CauHoi) session.get(CauHoi.class, noiDung);
        Transaction trans = session.beginTransaction();
        if (cauHoi == null) {
            // Them moi
            tinhTrangCH = isCapNhat ? 0 : 1;
            cauHoi = new CauHoi();
        }
        cauHoi.setNoiDung(noiDung);
        cauHoi.setDapAnA(dapAnA);
        cauHoi.setDapAnB(dapAnB);
        cauHoi.setDapAnC(dapAnC);
        cauHoi.setDapAnD(dapAnD);
        cauHoi.setDapAn(dapAn);
        cauHoi.setXoa(0);
        session.save(cauHoi);
        trans.commit();
        session.close();
        return tinhTrangCH;
    }

    private ObservableList<LinhVuc> loadLinhVuc() {
        List<LinhVuc> lstLinhVuc = new ArrayList<>();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(LinhVuc.class);
        lstLinhVuc = cr.list();
        session.close();
        return FXCollections.observableList(lstLinhVuc);
    }

    private ObservableList<MucDo> loadMucDo() {
        List<MucDo> lstMucDo = new ArrayList<>();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(MucDo.class);
        lstMucDo = cr.list();
        session.close();
        return FXCollections.observableList(lstMucDo);
    }

    private ObservableList<CauHoi> loadCauHoi() {
        List<CauHoi> lstCauHoi = new ArrayList<>();
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(CauHoi.class);
        lstCauHoi = cr.list();
        session.close();
        return FXCollections.observableList(lstCauHoi);
    }

    private void generateTableViewColumns() {
        TableColumn colNoiDung = new TableColumn("Nội dung");
        colNoiDung.setCellValueFactory(new PropertyValueFactory("noiDung"));

        TableColumn colDapAnA = new TableColumn("A");
        colDapAnA.setCellValueFactory(new PropertyValueFactory("dapAnA"));
        TableColumn colDapAnB = new TableColumn("B");
        colDapAnB.setCellValueFactory(new PropertyValueFactory("dapAnB"));
        TableColumn colDapAnC = new TableColumn("C");
        colDapAnC.setCellValueFactory(new PropertyValueFactory("dapAnC"));
        TableColumn colDapAnD = new TableColumn("D");
        colDapAnD.setCellValueFactory(new PropertyValueFactory("dapAnD"));
        TableColumn colDapAn = new TableColumn("Đáp án");
        colDapAn.setCellValueFactory(new PropertyValueFactory("dapAn"));

        TableColumn colLinhVuc = new TableColumn("Lĩnh vực");
        colLinhVuc.setCellValueFactory(new PropertyValueFactory("linhVuc"));
        TableColumn colMucDo = new TableColumn("Mức độ");
        colMucDo.setCellValueFactory(new PropertyValueFactory("mucDo"));

        tbCauHoi.getColumns().addAll(colNoiDung, colDapAnA, colDapAnB, colDapAnC, colDapAnD, colDapAn, colLinhVuc);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();
        msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Ai là triệu phú");

        cbLinhVuc.setItems(loadLinhVuc());
        cbLinhVucEdit.setItems(loadLinhVuc());
        cbMucDo.setItems(loadMucDo());
        cbMucDoEdit.setItems(loadMucDo());

        generateTableViewColumns();
        loadDataTableCauHoi();
        tbCauHoi.setRowFactory(tv -> {
            TableRow<CauHoi> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY) {
                    CauHoi cauHoi = row.getItem();
                    sltCauHoiEdit = cauHoi;
                    setContentEdit(cauHoi);
                }
            });
            return row;
        });
    }

    private void loadDataTableCauHoi() {
        tbCauHoi.getItems().clear();
        tbCauHoi.setItems(loadCauHoi());
    }

    private void setContentEdit(CauHoi cauHoi) {
        txtNoiDungEdit.setText(cauHoi.getNoiDung());
        txtDapAnAEdit.setText(cauHoi.getDapAnA());
        txtDapAnBEdit.setText(cauHoi.getDapAnB());
        txtDapAnCEdit.setText(cauHoi.getDapAnC());
        txtDapAnDEdit.setText(cauHoi.getDapAnD());
        switch (cauHoi.getDapAn()) {
            case "A":
                rdoAEdit.setSelected(true);
                break;
            case "B":
                rdoBEdit.setSelected(true);
                break;
            case "C":
                rdoCEdit.setSelected(true);
                break;
            case "D":
                rdoDEdit.setSelected(true);
                break;
        }
        cbLinhVucEdit.getSelectionModel().select(cauHoi.getLinhVuc());
        cbMucDoEdit.getSelectionModel().select(cauHoi.getMucDo());

        switch (cauHoi.getXoa()) {
            case 0:
                btnXoaOrKhoiHoi.setText("Xoá");
                break;
            case 1:
                btnXoaOrKhoiHoi.setText("Phục hồi");
                break;
        }
    }
}

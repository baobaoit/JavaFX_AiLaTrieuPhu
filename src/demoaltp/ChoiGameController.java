package demoaltp;

import demoaltp.database.HibernateUtil;
import demoaltp.modal.CauHoi;
import demoaltp.modal.MucDo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author BaoBao
 */
public class ChoiGameController implements Initializable {

    private List<Text> lstMocDiem = null;
    private List<CauHoi> lstCauHoiDe = null;
    private List<CauHoi> lstCauHoiTB = null;
    private List<CauHoi> lstCauHoiKho = null;
    private List<MucDo> lstMucDo = null;
    private SessionFactory factory = null;
    private int viTriCauHoi = 0;
    private int mucDoCauHoi = 0;
    private CauHoi curCauHoi = null;
    private Alert msg;
    private int mocCauHoi = 0;
    @FXML
    private VBox vbCotPhai;
    @FXML
    private Label lbNoiDung;
    @FXML
    private Button btnChoiceA;
    @FXML
    private Button btnChoiceB;
    @FXML
    private Button btnChoiceC;
    @FXML
    private Button btnChoiceD;
    @FXML
    private Button btnTroGiup5050;
    @FXML
    private Button btnGoiY;
    private String btnNormStyle = String.format("-fx-background-color: %s; -fx-border-color: %s; -fx-background-radius: %d; -fx-border-radius: %d", "#6495ED", " #00FFFF", 10, 10);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();
        msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Ai là triệu phú");

        initMocDiem();
        showMocDiem();

        getMucDo();
        newGame();
    }

    private void resetButtonDisable() {
        btnChoiceA.setDisable(false);
        btnChoiceB.setDisable(false);
        btnChoiceC.setDisable(false);
        btnChoiceD.setDisable(false);
    }

    private void newGame() {
        viTriCauHoi = 0;
        mocCauHoi = 0;
        mucDoCauHoi = 0;
        resetButtonDisable();
        btnTroGiup5050.setDisable(false);
        btnGoiY.setDisable(false);
        if (lstCauHoiDe != null) {
            lstCauHoiDe.clear();
        }
        getCauHoiDe();
        if (lstCauHoiTB != null) {
            lstCauHoiTB.clear();
        }
        getCauHoiTB();
        if (lstCauHoiKho != null) {
            lstCauHoiKho.clear();
        }
        getCauHoiKho();
        showCauHoi();
        showMocHienTai();
    }

    private void showKhanGiaBinhChon(int dapAnA, int dapAnB, int dapAnC, int dapAnD) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart bChart = new BarChart(xAxis, yAxis);
        bChart.setTitle("Trợ giúp");
        xAxis.setLabel("Đáp án");
        yAxis.setLabel("Phần trăm chọn");

        XYChart.Series series = new XYChart.Series();
        series.setName("Khán giả bình chọn");
        series.getData().add(new XYChart.Data<>("A", dapAnA));
        series.getData().add(new XYChart.Data<>("B", dapAnB));
        series.getData().add(new XYChart.Data<>("C", dapAnC));
        series.getData().add(new XYChart.Data<>("D", dapAnD));

        bChart.getData().add(series);
        Scene scene = new Scene(bChart, 800, 600);
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private int[] getLstPhanTram() {
        List<String> lstNhan = new ArrayList<>();
        lstNhan.add("A");
        lstNhan.add("B");
        lstNhan.add("C");
        lstNhan.add("D");

        int[] lstPhanTram = new int[4];
        int viTriDapAnDung = lstNhan.indexOf(curCauHoi.getDapAn());
        int phanTramDapAnDung = (mocCauHoi <= 4) ? 70 : (mocCauHoi > 4 && mocCauHoi <= 9) ? 60 : 50;
        lstPhanTram[viTriDapAnDung] = phanTramDapAnDung;
        Random rand = new Random();
        int phanTramConLai = 100 - phanTramDapAnDung;

        if (lstPhanTram[0] == 0) {
            lstPhanTram[0] = rand.nextInt(phanTramConLai + 1);
            phanTramConLai -= lstPhanTram[0];
        }

        for (int i = 1; i < 4; i++) {
            if (i != viTriDapAnDung) {
                lstPhanTram[i] = rand.nextInt(phanTramConLai + 1);
                phanTramConLai -= lstPhanTram[i];
            }
        }

        return lstPhanTram;
    }

    @FXML
    private void goiYHandler(ActionEvent event) {
        int[] lstPhanTram = getLstPhanTram();
        showKhanGiaBinhChon(lstPhanTram[0], lstPhanTram[1], lstPhanTram[2], lstPhanTram[3]);
        btnGoiY.setDisable(true);
    }

    @FXML
    private void troGiup5050Handler(ActionEvent event) {
        List<String> dapAn = new ArrayList<>();
        dapAn.add("A");
        dapAn.add("B");
        dapAn.add("C");
        dapAn.add("D");
        dapAn.remove(curCauHoi.getDapAn());
        dapAn.remove(dapAn.get((new Random()).nextInt(3))); // Dap an chon con lai

        if (btnChoiceA.getText().substring(0, 1).equals(dapAn.get(0))) {
            btnChoiceA.setDisable(true);
        }
        if (btnChoiceB.getText().substring(0, 1).equals(dapAn.get(0)) || btnChoiceB.getText().substring(0, 1).equals(dapAn.get(1))) {
            btnChoiceB.setDisable(true);
        }
        if (btnChoiceC.getText().substring(0, 1).equals(dapAn.get(0)) || btnChoiceC.getText().substring(0, 1).equals(dapAn.get(1))) {
            btnChoiceC.setDisable(true);
        }
        if (btnChoiceD.getText().substring(0, 1).equals(dapAn.get(1))) {
            btnChoiceD.setDisable(true);
        }

        btnTroGiup5050.setDisable(true);
    }

    @FXML
    private void chonDapAnHandler(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();
        String answer = btnClicked.getText().substring(0, 1);

        btnClicked.setStyle("-fx-background-color: RED; -fx-border-color: #00FFFF; -fx-background-radius: 10; -fx-border-radius: 10; -fx-font-weight: BOLD;");

        msg.setAlertType(Alert.AlertType.CONFIRMATION);
        msg.setHeaderText("Lựa chọn đáp án " + btnClicked.getText());
        msg.setContentText("Bạn có chắc đây là đáp án cuối cùng chứ?");

        Optional<ButtonType> option = msg.showAndWait();

        if (option.get() == ButtonType.OK) {
            if (answer.equals(curCauHoi.getDapAn())) {
                // Tra loi dung
                if (mocCauHoi < 14) {
                    // Chua toi cau hoi so 15
                    msg.setAlertType(Alert.AlertType.CONFIRMATION);
                    msg.setHeaderText("Chúc mừng bạn đã trả lời đúng");
                    msg.setContentText("Bạn muốn tiếp tục (OK) hay ngừng lại (Cancel)?");

                    Optional<ButtonType> optChoiHayDung = msg.showAndWait();

                    if (optChoiHayDung.get() == ButtonType.OK) {
                        viTriCauHoi++;
                        mocCauHoi++;
                        showCauHoi();
                        showMocHienTai();
                        resetMocTruoc();
                        btnClicked.setStyle(btnNormStyle);
                        resetButtonDisable();
                    } else {
                        msg.setAlertType(Alert.AlertType.INFORMATION);
                        msg.setHeaderText("Chúc mừng bạn");
                        msg.setContentText("Cảm ơn bạn đã tham gia cùng chúng tôi");

                        Optional<ButtonType> optThoat = msg.showAndWait();
                        if (optThoat.get() == ButtonType.OK) {
                            try {
                                Stage curWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                curWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument_DHGB.fxml"))));
                            } catch (IOException ex) {
                                Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } // Xu ly khi OK o thong bao thoat game
                    } // Xu ly khi hien thong bao dung
                } else {
                    // Toi cau hoi so 15
                    showDialogThongBao("Bạn đã chiến thắng", "Chúc mừng bạn đã xuất sắc vượt qua 15 câu hỏi.\nBạn có muốn chơi lại không?", event);
                }
            } else {
                // Tra loi sai
                String mocDiem;
                if (mocCauHoi > 4 && mocCauHoi < 9) {
                    mocDiem = lstMocDiem.get(4).getText();
                    showDialogThongBao("Bạn đã thua", "Bạn nhận được phần thưởng là " + mocDiem.substring(mocDiem.indexOf(" ")) + ".\nBạn có muốn chơi lại không?", event);
                } else if (mocCauHoi > 9) {
                    mocDiem = lstMocDiem.get(9).getText();
                    showDialogThongBao("Bạn đã thua", "Bạn nhận được phần thưởng là " + mocDiem.substring(mocDiem.indexOf(" ")) + ".\nBạn có muốn chơi lại không?", event);
                } else {
                    showDialogThongBao("Bạn đã thua", "Bạn có muốn chơi lại không?", event);
                }
                btnClicked.setStyle(btnNormStyle);
            }
        } else {
            // Cancel
            btnClicked.setStyle(btnNormStyle);
        }
    }

    private void showDialogThongBao(String headerText, String contentText, ActionEvent event) {
        msg.setAlertType(Alert.AlertType.CONFIRMATION);
        msg.setHeaderText(headerText);
        msg.setContentText(contentText);

        Optional<ButtonType> option = msg.showAndWait();
        if (option.get() == ButtonType.OK) {
            mocCauHoi++;
            resetMocTruoc();
            newGame();
        } else {
            try {
                Stage curWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

                curWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument_DHGB.fxml"))));
            } catch (IOException ex) {
                Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void resetMocTruoc() {
        if (mocCauHoi - 1 >= 0) {
            Text txtMocDiem = lstMocDiem.get(mocCauHoi - 1);
            txtMocDiem.setFill(Color.BLACK);
            txtMocDiem.setFont(Font.font(18));
        }
    }

    private void showMocHienTai() {
        if (mocCauHoi < 15) {
            Text txtMocDiem = lstMocDiem.get(mocCauHoi);
            txtMocDiem.setFill(Color.RED);
            txtMocDiem.setFont(Font.font(20));
        }
    }

    private void showCauHoi() {
        if (viTriCauHoi == 5) {
            mucDoCauHoi++;
            viTriCauHoi = 0;
        }

        switch (mucDoCauHoi) {
            case 0:
                curCauHoi = lstCauHoiDe.get(viTriCauHoi);
                break;
            case 1:
                curCauHoi = lstCauHoiTB.get(viTriCauHoi);
                break;
            case 2:
                curCauHoi = lstCauHoiKho.get(viTriCauHoi);
                break;
        }

        lbNoiDung.setText(curCauHoi.getNoiDung());
        btnChoiceA.setText(String.format("A. %s", curCauHoi.getDapAnA()));
        btnChoiceB.setText(String.format("B. %s", curCauHoi.getDapAnB()));
        btnChoiceC.setText(String.format("C. %s", curCauHoi.getDapAnC()));
        btnChoiceD.setText(String.format("D. %s", curCauHoi.getDapAnD()));
    }

    private void getMucDo() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(MucDo.class);
        lstMucDo = cr.list();
        session.close();
    }

    private List<CauHoi> getCauHoi(int mucDo) {
        List<CauHoi> lstCH = null;
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(CauHoi.class);
        cr.add(Restrictions.eq("mucDo", lstMucDo.get(mucDo)));
        cr.add(Restrictions.eq("xoa", 0));
        cr.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()"));
        cr.setMaxResults(5);
        lstCH = cr.list();
        session.close();
        return lstCH;
    }

    private void getCauHoiDe() {
        lstCauHoiDe = getCauHoi(0);
    }

    private void getCauHoiTB() {
        lstCauHoiTB = getCauHoi(1);
    }

    private void getCauHoiKho() {
        lstCauHoiKho = getCauHoi(2);
    }

    private void showMocDiem() {
        for (int i = lstMocDiem.size() - 1; i >= 0; i--) {
            vbCotPhai.getChildren().add(lstMocDiem.get(i));
        }
    }

    private void initMocDiem() {
        if (lstMocDiem == null) {
            lstMocDiem = new ArrayList<>();
            lstMocDiem.add(new Text("1. 200"));
            lstMocDiem.add(new Text("2. 400"));
            lstMocDiem.add(new Text("3. 600"));
            lstMocDiem.add(new Text("4. 1,000"));
            lstMocDiem.add(new Text("5. 2,000"));
            lstMocDiem.add(new Text("6. 3,000"));
            lstMocDiem.add(new Text("7. 6,000"));
            lstMocDiem.add(new Text("8. 10,000"));
            lstMocDiem.add(new Text("9. 14,000"));
            lstMocDiem.add(new Text("10. 22,000"));
            lstMocDiem.add(new Text("11. 30,000"));
            lstMocDiem.add(new Text("12. 40,000"));
            lstMocDiem.add(new Text("13. 60,000"));
            lstMocDiem.add(new Text("14. 85,000"));
            lstMocDiem.add(new Text("15. 150,000"));
        }
    }
}

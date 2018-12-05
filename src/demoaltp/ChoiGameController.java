package demoaltp;

import demoaltp.database.HibernateUtil;
import demoaltp.modal.CauHoi;
import demoaltp.modal.MucDo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = HibernateUtil.getSessionFactory();

        this.initMocDiem();
        this.showMocDiem();
//        this.showHieuUngMocDiem();

        this.getMucDo();
        this.getCauHoiDe();
        this.getCauHoiTB();
        this.getCauHoiKho();
        
        this.showCauHoi();
    }

    private void showCauHoi() {
        CauHoi curCauHoi = null;

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
        
        this.lbNoiDung.setText(curCauHoi.getNoiDung());
        this.btnChoiceA.setText(String.format("A. %s", curCauHoi.getDapAnA()));
        this.btnChoiceB.setText(String.format("B. %s", curCauHoi.getDapAnB()));
        this.btnChoiceC.setText(String.format("C. %s", curCauHoi.getDapAnC()));
        this.btnChoiceD.setText(String.format("D. %s", curCauHoi.getDapAnD()));
    }

    private void getMucDo() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(MucDo.class);
        this.lstMucDo = cr.list();
        session.close();
    }

    private void getCauHoiDe() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(CauHoi.class);
        cr.add(Restrictions.eq("mucDo", lstMucDo.get(0)));
        cr.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()"));
        cr.setMaxResults(5);
        this.lstCauHoiDe = cr.list();
        session.close();
    }

    private void getCauHoiTB() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(CauHoi.class);
        cr.add(Restrictions.eq("mucDo", lstMucDo.get(1)));
        cr.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()"));
        cr.setMaxResults(5);
        this.lstCauHoiTB = cr.list();
        session.close();
    }

    private void getCauHoiKho() {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(CauHoi.class);
        cr.add(Restrictions.eq("mucDo", lstMucDo.get(2)));
        cr.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()"));
        cr.setMaxResults(5);
        this.lstCauHoiKho = cr.list();
        session.close();
    }

//    private int viTriQuanTrong = 5;
//
//    private void showHieuUngMocDiem() {
//        Timeline tl2 = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
//            int viTriCanLay = 0;
//            switch (viTriQuanTrong) {
//                case 10:
//                    viTriCanLay = 4;
//                    break;
//                case 15:
//                    viTriCanLay = 9;
//                    break;
//                case 20:
//                    viTriCanLay = 14;
//                    break;
//            }
//
//            Text txtMocDiem = lstMocDiem.get(viTriCanLay);
//            txtMocDiem.setStroke(null);
//            txtMocDiem.setStrokeWidth(0);
//
//        }));
//        tl2.setCycleCount(3);
//        tl2.play();
//
//        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
//            Text txtMocDiem = lstMocDiem.get(viTriQuanTrong - 1);
//            txtMocDiem.setStroke(Color.YELLOW);
//            txtMocDiem.setStrokeWidth(3);
//            viTriQuanTrong += 5;
//        }));
//        tl.setCycleCount(3);
//        tl.play();
//
//    }
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

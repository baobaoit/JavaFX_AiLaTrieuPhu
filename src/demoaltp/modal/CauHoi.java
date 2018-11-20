package demoaltp.modal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BaoBao
 */
@Entity
@Table(name = "cau_hoi")
public class CauHoi {
    @Id
    @Column(name = "noi_dung", nullable = false, length = 500)
    private String noiDung;
    @Column(name = "dapan_a", nullable = false, length = 500)
    private String dapAnA;
    @Column(name = "dapan_b", nullable = false, length = 500)
    private String dapAnB;
    @Column(name = "dapan_c", nullable = false, length = 500)
    private String dapAnC;
    @Column(name = "dapan_d", nullable = false, length = 500)
    private String dapAnD;
    @Column(name = "dapan", nullable = false, length = 500)
    private String dapAn;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "linh_vuc", referencedColumnName = "id")
    private LinhVuc linhVuc;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "muc_do", referencedColumnName = "id")
    private MucDo mucDo;
    @Column(name = "xoa", nullable = false)
    private int xoa;
    
    public CauHoi() {}
    
    public CauHoi(String noiDung, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAn, LinhVuc linhVuc, MucDo mucDo, int xoa) {
        this.noiDung = noiDung;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAn = dapAn;
        this.linhVuc = linhVuc;
        this.mucDo = mucDo;
        this.xoa = xoa;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public LinhVuc getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(LinhVuc linhVuc) {
        this.linhVuc = linhVuc;
    }

    public MucDo getMucDo() {
        return mucDo;
    }

    public void setMucDo(MucDo mucDo) {
        this.mucDo = mucDo;
    }

    public int getXoa() {
        return xoa;
    }

    public void setXoa(int xoa) {
        this.xoa = xoa;
    }
}

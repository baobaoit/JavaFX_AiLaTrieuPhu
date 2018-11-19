package demoaltp.database;

import demoaltp.modal.CauHoi;
import demoaltp.modal.LinhVuc;
import demoaltp.modal.MucDo;
import demoaltp.modal.NguoiDung;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author BaoBao
 */
public class HibernateUtil {
    private static final SessionFactory FACTORY;
    private static final ServiceRegistry SERVICE_REGISTRY;
    static {
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(NguoiDung.class);
        conf.addAnnotatedClass(MucDo.class);
        conf.addAnnotatedClass(LinhVuc.class);
        conf.addAnnotatedClass(CauHoi.class);
        SERVICE_REGISTRY = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(SERVICE_REGISTRY);
    }
    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
    
    public static void close() {
        if (SERVICE_REGISTRY != null)
            StandardServiceRegistryBuilder.destroy(SERVICE_REGISTRY);
    }
}

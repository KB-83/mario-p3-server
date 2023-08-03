import org.hibernate.Session;
import org.hibernate.query.Query;
import model.main_model.Client;
import model.main_model.chat.Chat;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ChatDAO {

//    public List<Chat> getChatsForClient(String username) {
//        try (Session session = HibernateUtil.getSession()) {
//            String hql = "SELECT c FROM Chat c WHERE c.opponentUsername = :username";
//
//            Query<Chat> query = session.createQuery(hql, Chat.class);
//            query.setParameter("username", username);
//
//            return query.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
    public List<Chat> getChatsForClient(String username) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT DISTINCT c FROM Chat c LEFT JOIN FETCH c.massages WHERE c.opponentUsername = :username";
            Query<Chat> query = session.createQuery(hql, Chat.class);
            query.setParameter("username", username);

            List<Chat> chats = query.getResultList();

            return chats;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}

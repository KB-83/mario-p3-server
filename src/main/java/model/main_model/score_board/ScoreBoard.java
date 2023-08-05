package model.main_model.score_board;

import model.main_model.Client;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

public class ScoreBoard {
    private ScoreBoard(){}

    public static List<Client> getPlayersByScore() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Client ORDER BY score DESC", Client.class)
                          .list();
        }
    }

    public static List<Client> searchPlayersByUsername(String username) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createCriteria(Client.class)
                    .add(Restrictions.ilike("username", "%" + username + "%"))
                          .list();
        }
    }

    public static List<Client> getPlayersByScoreRange(int minScore, int maxScore) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createCriteria(Client.class)
                          .add(Restrictions.between("score", minScore, maxScore))
                          .list();
        }
    }
}

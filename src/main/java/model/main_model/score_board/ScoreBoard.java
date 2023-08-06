package model.main_model.score_board;

import controller.ShopController;
import model.dto.score.ScoreBoardDTO;
import model.dto.score.ScoreDTO;
import model.main_model.Client;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.Config;
import util.Constant;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private ScoreBoard(){}

    public static ScoreBoardDTO getScoreBoard() {
        try (Session session = HibernateUtil.getSession()) {
            List<Client> clientsByScore = session.createQuery("FROM Client ORDER BY score DESC", Client.class).list();
            ScoreBoardDTO scoreBoardDTO = new ScoreBoardDTO();
            List<ScoreDTO> scores = new ArrayList<>();
            for (int i = 0;i < clientsByScore.size();i++) {
                Client c = clientsByScore.get(i);
                int grade = 0;
                if (c.getScore() > 0) {
                    //  سطح بازیکن برابر با جزء صحیح لگاریتم مبنای دو امتیازش * levelMultiplier است
                    double log2 = Math.log(c.getScore()) / Math.log(2);
                    grade = (int) (log2 * ShopController.levelMultiplier);
                }
                scores.add(new ScoreDTO(String.valueOf(i+1),c.getUsername(),
                        String.valueOf(c.getScore()),String.valueOf(grade)));
            }
            scoreBoardDTO.setScoreDTOS(scores);
            return scoreBoardDTO;
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

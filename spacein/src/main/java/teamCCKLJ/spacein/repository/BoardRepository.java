package teamCCKLJ.spacein.repository;

import org.springframework.stereotype.Repository;
import teamCCKLJ.spacein.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardRepository {
    @PersistenceContext
    EntityManager em;

    public int save(Board board){
        em.persist(board);
        return board.getBoard_id();
    }
    public Board find(String id)
    {
        return em.find(Board.class, id);
    }
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }
}

package teamCCKLJ.spacein.repository;

import org.springframework.stereotype.Repository;
import teamCCKLJ.spacein.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepository {
    @PersistenceContext
    EntityManager em;
    public int save(Comment comment){
        em.persist(comment);
        return comment.getComment_id();
    }
    public Comment find(String id)
    {
        return em.find(Comment.class, id);
    }
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }
}

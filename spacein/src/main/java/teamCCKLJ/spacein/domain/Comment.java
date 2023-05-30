package teamCCKLJ.spacein.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    private String comment_content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
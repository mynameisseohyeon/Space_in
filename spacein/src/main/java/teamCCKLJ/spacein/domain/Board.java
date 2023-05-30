package teamCCKLJ.spacein.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int board_id;
    private String board_title;
    private String board_content;
    @OneToMany(mappedBy = "board")
    private List<Comment> comments;
}

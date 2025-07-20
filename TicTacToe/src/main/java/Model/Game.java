package Model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board {
    private Cell[][] board;
    private int len;

    public Board (int n) {
        this.board = new Cell[len][len];
    }
}

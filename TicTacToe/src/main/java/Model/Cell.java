package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    private int row;

    @Id
    private int col;

    private Player val;

    public Cell(int row, int col, Player val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

package minesweeper;

import java.io.Serializable;

/**
 *
 * @author Ghaith
 */
public enum SquareStatus implements Serializable{
    OPEN,
    CLOSED,
    FLAGGED
}

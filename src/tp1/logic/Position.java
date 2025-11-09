package tp1.logic;

public class Position {

	private int col;
	private int row;

	// Constructor
    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public Position up() {
    	Position newPos = new Position (this.col,this.row-1);
    	return newPos;
    }

    // Comparar si las posiciones son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }
    
}


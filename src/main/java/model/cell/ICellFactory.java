package model.cell;

public interface ICellFactory {
    public Cell load(char cell);
    public void save(Cell cell);
}

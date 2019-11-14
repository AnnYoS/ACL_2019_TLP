package model.dao;

import model.World;

public interface IWorldDAO {
    public World load(int lvl);
    public void save(World world);
}

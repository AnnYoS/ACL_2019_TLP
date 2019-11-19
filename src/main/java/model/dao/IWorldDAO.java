package model.dao;

import model.World;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IWorldDAO {
    public World load(String path) throws IOException;
    public void save(World world);
}

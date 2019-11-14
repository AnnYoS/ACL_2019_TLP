package model.dao;

import model.Map;

public interface IMapDAO {
    public Map load(int lvl);
    public void save(Map map);
}

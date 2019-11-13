package model.dao;

import model.Map;
import model.MapFactory;
import model.World;

public class DAOFactory implements IDAOFactory{
    private WorldDAO worldDAO;
    private MapDAO mapDAO;

    private static DAOFactory instance = null;

    private DAOFactory(){
        worldDAO = new WorldDAO();
        mapDAO = new MapDAO();
    }

    public static DAOFactory getInstance(){
        if(instance==null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public WorldDAO getWorldDAO(){
        return worldDAO;
    }

    public MapDAO getMapDAO(){
        return mapDAO;
    }

}

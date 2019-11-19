package model.dao;

import model.Map;
import model.MapFactory;
import model.World;

public class DAOFactory implements IDAOFactory{
    private WorldDAO worldDAO;

    private static DAOFactory instance = null;

    private DAOFactory(){
        worldDAO = new WorldDAO();
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
}

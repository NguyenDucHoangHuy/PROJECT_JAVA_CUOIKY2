package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
     public int Login(T t);
     public int createaccount(T t);
     public ArrayList<T> selectAll();

}

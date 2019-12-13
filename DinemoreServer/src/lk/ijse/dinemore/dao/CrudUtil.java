package lk.ijse.dinemore.dao;

import lk.ijse.dinemore.db.DBConnection;

import java.sql.PreparedStatement;

public class CrudUtil {
    public static <T> T execute(String sql,Object...params) throws Exception {
        PreparedStatement pst= DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pst.setObject(i+1,params[i]);
        }

        if(sql.startsWith("Select")){
            return (T) pst.executeQuery();
        }

        return (T) (Boolean)(pst.executeUpdate()>0);
    }
}

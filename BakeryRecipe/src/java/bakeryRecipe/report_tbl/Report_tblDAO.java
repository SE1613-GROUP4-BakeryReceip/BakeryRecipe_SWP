/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakeryRecipe.report_tbl;

import bakeryRecipe.utils.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ThongNT
 */
public class Report_tblDAO {

    public boolean addNewReport(int user_id, int recipe_id, String report_detail, Date created_date) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            connection = DBConnection.getConnection();
            if (connection != null) {
                //2. Create SQl String
                String sql = "INSERT INTO `bakery_recipe`.`report_tbl` (`user_id`, `recipe_id`, `report_detail`, `created_date`) "
                        + "VALUES (?, ?, ?, ?);";
                //3. Create statement obj
                stm = connection.prepareStatement(sql);
                stm.setInt(1, user_id);
                stm.setInt(2, recipe_id);
                stm.setString(3, report_detail);
                stm.setDate(4, created_date);
//                System.out.println("----------------------------------------------");
                //4. Execute query
                int tmp = stm.executeUpdate();

                //5. Process result
                if (tmp != 0) {
                    result = true;
                }
            }//end check conection is not null            
            return result;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    } //end addNewReport function
}
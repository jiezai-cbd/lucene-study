package cn.itheima.dao;

import cn.itheima.pojo.Sku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SkuDaoImpl implements SkuDao{


    @Override
    public List<Sku> querySkuList() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Sku> list = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene_demom","root","futian");

            String sql = "SELECT * from tb_sku";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Sku sku = new Sku();
                sku.setId(resultSet.getString("id"));
                sku.setName(resultSet.getString("name"));
                sku.setSpec(resultSet.getString("spec"));
                sku.setBrandName(resultSet.getString("brand_name"));
                sku.setCategoryName(resultSet.getString("category_name"));
                sku.setImage(resultSet.getString("image"));
                sku.setNum(resultSet.getInt("num"));
                sku.setPrice(resultSet.getInt("price"));
                sku.setSaleNum(resultSet.getInt("sale_num"));
                list.add(sku);

            }
        } catch (Exception e){
            e.printStackTrace();;
        }
        return list;
    }
}

package cn.itcast.dao.impl;

import java.util.List;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.QueryResult;
import cn.itcast.exception.DaoException;
import cn.itcast.utils.*;

public class CustomerDaoImpl implements CustomerDao {

    public void add(Customer customer) {
        try {
            String sql = "insert into customer(id,name,gender,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?)";
            Object params[] = {customer.getId(), customer.getName(), customer.getGender(), customer.getBirthday(), customer.getCellphone(), customer.getEmail(), customer.getPreference(), customer.getType(), customer.getDescription()};
            JdbcUtils.update(sql, params);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void update(Customer customer) {   //id
        try {
            String sql = "update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=?  where id=?";
            Object params[] = {customer.getName(), customer.getGender(), customer.getBirthday(), customer.getCellphone(), customer.getEmail(), customer.getPreference(), customer.getType(), customer.getDescription()};
            JdbcUtils.update(sql, params);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public void delete(String id) {
        try {
            String sql = "delete from customer where id=?";
            Object params[] = {id};
            JdbcUtils.update(sql, params);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public Customer find(String id) {
        try {
            String sql = "select * from customer where id=?";
            Object params[] = {id};
            return (Customer) JdbcUtils.query(sql, params, new BeanHandler(Customer.class));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }


    public List<Customer> getAll() {
        try {
            String sql = "select * from customer";
            Object params[] = {};
            return (List<Customer>) JdbcUtils.query(sql, params, new BeanListHandler(Customer.class));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    //获取到页面数据和大小
    public QueryResult pageQuery(int startIndex, int pageSize) {
        QueryResult qr = new QueryResult();

        try {
            String sql = "select * from customer limit ?,?";
            Object params[] = {startIndex, pageSize};
            List list = (List) JdbcUtils.query(sql, params, new BeanListHandler(Customer.class));
            qr.setList(list);

            sql = "select count(*) from customer";
            params = new Object[]{};
            int totalRecord = (Integer) JdbcUtils.query(sql, params, new IntHandler());
            qr.setTotalRecord(totalRecord);
            return qr;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

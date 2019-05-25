package org.smart4j.test.aop.service;

import org.smart4j.test.aop.helper.DatabaseHelper;
import org.smart4j.test.aop.model.Customer;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.bean.FileParam;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 */
@Service
public class CustomerService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
//
//    private static final String DRIVER;
//    private static final String URL;
//    private static final String USERNAME;
//    private static final String PASSWORD;
//
//    static {
//        Properties conf = PropsUtil.loadProps("config.properties");
//        DRIVER=conf.getProperty("jdbc.driver");
//        URL=conf.getProperty("jdbc.url");
//        USERNAME=conf.getProperty("jdbc.username");
//        PASSWORD=conf.getProperty("jdbc.password");
//
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("can not load jdbc driver",e);
//
//        }
//    }

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        //Connection conn=DatabaseHelper.getConnection();
        //Connection conn=null;
        //try {

            //List<Customer> customerList=new ArrayList<Customer>();
              String sql="SELECT * FROM customer";
//            conn= DatabaseHelper.getConnection();
//            PreparedStatement stmt=conn.prepareStatement(sql);
//            ResultSet rs=stmt.executeQuery();
//            while(rs.next()) {
//                Customer customer=new Customer();
//                customer.setId(rs.getLong("id"));
//                customer.setName(rs.getString("name"));
//                customer.setContact(rs.getString("contact"));
//                customer.setTelephone(rs.getString("telephone"));
//                customer.setEmail(rs.getString("email"));
//                customer.setRemark(rs.getString("remark"));
//                customerList.add(customer);
//            }
            return DatabaseHelper.queryEntityList(Customer.class,sql);
//        }
//        catch (SQLException e) {
//            LOGGER.error("execute sql failure",e);
//        }
//        finally {
//            DatabaseHelper.closeConnection(conn);
//        }
    }

    /**
     *
     * 获取客户
     */
    public Customer getCustomer(long id) {
        //TODO
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     *创建客户
     */
    @Transaction
    public boolean createCustomer(Map<String,Object> fieldMap, FileParam fileParam) {

        boolean result = DatabaseHelper.insertEntity(Customer.class,fieldMap);
        if(result) {
            UploadHelper.uploadFile("tmp/upload/", fileParam);
        }
        return result;
    }

    /**
     * 更新客户
     */
    @Transaction
    public boolean updateCustomer(long id,Map<String,Object> fieldMap) {

        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);

    }

    /**
     * 删除客户
     */
    @Transaction
    public boolean deleteCustomer(long id) {

        return DatabaseHelper.deleteEntity(Customer.class,id);
    }


}

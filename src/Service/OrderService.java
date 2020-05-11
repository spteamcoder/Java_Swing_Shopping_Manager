/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Order;
import java.util.List;

/**
 *
 * @author VLT
 */
public interface OrderService {

    List<Order> getListOrder();

    boolean findOrderById(String id);

}

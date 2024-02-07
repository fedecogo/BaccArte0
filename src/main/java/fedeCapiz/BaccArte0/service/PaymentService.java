
package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.repositories.OrderDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDAO orderDAO;

}

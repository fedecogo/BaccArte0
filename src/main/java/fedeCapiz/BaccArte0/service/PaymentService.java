
package fedeCapiz.BaccArte0.service;

import fedeCapiz.BaccArte0.repositories.OrderDAO;
import fedeCapiz.BaccArte0.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderDAO orderDAO;}
   /*
    public void processPayment(Payment payment) {
        try {
            // Controlla se l'ordine esiste nel sistema
            Order order = orderDAO.findById(payment.getId())
                    .orElseThrow(() -> new OrderNotFoundException("Ordine non trovato"));

            // Controlla se l'utente esiste nel sistema
            User user = userDAO.findById(payment.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

            // Elabora il pagamento...
        } catch (OrderNotFoundException | UserNotFoundException e) {
            // Gestisci l'eccezione
            // Log, notifica l'utente, ecc.
        } catch (InvalidPaymentException e) {
            // Gestisci il pagamento non valido
            // Log, notifica l'utente, ecc.
        } catch (Exception e) {
            // Gestisci altre eccezioni non previste
            // Log, notifica l'utente, ecc.
        }
    }
}
*/

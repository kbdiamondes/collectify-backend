package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.DuePayments;
import com.capstone.collectify.repositories.ClientModuleRepository.DuePaymentsRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service class for handling DuePayments related operations.
 */
@Service
public class DuePaymentsServiceImpl implements DuePaymentsService {

    @Autowired
    private DuePaymentsRepository duePaymentRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Creates a new DuePayments entry associated with the specified client.
     *
     * @param duePayments The DuePayments object to be created.
     * @param clientId    The ID of the associated client.
     */
    public void createDuePayments(Long clientId, DuePayments duePayments) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            //Collector placeholder = new Collector
            //.setCollector(placeholder)
            duePayments.setClient(client);
            duePaymentRepository.save(duePayments);
        }

    }

    /**
     * Retrieves all DuePayments entries.
     *
     * @return Iterable of DuePayments objects.
     */
    public Iterable<DuePayments> getDuePayments() {
        //if(placeholder
        return duePaymentRepository.findAll();
    }

    /**
     * Retrieves all DuePayments entries associated with a specific client.
     *
     * @param clientId The ID of the client.
     * @return Iterable of DuePayments objects.
     */
    public Iterable<DuePayments> getDuePaymentsByClientId(Long clientId) {
        return duePaymentRepository.findDuePaymentsByClientId(clientId);
    }

    /**
     * Deletes a DuePayments entry associated with a specific client.
     *
     * @param clientId       The ID of the client.
     * @param id  The ID of the DuePayments entry.
     * @return ResponseEntity with a message indicating success or failure.
     */
    public ResponseEntity deleteDuePayments(Long clientId, Long id) {
        DuePayments duePaymentsToDelete = duePaymentRepository.findById(id).orElse(null);

        if (duePaymentsToDelete != null && duePaymentsToDelete.getClient().getClient_id().equals(clientId)) {
            duePaymentRepository.deleteById(id);
            return new ResponseEntity<>("Due Payments Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Due payment not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a DuePayments entry associated with a specific client.
     *
     * @param clientId      The ID of the client.
     * @param id            The ID of the DuePayments entry.
     * @param duePayments   The updated DuePayments object.
     * @return ResponseEntity with a message indicating success or failure.
     */
    public ResponseEntity updateDuePayments(Long clientId, Long id, DuePayments duePayments) {
        DuePayments duePaymentsForUpdate = duePaymentRepository.findById(id).orElse(null);
        if (duePaymentsForUpdate != null && duePaymentsForUpdate.getClient().getClient_id().equals(clientId)) {
            duePaymentsForUpdate.setRequiredCollectible(duePayments.getRequiredCollectible());
            duePaymentsForUpdate.setDueStatus(duePayments.isDueStatus());
            duePaymentRepository.save(duePaymentsForUpdate);
            return new ResponseEntity<>("Due Payments Updated successfully", HttpStatus.OK);
        }
        //if(==0)

        return new ResponseEntity<>("Due payment not found or does not belong to the specified client",HttpStatus.NOT_FOUND);
    }
}
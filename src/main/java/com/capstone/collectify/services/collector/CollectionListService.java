package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.PaymentTransactionForCollectionListDTO;
import com.capstone.collectify.models.PaymentTransactionWithCollectorDTO;

import java.util.List;

public interface CollectionListService {
    //List<PaymentTransaction> getAssignedUncollectedPaymentsTransactionsForCollector(Long collectorId);

    List<PaymentTransactionForCollectionListDTO> getAssignedUncollectedPaymentsTransactionsForCollector(Long collectorId);
    /*
    List<Contract> getAssignedPaidContractsForCollector(Long collectorId);

    List<Contract> getAssignedUnpaidContractsForCollector(Long collectorId);


    List<Contract> getAssignedUncollectedContractsForCollector(Long collectorId);

     */

}

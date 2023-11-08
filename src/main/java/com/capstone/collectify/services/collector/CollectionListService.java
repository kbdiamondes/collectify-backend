package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransaction;

import java.util.List;

public interface CollectionListService {
    List<PaymentTransaction> getAssignedUncollectedPaymentTransactionsForCollector(Long collectorId);

    /*
    List<Contract> getAssignedPaidContractsForCollector(Long collectorId);

    List<Contract> getAssignedUnpaidContractsForCollector(Long collectorId);


    List<Contract> getAssignedUncollectedContractsForCollector(Long collectorId);

     */

}

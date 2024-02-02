package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.PaymentTransactionWithCollectorDTO;

import java.util.List;

public interface MyCollectorsService {
    //List<PaymentTransaction> getCollectorsAssignedPaymentTransactions(Long resellerId);
    List<PaymentTransaction> getAssignedPaymentTransactionsByReseller(Long resellerId);

    List<PaymentTransactionWithCollectorDTO> getAssignedPaymentTransactionsByResellerWithCollectorName(Long resellerId);

    /*
    List<Collector> getCollectorsAssignedByReseller(Long resellerId);
    List<Contract> getContractsAssignedToCollectorByReseller(Long resellerId, Long collectorId);*/
}

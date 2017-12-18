package com.service;

import com.businessdomain.Cheque;
import com.businessdomain.Pizza;

import java.util.Map;

public interface ChequeService {

    Cheque placeCheque(Map<Pizza, Integer> orderedPizzas);
}

package com.dg.main.service;


import com.dg.main.util.slzcResult;

public interface SaleService {

	slzcResult selectAll(int offset, int limit, String search);

}

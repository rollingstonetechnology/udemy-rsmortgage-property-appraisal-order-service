package com.rollingstone.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.PropertyAppraisalOrder;



public interface RsMortgagePropertyAppraisalOrderRepository extends PagingAndSortingRepository<PropertyAppraisalOrder, Long> {

    Page findAll(Pageable pageable);
}

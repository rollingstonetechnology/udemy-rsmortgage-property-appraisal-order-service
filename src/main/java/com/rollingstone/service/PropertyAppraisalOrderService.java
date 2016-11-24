package com.rollingstone.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rollingstone.dao.jpa.RsMortgagePropertyAppraisalOrderRepository;
import com.rollingstone.domain.Customer;
import com.rollingstone.domain.PropertyAppraisalOrder;


/*
 * Service class to do CRUD for Customer PropertyAppraisalOrder through JPS Repository
 */
@Service
public class PropertyAppraisalOrderService {

    private static final Logger log = LoggerFactory.getLogger(PropertyAppraisalOrderService.class);

    @Autowired
    private RsMortgagePropertyAppraisalOrderRepository customerPropertyAppraisalOrderRepository;
    
  
    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public PropertyAppraisalOrderService() {
    }

    public PropertyAppraisalOrder createPropertyAppraisalOrder(PropertyAppraisalOrder propertyAppraisalOrder) {
        return customerPropertyAppraisalOrderRepository.save(propertyAppraisalOrder);
    }

    public PropertyAppraisalOrder getPropertyAppraisalOrder(long id) {
        return customerPropertyAppraisalOrderRepository.findOne(id);
    }

    public void updatePropertyAppraisalOrder(PropertyAppraisalOrder propertyAppraisalOrder) {
    	customerPropertyAppraisalOrderRepository.save(propertyAppraisalOrder);
    }

    public void deletePropertyAppraisalOrder(Long id) {
    	customerPropertyAppraisalOrderRepository.delete(id);
    }

    public Page<PropertyAppraisalOrder> getAllPropertyAppraisalOrdersByPage(Integer page, Integer size) {
        Page pageOfPropertyAppraisalOrders = customerPropertyAppraisalOrderRepository.findAll(new PageRequest(page, size));
        
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfPropertyAppraisalOrders;
    }
    
    public List<PropertyAppraisalOrder> getAllPropertyAppraisalOrders() {
        Iterable<PropertyAppraisalOrder> pageOfPropertyAppraisalOrders = customerPropertyAppraisalOrderRepository.findAll();
        
        List<PropertyAppraisalOrder> customerPropertyAppraisalOrders = new ArrayList<PropertyAppraisalOrder>();
        
        for (PropertyAppraisalOrder propertyAppraisalOrder : pageOfPropertyAppraisalOrders){
        	customerPropertyAppraisalOrders.add(propertyAppraisalOrder);
        }
    	log.info("In Real Service getAllPropertyAppraisalOrders  size :"+customerPropertyAppraisalOrders.size());

    	
        return customerPropertyAppraisalOrders;
    }
    
}

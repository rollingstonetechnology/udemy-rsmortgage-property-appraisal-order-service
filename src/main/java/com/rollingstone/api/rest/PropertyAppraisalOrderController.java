package com.rollingstone.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.rollingstone.domain.Customer;
import com.rollingstone.domain.PropertyAppraisalOrder;
import com.rollingstone.exception.HTTP400Exception;
import com.rollingstone.service.PropertyAppraisalOrderService;
/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/rsmortgage-customer-background-check-service/v1/background-check-order")
public class PropertyAppraisalOrderController extends AbstractRestController {

    @Autowired
    private PropertyAppraisalOrderService customerPropertyAppraisalOrderService;
  
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerPropertyAppraisalOrder(@RequestBody PropertyAppraisalOrder propertyAppraisalOrder,
                                 HttpServletRequest request, HttpServletResponse response) {
    	PropertyAppraisalOrder createdPropertyAppraisalOrder = this.customerPropertyAppraisalOrderService.createPropertyAppraisalOrder(propertyAppraisalOrder);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdPropertyAppraisalOrder.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<PropertyAppraisalOrder> getAllCustomersPropertyAppraisalOrderByPage(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.customerPropertyAppraisalOrderService.getAllPropertyAppraisalOrdersByPage(page, size);
    }
    
    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<PropertyAppraisalOrder> getAllCustomerPropertyAppraisalOrders(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
    
        return this.customerPropertyAppraisalOrderService.getAllPropertyAppraisalOrders();
    }
    
  
    
    @RequestMapping("/simple/{id}")
	public PropertyAppraisalOrder getSimpleCustomerPropertyAppraisalOrder(@PathVariable("id") Long id) {
    	PropertyAppraisalOrder propertyAppraisalOrder = this.customerPropertyAppraisalOrderService.getPropertyAppraisalOrder(id);
         checkResourceFound(propertyAppraisalOrder);
         return propertyAppraisalOrder;
	}

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    PropertyAppraisalOrder getPropertyAppraisalOrder(@PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	PropertyAppraisalOrder propertyAppraisalOrder = this.customerPropertyAppraisalOrderService.getPropertyAppraisalOrder(id);
        checkResourceFound(propertyAppraisalOrder);
        return propertyAppraisalOrder;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerPropertyAppraisalOrder(@PathVariable("id") Long id, @RequestBody PropertyAppraisalOrder propertyAppraisalOrder,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.customerPropertyAppraisalOrderService.getPropertyAppraisalOrder(id));
        if (id != propertyAppraisalOrder.getId()) throw new HTTP400Exception("ID doesn't match!");
        this.customerPropertyAppraisalOrderService.updatePropertyAppraisalOrder(propertyAppraisalOrder);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerPropertyAppraisalOrder(@PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.customerPropertyAppraisalOrderService.getPropertyAppraisalOrder(id));
        this.customerPropertyAppraisalOrderService.deletePropertyAppraisalOrder(id);
    }
}

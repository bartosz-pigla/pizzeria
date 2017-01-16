package com.pizzashop.models.builders;

import com.pizzashop.models.Complaint;
import com.pizzashop.models.Order;
import com.pizzashop.models.enums.ComplaintStatus;

import java.sql.Date;

public class ComplaintBuilder {
    private Date submitDate;
    private String comment;
    private Order order;
    private ComplaintStatus complaintStatus;

    public ComplaintBuilder setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
        return this;
    }

    public ComplaintBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ComplaintBuilder setOrder(Order order) {
        this.order = order;
        return this;
    }

    public ComplaintBuilder setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
        return this;
    }

    public Complaint createComplaint() {
        return new Complaint(submitDate, comment, order, complaintStatus);
    }
}
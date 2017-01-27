package com.pizzashop.models;

import com.pizzashop.models.enums.ComplaintStatus;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@Entity
public class Complaint  implements Serializable {
    private Integer complaintId;

    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    private Date submitDate;

    @NotNull    @Length(max=255)
    private String comment;

    @NotNull
    private Order order;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ComplaintStatus complaintStatus;

    public Complaint(Date submitDate, String comment, Order order, ComplaintStatus complaintStatus) {
        this.submitDate = submitDate;
        this.comment = comment;
        this.order = order;
        this.complaintStatus = complaintStatus;
    }

    public Complaint() {
    }

    @Id    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaintId")
    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    @Basic
    @Column(name = "submitDate")
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="productOrderId")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Basic
    @Column(name = "complaintStatus")
    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(order, complaint.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order);
    }
}

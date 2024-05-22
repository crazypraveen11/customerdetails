package IndianBank.customerdetails;

import java.math.BigDecimal;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactiondetails")
public class transactionEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence1")
    @SequenceGenerator(name = "Sequence1", sequenceName = "myseq1",allocationSize = 1)
    private Long transactionNumber;
    private String transactionType;
    private BigDecimal currentBalance;
    private BigDecimal transactionAmount;
    private String transactionHolderNumber;
    @Column(name = "transactiondate")
    private Date transactionDate;
    

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "accountNumber",nullable = false)
    private accountentity account;
}

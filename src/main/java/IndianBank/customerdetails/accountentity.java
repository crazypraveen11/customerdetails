package IndianBank.customerdetails;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Account")
public class accountentity 
{
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence")   
    @SequenceGenerator(name = "sequence", sequenceName = "Mysequence",allocationSize = 1)
    private Long accountnumber;
    private String accountHoldername; 
    private String accountIfsccode;
    private BigDecimal accountBalance;
    @Column(name = "contactno")
    private Long accountHoldercontactno;
    @Column(name = "place")
    private String accountHolderplace;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "connection", joinColumns = @JoinColumn(name="accountNumber"),inverseJoinColumns = @JoinColumn(name="transactionNumber"))
    private List<transactionEntity> translist = new ArrayList<>();
}
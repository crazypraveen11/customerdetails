package IndianBank.customerdetails;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "AccountDetails")
public class accountentity implements UserDetails
{
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence")   
    @SequenceGenerator(name = "sequence", sequenceName = "Mysequence",allocationSize = 1)
    private Long accountNumber;
    private String accountHoldername; 
    private String accountIfsccode;
    private BigDecimal accountBalance;
    @Column(name = "contactno")
    private Long accountHoldercontactno;
    @Column(name = "place")
    private String accountHolderplace;
    private String password;
 

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "connection", joinColumns = @JoinColumn(name="accountNumber"),inverseJoinColumns = @JoinColumn(name="transactionNumber"))
    private List<transactionEntity> translist = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return null;
    }


    @Override
    public String getPassword() {
           return password;
    }


    @Override
    public String getUsername() {
          return accountHoldername;
    }


    @Override
    public boolean isAccountNonExpired() {
          return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
package IndianBank.customerdetails;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("indianbank")
public class accountcontroller 
{
    @Autowired
    accountservice service;


    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    //accountcreate
    @PostMapping("/accountcreate")
    public String accountcreate(@RequestBody accountentity accountdetails)
    {
        accountdetails.setPassword(encoder().encode(accountdetails.getPassword()));
        return service.creation(accountdetails).getAccountHoldername()+" has been created successfully...!";
    }

    //listalldetails
    @GetMapping("/listalldetails")
    public List<accountentity> allaccountvalues()
    {
        return service.allaccountlist();
    }

    //findaccount
    @GetMapping("/findaccount/{accno}")
    public accountentity getaccountdetails(@PathVariable("accno") Long accno)
    {
        return service.findbyaccount(accno) ;
    }

    //updateaccountdetails
    @PutMapping("/updateaccountdetails")
    public String updateaccount(@RequestBody accountentity accountdetails)
    {
        accountentity acc = service.creation(accountdetails);
        return acc.getAccountNumber() +" your account has been updated successfully..!";
    }

    //deletebyid
    @DeleteMapping("/deletebyid/{account}")
    public String  deleteaccount(@PathVariable("account")long account)
    {
        return service.deletebyaccount(account);
    }

    //findbyplace
    @GetMapping("/findbyplace/{place}")
    public List<accountentity> findbyplace(@PathVariable("place")String place)
    {
        return service.getvaluebyplace(place);
    }


    @Autowired
    transactionservice tservice;

    //Create transaction
    @PostMapping("/createtransaction")
    public transactionEntity transactioncreate(@RequestBody transactionEntity transdetails)
    {
        transdetails.setCurrentBalance(transdetails.getAccount().getAccountBalance());

        if(transdetails.getTransactionType().equalsIgnoreCase("credit"))
        {
            transdetails.setCurrentBalance(transdetails.getCurrentBalance().add(transdetails.getTransactionAmount()));

            BigDecimal currentbalance = transdetails.getCurrentBalance();

            accountentity acc = service.findbyaccount(transdetails.getAccount().getAccountNumber());

            acc.setAccountBalance(currentbalance);
        }
        else if (transdetails.getTransactionType().equalsIgnoreCase("debit")) 
        {
            if (transdetails.getAccount().getAccountBalance().compareTo(transdetails.getTransactionAmount())>0) 
            {
                transdetails.setCurrentBalance(transdetails.getCurrentBalance().subtract(transdetails.getTransactionAmount()));

                BigDecimal currentbalance = transdetails.getCurrentBalance();

                accountentity acc = service.findbyaccount(transdetails.getAccount().getAccountNumber());
    
                acc.setAccountBalance(currentbalance); 
            }      
        }
        return tservice.createtransaction(transdetails);
    }

    //List alltransaction
    @GetMapping("/getalltransaction")
    public List<transactionEntity> listalltransaction()
    {
        return tservice.listtransaction();
    }

    //getbytransaction Id
    @GetMapping("/transactionbyid/{transnum}")
    public Optional<transactionEntity> findbytransid(@PathVariable long transnum)
    {
        return tservice.findonetransaction(transnum);
    }

    //Delete transaction
    @DeleteMapping("/deletetransactionbyid/{id}")
    public void deletetransactionid(@PathVariable long id )
    {
        tservice.deleteTransaction(id);
    }

    //gettransactionfromoneaccount
    @GetMapping("/gettransactionfromoneaccount/{id}")
    public List<transactionEntity> gettrans(@PathVariable long id)
    {
        accountentity acc = service.findbyaccount(id);
        return tservice.gettransactionfromoneuser(acc);
    }
}

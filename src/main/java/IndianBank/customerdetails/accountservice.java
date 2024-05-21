package IndianBank.customerdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class accountservice 
{
    @Autowired
    accountrepository repo;

    public accountentity creation(accountentity accountdetails)
    {
        return repo.save(accountdetails);
    }

    public List<accountentity> allaccountlist()
    {
        return repo.findAll();
    }

    public accountentity findbyaccount(Long accountno)
    {
        return repo.findById(accountno).orElse(new accountentity());
    } 

    public String deletebyaccount(Long account)
    {
        accountentity acc = repo.findById(account).orElse(new accountentity());
        repo.deleteById(account);
        return acc.getAccountHoldername()+" has been deleted successfully...!";
    }

    public List<accountentity> getvaluebyplace(String place)
    {
       return repo.findByAccountHolderplace(place);
    }
}
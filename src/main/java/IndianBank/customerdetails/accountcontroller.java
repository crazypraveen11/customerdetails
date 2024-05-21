package IndianBank.customerdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class accountcontroller 
{
    @Autowired
    accountservice service;

    @PostMapping("/accountcreate")
    public String accountcreate(@RequestBody accountentity accountdetails)
    {
        return service.creation(accountdetails).getAccountHoldername()+" has been created successfully...!";
    }

    @GetMapping("/listalldetails")
    public List<accountentity> allaccountvalues()
    {
        return service.allaccountlist();
    }

    @GetMapping("/findaccount/{accno}")
    public accountentity getaccountdetails(@PathVariable("accno") Long accno)
    {
        return service.findbyaccount(accno) ;
    }

    @PutMapping("/updateaccountdetails")
    public String updateaccount(@RequestBody accountentity accountdetails)
    {
        accountentity acc = service.creation(accountdetails);
        return acc.getAccountnumber() +" your account has been updated successfully..!";
    }

    @DeleteMapping("/deletebyid/{account}")
    public String  deleteaccount(@PathVariable("account")long account)
    {
        return service.deletebyaccount(account);
    }

    @GetMapping("/findbyplace/{place}")
    public List<accountentity> findbyplace(@PathVariable("place")String place)
    {
        return service.getvaluebyplace(place);
    }
}

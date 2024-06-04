package IndianBank.customerdetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import javax.swing.Spring;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
// @RunWith(Spring.class)
@ExtendWith(MockitoExtension.class)
class CustomerdetailsApplicationTests 
{
	@Autowired
		transactionservice tservice;

	@Mock
	private accountrepository repo;

	@InjectMocks
	private accountentity account;
	private accountservice service;

	@Mock
	private transactionrepository trepo;

	@InjectMocks
	private transactionEntity transaction1;
	private transactionEntity transaction2;

	@BeforeEach
	public void setUp()
	{
		account = new accountentity();
		account.setAccountHoldername("Praveen");
		account.setAccountNumber(7654323456l);
		account.setAccountHoldercontactno(6379873794l);
		account.setAccountIfsccode("IDIB00897");
		account.setAccountHolderplace("Rasipuram");
		account.setAccountBalance(BigDecimal.valueOf(20000.00));

		Date transdate = new Date();
		transaction1 = new transactionEntity(1234567856723l, "credit", account.getAccountBalance(),
		BigDecimal.valueOf(5000), "6380590269", transdate, account);
		transaction2 = new transactionEntity(8765454328765l, "debit", account.getAccountBalance(),
		BigDecimal.valueOf(10000), "6381722900", transdate, account);
	}

	@Test
	public void testCreation()
	{
		when(repo.save(any(accountentity.class))).thenReturn(account);

		accountentity acc = service.creation(account);

		// assertNotNull(acc);

		assertNotEquals(account, acc);

		// assertEquals(BigDecimal.valueOf(20000.00), acc.getAccountBalance());
	}

	// @Test
	// public void testGetTransactionByOneUser()
	// {
	// 	List<transactionEntity> trans = Arrays.asList(transaction1,transaction2);

	// 	when(trepo.findAllByAccount(account)).thenReturn(trans);
	// 	List<transactionEntity> process = tservice.gettransactionfromoneuser(account);
		
	// 	assertNotNull(process);

	// 	// assertEquals(5, process.size());

	// 	// assertEquals(trans, process);

	// 	// verify(trepo, times(1)).findAllByAccount(account);
	// }

}

package insper.store.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AccountResource implements AccountController {

    @Autowired
    private AccountService accountService;
    

    @GetMapping("/accounts/info")
    public String info() {
        return "Account service";
    }

    
    @Override
    public ResponseEntity<AccountOut> create(AccountIn in) {

        // TODO Auto-generated method stub

        Account account = AccountParser.to(in);
        Account created = new AccountService().create(account);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.id()).toUri()).body(AccountParser.to(created));

    }

    @Override
    public ResponseEntity<AccountOut> update(String id, AccountIn in) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ResponseEntity<AccountOut> auth(AuthIn in) {
        // TODO Auto-generated method stub
        Account  account= accountService.auth(in.email(), in.password());
        if(account == null){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(AccountParser.to(account));
    }
    
}
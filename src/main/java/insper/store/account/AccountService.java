package insper.store.account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account in) {

        return accountRepository.save(new AccountModel(in)).to();
    }

    public Account read(String id) {
        return accountRepository.findById(id).map(AccountModel::to).orElse(null);
    }

    public Account  findByEmailAndPass (String email, String pass) throws NoSuchAlgorithmException{
        String hash = calculateHash(pass);

        return accountRepository.findByEmailAndHash(email, hash).map(AccountModel::to).orElse(null);
    }


    private String calculateHash(String pass) throws NoSuchAlgorithmException{

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(pass.getBytes(StandardCharsets.UTF_8));
        byte [] encoded = Base64.getEncoder().encode(hash);
        return new String(encoded);
    }

    public Account auth(String email, String pass) {
        try {
            return findByEmailAndPass(email, pass);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
            // e.printStackTrace();
            // return null;
        }
    }



    
}

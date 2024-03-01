package insper.store.account;

public class AccountParser {

    public static Account to (AccountIn  in) {

        return Account.builder()
            .name(in.name())
            .email(in.email())
            .password(in.password())
            .build();

    }

    public static AccountOut to (Account in) {

        return AccountOut.builder()
            .id(in.id())
            .name(in.name())
            .email(in.email())
            .build();

    }

    
}

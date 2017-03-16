package restful.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by TungNguyen on 8/12/16.
 */

@Entity
@Table (name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "account")
    private double account;

    @Column(name = "password")
    private String password;

    @Column (name = "balance")
    private double balance;

    @Column (name = "payment")
    private double payment;

    public AccountEntity() {
    }

    public AccountEntity(int account, String password, double balance, double payment) {
        this.account = account;
        this.password = password;
        this.balance = balance;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}

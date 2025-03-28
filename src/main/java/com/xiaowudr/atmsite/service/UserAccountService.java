package com.xiaowudr.atmsite.service;

import com.xiaowudr.atmsite.mapper.AccountLockMapper;
import com.xiaowudr.atmsite.mapper.AntiScriptAccountMapper;
import com.xiaowudr.atmsite.mapper.AccountMapper;
import com.xiaowudr.atmsite.pojo.Account;
import com.xiaowudr.atmsite.pojo.AccountLock;
import com.xiaowudr.atmsite.util.TTASP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;

@Service
public class UserAccountService {

    private static final Logger log = LoggerFactory.getLogger(UserAccountService.class);

    static String connectionUrl = "jdbc:sqlserver://123.57.182.30:1433;databaseName=drgame;;user=sa;password=P@ss123456";
    static Connection con;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLockMapper accountLockMapper;

    public void registerUser(Account account) {
        account.setEncryptedPassword(TTASP.ttasp_Password(account.getPassword()));
        accountMapper.insertUser(account);
    }

    public Account getUserByAccount(String accountID) {
       return accountMapper.getUserByAccount(accountID);
    }

    public int updateUser(Account account) {
        return accountMapper.updateUser(account);
    }

    public void unlockUser(String accountId) {
       accountLockMapper.unblockAccountLock(accountId);
    }

    public void lockUser(String accountId, int lockType, String execPath) {
        Account currentAccount = accountMapper.getUserByAccount(accountId);
        if(currentAccount!=null) {
            //String accountId = currentAccount.getAccountID();
            AccountLock accountLock = accountLockMapper.getAccountLock(accountId);

            AccountLock newAccountLock = new AccountLock();
            newAccountLock.setAccountId(accountId);
            newAccountLock.setLockType(lockType);
            newAccountLock.setIsBlocked(1);
            newAccountLock.setExecPath(execPath);

            if(accountLock!=null) {
                accountLockMapper.updateAccountLock(newAccountLock);
            } else {
                accountLockMapper.insertAccountLock(newAccountLock);
            }
            //accountLockMapper.insertAccountLock(newAccountLock);
        }
        //return accountMapper.lockUser(accountId, lockType);
    }

    //static Statement stcd mt;
    static {
        //log.info("test log");
        try {
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void assertConnection() {
        try {
            if (con.isClosed()) {
                con = DriverManager.getConnection(connectionUrl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllCurrentUser() {
        assertConnection();
        // Create a variable for the connection string.
        ResultSet rs = null;
        try {
            String SQL = "SELECT TOP 10 * FROM GAMEUSER";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                if (log.isDebugEnabled()) {
                    log.debug(rs.getString("FirstName") + " " + rs.getString("LastName"));
                }
                System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void freezeUserAccount(String userID) {
        assertConnection();
        Statement stmt = null;

        try {
            String SQLTemplate = "update user_antiscript set isblocked = 1 where account = '%s'";
            stmt = con.createStatement();
            int rs = stmt.executeUpdate(String.format(SQLTemplate, userID));
            LocalDateTime currentDate = LocalDateTime.now();
            if (rs == 1) {
                if (log.isDebugEnabled()) {
                    log.debug("block user successful - " + userID + ", current time: " + currentDate);
                }
                System.out.println("Freeze user successful - " + userID + ", current time: " + currentDate);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateantitimestamp(String userID) {
        assertConnection();
        Statement stmt = null;
        try {
            String SQLTemplate = "update user_antiscript set antitimestamp = getdate() where account = '%s'";
            stmt = con.createStatement();
            int rs = stmt.executeUpdate(String.format(SQLTemplate, userID));
            //LocalDate currentDate = LocalDate.now();
            LocalDateTime currentDate = LocalDateTime.now();
            if (rs == 1) {
                if (log.isDebugEnabled()) {
                    log.debug("update timestamp successful - " + userID + ", current time: " + currentDate);
                }

                System.out.println("update timestamp successful - " + userID + ", current time: " + currentDate);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
import java.util.*;
interface Details
{
        public void DisplayDetails();
}
class Bank implements Details
{
      public String account_holder;
      public String account_no;
      
      public long  balance;
      Bank(String account_holder)
      {
            this.account_holder=account_holder;
      }
      public void DisplayDetails()
      {
            ;
      }

}
class Savings_Account extends Bank
{
     Savings_Account(String account_holder)
     {
          super(account_holder);
     }
     public void DisplayDetails()
     {
         System.out.print("This is your Savings Account..Your Account Number is:"+this.account_no+"\nYou hold the Balance of Rs."+this.balance);
     }
}

class Expenses_Account extends Bank
{
      Expenses_Account(String account_holder)
      {
          super(account_holder);
      }
      public void DisplayDetails()
     {
         System.out.print("This is your Expenses Account..Your Account Number is:"+this.account_no+"\nYou hold the Balance of Rs."+this.balance);
     }

}

class FD_Account   extends Bank
{
     
      FD_Account(String account_holder)
      {
           super(account_holder);
      }
      public void DisplayDetails()
     {
         System.out.print("This is your FixedDeposit Account..Your Account Number is:"+this.account_no+"\nYou hold the Balance of Rs."+this.balance);
     }

}

class NRI_Account   extends Bank
{
       public String country_of_account_holder;
       
        NRI_Account(String account_holder,String country_of_account_holder)
        {
             
              super(account_holder);
              this.country_of_account_holder=country_of_account_holder;
        }
        public void DisplayDetails()
        {
            System.out.print("This is your NRI Account and you are from "+this.country_of_account_holder+" Your Account Number is:"+this.account_no+"\nYou hold the Balance of Rs."+this.balance);
        }


}
public class BankApplication {
  public static int search(ArrayList<Bank>arr,String account_number)
  {
          for(int i=0;i<arr.size();i++)
          {
                if(arr.get(i).account_no.equals(account_number))
                   return i;
          }
          return -1;
  }
  public static String createAccountNumber()
  {
         String alpha="abcdefghijklmnopqrstuvwxyz"+"1234567890";
         int size=16;
         StringBuilder sb = new StringBuilder(16); 
 
         for (int i = 0; i < size; i++) { 
        
       
          int index 
           = (int)(alpha.length() 
             * Math.random()); 
        
        
          sb.append(alpha.charAt(index)); 
         } 
         return sb.toString();
  }
    public static void main(String[] args)
    {
              ArrayList<Bank>accounts=new ArrayList<Bank>();
                Scanner sc=new Scanner(System.in);
                System.out.println("Welcome To Our Bank! We are here to help You");
                System.out.println("You are requested to choose the purpose of visiting our bank");
                String query=sc.nextLine();
                if(query.equals("NEW_USER_ACCOUNT"))
                {
                         
                    System.out.println("We are Happy to have you here!");
                    System.out.println("Please Enter the Type of Account that you gonna have");
                    String account_type=sc.nextLine();
                    if(!(account_type.equals("SAVINGS") || account_type.equals("NRI") || account_type.equals("FD")))
                    {
                            System.out.println("Sorry! Account Type Invalid");
                    }
                    else
                    {
                        System.out.println("You have chosen "+account_type+" Account");
                        System.out.println("You are asked to Proceed..");
                        System.out.println("Enter the Name:");
                        String name=sc.nextLine();
                        Bank user;
                        if(account_type.equals("SAVINGS"))
                        {
                               user=new Savings_Account(name);
                        }
                        else if(account_type.equals("NRI"))
                        {
                               System.out.println("Please Enter your Country");
                               String country=sc.nextLine();
                                user=new NRI_Account(name,country);
                        }
                        else
                        {
                              user=new FD_Account(name);

                        }
                        user.account_no=createAccountNumber();
                        accounts.add(user);
                        System.out.println("Account Created Successfully");
                        System.out.println("Your Minimum Balance Should be Rs.500. You are requested to fullfill the request");
                        System.out.println("Enter the amount to be deoposited ");
                        long amount=sc.nextLong();
                        
                        if(amount<500)
                        {
                               System.out.println("Request Not Satisfied !");
                        }
                        else
                        {
                               System.out.println("You have deposited Rs."+amount+"successfully");
                               user.balance=amount;
                               
                        }
                        user.DisplayDetails();
                    }
            }
                else if(query.equals("EXISTING_USER"))
                {
                      System.out.println("Please Enter you Account Number");

            
                        String account_number=sc.nextLine();int index=search(accounts,account_number);
                        if(index==-1)
                        {
                                 System.out.println("You are not the Existing User");
                        }
                        else
                        {
                                Bank user=accounts.get(index);
                                 System.out.println("You have to deposit or withdraw");
                                 System.out.println("Enter 0 for withdraw, 1 for deposit");
                                  int option=sc.nextInt();
                                 if(option==0)
                                 {
                                       System.out.println("Enter the Amount for withdrawing");
                                       long withdraw=sc.nextLong();
                                       if(user.balance>=withdraw)
                                       {
                                               user.balance-=withdraw;
                                               System.out.println("You have withdrew successfully");
                                       }
                                       else
                                       {
                                             System.out.println("Insufficient Balance");
                                       }
                                 }
                                 else
                                 {
                                       System.out.println("Enter the money for depositing");
                                       long depo=sc.nextLong();
                                       user.balance+=depo;
                                 }
                                 
                        }
                }
                else if(query.equals("ABOUT"))
                {
                      System.out.println("THIS IS ABC BANK OF INDIA. We are one of the most prefered Banks all over India");
                }


                sc.close();
    }
}

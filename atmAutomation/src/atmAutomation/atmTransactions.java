package atmAutomation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class atmTransactions {
	Scanner myChoose = new Scanner(System.in);
	String name;
	String sirname;
	double balance;
	String filePath="C:\\Users\\user\\Desktop\\Data\\usersData.txt";
	String tempFile="C:\\Users\\user\\Desktop\\Data\\temp.txt";
	
	public atmTransactions(){	
	}
	void withdrawMoney() {
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(filePath));
			String line=buffRead.readLine();
			StringBuilder updatedBalance = new StringBuilder();
			while(line!=null) {
				String[] sp = line.split(" ");
				for(int i=0;i<sp.length;i++) {
					if(name.equals(sp[i])&&sirname.equals(sp[i+1])) {
						System.out.print("Money:");
						double money;
				        while (true) {
				            if (myChoose.hasNextDouble()) {
				                money = myChoose.nextDouble();
				                break;
				            } else {
				                System.out.println("Please enter a valid number.");
				                myChoose.next(); 
				            }
				        }
						balance = Double.parseDouble(sp[i+2]);
						if (balance > 0) {
	                        double sub = balance - money;
	                        if (sub < 0) {
	                            System.out.println("Insufficient funds. Withdrawal cannot be completed.");
	                        } else {
	                            sp[i + 2] = Double.toString(sub);
	                        }
	                    } else {
	                        System.out.println("Your balance is 0. Withdrawal cannot be completed.");
	                    }
					}
					
				}
				updatedBalance.append(String.join(" ", sp)).append("\n");
				line=buffRead.readLine();
			}
			buffRead.close();
			BufferedWriter writeBuff = new BufferedWriter(new FileWriter(filePath));
			writeBuff.write(updatedBalance.toString().trim());
			writeBuff.close();
			System.out.println("press y to menu ...");
			String choose =myChoose.next();
			if("y".equals(choose) || "Y".equals(choose)) {
				mainScreen();
			}else {
				mainScreen();
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	void deposit() {
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(filePath));
			String line=buffRead.readLine();
			StringBuilder updatedBalance = new StringBuilder();
			while(line!=null) {
				String[] sp = line.split(" ");
				for(int i=0;i<sp.length;i++) {
					if(name.equals(sp[i])&&sirname.equals(sp[i+1])) {
						System.out.print("Money:");
						 double money;
					        while (true) {
					            if (myChoose.hasNextDouble()) {
					                money = myChoose.nextDouble();
					                break;
					            } else {
					                System.out.println("Please enter a valid number.");
					                myChoose.next(); 
					            }
					        }
						balance = Double.parseDouble(sp[i+2]);
						double sum=balance+money;
						sp[i+2] = Double.toString(sum);
						
					}
					
				}
				updatedBalance.append(String.join(" ", sp)).append("\n");
				line=buffRead.readLine();
			}
			buffRead.close();
			BufferedWriter writeBuff = new BufferedWriter(new FileWriter(filePath));
			writeBuff.write(updatedBalance.toString().trim());
			writeBuff.close();
			System.out.println("press y to menu ...");
			String choose =myChoose.next();
			if("y".equals(choose) || "Y".equals(choose)) {
				mainScreen();
			}else {
				mainScreen();
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	void information() {
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(filePath));
			String line=buffRead.readLine();
			while(line!=null) {
				String[] sp = line.split(" ");
				for(int i=0;i<sp.length;i++) {
					if(name.equals(sp[i])&&sirname.equals(sp[i+1])) {
						System.out.println("My Balance:"+sp[i+2]);
						System.out.println("press y to menu ...");
						String choose =myChoose.next();
						if("y".equals(choose) || "Y".equals(choose)) {
							mainScreen();
						}else {
							menu();
						}
					}
				}
				line=buffRead.readLine();
			}
			buffRead.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	void mainScreen() {
		System.out.println("*****Menu*****");
		System.out.println("1-My Information");
		System.out.println("2-Deposit");
		System.out.println("3-Withdraw Money");
		System.out.println("4-Return Log");
		System.out.println("5-Exit");
		System.out.print("Choose an action:");
		 int action;
	        while (true) {
	            if (myChoose.hasNextInt()) {
	                action = myChoose.nextInt();
	                break;
	            } else {
	                System.out.println("Please enter a valid number.");
	                myChoose.next(); 
	            }
	        }
		switch(action) {
		case 1:
			information();
			break;
		case 2:
			deposit();
			break;
		case 3:
			withdrawMoney();
			break;
		case 4:
			menu();
			break;
		case 5:
			System.exit(1);
			break;
		}
		
		
	}
	
	void menu() {
		
		System.out.println("ATM AUTOMATION");
		System.out.println("1- SIGN IN");
		System.out.println("2- SIGN UP");
		System.out.println("3- Delete Record");
		System.out.println("Choose an action:");
		int data;
	        while (true) {
	            if (myChoose.hasNextInt()) {
	                data = myChoose.nextInt();
	                break;
	            } else {
	                System.out.println("Please enter a valid number.");
	                myChoose.next(); 
	            }
	        }
		atmTransactions action = new atmTransactions();
	 
		switch (data) {
        case 1:
            System.out.print("Name:");
            name = readName();
            System.out.print("Surname:");
            sirname = readName();
            action.signIn(name, sirname);
            myChoose.close();
            break;
        case 2:
            System.out.print("Name:");
            name = readName();
            System.out.print("Surname:");
            sirname = readName();
            double balance = 0;
            action.signUp(name, sirname, balance);
            myChoose.close();
            break;
        case 3:
        	dataDelete();
        	break;
        default:
            System.out.println("Invalid option.");
            break;
		}
		
	}
	 private String readName() {
	        while (true) {
	            String input = myChoose.next();
	            if (input.matches("[a-zA-ZüÜğĞıİöÖçÇşŞ]+")) {
	                return input;
	            } else {
	                System.out.println("Please enter a valid name (only letters).");
	            }
	        }
	    }
	
	void signIn(String name,String sirname) {
			BufferedReader buffRead = null;
			try {
				FileReader readFile = new FileReader(filePath);
				buffRead = new BufferedReader(readFile);
				
				String line=buffRead.readLine();
				while(line!=null) {
					String[] sp = line.split(" ");
					for(int i=0;i<sp.length;i++) {
						if(name.equals(sp[i])&&sirname.equals(sp[i+1])) {
							System.out.println("Successful transaction.");
							this.name=name;
							this.sirname=sirname;
							mainScreen();
							return ;
						}
					}
					line=buffRead.readLine();
				}
				
			
				System.out.println("Failed login. You are directed to the menu...");
				menu();
				
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
		            if (buffRead != null) {
		                buffRead.close();
		            }
		        } catch (IOException e) {
		            System.out.println("Error closing BufferedReader: " + e.getMessage());
		        }
			}
		
		
	}
	String signUp(String name,String sirname,double balance) {
		try {
			FileWriter data = new FileWriter(filePath,true);
			data.write(name + " " + sirname + " " + balance + "\n");
			data.close();
			System.out.println("Successfully registered.");
			menu();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return name+sirname+balance;
	}
	public void dataDelete() {
		Scanner rmD=new Scanner(System.in);
		String name,sirname,currentLine;;
		
		System.out.println("Type the name and surname of the record you want to delete:");
		System.out.print("Name:");
		name=rmD.next();
		System.out.print("Sirname:");
		sirname=rmD.next();
		try
		{
		File oldFile= new File(filePath);
		File newFile= new File(tempFile);
		
		
			BufferedReader reader = new BufferedReader(new FileReader(oldFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
			String lineToRemove = name + " " + sirname + " " + balance;
			while ((currentLine = reader.readLine()) != null) {
               
                if (currentLine.trim().equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            rmD.close();
            if (!oldFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!newFile.renameTo(oldFile)) {
                System.out.println("Could not rename file");
            }
            System.out.println("It was deleted successfully.");
            menu();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

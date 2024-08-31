import java.io.File;//Enhances both the use of Files and Scanners 
import java.io.FileNotFoundException;
import java.util.Scanner;
	class BESTMEALS{
		public static void main (String[]args){//Initializes all of the variables needed for the app.
			double price = 0.0;
			int numberOfItems = 0;
			int totalItems = 0;
			double subTotal= 0.0;
			double total = 0.0;	
			double tax = 0.0;
			double totalPlusTax = 0.0;
			double tip =0.0;
			boolean continueInApp = true;
			boolean	inMenu = false;

			while(continueInApp){//Starts a loop that will keep the App running until the condition 'continueInApp' becomes false.
				System.out.println("   Welcome to BEST MEALS!");
				System.out.println("-----------MENU-----------");
				System.out.println("  Choose an option (1-5)");
				System.out.println("1. Add a Food");
				System.out.println("2. View Cart");
				System.out.println("3. Clear Cart");
				System.out.println("4. Checkout");
				System.out.println("5. Exit");
				System.out.println("--------------------------");

				Scanner input = new Scanner(System.in);//Starts the scanner 'input' which will store the users input. It will determine where to send the user in the app based on their input.
				int selection = input.nextInt();
				input.nextLine();

				if(selection==1){ //If the user selects option 1, this allows the code to extract the information from the text file by using the Scanner.
					try{ //Reads the text file information
						File menuFile = new File("food.txt");
						Scanner fileScanner = new Scanner(menuFile);
						while(fileScanner.hasNextLine()){ //This will print all of the menu items. 
							System.out.println(fileScanner.nextLine());
						}
						System.out.println("Please enter an item from the menu (The system is case sensitive, please write the menu item down as they appear without including the price and without any spaces): ");
						Scanner userInput = new Scanner(System.in); 
						String menuItem = userInput.nextLine();//Assigns the new created scanner to the variable menuItem.
						Scanner newFileScanner = new Scanner(menuFile);//Reads the file once again so it can detect the .nextLine(); from the beginning.
						while(newFileScanner.hasNextLine()){
							if(menuItem.equals(newFileScanner.next())){ //Reads all the menu items one by one, once the menu item matches the item the user wants it will continue.
								price = newFileScanner.nextDouble();
								System.out.println("Please enter the amount of items you want: ");
								numberOfItems = input.nextInt();
								subTotal = price * numberOfItems; //Saves the amount of items the user wants and it multipies it by the items price.
								System.out.println("Your subTotal is:" + subTotal); 
								total += subTotal; //Stores the total and totalItems for them to be used once the user wants to input a new item.
								totalItems += numberOfItems;
								inMenu = true;
							}//if
						}//while
						if(!inMenu){ //shows a message if the inputed menu item is wrong.
							System.out.println("Your input is wrong, try again!");
						}
	 				}// try
	 				catch(FileNotFoundException e){ // In case the file wasn't read correctly it will print an error message.
						System.out.println("an error occurred reading this file");
					} // catch 
				}// if - selection == 1
				else if(selection==2){ //If the user selects option 2, it will print the users cart.
					System.out.println("-----------Cart-----------");
					System.out.println("Number of items: " + totalItems);
					System.out.println("Total: " + total);
					System.out.println("--------------------------");
				}// else if - selection==2
				else if(selection==3){ //If the user selects option 3, it will clear the cart
					System.out.println("Are you sure you want to clear your cart? (Enter true or false) ");
					boolean clearCart = input.nextBoolean();
					if(clearCart){
						totalItems = 0;
						subTotal = 0.0;
						total = 0.0;
						System.out.println("Your cart has been changed!");
					}//if
					else{
						System.out.println("Cart not changed. Going back to menu.");
					}//else
				} // else if - selection==3
				else if(selection==4){ //If the user selects option 4, it will go to check out
					System.out.println("-----------Total-----------");
					tax = total*0.0725;
					totalPlusTax=total+tax;
					System.out.println("Total (includes 7.25% tax): " + totalPlusTax);
					System.out.println("Please choose an option below: ");
					System.out.println("1. Pick up  2.Delivery");
					System.out.println("---------------------------");
					int option = input.nextInt();	
					input.nextLine();
					switch (option){ //Will determine what to do if the user wants to pick up their order or for it to be delivered
					    case 1:
					    	System.out.println("Please enter your 16 digits card number (must not exceed 16 numbers, nor be less than 16 numbers): ");
							String cardNumber = input.nextLine();
							if(cardNumber.length()==16){ //Checking if the card number is actually 16 digits.
								for(int x = 0; x<=16 ; x++){ // Will check line by line that all of the numbers stay between 0 and 9.
									if(cardNumber.charAt(x)>='0' && cardNumber.charAt(x) <='9'){
										System.out.println("----------Your order information----------");
										System.out.println("Number of items: " + totalItems);
										System.out.println("Total: " + totalPlusTax);
										System.out.println("Card number: " + cardNumber);
										System.out.println("Thank you for using Miner Eats. Goodbye!!");
										System.out.println("-----------------------------------------");
										break;//if
									}//if
									else{
										System.out.println("Wrong use of characters, try again.");
										break;
									}//else
								}//for
							}//if
							else{
								System.out.println("Wrong input, try again!");	
								break;//else 
							}//else
							continueInApp = false;
							break;//case 1
						case 2:
							totalPlusTax+=5.0;
							System.out.println("Would you like to provide a tip?");
							System.out.println("1. Yes  2.No");
							int wantsTip = input.nextInt();
							if(wantsTip==1){//If the user wants to include a tip it will ask for the percentage they want it to be. It will be added to their total.
								System.out.println("Please enter the tip percentage you would like to give: ");
	                            double tipPercentage = input.nextDouble();
	                            tipPercentage=tipPercentage/100;
	                            tip=tipPercentage;
	                            totalPlusTax+=tip;
	                            System.out.println("Thank you:)");
							}//if
							System.out.println("Please add an adress: ");
							input.nextLine();
							String address = input.nextLine();
							System.out.println("Please enter your card number (must not exceed 16 numbers): ");
							cardNumber = input.nextLine();
							if(cardNumber.length()==16){ //Checking if the card number is actually 16 digits.
								for(int x = 0; x<=16 ; x++){ // Will check line by line that all of the numbers stay between 0 and 9.
									if(cardNumber.charAt(x)>='0' && cardNumber.charAt(x) <='9'){//If the card numbers are correct it will print the order information.
										System.out.println("----------Your order information----------");
										System.out.println("Number of items: " + totalItems);
										System.out.println("Total: " + totalPlusTax);
										System.out.println("Adress: "+ address);
										System.out.println("Card number: " + cardNumber);
										System.out.println("Thank you for using Miner Eats. Goodbye!!");
										System.out.println("-----------------------------------------");
										break;//if
									}//if
									else{
										System.out.println("Wrong use of characters, try again.");
										break;
									}
								}//for
							}//if
							else{
								System.out.println("Wrong input, try again!");
								break;//else
							}//else
							continueInApp = false;
							break;//case 2	
	                 }//else if - selection==4
				}//else if - selection==4
				else if(selection==5){//It will end the program by making the while loop 'continueApp' false.
							System.out.println("Cart abandoned, Goodbye!");
							System.out.println("Ending program");
					 		continueInApp = false;
						}//else if - selection==5
			} // While loop 'continueInApp' (Keeps the program running)
		} // public static void 
	}//class 

import java.util.Scanner;

public class gpaCalculator {
   public static void main(String args[]){

       String letterAA = "4.00";
       double AA = Double.parseDouble(letterAA);
       String letterBA = "3.50";
       double BA = Double.parseDouble(letterBA);
       String letterBB = "3.00";
       double BB = Double.parseDouble(letterBB);
       String letterCB = "2.50";
       double CB = Double.parseDouble(letterCB);
       String letterCC = "2.00";
       double CC = Double.parseDouble(letterCC);
       String letterDC = "1.50";
       double DC = Double.parseDouble(letterDC);
       String letterDD = "1.00";
       double DD = Double.parseDouble(letterDD);
       String letterFD = "0.50";
       double FD = Double.parseDouble(letterFD);
       String letterFF = "0.00";
       double FF = Double.parseDouble(letterFF);

       Scanner scanner = new Scanner(System.in);
       System.out.print("Lutfen ders sayisini giriniz: ");
       int lectureCount = scanner.nextInt();

       double sum = 0;
       int creditSum = 0;
       for(int i = 0; i<lectureCount; i++ ){

           System.out.print("Dersin kredini giriniz: ");
           Scanner scanner1 = new Scanner(System.in);
           int credit = scanner1.nextInt();
           System.out.println("Lutfen harf notunu giriniz:");
           Scanner scanner2 = new Scanner(System.in);

           String letter = scanner2.nextLine();
           if(letter.equals("AA")) {

               sum = sum +  (AA * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }

           else if(letter.equals("BA")){
               sum = sum + (BA * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else if(letter.equals("BB")){
               sum = sum + (BB * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else if(letter.equals("CB")){
               sum = sum +   (CB * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else if(letter.equals("CC")){
               sum = sum +(CC * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;


           }
           else if(letter.equals("DC")){
               sum = sum + (DC * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else if(letter.equals("DD")){
               sum = sum +  (DD * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else if(letter.equals("FD")){
               sum = sum + (FD * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else if(letter.equals("FF")){
               sum = sum + (FF * credit);
               System.out.println("Sum = " + sum);
               creditSum = creditSum + credit;

           }
           else{
               System.out.println("Hatali giris yaptiniz.");
           }

       }
       System.out.println("Total Credit : " + creditSum);
       double GPA = (int)((sum / creditSum) * 100.0) / 100.0 ;
       System.out.println("GPA: " + GPA);



       }

       }






import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TransactionApp {
    public static void main(String[] args) {

        int choice = 0;
        do {
            System.out.println("Witaj w Domowym Budżecie!!!\n");

            System.out.println("Masz następujące opcje:");
            System.out.println("\t( 1 ) Dodwanie nowej transakcji");
            System.out.println("\t( 2 ) Modyfikowanie transakcji");
            System.out.println("\t( 3 ) Usuwanie transakcji");
            System.out.println("\t\t albo możesz przejrzeć wszystkie");
            System.out.println("\t( 4 ) przychody");
            System.out.println("\t( 5 ) wydatki \n");
            System.out.println("\t( 0 ) zamknięcie programu.\n");
            System.out.println("Wpisz odpowiednia cyfrę...");

            Scanner sc = new Scanner(System.in);


            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("nie podałeś cyfry. Spróbój jeszcze raz...");
            }
            TransactionDao transDao = new TransactionDao();


            switch (choice) {
                case 1:
                    Transaction addingTrans = TransactionMethods.addTrans();
                    transDao.save(addingTrans);
                    transDao.close();
                    break;
                case 2:
                    Transaction modifingTrans = TransactionMethods.modifyTrans();
                    transDao.update(modifingTrans);
                    transDao.close();
                    break;
                case 3:
                    long deletingTrans = TransactionMethods.deleteTrans();
                    transDao.deleteTrans(deletingTrans);
                    transDao.close();
                    break;
                case 4:
                    List<Transaction> receipts = transDao.showList(TransactionType.RECEIPT);
                    TransactionMethods.show(receipts, TransactionType.RECEIPT);
                    transDao.close();
                    break;
                case 5:
                    List<Transaction> expenses = transDao.showList(TransactionType.EXPENSE);
                    TransactionMethods.show(expenses, TransactionType.EXPENSE);
                    transDao.close();
                    break;
            }
        } while (choice != 0);
    }
}


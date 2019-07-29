import java.util.List;
import java.util.Scanner;

public class TransactionMethods {


    public static Transaction addTrans() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj wartości dotyczące twojej transakcji: ");
        System.out.println("typ");
        String ty = sc.nextLine();
        TransactionType type = TransactionType.valueOf(ty);

        System.out.println("opis transakcji");
        String description = sc.nextLine();

        System.out.println("kwotę transakcji");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.println("datę transacji RRRR-MM-DD");
        String date = sc.nextLine();
        Transaction transaction = new Transaction(type, description, amount, date);
        return transaction;
    }


    public static Transaction modifyTrans() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj id transakcji, którą chcesz zmienić: ");
        long id = sc.nextLong();
        sc.nextLine();
        Transaction transHalf = addTrans();
        Transaction transaction = new Transaction(id, transHalf.getType(), transHalf.getDescription(), transHalf.getAmount(), transHalf.getDate());
        return transaction;
    }

    public static long deleteTrans() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj id transakcji, którą chcesz usunąć");
        long id = sc.nextLong();
        sc.nextLine();
        return id;

    }

    public static void show(List<Transaction> list, TransactionType type) {
        System.out.println("Transakcje typu " + type.getName());
        for (Transaction transaction : list) {
            System.out.println("\t id: " + transaction.getId() + " opis: \"" + transaction.getDescription() + "\" kwota: " + transaction.getAmount() +
                    " z dnia " + transaction.getDate());
        }
    }

}

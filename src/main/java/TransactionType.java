public enum TransactionType {
    RECEIPT("przychody"),
    EXPENSE("wydatki");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

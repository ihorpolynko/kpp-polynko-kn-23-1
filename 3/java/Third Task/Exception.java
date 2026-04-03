// базовий клас винятків
class ATMException extends Exception {
    public ATMException(String msg) {
        super(msg);
    }
}

// недостатньо коштів
class NotEnoughMoneyException extends ATMException {
    public NotEnoughMoneyException(String msg) {
        super(msg);
    }
}

// перевищення ліміту
class LimitExceededException extends ATMException {
    public LimitExceededException(String msg) {
        super(msg);
    }
}

// неможливо видати суму
class CannotDispenseException extends ATMException {
    public CannotDispenseException(String msg) {
        super(msg);
    }
}
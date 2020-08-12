package rbt;

public class NullKeyException extends RuntimeException {

    public NullKeyException() {

        super("Podany klucz ma wartość null");
    }
}

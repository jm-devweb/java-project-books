/**
 * Class SelectionException
 *
 */
public class SelectionException  extends RuntimeException  {
    // **************************************************
    // Constructors
    // **************************************************
    /**
     * Default constructor.

     * The current value will be 0.
     */
    public SelectionException()  {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param String
     */
    public SelectionException(String s) {
        super(s);
    }
}
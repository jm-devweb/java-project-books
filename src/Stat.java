/**
 * Class Stat
 *
 */
public class Stat  implements Comparable<Stat> {

    // **************************************************
    // Fiels :
    // String strWord : Word
    // Integer intOccurrence : Occurence in this text
    // **************************************************
    final String strBook;
    final Float fltPourcent;

    // **************************************************
    // Constructors
    // **************************************************
    /**
     * Parameterized constructor.
     *
     * @param strBook : New Word
     * @param fltPourcent : Occurence in the text
     */
    public Stat(String strBook, Float fltPourcent) {
        this.strBook = strBook ;
        this.fltPourcent = fltPourcent ;
    }

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * Returns the result of equal of strWord.
     *
     * @return boolean.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stat stat = (Stat) o;

        return strBook.equals(stat.strBook);
    }

    /**
     * Returns the result of equal of strWord.
     * Compare 2 words.
     *  by occurence
     *  by word
     * @return int
     */
    @Override
    public int compareTo(Stat oOtherStat) {
        int intResult = 0 ;

        intResult = this.fltPourcent.compareTo(oOtherStat.fltPourcent) * -1 ;
        if(intResult == 0 ) {
            intResult = this.strBook.compareTo(oOtherStat.strBook) ;
        }
        return (intResult) ;
    }

    /**
     * Returns HashCode
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return (strBook.hashCode());
    }
}

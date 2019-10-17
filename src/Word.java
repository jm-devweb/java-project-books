/**
 * Class Word
 *
 */
public class Word  implements Comparable<Word> {

    // **************************************************
    // Fiels :
    // String strWord : Word
    // Integer intOccurrence : Occurence in this text
    // **************************************************
    final String strWord ;
    final Long lngOccurrence ;

    // **************************************************
    // Constructors
    // **************************************************
    /**
     * Parameterized constructor.
     *
     * @param strWord : New Word
     * @param lngOccurence : Occurence in the text
     */
    public Word(String strWord, Long lngOccurence) {
        this.strWord = strWord ;
        this.lngOccurrence = lngOccurence ;
    }

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * Returns the result of equal of strWord.
     *
     * @return boolean.
     */
    public boolean equals(Word oOtherWord) {
        return (this.strWord.equals(oOtherWord.strWord)) ;
    }

    /**
     * Returns the result of equal of strWord.
     * Compare 2 words.
     *  by occurence
     *  by word
     * @return int
     */

    @Override
    public int compareTo(Word oOtherWord) {
        int intResult = 0 ;

        intResult = this.lngOccurrence.compareTo(oOtherWord.lngOccurrence) * -1 ;
        if(intResult == 0 ) {
            intResult = this.strWord.compareTo(oOtherWord.strWord) ;
        }
        return (intResult) ;
    }

    @Override
    public int hashCode() {
        return strWord.hashCode();
    }

}

/**
 * Class BookCollection
 *
 * @author Spiria
 */

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class BookCollection {

    // **************************************************
    // Fields
    // **************************************************
    private int intSelectedIndex;
    private List<Book> lBooks = null;

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor.
     * <p>
     * The current value will be 0.
     */
    BookCollection() {
        lBooks = new ArrayList<Book>();
        intSelectedIndex = -1;
    }

    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Add a book to the collection.
     *
     * @return Nothing
     */
    public void add(String strNewItem) {
        File fNewFileName = null;

        fNewFileName = new File(strNewItem);
        if (fNewFileName.exists()) {
            if (fNewFileName.isDirectory()) {
                FilenameFilter filter = new FilenameFilter() {
                    public boolean accept(File fNewDirectory, String name) {
                        return name.endsWith(".txt");
                    }
                };

                File[] faNewFile = fNewFileName.listFiles(filter);
                for (File fNewFile : faNewFile) {
                    lBooks.add(new Book(fNewFile.getName(), fNewFile.getAbsolutePath()));
                }
            } else {
                lBooks.add(new Book(fNewFileName.getName(), fNewFileName.getAbsolutePath()));
            }
            Collections.sort(lBooks);
        } else {
            System.out.println("Ce fichier n'existe pas : " + strNewItem);
        }
    }

    /**
     * Returns the book at index.
     *
     * @return Book.
     */
    public Book getBook(String strIndex) {
        int intIndex = 0;

        intIndex = Integer.parseInt(strIndex);
        return (lBooks.get(intIndex - 1));
    }


    /**
     * Returns  selected book.
     *
     * @return Book
     */
    public Book getSelectBook() {
        return lBooks.get(intSelectedIndex);
    }

    /**
     * Returns index of selected book.
     *
     * @return integer
     */
    public int getSelectIndex() {
        return intSelectedIndex;
    }

    /**
     * Remove the book at the index
     *
     * @return Nothing
     */
    public void remove(String strIndex) {
        int intIndex = 0;

        intIndex = Integer.parseInt(strIndex);
        lBooks.remove(intIndex - 1);
    }

    /**
     * Returns all the books in a menu.
     *
     * @return Nothing
     */
    public void toMenu(Menu oMenu) {
        Book oCurrentBook;
        String strResult = "";
        int intCount = 1;

        oMenu.clear();
        ListIterator<Book> liBooks = lBooks.listIterator();

        while (liBooks.hasNext()) {
            oCurrentBook = liBooks.next();
            oMenu.additem(oCurrentBook.toString(), Integer.toString(intCount));
            intCount += 1;
        }
    }

    /**
     * Returns a format string contains the list of the book.
     *
     * @return String
     */
    public String toString() {
        Book oCurrentBook;
        String strResult = "";
        int intCount = 1;

        ListIterator<Book> liBooks = lBooks.listIterator();

        strResult += "\n******************************************" + "\n";
        strResult += "************ Liste des Livres ************" + "\n";
        strResult += "******************************************" + "\n";
        while (liBooks.hasNext()) {
            oCurrentBook = liBooks.next();
            strResult += oCurrentBook.toString() + "\n";
            intCount += 1;
        }
        strResult += "******************************************" + "\n";
        strResult += "************** Fin de Liste **************" + "\n";
        strResult += "******************************************" + "\n";
        return (strResult);
    }

    /**
     * Returns a format string contains the list of the book.
     *
     * @return String
     */
    public String toPourcent() {
        String strResult = "";

        List<Stat> lResult = Pourcent();

        for (Stat oCurrentStat : lResult) {
            strResult += oCurrentStat.strBook+ " " +String.format("%.02f", oCurrentStat.fltPourcent)  + "\n";
        }
        return (strResult);
    }

    /**
     * Returns a format string contains the list of the book.
     *
     * @return String
     */
    public String toUniqueWord() {
        String strResult = "";

        Set<Word> lResult = UniqueWord();

        for (Word oCurrentWord : lResult) {
            strResult += oCurrentWord.strWord + "\n";
        }
        return (strResult);
    }

    /**
     * Set index of selected book.
     *
     * @return Nothing
     */
    public void setSelectBook(String strSelectBook) {
        this.intSelectedIndex = Integer.parseInt(strSelectBook) - 1;
    }

    /**
     * Set index of selected book.
     *
     * @return Nothing
     */
    public void setSelectIndex(int intSelectIndex) {
        this.intSelectedIndex = intSelectIndex;
    }

    /**
     * List of word unique in the selectd book
     *
     * @return List of Word
     */
    public Set<Word> UniqueWord() {
        Set<Word> lResult = new HashSet<Word>();

        lResult.addAll(lBooks.get(intSelectedIndex).lWords);

        for (Book oCurrentBook : lBooks) {   // pour tous les livres de la collection
            if (!oCurrentBook.equals(lBooks.get(intSelectedIndex))) { // Si ce n'est pas le livre selectionner
                lResult.removeAll(oCurrentBook.lWords);
            }
        }
        return (lResult);
    }

    /**
     * List of Stat
     *
     * @return List of Stat
     */

    public List<Stat> Pourcent() {
        List<Stat> lResult = new ArrayList<Stat>();
        Set<Word> sReference ;
        float fltResult = 0F;



        for (Book oCurrentBook : lBooks) {   // pour tous les livres de la collection
            sReference = new HashSet<Word>();
            if (!oCurrentBook.equals(lBooks.get(intSelectedIndex))) { // Si ce n'est pas le livre selectionner
                sReference.addAll(lBooks.get(intSelectedIndex).lWords);
                sReference.containsAll(oCurrentBook.lWords);
                fltResult = (float) ((float)sReference.size()) / ((float) oCurrentBook.lWords.size()) ;
                lResult.add(new Stat(oCurrentBook.strBookName, fltResult));
            }
        }
        return (lResult);
    }
}

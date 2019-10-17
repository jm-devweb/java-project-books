/**
 * Class Book
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Book implements Comparable<Book> {
    // **************************************************
    // Fields :
    // String strBookName : book name
    // String strFileName : path of the file
    // int intNumberOfline : number of line
    // List<Word> lWords : list of word
    // **************************************************
    final String strBookName;
    final String strFileName;
    final Integer intNumberOfline ;
    final List<Word> lWords ;

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Parameterized constructor.
     *
     * @param strNewBook : book name
     * @param strNewFile : path of the file
     */
    Book(String strNewBook, String strNewFile) {
        int intIndex = 0 ;
        Map<String, Long> mFrequence = null;
        List<String> lCurrentWords = new ArrayList<String>();

        strFileName = strNewFile;
        strBookName = strNewBook;
        lWords = new ArrayList<Word>();

        Pattern p = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        try (Scanner sc = new Scanner(new File(strNewFile))) {
            for (intIndex = 0; sc.hasNextLine(); ++intIndex) {
                for (Matcher m1 = p.matcher(sc.nextLine()); m1.find(); ) {
                    lCurrentWords.add(m1.group().toLowerCase());
                }
            }

            mFrequence = lCurrentWords.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            for (Map.Entry<String, Long> entry : mFrequence.entrySet()) {
                lWords.add(new Word(entry.getKey(), entry.getValue()));
            }
            Collections.sort(lWords);

        } catch (FileNotFoundException e) {
            System.out.println("Ce fichier n'existe pas :" + strNewFile);
            ;
        }
        intNumberOfline = intIndex ;
    }

    // **************************************************
    // Public methods
    // **************************************************


    /**
     * Returns Compare book.
     * - by book
     * - by path
     * @return int
     */
    @Override
    public int compareTo(Book oOtherBook) {
        int intResult = 0 ;

        intResult = this.strBookName.compareTo(oOtherBook.strBookName) ;
        if(intResult == 0 ) {
            intResult = this.strFileName.compareTo(oOtherBook.strFileName) ;
        }
        return (intResult) ;
    }


    @Override
    public int hashCode() {
        return strBookName.hashCode();
    }

    /**
     * Returns True if it's the same path.
     *
     * @return Boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return strBookName.equals(book.strBookName);
    }

    /**
     * Returns a format string contains the n first words by occurence.
     *
     * @return String.
     */
    public String topOftheWord (int intTop) {
        String strResult = "" ;
        int intCount = 0 ;
        Word oCurrentWord ;
        ListIterator<Word> liWords = lWords.listIterator();

        if(intTop>0) {
            while (liWords.hasNext() && intCount < intTop) {
                oCurrentWord = liWords.next();
                strResult += oCurrentWord.strWord + " " + oCurrentWord.lngOccurrence + "\n";
                intCount += 1;
            }
        }
        return (strResult) ;
    }

    /**
     * Returns the name and the path of the book.
     *
     * @return String.
     */
    public String toString() {
        return ("Livre : " + strBookName + " Chemin : " + strFileName);
    }


}


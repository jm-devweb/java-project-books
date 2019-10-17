/**
 * Class Menu
 *
 */

import java.util.*;
import java.io.IOException;

public class Menu {
    // **************************************************
    // Fields
    // **************************************************
    private Map<String, String> oMap = null ;
    private String strTitle ;

    // **************************************************
    // Constructors
    // **************************************************
    /**
     * Default constructor.

     * The current value will be 0.
     */
    Menu() throws NullPointerException {
        oMap = new HashMap<String, String>();
        strTitle = null ;
    }

    // **************************************************
    // Private methods
    // **************************************************
    /**
     * Check if a key is in the menu
     *
     * @return Boolean
     */
    private boolean exist(String strKey) {
        boolean booResult = false ;

        for (Map.Entry<String,String> entry : oMap.entrySet())
            if(entry.getKey().equals(strKey)) booResult = true ;

        return(booResult) ;
    }

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * Add item
     *
     * @return Nothing
     */
    public void additem(String strNewItem,String strNewKey) {
        oMap.put(strNewKey, strNewItem);
    }

    /**
     * Set the title of the menu
     *
     * @return Nothing
     */
    public void clear() {

        this.oMap.clear();
    }

    /**
     * Return a valid key in this menu
     *
     * @return String
     */
    public String getselection() throws SelectionException {
        String strResult = null ;

        Scanner scKeyboard = new Scanner(System.in);
        System.out.print("Saisissez un numéro : ");
        strResult = scKeyboard.nextLine();
        if (this.exist(strResult)) {
            return (strResult);
        } else {
            throw new SelectionException("Option incorrecte !");
        }
    }

    /**
     * Test if the menu is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (oMap.isEmpty());
    }

    /**
     * Return a format string contains the menu
     *
     * @return String
     */
    public String toString (){
        String strResult = "" ;

        strResult +="\n\n******************************************" + "\n";
        strResult +=this.strTitle + "\n" ;
        strResult +="******************************************" + "\n";

        SortedSet<String> keys = new TreeSet<>(oMap.keySet());
        for (String key : keys) {
            strResult += key + ": " + oMap.get(key) + "\n";
        }
        return (strResult) ;
    }

    /**
     * Set the title of the menu
     *
     * @return Nothing
     */
    public void setTitle(String strTitle) {
        this.strTitle = strTitle;
    }
}

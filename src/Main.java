/**
 * Class Main
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // **************************************************
    // Constants
    // **************************************************
    public static final int PRINCIPAL = 0;
    public static final int CONSULT = 1;
    public static final int DELETE = 2;
    public static final int SELECT = 3;

    // **************************************************
    // Fields
    // **************************************************
    public static List<Menu> lMenu = new ArrayList<Menu>();

    // **************************************************
    // Private methods
    // **************************************************

    // **************************************************
    // Public methods
    // **************************************************
    /**
     * Main !
     *
     * @return Nothing.
     */
    public static void main(String[] args) {
        int intCurrentMenu = 0;
        String strSelection = "0" ;
        Menu oNewMenu = null;
        BookCollection oBookCollection = new BookCollection();
        Book oCurrentBook = null ;

        // Menu MENU_PRINCIPAL - 0
        oNewMenu = new Menu();
        oNewMenu.setTitle("Menu principal");
        oNewMenu.additem("Lister les fichiers", "1");
        oNewMenu.additem("Ajouter un fichier", "2");
        oNewMenu.additem("Supprimer un fichier", "3");
        oNewMenu.additem("Afficher des informations sur un livre", "4");
        oNewMenu.additem("Quitter", "5");
        lMenu.add(oNewMenu);

        // Menu CONSULTATION -1
        oNewMenu = new Menu();
        oNewMenu.setTitle("Analyse d'un fichier");
        oNewMenu.additem("Sélectionner un fichier", "1");
        oNewMenu.additem("Afficher le nombre de lignes du fichier", "2");
        oNewMenu.additem("Afficher le nombre de mots du fichier ", "3");
        oNewMenu.additem("Retour menu précédent", "4");
        lMenu.add(oNewMenu);

        // Menu SUPPRESSION - 2
        oNewMenu = new Menu();
        oNewMenu.setTitle("Suppression d'un livre");
        lMenu.add(oNewMenu);

        // Menu SELECTION - 3
        oNewMenu = new Menu();
        oNewMenu.setTitle("Sélection d'un livre");
        lMenu.add(oNewMenu);

        // Chargement des fichiers si il existe
        if (args.length != 0) {
            for (int intIndex = 0; intIndex <= args.length - 1; ++intIndex) {
                oBookCollection.add(args[intIndex]);
            }
        } else {
            System.out.println("Pas de fichier en entrée");
        }

        intCurrentMenu = PRINCIPAL;
        strSelection = "0";
        do {
            System.out.println(lMenu.get(intCurrentMenu));
            try {
                strSelection = lMenu.get(intCurrentMenu).getselection();

                switch (intCurrentMenu) {
                    case PRINCIPAL:
                        intCurrentMenu = actionMenuPrincipal(strSelection, intCurrentMenu, oBookCollection);
                        break;
                    case CONSULT:
                         intCurrentMenu = actionMenuConsult(strSelection, intCurrentMenu, oBookCollection);
                        break;
                    case DELETE:
                        intCurrentMenu = actionMenuDelete(strSelection, oBookCollection);
                        break;
                    case SELECT:
                        intCurrentMenu = actionMenuSelect(strSelection, oBookCollection)  ;
                        break;
                }
            } catch (SelectionException exception) {
                System.out.println(exception.getMessage());
                strSelection = "0";
            }

        } while ( !strSelection.equals("5") || intCurrentMenu != 0) ;
        return;
    }

    /**
     * Action menu principal
     *
     * @return Nothing
     */
    public static int actionMenuPrincipal(String strSelection, int intCurrentMenu, BookCollection oBookCollection) {
        int intNextMenu = intCurrentMenu ;
        switch (strSelection) {
            case "1":
                // 1. Lister les fichiers
                System.out.println(oBookCollection);
                break;
            case "2":
                // 2. Ajouter un fichier
                Scanner scKeyboard = new Scanner(System.in);
                System.out.print("Indiquer le chemin du nouveau fichier : ");
                oBookCollection.add(scKeyboard.nextLine());
                break;
            case "3":
                // 3. Supprimer un fichier
                lMenu.get(DELETE).clear();
                oBookCollection.toMenu(lMenu.get(DELETE));
                lMenu.get(DELETE).additem("Revenir au menu précédent","0");
                intNextMenu = DELETE;
                 break;
            case "4":
                // 4. Afficher des informations sur un livre  / Changement de menu
                oBookCollection.setSelectIndex(-1) ;
                lMenu.get(CONSULT).setTitle("Analyse du fichier (aucun)");
                intNextMenu = CONSULT;
                break;
            case "5":
                // 5. Books
                intNextMenu = PRINCIPAL;
                break;
        }
        return (intNextMenu) ;
    }

    /**
     * Action menu Consult
     *
     * @return Nothing
     */
    public static int actionMenuConsult(String strSelection, int intCurrentMenu, BookCollection oBookCollection) {
        int intNextMenu = intCurrentMenu;
        String strResult = "";
        switch (strSelection) {
            case "1":
                // 1. Sélectionner un fichier
                lMenu.get(SELECT).clear();
                oBookCollection.toMenu(lMenu.get(SELECT));
                lMenu.get(SELECT).additem("Revenir au menu précédent", "0");
                intNextMenu = SELECT;
                break;
            case "2":
                // 2. Afficher le nombre de lignes du fichier
                strResult = "";
                strResult += "\n******************************************" + "\n";
                if (oBookCollection.getSelectIndex() != -1) {
                    strResult += oBookCollection.getSelectBook().strBookName + "\n";
                } else {
                    strResult += "(Aucun)\n";
                }
                strResult += "******************************************" + "\n";
                if (oBookCollection.getSelectIndex() != -1) {
                    strResult += "le nombre ligne est de : " + oBookCollection.getSelectBook().intNumberOfline + "\n";
                } else {
                    strResult += "le nombre ligne est de : 0\n";

                }
                strResult += "******************************************" + "\n";
                strResult += "******************************************" + "\n";

                System.out.println(strResult);
                break;
            case "3":
                // 3. Afficher le nombre de mots du fichier
                strResult = "";
                strResult += "\n******************************************" + "\n";
                if (oBookCollection.getSelectIndex() != -1) {
                    strResult += oBookCollection.getSelectBook().strBookName + "\n";
                } else {
                    strResult += "(Aucun)\n";
                }
                strResult += "******************************************" + "\n";
                if (oBookCollection.getSelectIndex() != -1) {
                    strResult += "le nombre de mot : " + oBookCollection.getSelectBook().lWords.size() + "\n";
                    strResult += "******************************************" + "\n";
                  strResult += "le top des mots 50 :\n" + oBookCollection.getSelectBook().topOftheWord(50) + "\n";
                    strResult += "******************************************" + "\n";

                    //
                    //               strResult += "la liste des mots unique :\n" + oBookCollection.toUniqueWord() + "\n";

                } else {
                    strResult += "le top des mots  : vide\n";

                }
                strResult += "******************************************" + "\n";
                strResult += "******************************************" + "\n";

                System.out.println(strResult);
                break;

            case "4":
                // 4. Retour au menu précédent
                intNextMenu = PRINCIPAL;
                break;
        }
        return (intNextMenu);
    }

    /**
     * Action menu suppression
     *
     * @return Nothing
     */
    public static int actionMenuDelete(String strSelection, BookCollection oBookCollection) {
        switch (strSelection) {
            case "0":
                // 0. Retour au menu précédent
                // Nothing to do
                break;
            default:
                oBookCollection.remove(strSelection);
        }
        return (PRINCIPAL);
    }

    /**
     * Action menu suppression
     *
     * @return Nothing
     */
    public static int actionMenuSelect(String strSelection, BookCollection oBookCollection) {
        switch (strSelection) {
            case "0":
                // 0. Retour au menu précédent
                // Nothing to do
                break;
            default:
                lMenu.get(CONSULT).setTitle("Analyse du fichier : " + oBookCollection.getBook(strSelection).strBookName);
                oBookCollection.setSelectBook(strSelection);
         }
        return (CONSULT);
    }
}



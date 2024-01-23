package Modele;

import java.io.File;

public class DocsIndexListe
{
    private DocIndex premier;
    private String printTxts;

    public DocsIndexListe()
    {
        this.printTxts="";
    }

    // Gets & set
    public DocIndex getPremier()
    {
        return this.premier;
    }
    public String getPrintTxts()
    {
        this.printTxts = "";
        setPrintTxts();
        return this.printTxts;
    }
    private void setPrintTxts()
    {
        if(premier == null)
        {
            return;
        }

        DocIndex currDocIndex = premier;
        while(currDocIndex != null)
        {
            printTxts += "[   " + currDocIndex.getNom() + "   ] : ";
            printTxts += currDocIndex.getPrintTxt();
            currDocIndex = currDocIndex.getProchain();
        }
    }

    // Méthodes
    public void ajoutDocIndexTriant(File doc) {
        DocIndex newDocIndex = new DocIndex(doc);
        String newIndexNom = newDocIndex.getNom();

        // Si nombre d'éléments = 0
        if(premier == null)
        {
            newDocIndex.setProchain(premier);
            premier = newDocIndex;
            return;
        }
        else    // Si nombre d'éléments = 1
        {
            String premierIndexNom = premier.getNom();
            if (premierIndexNom.length() > newIndexNom.length() ||
                    (premierIndexNom.length() == newIndexNom.length() &&
                            premierIndexNom.compareTo(newIndexNom) > 0))
            {
                newDocIndex.setProchain(premier);
                premier = newDocIndex;
                return;
            }
        }

        // Si nombre d'éléments > 1
        DocIndex currIndex = premier;
        while(currIndex.getProchain() != null)
        {
            String prochainIndexNom = currIndex.getProchain().getNom();
            // Comaprer les longueurs des noms des 2 index
            if (prochainIndexNom.length() > newIndexNom.length()) break;
            // Comaprer les noms des 2 index si meme longueur
            if (prochainIndexNom.length() == newIndexNom.length() &&
                    prochainIndexNom.compareTo(newIndexNom) > 0) break;

            currIndex = currIndex.getProchain(); // Comparaison suivant
        }

        // Swap
        newDocIndex.setProchain(currIndex.getProchain());
        currIndex.setProchain(newDocIndex);
    }
    public DocIndex trouveDocIndex(String docName)
    {
        if(this.premier == null) return null;

        DocIndex currDocIndex = this.premier;

        while(currDocIndex != null)
        {
            if(currDocIndex.getNom().compareTo(docName) == 0)
            {
                return currDocIndex;
            }
            currDocIndex = currDocIndex.getProchain();
        }

        return null;
    }
    public void effaceTout()
    {
        this.premier = null;
    }
}

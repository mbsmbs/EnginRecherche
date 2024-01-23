package Modele;

import java.io.File;

public class MotIndex
{
    private MotIndex prochain;
    private int frequence;
    private DocsIndexListe listeDocuments;
    private String mot;
    private String printTxt;

    public MotIndex(String mot)
    {
        this.mot = mot;
        this.listeDocuments = new DocsIndexListe();
        this.frequence = 1;
        this.printTxt = "";
    }
    // Gets & Sets
    public MotIndex getProchain()
    {
        return this.prochain;
    }
    public void setProchain(MotIndex motIndex)
    {
        this.prochain = motIndex;
    }
    public String getMot()
    {
        return this.mot;
    }
    public int getFrequence()
    {
        return this.frequence;
    }
    public String getPrintTxt()
    {
        setPrintTxt();
        return this.printTxt;
    }
    public void setPrintTxt()
    {
        printTxt = "";
        printTxt += "[  " + this.mot + "  ] : ";
        if(this.listeDocuments.getPremier() == null)
        {
            this.printTxt += "( Aucun résultat trouvé )" + System.lineSeparator();
            return;
        }

        DocIndex currDocIndex = this.listeDocuments.getPremier();

        while(currDocIndex != null)
        {
            String docNom = currDocIndex.getNom();
            int frequence = currDocIndex.getFrequence();

            if(currDocIndex.getProchain() != null)
            {
                printTxt += "( " + docNom + " : " + frequence + " ), ";
            }
            else
            {
                printTxt += "( " + docNom +" : "  + frequence + " )";
            }

            currDocIndex = currDocIndex.getProchain();
        }

        printTxt += System.lineSeparator();
    }
    public DocsIndexListe getListeDocuments()
    {
        return this.listeDocuments;
    }
    public void setListeDocuments(DocsIndexListe docsIndexListe)
    {
        this.listeDocuments = docsIndexListe;
    }

    // Méthodes
    public void frequenceIncrement()
    {
        this.frequence++;
    }
    public void saveDocsFrequences(DocsIndexListe docsIndexListe)
    {
        if(docsIndexListe == null) return;

        DocIndex currDocIndex = docsIndexListe.getPremier();

        while(currDocIndex != null)
        {
            MotIndex motIndexTrouve = currDocIndex.trouveMotIndex(this.mot);
            // Si mot existe dans la liste
            if(motIndexTrouve != null)
            {
                File doc = currDocIndex.getDoc();
                this.listeDocuments.ajoutDocIndexTriant(doc);
                String docName = doc.getName();
                DocIndex docIndexTrouve = this.listeDocuments.trouveDocIndex(docName);
                int docFrequence = motIndexTrouve.getFrequence();
                docIndexTrouve.setFrequence(docFrequence);
            }
            currDocIndex = currDocIndex.getProchain();
        }
    }
}

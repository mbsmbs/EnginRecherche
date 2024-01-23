package Modele;

public class MotsIndexListe
{
    private MotIndex premier;
    private String printTxts;
    public MotsIndexListe()
    {
        this.printTxts = "";
    }

    // Gets & set
    public MotIndex getPremier() {
        return this.premier;
    }
    public String getPrintTxts()
    {
        setPrintTxts();
        return this.printTxts;
    }
    public void setPrintTxts()
    {
        this.printTxts = "";
        if(this.premier == null) return;

        MotIndex currMotIndex = this.premier;

        while(currMotIndex != null)
        {
            String printTxt = currMotIndex.getPrintTxt();
            this.printTxts += printTxt;
            currMotIndex = currMotIndex.getProchain();
        }
    }

    // Méthodes
    public MotIndex trouveIndex(String mot)
    {
        if(premier == null) return null;

        MotIndex currMotIndex = premier;

        while(currMotIndex != null)
        {
            if(currMotIndex.getMot().compareTo(mot) == 0) break;
            currMotIndex = currMotIndex.getProchain();
        }

        return currMotIndex;
    }
    public void ajoutMotIndexTriant(String mot)
    {
        MotIndex newMotIndex = new MotIndex(mot);
        String newIndexMot = newMotIndex.getMot();

        if(premier == null)
        {
            newMotIndex.setProchain(premier);
            premier = newMotIndex;
            return;
        }
        else
        {
            String premierIndexNom = premier.getMot();
            if (premierIndexNom.length() > newIndexMot.length() ||
                    (premierIndexNom.length() == newIndexMot.length() &&
                            premierIndexNom.compareTo(newIndexMot) > 0))
            {
                newMotIndex.setProchain(premier);
                premier = newMotIndex;
                return;
            }
        }

        MotIndex currIndex = premier;
        while(currIndex.getProchain() != null)
        {
            String prochainIndexNom = currIndex.getProchain().getMot();
            if (prochainIndexNom.length() > newIndexMot.length()) break;
            if (prochainIndexNom.length() == newIndexMot.length() && prochainIndexNom.compareTo(newIndexMot) > 0) break;
            currIndex = currIndex.getProchain();
        }

        newMotIndex.setProchain(currIndex.getProchain());
        currIndex.setProchain(newMotIndex);
    }
    public void ajoutMotListes(DocsIndexListe docsIndexListe)
    {
        if (docsIndexListe == null) return;

        DocIndex currDocIndex = docsIndexListe.getPremier();

        while(currDocIndex != null)
        {
            MotsIndexListe motsIndexListe = currDocIndex.getMotsListe();

            if(motsIndexListe == null) continue;

            MotIndex currMotIndex = motsIndexListe.getPremier();
            while(currMotIndex != null)
            {
                String mot = currMotIndex.getMot();
                MotIndex motIndexTrouve = trouveIndex(mot);
                if(motIndexTrouve == null)
                {
                    ajoutMotIndexTriant(mot);
                }
                else
                {
                    motIndexTrouve.frequenceIncrement();
                }

                currMotIndex = currMotIndex.getProchain();
            }

            currDocIndex = currDocIndex.getProchain();
        }
    }
    public void compteDocsFrequences(DocsIndexListe docsIndexListe)
    {
        if(this.premier == null || docsIndexListe.getPremier() == null) return;

        MotIndex currMotIndex = this.premier;

        while(currMotIndex != null)
        {
            currMotIndex.saveDocsFrequences(docsIndexListe);
            currMotIndex = currMotIndex.getProchain();
        }
    }
    public void effaceTout()
    {
        this.premier = null;
    }
}

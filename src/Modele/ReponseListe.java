package Modele;

import java.util.ArrayList;
import java.util.Arrays;

public class ReponseListe
{
    MotIndex premier;
    private ArrayList<String> reponseMots;
    private String printTxts;

    public ReponseListe()
    {
        this.reponseMots = new ArrayList<String>();
        this.printTxts = "";
    }

    // Gets & Set
    public MotIndex getPremier()
    {
        return this.premier;
    }
    public String getPrintTxts()
    {
        this.printTxts = "";
        this.setPrintTxts();
        return this.printTxts;
    }
    public void setPrintTxts()
    {
        if(this.premier == null) return;

        MotIndex currMotIndex = this.premier;

        while(currMotIndex != null)
        {
            System.out.println(currMotIndex.getPrintTxt());
            this.printTxts += currMotIndex.getPrintTxt();
            currMotIndex = currMotIndex.getProchain();
        }
    }

    // Méthodes
    public void recherche(MotsIndexListe motsIndexListe, String motsARechercher)
    {
        this.reponseMots.clear();
        this.tokenize(motsARechercher);
        this.rassembleReponseMotIndex(motsIndexListe, reponseMots);
    }
    public void tokenize(String motsARechercher) {
        reponseMots.addAll(Arrays.asList(motsARechercher.split("[\\s/]+")));
    }
    public void rassembleReponseMotIndex(MotsIndexListe motsIndexListe, ArrayList<String> reponseMots)
    {
        if(motsIndexListe.getPremier() == null) return;

        for(String reponseMot : reponseMots)
        {
            MotIndex motIndexTrouve = motsIndexListe.trouveIndex(reponseMot);
            MotIndex reponseIndex = new MotIndex(reponseMot);
            if(motIndexTrouve != null)
            {
                reponseIndex.setListeDocuments(motIndexTrouve.getListeDocuments());
                reponseIndex.setPrintTxt();
                ajoutReponseMotIndex(reponseIndex);
            }
            else
            {
                ajoutReponseMotIndex(reponseIndex);
            }
        }
    }
    public void ajoutReponseMotIndex(MotIndex motIndexTrouve)
    {
        if(this.premier == null)
        {
            this.premier = motIndexTrouve;
            return;
        }

        MotIndex currMotIndex = this.premier;

        while(currMotIndex != null)
        {
            if(currMotIndex.getProchain() == null)
            {
                currMotIndex.setProchain(motIndexTrouve);
                return;
            }
            currMotIndex = currMotIndex.getProchain();
        }
    }
    public void effaceTout()
    {
        this.premier = null;
    }
}

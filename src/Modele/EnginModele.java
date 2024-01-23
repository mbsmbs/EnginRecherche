package Modele;

import Vue.EnginVue;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class EnginModele extends Component
{
    private EnginVue vue;
    private JFileChooser fileChooser;
    private DocsIndexListe docsIndexListe;
    private MotsIndexListe motsIndexListe;
    private ReponseListe reponseListe;

    public EnginModele(EnginVue vue)
    {
        this.vue = vue;
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        docsIndexListe = new DocsIndexListe();
        motsIndexListe = new MotsIndexListe();
        reponseListe = new ReponseListe();
    }

    // Gets
    public EnginVue getVue()
    {
        return this.vue;
    }
    public DocsIndexListe getDocsIndexListe()
    {
        return this.docsIndexListe;
    }
    public MotsIndexListe getMotsIndexListe()
    {
        return this.motsIndexListe;
    }
    public ReponseListe getReponseListe(){return this.reponseListe;}

    // Méthodes
    public void indexer()
    {
        fileChooser.setCurrentDirectory(new File("."));
        int option = this.fileChooser.showOpenDialog(this);

        try {
            if (option == JFileChooser.APPROVE_OPTION)
            {
                File[] files = fileChooser.getSelectedFiles();

                if(docsIndexListe != null) docsIndexListe.effaceTout();
                if(motsIndexListe != null) motsIndexListe.effaceTout();

                for(File file:files)
                {
                    docsIndexListe.ajoutDocIndexTriant(file);
                }
            }
        }
        catch (Exception e)
        {
            // A terminer
            e.printStackTrace();
        }
    }
    public void inverser()
    {
        if (this.docsIndexListe == null) return;
        this.motsIndexListe.ajoutMotListes(this.docsIndexListe);
        this.motsIndexListe.compteDocsFrequences(this.docsIndexListe);
    }
    public void recherche(String motsARechercher)
    {
        this.reponseListe.effaceTout();
        this.reponseListe.recherche(this.motsIndexListe, motsARechercher);
    }
}
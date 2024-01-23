package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DocIndex
{
    private DocIndex prochain;
    private int motFrequence;
    private MotsIndexListe motsListe;
    private File doc;
    private String nom, docText, printTxt;
    private ArrayList<String> docMots;
    public DocIndex(File doc)
    {
        this.doc = doc;
        this.nom = doc.getName();
        this.printTxt = "";

        this.docMots = new ArrayList<String>();
        readFile(doc.getPath());
        tokenizeDocMots();

        // Creer une liste des mots et ajout ou frequence++
        this.motsListe = new MotsIndexListe();
        for(String mot : docMots)
        {
            MotIndex currMotIndex = this.motsListe.trouveIndex(mot);
            if(currMotIndex == null)
            {
                this.motsListe.ajoutMotIndexTriant(mot);
            }
            else
            {
                currMotIndex.frequenceIncrement();
            }
        }
    }

    // Gets & Sets
    public DocIndex getProchain()
    {
        return this.prochain;
    }
    public void setProchain(DocIndex docIndex)
    {
        this.prochain = docIndex;
    }
    public File getDoc()
    {
        return this.doc;
    }
    public String getNom()
    {
        return this.nom;
    }
    public String getPrintTxt()
    {
        this.printTxt = "";
        setPrintTxt();
        return this.printTxt;
    }
    private void setPrintTxt()
    {
        if(motsListe.getPremier() == null) return;

        MotIndex currMotIndex = motsListe.getPremier();

        while(currMotIndex != null)
        {
            if(currMotIndex.getProchain() == null)
            {
                this.printTxt += "( " + currMotIndex.getMot() + " : " + currMotIndex.getFrequence() + " )";
            }
            else
            {
                this.printTxt += "( " + currMotIndex.getMot() + " : " + currMotIndex.getFrequence() + " ), ";
            }

            currMotIndex = currMotIndex.getProchain();
        }
        this.printTxt += System.lineSeparator();
    }
    public int getFrequence()
    {
        return this.motFrequence;
    }
    public void setFrequence(int frequence)
    {
        this.motFrequence = frequence;
    }

    // Méthodes
    public MotsIndexListe getMotsListe()
    {
        return this.motsListe;
    }
    public void readFile(String path)
    {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bfReader = new BufferedReader(new FileReader(path))) {
            String line = null;

            while((line = bfReader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            docText = sb.toString();
        }
    }
    public void tokenizeDocMots()
    {
        docMots.addAll(Arrays.asList(docText.split("[\\s/]+")));
    }
    public MotIndex trouveMotIndex(String mot)
    {
        if(this.motsListe.getPremier() == null) return null;

        MotIndex currMotIndex = this.motsListe.trouveIndex(mot);

        if(currMotIndex != null) return currMotIndex;

        return null;
    }
}
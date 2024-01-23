package Vue;

import Controle.EnginControle;
import Modele.DocsIndexListe;
import Modele.EnginModele;
import Modele.MotsIndexListe;
import Modele.ReponseListe;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.*;

import static javax.swing.GroupLayout.Alignment.*;

public class EnginVue extends JFrame
{
    private EnginControle controle;
    private EnginModele modele;
    private JMenuBar menuBar;
    private JMenu indexerMenu, afficherMenu, rechercheMenu;
    private JMenuItem fichMenuItem, inverseMenuItem, docsListMenuItem, motsListMenuItem, motsRequeteMenuItem;
    private JTextArea txtArea;
    private JScrollPane jScrollPane;

    public EnginVue(String title)
    {
        super(title);

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        menuBar = new JMenuBar();

        indexerMenu = new JMenu("Indexer");
        fichMenuItem = new JMenuItem("Fichiers");
        inverseMenuItem = new JMenuItem("Inverser");
        indexerMenu.add(fichMenuItem);
        indexerMenu.add(inverseMenuItem);
        menuBar.add(indexerMenu);

        afficherMenu = new JMenu("Afficher");
        docsListMenuItem = new JMenuItem("Liste des documents");
        motsListMenuItem = new JMenuItem("Liste des mots");
        afficherMenu.add(docsListMenuItem);
        afficherMenu.add(motsListMenuItem);
        menuBar.add(afficherMenu);

        rechercheMenu = new JMenu("Recherche");
        motsRequeteMenuItem = new JMenuItem("Mots");
        rechercheMenu.add(motsRequeteMenuItem);
        menuBar.add(rechercheMenu);

        this.setJMenuBar(menuBar);

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        jScrollPane = new JScrollPane(txtArea);
        this.add(jScrollPane);

        modele = new EnginModele(this);
        controle = new EnginControle(modele);

        // Add event listeners
        fichMenuItem.addActionListener(controle.new Indexer());
        inverseMenuItem.addActionListener(controle.new Indexer());
        docsListMenuItem.addActionListener(controle.new Afficheur());
        motsListMenuItem.addActionListener(controle.new Afficheur());
        motsRequeteMenuItem.addActionListener(controle.new Rechercheur());
    }

    // Gets
    public JMenuItem getFichMenuItem()
    {
        return this.fichMenuItem;
    }
    public JMenuItem getInverseMenuItem()
    {
        return this.inverseMenuItem;
    }
    public JMenuItem getDocsListMenuItem()
    {
        return this.docsListMenuItem;
    }
    public JMenuItem getMotsListMenuItem()
    {
        return this.motsListMenuItem;
    }
    public JMenuItem getMotsRequeteMenuItem(){return this.motsRequeteMenuItem;}

    // Méthodes
    public void afficher()
    {
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void motRechercheDialog()
    {
        String motsARechercher = JOptionPane.showInputDialog(this, "Entrez le(s) mot(s) à rechercher");
        modele.recherche(motsARechercher);
        afficherReponseList();
    }
    public void afficherReponseList()
    {
        this.txtArea.setText("");
        ReponseListe reponseListe = modele.getReponseListe();

        if(reponseListe.getPremier() == null)
        {
            this.txtArea.append("Liste des réponses est vide! " + System.lineSeparator()
                    + "Il faut d'abord cliquer sur le menu \"Inverser\"");
        }
        else
        {
            this.txtArea.append("Résultat(s) trouvé(s) : " + System.lineSeparator() + System.lineSeparator());
            String printTxts = reponseListe.getPrintTxts();
            this.txtArea.append(printTxts);
        }
    }
    public void afficheDocsList()
    {
        txtArea.setText("");
        DocsIndexListe docsIndexListe = modele.getDocsIndexListe();

        if(docsIndexListe.getPremier() == null)
        {
            this.txtArea.append("Liste des documents est vide");
        }
        else
        {
            this.txtArea.append("Liste des documents : " + System.lineSeparator() + System.lineSeparator());
            String docsPrintTxt = docsIndexListe.getPrintTxts();
            this.txtArea.append(docsPrintTxt);
        }
    }
    public void afficheMotsList()
    {
        txtArea.setText("");
        MotsIndexListe motsIndexListe = modele.getMotsIndexListe();

        if(motsIndexListe.getPremier() == null)
        {
            this.txtArea.append("Liste des mots est vide!" + System.lineSeparator() +
                    "Il faut d'abord indexer les fichiers!");
        }
        else
        {
            this.txtArea.append("Liste des mots : " + System.lineSeparator() + System.lineSeparator());
            String motsPrintTxt = motsIndexListe.getPrintTxts();
            this.txtArea.append(motsPrintTxt);
        }
    }

}

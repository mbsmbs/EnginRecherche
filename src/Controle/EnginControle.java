package Controle;

import Modele.EnginModele;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnginControle
{
    private EnginModele modele;
    public EnginControle(EnginModele modele)
    {
        this.modele = modele;
    }
    // Event listener pour les menus Indexer
    public class Indexer implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == modele.getVue().getFichMenuItem())
            {
                modele.indexer();
            }
            else if(e.getSource() == modele.getVue().getInverseMenuItem())
            {
                 modele.inverser();
            }
        }
    }
    // Event listener pour les menus Afficher
    public class Afficheur implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == modele.getVue().getDocsListMenuItem())
            {
                modele.getVue().afficheDocsList();
            }
            else if(e.getSource() == modele.getVue().getMotsListMenuItem())
            {
                modele.getVue().afficheMotsList();
            }
        }
    }
    // Event listener pour le menu Recherche
    public class Rechercheur implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == modele.getVue().getMotsRequeteMenuItem())
            {
                modele.getVue().motRechercheDialog();
            }
        }
    }
}

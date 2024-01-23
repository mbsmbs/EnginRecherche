/*
IFT1025 TP2 Engin de recherche simple
18/12/2022
Byungsuk Min
Yilin Zang
*/

import Vue.EnginVue;


public class EnginMain
{
    private EnginVue enginVue;
    public EnginMain(String title)
    {
        this.enginVue = new EnginVue(title);
    }
    public void creerEtAfficher()
    {
        enginVue.afficher();
    }

    public static void main(String[] args)
    {
        EnginMain engin = new EnginMain("EnginDeRecherche");

        engin.creerEtAfficher();
    }
}

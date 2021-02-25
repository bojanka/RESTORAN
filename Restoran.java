class Namirnica{
    private String naziv;
    private double cena;

    String getNaziv(){return naziv;}
    double getCena(){return cena;}

    void setNaziv(String naziv){this.naziv = naziv;}
    void setCena(double cena){this.cena = cena;}

    Namirnica(String naziv, double cena){
        this.naziv = naziv;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "naziv namirnice: " + naziv + ", cena namirnice: " + cena;
    }

}
class Pice extends Namirnica{
    private boolean gaziranost;

    Pice(String naziv, double cena, boolean gaziranost)
    {
        super(naziv, cena);
        this.gaziranost = gaziranost;
    }

    String getGaziranost(){
        if(gaziranost == true)
        {
            return "DA";
        }else
            return "NE";
    }

    @Override
    public String toString() {
        return "naziv namirnice: " + getNaziv() + ", cena namirnice: " + getCena() + "gaziranost: " + getGaziranost();
    }
}
class Jelo{
    private String naziv;
    private double cena;
    private Namirnica[] potrebneNamirnice;

    String getNaziv(){return naziv;}
    double getCena(){return cena;}

    void setNaziv(String naziv){this.naziv = naziv;}
    void setCena(double cena){this.cena = cena;}
    void setNizNamirnica(Namirnica[] potrebneNamirnice){this.potrebneNamirnice = potrebneNamirnice;}

    @Override
    public String toString() {
        return "Naziv jela: " + naziv + ", cena jela: " + cena;
    }
}
class Meni{
    private Jelo[] meni;

    void ispisiJela(){
        for(int i = 0; i<meni.length ;i++)
        {
            System.out.println(meni[i].toString() + "\n");
        }
    }
    ///dodaj jelo,ispis jelo...
}
class Narudzbina{
    ///liste jela i pica
    ///metoda poruci 
}
class Racun{
    private Narudzbina n;
    ///narudzbinu

    ///metodu ispisuje sva jela u narudzbini
    ///i sabira cene i ispisuje zajednicku cenu
}

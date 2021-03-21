class Namirnica{
    private String naziv;

    String getNaziv(){return naziv;}

    void setNaziv(String naziv){this.naziv = naziv;}

    Namirnica(String naziv){
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "naziv namirnice: " + naziv;
    }

}
class Pice extends Namirnica{
    private boolean gaziranost;
    private double cena;

    Pice(String naziv, double cena, boolean gaziranost)
    {
        super(naziv);
        this.cena=cena;
        this.gaziranost = gaziranost;
    }
    String getCena(){return cena;}
    void setCena(String cena){this.cena = cena;}
    
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
    private ArrayList<Jelo> meni=new ArrayList<Jelo>();
    public Meni(String fajl) throws IOException{
        BufferedReader br=new BufferedReader(new FileReader(fajl));
        String linija=br.readLine();
        int i=0;
        Jelo o=new Jelo();
        while(linija != null)
        {
            int j=0;
            String tokeni[]=linija.split(", ");
            o.setNaziv(tokeni[0].trim());
            o.setCena(Integer.parseInt(tokeni[1].trim()));
            Namirnica potrebno[];
            while((tokeni.length-2)>j){
                potrebno[j]=tokeni[j+2].trim();
            }
            o.setNizNamirnica(potrebno);
            meni.set(i, o);
            linija=br.readLine();
        }
        br.close();
    }

    void ispisiJela(){
        for (Jelo jelo : meni)
            {
                System.out.println(jelo.toString() + "\n");
            }
    }
    public void DodajJelo(Jelo jelo){
        meni.add(jelo);
    }
    ///dodaj jelo,ispis jelo...
}
class Narudzbina{
    ///liste jela i pica
    ///metoda poruci 
}
class Racun{
    private Narudzbina n;
    ///metodu ispisuje sva jela u narudzbini
    ///i sabira cene i ispisuje zajednicku cenu
}
public static void main(String[] args) {
    ArrayList<Namirnica> frizider=new ArrayList<Namirnica>();
    BufferedReader br=new BufferedReader(new FileReader("frizider.txt"));
    String linija=br.readLine();
    String tokeni[]=linija.split(", ");
    for(int i=0;i<tokeni.length;i++)
    {
        Namirnica n=new Namirnica(tokeni[i].trim());
        frizider.add(n);
    }
    br.close();
    Meni meni=new Meni("jelovnik.txt");
    meni.ispisiJela();
}

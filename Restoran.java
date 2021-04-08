import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Namirnica{
    private String naziv;

    String getNaziv(){return naziv;}

    void setNaziv(String naziv){this.naziv = naziv;}

    Namirnica(String naziv){
        this.naziv = naziv;
    }
    void kupi(Namirnica n)
    {
        ///upisivanjem u fajl dodajemo namirnicu u frizider
    }

    @Override
    public String toString() {
        return "naziv namirnice: " + naziv ;
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
    double getCena(){return cena;}

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
}
class Narudzbina{
    public ArrayList<Jelo> narucenaJela=new ArrayList<Jelo>();
    public ArrayList<Pice> narucenaPica=new ArrayList<Pice>();
    void naruciJelo(Jelo j)
    {
        narucenaJela.add(j);
    }
    void naruciPice(Jelo p)
    {
        narucenaJela.add(p);
    }
    void ispisiNarudzbinu()
    {
        for (Jelo jelo : narucenaJela )
        {
            System.out.println(jelo.toString() + "\n");
        }
        for (Pice pice : narucenaPica)
        {
            System.out.println(pice.toString() + "\n");
        }
    }
}
class Racun{
    private Narudzbina n;
    double ukupnaCena;
    Racun(Jelo narucenaJela,Pice narucenaPica)
    {
        this.narucenaJela=narucenaJela;
        this.narucenaPica=narucenaPica;
    }
    void ukupnaCena()
    {
        for (Jelo jelo : n.narucenaJela )
        {
            ukupnaCena+=jelo.getCena();
        }
        for (Pice pice : narucenaPica)
        {
            ukupnaCena+=pice.getCena();
        }
    }
    void ispisiRacun()
    {
        n.ispisiNarudzbinu();
        System.out.println("Cena: "+ukupnaCena);
    }
}
public class NemaNamirnice extends Exception {

	public NemaNamirnice(String poruka) {
		super(poruka);
	}
	
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

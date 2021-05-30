import java.util.Scanner;
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

    @Override
    public String toString() {
        return "naziv namirnice: " + naziv ;
    }
}

class Pice extends Namirnica{
    private double cena;
    private boolean gaziranost;

    Pice(String naziv, double cena, boolean gaziranost)
    {
        super(naziv);
        this.cena=cena;
        this.gaziranost = gaziranost;
    }
    double getCena(){return cena;}
    void setCena(double cena){this.cena = cena;}

    String getGaziranost(){
        if(gaziranost == true)
        {
            return "DA";
        }else
            return "NE";
    }
    void setGaziranost(boolean gaziranost){this.gaziranost = gaziranost;}

    @Override
    public String toString() {
        return "naziv namirnice: " + getNaziv() + ", cena namirnice: " + getCena() + "gaziranost: " + getGaziranost();
    }
}
class Jelo{
    private String naziv;
    private double cena;
    private ArrayList<Namirnica> potrebneNamirnice = new ArrayList<Namirnica>();

    String getNaziv(){return naziv;}
    double getCena(){return cena;}

    void setNaziv(String naziv){this.naziv = naziv;}
    void setCena(double cena){this.cena = cena;}
    void setNizNamirnica(ArrayList<Namirnica> potrebneNamirnice){this.potrebneNamirnice = potrebneNamirnice;}

    @Override
    public String toString() {
        return "Naziv jela: " + naziv + ", cena jela: " + cena;
    }
}
class Meni{
    private ArrayList<Jelo> meni = new ArrayList<Jelo>();

    public void Ucitaj() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("jelovnik.txt"));
        String linija = br.readLine();
        Jelo o = new Jelo();
        while(linija != null)
        {
            int j = 0;
            String tokeni[] = linija.split(", ");
            o.setNaziv(tokeni[0].trim());
            o.setCena(Integer.parseInt(tokeni[1].trim()));
            ArrayList<Namirnica> potrebno = new ArrayList<Namirnica>();
            while((tokeni.length-2)>j){
                potrebno.get(j).setNaziv(tokeni[j+2].trim());
            }
            o.setNizNamirnica(potrebno);
            meni.add(o);
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
}
class Picovnik
{
    private ArrayList<Pice> picovnik = new ArrayList<Pice>();

    public void Picovnik(String fajl) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fajl));
        String linija = br.readLine();
        
        while(linija != null)
        {
            String tokeni[] = linija.split(", ");
            if(tokeni[2].equals("gazirano"))
            {
                Pice p = new Pice(tokeni[0].trim(), Integer.parseInt(tokeni[1].trim()),true);
            }else{
                Pice p = new Pice(tokeni[0].trim(), Integer.parseInt(tokeni[1].trim()),false);
            }
            picovnik.add(p);
            linija = br.readLine();
        }
        br.close();
    }

    void ispisiPica(){
        for (Pice pice : picovnik)
            {
                System.out.println(pice.toString() + "\n");
            }
    }

}
class Narudzbina{
    public ArrayList<Jelo> narucenaJela = new ArrayList<Jelo>();
    public ArrayList<Pice> narucenaPica = new ArrayList<Pice>();

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

    void ukupnaCena()
    {
        for (Jelo jelo : n.narucenaJela )
        {
            ukupnaCena += jelo.getCena();
        }
        for (Pice pice : n.narucenaPica)
        {
            ukupnaCena += pice.getCena();
        }
    }
    void ispisiRacun()
    {
        n.ispisiNarudzbinu();
        System.out.println("Cena: " + ukupnaCena);
    }
}

public class Restoran {
    static int opcije()
    {
        Scanner s = new Scanner(System.in);
        int opcija;
        do{
            System.out.println("\nIzaberite zeljenu operaciju:\n");
            System.out.println(" 1. Naruciti obrok");
            System.out.println(" 2. Racun");
            System.out.println(" 3. Dovidjenja");
            opcija = s.nextInt();
            if(opcija < 1 || opcija > 3)
            System.out.println("\nOpcija van opsega (1-3)! Izaberite zeljenu operaciju ponovo!\n");
        }while(opcija > 1 || opcija < 3);
        s.close();
        return opcija;
    }
    static int opcije2()
    {
        Scanner s = new Scanner(System.in);
        int opcija2;
        do{
            System.out.println("\nIzaberite zeljenu operaciju:\n");
            System.out.println(" 1. Naruci jelo");
            System.out.println(" 2. Naruci pice");
            System.out.println(" 3. Nastavi na racun");
            opcija2 = s.nextInt();
            if(opcija2 < 1 || opcija2 > 3)
            System.out.println("\nOpcija van opsega (1-3)! Izaberite zeljenu operaciju ponovo!\n");
        }while(opcija2 > 1 || opcija2 < 3);
        s.close();
        return opcija2;
    }
    public static void main(String[] args) {

        int operacija;
        int operacija2;
        
    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    System.out.println("\t\t\t\t\t  DOBRODOSLI U RESTORAN");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    do
    {
        operacija = opcije();
        switch(operacija)
        {
        case 1:
            do
            {
                operacija2 = opcije2();
                    switch(operacija2)
                    {
                    case 1:
                    /// ispisivanje jelovnika i ubacianje u naruzbinu jela
                    
                        break;
                    case 2:
                    ///ispisivanje picovnika i narudzbina
                        break;
                    case 3:
                        break;
                    }
            }while(operacija<3);
            break;
        case 2:
            ///ispisivanje racuna
            break;
        case 3:
            break;
        }
    }while(operacija<3);
}
}

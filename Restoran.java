import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Namirnica{
    private String naziv;

    String getNaziv(){return naziv;}

    void setNaziv(String naziv){this.naziv = naziv;}

    Namirnica(String naziv){
        this.naziv = naziv;
    }
    public Namirnica(){};

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
    public ArrayList<Namirnica> potrebneNamirnice = new ArrayList<Namirnica>();

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
    public ArrayList<Jelo> meni = new ArrayList<Jelo>();
    public Meni(){};
    public void Ucitaj() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("jelovnik.txt"));
        try{
            String linija = br.readLine();
            
            while(linija != null)
            {   Jelo o = new Jelo();
                String tokeni[] = linija.split(",");
                o.setNaziv(tokeni[0].trim());
                o.setCena(Integer.parseInt(tokeni[1].trim()));
                ArrayList<Namirnica> potrebno = new ArrayList<Namirnica>();
                Namirnica nam= new Namirnica();
                for(int j=2;j<tokeni.length;j++){
                    nam.setNaziv(tokeni[j].trim());
                    potrebno.add(nam);
                }
                o.setNizNamirnica(potrebno);
                meni.add(o);
                linija=br.readLine();
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        finally{
            br.close();
        }
        
    }

    void ispisiJela(){
        int i=1;
        for (Jelo jelo : meni)
            {
                System.out.println(i +". " + jelo.toString() + "\n");
                i++;
            }
    }
}
class Picovnik
{
    public ArrayList<Pice> picovnik = new ArrayList<Pice>();
    public Picovnik(){};
    public void Ucitaj() throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("picovnik.txt"));;
        try{
            String linija = br.readLine();
            
            while(linija != null)
            {
                String tokeni[] = linija.split(",");

                if(tokeni[2].trim().equals("gazirano"))
                {
                    //Pice p = new Pice(tokeni[0].trim(), Integer.parseInt(tokeni[1].trim()),true);
                    picovnik.add(new Pice(tokeni[0].trim(), Integer.parseInt(tokeni[1].trim()),true));
                }else{
                    picovnik.add(new Pice(tokeni[0].trim(), Integer.parseInt(tokeni[1].trim()),false));
                }
                linija = br.readLine();
            }
        }
        catch(IOException e){
            System.out.println(e);
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
    void naruciPice(Pice p)
    {
        narucenaPica.add(p);
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
    public Racun(Narudzbina n){
        this.n=n;
    }
    public Racun(){};
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
class Frizider{
    ArrayList<Namirnica> frizider= new ArrayList<Namirnica>();
    public Frizider(){};
    public void Ucitaj() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("frizider.txt"));
        String linija = br.readLine();
        String tokeni[] = linija.split(", ");
        Namirnica nam= new Namirnica();
        for(int i=0;i<tokeni.length;i++){
            nam.setNaziv(tokeni[i].trim());
            frizider.add(nam);
        }
        br.close();
    }
}
class Greska extends Exception{
    public Greska(String poruka){
        super(poruka);
    }
}
public class Restoran {
    static int opcije(BufferedReader br) throws IOException
    {
        //Scanner s = new Scanner(System.in);
        int opcija=3;
        try{
            do{
                System.out.println("\nIzaberite zeljenu operaciju:\n");
                System.out.println(" 1. Naruciti obrok");
                System.out.println(" 2. Racun");
                System.out.println(" 3. Dovidjenja");
                opcija = Integer.parseInt(br.readLine());
                if(opcija < 1 || opcija > 3)
                System.out.println("\nOpcija van opsega (1-3)! Izaberite zeljenu operaciju ponovo!\n");
            }while(opcija < 1 || opcija > 3);
        }
        catch(IOException e){
            System.out.println(e);
        }
            return opcija;
           
        
    }
    static int opcije2(BufferedReader br)throws IOException
    {
        //nastavi na dovidjenja
        int opcija2=3;
        try{
            do{
                System.out.println("\nIzaberite zeljenu operaciju:\n");
                System.out.println(" 1. Naruci jelo");
                System.out.println(" 2. Naruci pice");
                System.out.println(" 3. Nastavi na racun");
                opcija2 = Integer.parseInt(br.readLine());
                if(opcija2 < 1 || opcija2 > 3)
                System.out.println("\nOpcija van opsega (1-3)! Izaberite zeljenu operaciju ponovo!\n");
            }while(opcija2 < 1 || opcija2 > 3);
        }
        catch(IOException e){
            System.out.println(e);
        }
        return opcija2;
    }
    public static void main(String[] args) throws Greska {

        int operacija;
        int operacija2;
        Meni m=new Meni();
        try{
            m.Ucitaj();
        }
        catch(IOException e){
            System.out.println(e);
        }
        Picovnik picovnik=new Picovnik();
        try{
            picovnik.Ucitaj();
        }
        catch(IOException e){
            System.out.println(e);
        }
        Narudzbina n = new Narudzbina();
        Frizider f=new Frizider();
        int brojac=0;
        
    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    System.out.println("\t\t\t\t\t  DOBRODOSLI U RESTORAN");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    try {
        BufferedReader brd = new BufferedReader(new InputStreamReader(System.in));
        do
    {
        operacija = opcije(brd);
        switch(operacija)
        {
        case 1:
            do
            {
                operacija2 = opcije2(brd);
                    switch(operacija2)
                   
                    {
                    case 1:
                        m.ispisiJela();
                        Scanner j = new Scanner(System.in);
                        int br=j.nextInt();
                        if(br<=m.meni.size() && br>=1){
                            for(int i=0;i<m.meni.get(br-1).potrebneNamirnice.size();i++)
                            {
                                for(int k=0;k<f.frizider.size();k++)
                                {
                                    if(!m.meni.get(br-1).potrebneNamirnice.get(i).getNaziv().equals(f.frizider.get(k))){
                                        brojac++;
                                    }
                                }
                            }
                            if(brojac==0)
                            {
                                n.naruciJelo(m.meni.get(br-1));
                            }
                            else{
                                throw new Greska("Ne mozemo napraviti ovo jelo.");
                            }
                        }
                        else{
                                throw new Greska("To jelo ne postoji.");
                        }
                        j.close();
                        break;
                    case 2:
                        picovnik.ispisiPica();
                        Scanner p = new Scanner(System.in);
                        int index=p.nextInt();
                        if(index<=picovnik.picovnik.size() && index>=1){
                            n.naruciPice(picovnik.picovnik.get(index-1));
                        }
                        else{
                            throw new Greska("To pice ne postoji.");
                        }
                        p.close();
                        break;
                    case 3:
                        break;
                    }
            }while(operacija<3);
            break;
        case 2:
            Racun r= new Racun(n);
            r.ispisiRacun();
            break;
        case 3:
            break;
        }
    }while(operacija<3);
    brd.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
}
}

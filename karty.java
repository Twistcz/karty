import java.util.Random;
import java.util.Scanner;

class Karta{
    
    //♥ = srdce
    //♠ = listy
    //♣ = žaludy
    //♦ = kule
    
    private String hodnota;
    private char barva;
    
    public Karta(String h, char b){
        hodnota = h;
        barva = b;
    }
    
    public char barva(){
        return barva;
    }
    
    public String hodnota(){
        return hodnota;
    }
}

class Balicek{
    
    private Karta[] balicek;
    private int aktualniKarta;
    private final int pocetKaret;
    private Random rand;
    private int relativniVelikostBalicku;
    
    public Balicek(){
        pocetKaret = 32;
        relativniVelikostBalicku = 32;
        
        String[] hodnota = {"7", "8", "9", "10", "Spodek", 
                            "Svrsek", "Kral", "Eso"};
        char[] barva = {'♥', '♠', '♣', '♦'};
        
        balicek = new Karta[pocetKaret];
        aktualniKarta = 0;
        rand = new Random();
        
        for(int pocet = 0; pocet < balicek.length; pocet++){
            balicek[pocet] = new Karta(hodnota[pocet%8], barva[pocet/8]);
        }
    }
    
    public void zamichat(){
        aktualniKarta = 0;
        for(int prvni = 0; prvni < relativniVelikostBalicku; prvni++){
            int druha = rand.nextInt(relativniVelikostBalicku);
            Karta x = balicek[prvni];
            balicek[prvni] = balicek[druha];
            balicek[druha] = x;
        }
    }
    
    public Karta lizniKartu(){
        Karta liznutaKarta = balicek[0];
        for(int i = 1; i < relativniVelikostBalicku; i++){
            balicek[i-1] = balicek[i];
            }
        balicek[relativniVelikostBalicku-1] = null;
        relativniVelikostBalicku--;
        return liznutaKarta;
        }
    
    public void vratKartuNaSpod(Karta k){
        balicek[relativniVelikostBalicku] = k;
        relativniVelikostBalicku++;
    }
    
    public char barvaKarty(int i){
        return balicek[i].barva();
    }
    
    public String hodnotaKarty(int i){
        return balicek[i].hodnota();
    }
    
    public int velikostBalicku(){
        return relativniVelikostBalicku;
    }
}

public class KartyMain {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int vstupUzivatele = 0;
        Balicek balicek1 = new Balicek();
        
        //start - zamíchání a líznutí karty na stůl
        balicek1.zamichat();
        Karta kartaNaStole = balicek1.lizniKartu();
        System.out.println("----------------------");
        System.out.println("Karta na stole: " + kartaNaStole.barva() + kartaNaStole.hodnota());
        
        //cyklus lízání karet a vstup od uživatele
        do{
            System.out.println("----------------------");
            System.out.println("Akce:");
            System.out.println("1...Sejmout kartu");
            System.out.println("2...Zamíchat balíček");
            System.out.println("3...Konec");
            System.out.println("----------------------");
            vstupUzivatele = input.nextInt();
            
            switch(vstupUzivatele){
                case 1:
                    balicek1.vratKartuNaSpod(kartaNaStole);
                    kartaNaStole = balicek1.lizniKartu();
                    System.out.println("----------------------");
                    System.out.println("Karta na stole: " + kartaNaStole.barva() + kartaNaStole.hodnota());
                    break;
                case 2:
                    balicek1.zamichat();
                    System.out.println("----------------------");
                    System.out.println("ZAMÍCHÁNO");
                    System.out.println("Karta na stole: " + kartaNaStole.barva() + kartaNaStole.hodnota());
                    break;
                case 3:
                    System.out.println("!!!KONEC!!!");
                    break;
                default:
                    System.out.println("----------------------");
                    System.out.println("Špatná akce!");
                    break;
            }            
        }while(vstupUzivatele != 3);
    }
}
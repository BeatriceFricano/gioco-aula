/*
1. Struttura Minima:
- Il gioco deve essere composto da almeno 3 livelli, ciascuno rappresentando una fase diversa
dell'avventura e delle sfide crescenti.
- Ogni livello deve introdurre nuove sfide o nemici, richiedendo ai giocatori di utilizzare abilità 
diverse o di potenziare i loro personaggi.

2. Requisito di Incapsulamento:
- Ogni personaggio deve avere attributi privati, come la salute o la mana, che possono essere modificati
solo attraverso metodi pubblici.
- Implementare metodi per ottenere o modificare la salute che verificano altre condizioni prima di apportare modifiche, 
proteggendo così i dati sensibili del personaggio.

3. Requisito di Ereditarietà:
- Definire una classe base Personaggio da cui tutti i tipi di personaggi ereditano.
- La classe base deve includere attributi comuni come salute, energia e livello, oltre a metodi base come
Attacca() e Difendi().

4. Requisito di Polimorfismo:
- Ogni tipo di personaggio (Guerriero, Mago, Ladro) deve avere una propria implementazione del metodo
Attacca() che riflette le loro capacità uniche. o Guerriero: Attacca() con attacchi fisici potenti.
- Mago: Attacca() con incantesimi che possono causare danno o effetti di supporto.
- Ladro: Attacca() con tecniche di agilità e furtività.

5. Interazione e Scelta del Giocatore:
- A ogni livello, i giocatori devono fare scelte strategiche su come utilizzare le abilità dei loro personaggi
per superare gli ostacoli.
- Le decisioni prese influenzeranno non solo l'esito delle battaglie ma anche il percorso narrativo e le
interazioni tra i personaggi */

import java.util.Scanner;


// Classe padre personaggio
public class Personaggio {
    private String nome;
    private int salute;
    private int mana;
    private int livello;

    // Costruttore
    public Personaggio(String nome, int salute, int mana, int livello) {
        this.nome = nome;
        this.salute = salute;
        this.mana = mana;
        this.livello = livello;
    }



    // Metodo per attaccare
    public void attacca() {
        System.out.println(nome + " attacca!");
    }

    // Metodo per difendersi
    public void difendi() {
        System.out.println(nome + " si difende!");
        // Non ci sono perdite durante la difesa
        //quindi salute e mana rimangono uguali
        System.out.println(nome + " si difende con successo!");
        System.out.println("Riepilogo salute: " + salute);
        System.out.println("Riepilogo mana: " + mana);
    }

    // Metodo per subire un attacco
    public void subisciAttacco(int danni) {
        salute -= danni;
        if (salute < 0) {
            salute = 0;  //Controlla se la salute è negativa (non può esserlo) e imposta a 0 in caso positivo
        }
        modificaSalute(-5); //ogni attacco influisce di 5 sulla vita
        modificaMana(-2); //ogni attacco influisce di 2 sul mana
        System.out.println(nome + " ha subito un attacco!");
        System.out.println("Salute rimanente: " + salute);
        System.out.println("Mana rimanente: " + mana);
    }

    //subisci difesa non lo faccio perchè tanto rimane tutto uguale nei livelli






    // get e set
    // Metodi per ottenere salute e mana
    public int getSalute() {
        return salute;
    }

    public int getMana() {
        return mana;
    }

    // Metodo per modificare salute
    public void modificaSalute(int valore) {
        salute += valore;
    }

    // Metodo per modificare mana
    public void modificaMana(int valore) {
        mana += valore;
    }

    // Metodo getter per il nome
    public String getNome() {
        return nome;
    }

    // Metodo setter per il nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Metodo setter per i punti mana
    public void setMana(int Mana) {
        this.mana = mana;
    }

    // Metodo getter per il livello
    public int getLivello() {
        return livello;
    }

    // Metodo setter per il livello
    public void setLivello(int livello) {
        this.livello = livello;
    }
}








// Classe figlia guerriero
class Guerriero extends Personaggio {
    // Costruttore
    public Guerriero(String nome) {
        super(nome, 100, 60, 1); // Punti vita e mana iniziali al 1 livello di ogni personaggio
    }

    // Override del metodo attacca per il Guerriero
    @Override
    public void attacca() {
        System.out.println(getNome() + "Il guerriero attacca a colpi di 'Welcome back' ");
        int danni = 20; //il guerriero infligga 20 danni con ogni attacco
        System.out.println("Hai inflitto " + danni + " danni!");
    }
    }


// Classe figlia mago
class Mago extends Personaggio {
    // Costruttore
    public Mago(String nome) {
        super(nome, 100, 30, 1); 
    }

    // Override del metodo attacca per il Mago
    @Override
    public void attacca() {
        System.out.println(getNome() + "con l'incantesimo 'chi domanda comanda' ruba una mozzarella");
        int danni = 15; 
        System.out.println("Hai inflitto " + danni + " danni!");
    }
}

// Classe figlia Ladro
class Ladro extends Personaggio {
    // Costruttore
    public Ladro(String nome) {
        super(nome, 100, 10, 1); 
    }

    // Override del metodo attacca per il Ladro
    @Override
    public void attacca() {
        System.out.println(getNome() + "furtivamente ruba una pizza");
        int danni = 10; 
        System.out.println("Non molto efficace, ma hai inflitto " + danni + " danni!");
       
    }
}

class Nemico extends Personaggio {
    public Nemico(String nome) {
        super(nome, 100, 5, 1); // Valori arbitrari per salute, mana e livello
    }

    // metodo attacca per il Nemico
    @Override
    public void attacca() {
        System.out.println(getNome() + " attacca!");
        //int danni = 10;

        //qui da aggiustare
    }
}





// Classe Livello
class Livello {
    private int numeroLivello;
    private Personaggio[] nemici;
    private Scanner scanner;

    // Costruttore
    public Livello(int numeroLivello, Personaggio[] nemici) {
        this.numeroLivello = numeroLivello;
        this.nemici = nemici;
        this.scanner = new Scanner(System.in);
    }


    // Metodo per iniziare il livello
    public void iniziaLivello() {

        narrativaLivello(); //mostra la trama

        Scanner input = new Scanner(System.in);
        System.out.println("Benvenuto al Livello " + numeroLivello);
        System.out.println("Sei pronto per l'avventura? Si/No");
        String risposta = scanner.nextLine();
        if (risposta.equalsIgnoreCase("no")) {
            System.out.println("Fine del gioco.");
            return;
        }


        // Scelta del personaggio
        System.out.println("Benvenuto in un'avventura al sapore di pizza!");
        System.out.println("Scegli il tuo personaggio:");
        System.out.println("1. Guerriero");
        System.out.println("2. Mago");
        System.out.println("3. Ladro");
        int sceltaPersonaggio = input.nextInt();

        Personaggio giocatore = null;
        switch (sceltaPersonaggio) {
            case 1:
                giocatore = new Guerriero("Mikk");
                break;
            case 2:
                giocatore = new Mago("San");
                break;
            case 3:
                giocatore = new Ladro("Tom");
                break;
        }
      


        // Affronta i nemici
        boolean i = true;

        while (i) {    //si continua finchè uno dei due non muore
            for (Personaggio nemico : nemici) {
                if (nemico != null && giocatore.getSalute() > 0) { //deve essere in salute
                    System.out.println("Affronta il nemico: " + nemico.getNome());
                    

                    // Turno del giocatore
                    System.out.println("Cosa vuoi fare? Premi 1 per attaccare o 2 per difenderti:");
                    int azioneGiocatore = scanner.nextInt();
                    if (azioneGiocatore == 1) {
                        giocatore.attacca();
                        nemico.subisciAttacco(20); // Esempio: il nemico subisce 20 danni
                    } else if (azioneGiocatore == 2) {
                        giocatore.difendi();
                    }
                    
                    if (nemico.getSalute() <= 0) {
                        System.out.println(nemico.getNome() + " è stato sconfitto!");
                        i = false; // Termina la battaglia se il nemico è stato sconfitto
                        break;
                    } 


                    // Turno del nemico
                    if (giocatore.getSalute() > 0) {
                        nemico.attacca();
                        giocatore.subisciAttacco(10); // Esempio: il giocatore subisce 10 danni
                    } else {
                        System.out.println("Sei stato sconfitto! Game Over.");
                        i = false; // Termina la battaglia se il giocatore è stato sconfitto
                        break;
                        }


                }   //manca lo sviluppo di nuove abilità
                
            }
        }
        System.out.println("Fine Livello " + numeroLivello); 
    }

      // Metodo per controllare se tutti i nemici sono stati sconfitti
        public boolean completato() {
            for (Personaggio nemico : nemici) {
                if (nemico.getSalute() > 0) {
                    return false; // Se anche un nemico ha salute maggiore di 0, il livello non è completato
                }
            }
            return true;
    }
        



    private void narrativaLivello() {
        switch (numeroLivello) {
            case 1:
                System.out.println("La saga dei Tre campioni inizia con un giorno come tanti nel paesino di Osteria Grande'.");
                System.out.println("Il trio si ritrova nella locanda 'Il Boom', discutendo di avventure e caffe da macchinetta.");
                System.out.println("I nostri eroi si avventurano nel luogo sperduto noto come 'Pizzeria Paradiso', odiato dai napoletani");
                System.out.println("E quindi dovranno fare i conti con il temibile boss del livello: 'Il napoletano incazzato'.");
                break;
            case 2:
                System.out.println("Gli ostacoli non si fermano qua, ma arrivano due nuovi nemici");
                break;
            case 3:
                System.out.println("I nostri eroi usciranno vivi solo se affrontano i due temibili boss");
                break;
        }
    }
}


    class Main {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
    
            // Creazione del nemico per il livello 1
            Personaggio nemicoLivello1 = new Nemico("Pizzaiolo incazzato");
            // Creazione del livello 1 con un solo nemico
            Livello livello1 = new Livello(1, new Personaggio[]{nemicoLivello1});
            // Inizia il livello 
            livello1.iniziaLivello();


            // Creazione dei nemici per il livello 2
            Personaggio nemicoLivello2A = new Nemico("Pizzaiolo più grosso");
            Personaggio nemicoLivello2B = new Nemico("Cameriere sottopagato");
            // Creazione del livello 2 con tre nemici
            Livello livello2 = new Livello(2, new Personaggio[]{nemicoLivello2A, nemicoLivello2B});
            // Inizia il livello 2
            livello2.iniziaLivello();


            // Creazione dei nemici per il livello 3 (ultimo)
            Personaggio nemicoLivello3A = new Nemico("Tizio che aspetta da 2 ore");
            Personaggio nemicoLivello3B = new Nemico("Napoletano in aiuto");
            // Creazione del livello 3 con tre nemici
            Livello livello3 = new Livello(3, new Personaggio[]{nemicoLivello3A, nemicoLivello3B});
            // Inizia il livello 3
            livello3.iniziaLivello();


            // Condizioni di vittoria e sconfitta
            if (livello3.completato()) {
                System.out.println("Complimenti! Hai completato tutti i livelli. Hai vinto il gioco!");
            } else {
                System.out.println("Mi dispiace, hai perso il gioco. Meglio fortuna la prossima volta!");
            }

    

            input.close();
        }
    }

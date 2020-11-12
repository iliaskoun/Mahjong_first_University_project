//icsd17092 ilias kounelis
package code;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Main_Activities extends JFrame {

    private Player paixths;// apothhkeuetai to onoma kai to epipedo duskolias
    private int anadiataksh;//counter voithias 1
    private int voithia;//counter voithias 2
    private int diagrafh;//counter voithias 3
    private boolean emf;//voithia emfanishs plakidiwn 

    private ArrayList<Integer> anairesh;//pinakas pou apothikeuei ta plakidia pou exw ekatharisei,wste na ta epanaferw

    private ArrayList<Tile> plakidia;//pinakas me ola ta plakidia mesa

    private int vathm;//bathmoi 

    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private JPanel buttons = new JPanel(new GridBagLayout());//

    private ArrayList<JButton> kplak;//plakidia sta grafika

    private int epil1;//epilogh 1 gia afairesh plakidiou
    private int epil2;//epilogh 2 gia afairesh plakidiou

    private JLabel l1;//etiketa gia voithia anadiatakshs
    private JLabel l2;//etiketa gia voithia epilogh plakidiwn pou mporoun n diagrafoun
    private JLabel l3;//etiketa gia voithia diagrafhs
    private JLabel l5;//etiketa gia bathmologia
    String name, dusk;
    private Border vasiko = BorderFactory.createLineBorder(Color.GRAY, 1);//vasikos xrwmatismos gyro gyro apo kathe plakidio
    private Border epilegmeno = BorderFactory.createLineBorder(Color.BLACK, 5);//xrwmatismos gia otan clikaroume panw sto kathe plakidio gia afairesh
    private Border emfanish = BorderFactory.createLineBorder(Color.ORANGE, 5);//xrwmatismos gia th voithia emfanishs 
    private JPanel contentPane;

    public Main_Activities() {
        emf = false;//arxikopoihsh voithias emfanishs ws false 
        vathm = 0;
        epil1 = -1;
        epil2 = -1;
        anairesh = new ArrayList();//ftiaxnoume to pinaka 
        anairesh.add(-1);//arxikopoioume me auta wste otan elegxoume kai exei autes tis times na mhn na kanei anairesh
        anairesh.add(-1);

        arxikopoihsh_plakidiwn();//kalw th synarthsh gia na gemisw ta plakidia me baboo,louloudia,epoxes,xarakthres,kiklous 

        graphics();//kalw ta grafika gia na emfanisw arxiko_menu ,pista 
    }

    private void graphics() {
        arxiko_menu();//kalw to menu wste na grapsw onoma kai na epileksw diskolia
        pista();//kalw pista pou exw ftiaksei
//to parakatw emfanizei amesws meta to anoigma ths pistas tis odhgies wste na ksexwrizei ta plakidia o xrhsths
        JOptionPane.showMessageDialog(null, "Character Tiles  = Red \n"
                + "Circle Tiles = Cyan \n" + "Bamboo Tiles  = Green \n"
                + "Season Tiles = Magenda \n" + "Flower Tiles = Yellow", null, JOptionPane.WARNING_MESSAGE);

    }
//C:\\Users\\leeko\\Desktop\\glastra.png

    private void arxiko_menu() {

        do {
            name = JOptionPane.showInputDialog(null, "Enter your name", "Player Info", JOptionPane.QUESTION_MESSAGE);
        } while (name == null);
        String[] list = {"Easy", "Medium", "Hard"};//lista epiloghs diskolia 
        JComboBox jcb = new JComboBox(list);
        jcb.setEditable(true);
        do {
            JOptionPane.showMessageDialog(null, jcb, "Choose Difficulty", JOptionPane.QUESTION_MESSAGE);//emfanish parathyrou me mhnima epelekse duskolia
            dusk = (String) jcb.getSelectedItem();
        } while (dusk == null);
        paixths = new Player(name, dusk);//neos paixths me ta stoixeia pou edwsa
        //prepei n ftiaksw ena file pou apothikeuei to xrhsth(na dw asfaleia file writter)
        arxikopoihsh_voithiwn();//kaloume thn arxikopoihsh_voithiwn wste na broume ti voithies exei o xrhsths analoga me th diskolia pou dialekse

    }

    private void pista() {
//panw sto frame emfanizetai to Mahjong

        frame = new JFrame("Mahjong");

        panel = new JPanel();// panel 1(pista)
        panel2 = new JPanel();//to panel twn plakidiwn(pou einai mesa sto panel 1 ths pistas)

        panel.setLayout(null);

        panel2.setBounds(10, 10, 700, 600);//oria tou panel2(dhladh megethos plakidiwn)
        panel.add(panel2);
        panel2.setLayout(new GridLayout(10, 13));//grammes stiles twn plakidiwn

        frame.add(panel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setLocationRelativeTo(null);

        arxikopoihsh_koumpiwn();//kaloume th arxikopoihsh_koumpiwn wste na ftiaksoume ta plakidia 
        for (JButton b : kplak) {
            panel2.add(b);
        }

        JButton b5 = new JButton("Undo");//koumpi anaireshs
        JButton b1 = new JButton("Show Free Tiles");//koumpi emfanishs eleutherwn plakidiwn
        JButton b2 = new JButton("Rearrange Tiles");//koumpi anadiatakshs
        JButton b3 = new JButton("Show Available Tiles");//koumpi epiloghs plakidiwn
        JButton b4 = new JButton("Remove 2 Pairs of Tiles");//koumpi ekatharishs zeugous plakidiwn
        //parakatw ta megeth koumpiwn pou grafoun ta parapanw
        b5.setBounds(800, 30, 250, 50);
        b1.setBounds(800, 110, 250, 50);
        b2.setBounds(800, 190, 250, 50);
        b3.setBounds(800, 270, 250, 50);
        b4.setBounds(800, 350, 250, 50);

        b5.addActionListener(new ActionListener() {//otan pataw to koumpi anaireshs
            @Override
            public void actionPerformed(ActionEvent ae) {
                anairesh();//kalw sunarthsh anairesh
            }

        });

        b1.addActionListener(new ActionListener() {//otan pataw to koumpi emfanishs eleutherwn plakidiwn
            @Override
            public void actionPerformed(ActionEvent ae) {
                emf = true;//ginete true kai emfanizontai ola ta eleuthera plakidia
                diorthosh_border();//kalw sunarthsh diorthosh border
            }

        });

        b2.addActionListener(new ActionListener() {//otan pataw to koumpi anadiatakshs
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (anadiataksh > 0) {//an exw akoma voithia anadiatakshs
                    anadiataksh();//kalw sunarthsh anadiataksh
                }
            }

        });

        b3.addActionListener(new ActionListener() {//otan pataw to koumpi epiloghs plakidiwn
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (voithia > 0) {//an exw akoma voithia epiloghs plakidiwn
                    ArrayList<Integer> a = voithia();//pinakas 
                    //kanw ta plakidia me gyro gyro aspro border gia n deiksei tis epiloges digrafhs
                    kplak.get(a.get(0)).setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
                    kplak.get(a.get(1)).setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
                    voithia--;//afairw mia voithia

                }

                l2.setText(Integer.toString(voithia));//enhmerwsh boithiwn sta grafika
            }

        });

        b4.addActionListener(new ActionListener() {//otan pataw to koumpi ekatharishs zeugous plakidiwn
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (diagrafh > 0) {//an exw voithia diagrafhs
                    diagrafh();//kalw sunarthsh diagrafhs
                    diorthosh_border();
                    l3.setText(Integer.toString(diagrafh));//enhmerwsh grafikwn
                }
            }

        });
        //prosthetw ta koumpia
        panel.add(b5);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);

        //dhmiourgia label kathe koumpiou voithiwn kai bathomologias wste na grafei to score kai tis ypolipes voithies deksia apo ta koumpia tous 
        l1 = new JLabel(Integer.toString(anadiataksh));
        l1.setBounds(1100, 200, 30, 30);//megethois kai thesh label
        l2 = new JLabel(Integer.toString(voithia));
        l2.setBounds(1100, 280, 30, 30);//megethois kai thesh label
        l3 = new JLabel(Integer.toString(diagrafh));
        l3.setBounds(1100, 360, 30, 30);//megethois kai thesh label
        JLabel l4 = new JLabel("Score:");
        l4.setBounds(800, 420, 100, 50);//megethois kai thesh label
        l5 = new JLabel(Integer.toString(vathm));
        l5.setBounds(950, 430, 50, 30);//megethois kai thesh label

        //prosthikh label
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(l5);

        arxikopoihsh_menu();//kalw synarthsh arxikopoihsh_menu
        frame.setVisible(true);//einai edw giati prepei n ginoyn ola ta layouts kai meta na ginei visible
    }

    private void arxikopoihsh_menu() {
        JMenuBar menuBar = new JMenuBar();//ftiaxnw menu gia ta parakatw
        JMenu menu = new JMenu("Menu");//koumpi menu

        JMenuItem m1, m2, m3, m4, m5, m6;
        menuBar.add(menu);//prosthikh menu
        //ti epiloges tha emfanizei to menu otan to pataw
        m1 = new JMenuItem("New Game");
        m2 = new JMenuItem("Exit Game");
        m3 = new JMenuItem("Update Player info");
        m4 = new JMenuItem("Rules");
        m5 = new JMenuItem("Name,Difficulty");
        m6 = new JMenuItem("Exit Menu");
        //prosthikh 
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        menu.add(m5);
        menu.add(m6);

        m1.addActionListener(new ActionListener() {//an pathsw neo paixnidi
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();//kleinw palio frame
                new Main_Activities();
            }
        });

        m2.addActionListener(new ActionListener() {//an pathsw termatismo
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        m3.addActionListener(new ActionListener() {//an pathsw enhmerwsh stoixeiwn paixth
            @Override
            public void actionPerformed(ActionEvent e) {
                //diabazw apo to file poy swzw tous pextes kai score kai kanw modify ta names(na dw asfaleia pws)
            }
        });

        m4.addActionListener(new ActionListener() {//an pathsw kanones
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Working Directory = " + System.getProperty("user.dir"));
                File pdfFile = new File("rules.html");
                if (pdfFile.exists()) {

                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().open(pdfFile);
                        } catch (IOException ex) {
                            Logger.getLogger(Main_Activities.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Awt Desktop is not supported!");
                    }

                } else {
                    System.out.println("File is not exists!");
                }
            }
        });

        m5.addActionListener(new ActionListener() {//an pathsw onomata
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Player name : '" + name + "'");
                JOptionPane.showMessageDialog(null, "chosen difficulty : '" + dusk + "'");
            }
        });

        m6.addActionListener(new ActionListener() {//an pathsw eksodos
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.setJMenuBar(menuBar);//set to menubar
    }

    private void arxikopoihsh_koumpiwn() {
        kplak = new ArrayList<>();//dhmiurgia pinaka koumpiwn

        JButton temp;
        for (Tile p : plakidia) {//gia kathe  p pernoume sto temp (plakidio) me ton arihmo tou p
            temp = new JButton(Integer.toString(p.getArithmos()));
            if (p instanceof Character) {//an to p einai xarakthras tote ginetai kokkino
                temp.setBackground(Color.RED);
            } else if (p instanceof Circle) {//an to p einai kiklos tote ginetai cyan
                temp.setBackground(Color.CYAN);
            } else if (p instanceof Baboo) {//an to p einai baboo tote ginetai prasino
                temp.setBackground(Color.GREEN);
            } else if (p instanceof Season) {//an to p einai epoxh tote ginetai magenta
                temp.setBackground(Color.MAGENTA);
            } else if (p instanceof Loul) {//an to p einai louloudi tote ginetai kitrino
                temp.setBackground(Color.YELLOW);
            }
            temp.setSize(50, 50);
            temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //an den exoume epileksei kanena plakidio tote kanei to border tou prwtou plakidiou pou tha pathsoume mauro
                    if (epil1 == -1 && epil2 == -1) {
                        epil1 = kplak.indexOf(ae.getSource());//epistrefei sto plakidio 1 pou dialeksa to textfield
                        kplak.get(epil1).setBorder(epilegmeno);//me to epilegmeno ginetai mauro gyro gyro to plakidio

                    } else if (epil1 != -1 && epil2 == -1) {//an exoume epileksei to prwto plakidio alla oxi to deutero
                        if (diagrafh_plakidiwn(epil1, kplak.indexOf(ae.getSource()))) {//kaloume diagrafh_plakidiwn otan epilexthei to plakidio 2
                            diorthosh_border();//diorthwnoume ta border
                            kplak.get(epil1).setVisible(false);//den emfanizontai pleon ta diagramena plakidia
                            kplak.get(kplak.indexOf(ae.getSource())).setVisible(false);//den emfanizontai pleon ta diagramena plakidia
                            vathmologia(epil1);//kaloume bathomologia gia na eleksoume posous pontous tha parei analoga me ti plakidio afairese
                        } else {//an den digrafontai apla diorthwnei ta border
                            //kplak.get(epil1).setBorder(null);
                            diorthosh_border();
                        }

                        epil1 = -1;//kanei to epilogh 1 pali -1 gia thn epilogh plakidiou 1
                    }
                }

            });
            kplak.add(temp);

        }
    }

    private void vathmologia(int epil) {//synarthshs gia na brw posous pontous tha dinw 
        //elegxos ti eious plakidio einai auto pou dialeksa
        if (plakidia.get(epil) instanceof Character || plakidia.get(epil) instanceof Circle || plakidia.get(epil) instanceof Baboo) {
            vathm += 20;
        } else {
            vathm += 10;
        }
        l5.setText(Integer.toString(vathm));//enhmerwsh vathmologia sta grafika
    }

    private void arxikopoihsh_voithiwn() {
        if (paixths.getDuskolia().equals("Easy")) {//elegxos an h diskolia pou dialeksame einai eukolo 
            //dinei toses voithies
            anadiataksh = 3;
            voithia = 3;
            diagrafh = 3;
        } else if (paixths.getDuskolia().equals("Medium")) {//omoiws gia metrio epipedo
            anadiataksh = 2;
            voithia = 2;
            diagrafh = 2;
        } else {//omoiws gia diskolo epipedo
            anadiataksh = 1;
            voithia = 0;
            diagrafh = 1;
        }
    }

    private void arxikopoihsh_plakidiwn() {//voh8htikh sunarthsh gia arxikopoihsh plakidiwn
        plakidia = new ArrayList<>();
        //gemisma xaraktirwn,kuklwn,mpampou
        for (int i = 1; i <= 4; i++) {//4 fores
            for (int j = 1; j <= 9; j++) {//epi 9 =36 plakidia kathe kathgorias
                plakidia.add(new Character(j));
                plakidia.add(new Circle(j));
                plakidia.add(new Baboo(j));
            }
        }
        //gemisma epoxwn kai louloudiwn
        for (int i = 1; i <= 4; i++) {
            plakidia.add(new Season(i));
            plakidia.add(new Season(i));
            plakidia.add(new Loul(i));
            plakidia.add(new Loul(i));
        }

        //anakatema ths ArrayList
        Collections.shuffle(plakidia);//anakateuw ton arraylist me ta plakidia
        check_eleuthero();//elegxos an einai eleuthero

        //2h fash prosthikh sthn main lista kai setEleuthero
    }

//synarthsh typou boolean gia diagrafh plakidiwn||An ta diagrapsei epistrefei true alliws false
//exoume th thesh_plakidiou 1 a ,th thesh_plakidiou 1 b,th thesh_plakidiou 2 a,th thesh_plakidiou 2 b 
    private Boolean diagrafh_plakidiwn(int thesi_plak1, int thesi_plak2) {

//an ta plakidia me tis parapanw theseis einai idia h einai tupou epoxh h louloudia tote mporoun n diagrafoun ta plakidia.epishs elegxoume an einai eleuthera
//telos kanoume ta setDiathesimo twn plakidiwn autwn false wste na mhn epilexthoun ksana
        if (check(thesi_plak1, thesi_plak2) && plakidia.get(thesi_plak1).getEleuthero() && plakidia.get(thesi_plak2).getEleuthero()) {

            plakidia.get(thesi_plak1).setDiathesimo(false);
            plakidia.get(thesi_plak2).setDiathesimo(false);
            anairesh.set(0, thesi_plak1);
            anairesh.set(1, thesi_plak2);

            System.out.println("Deleted");
            check_eleuthero();
            return true;
        } else {
            System.out.println("Noth the same to be deleted");
            return false;
        }

    }
//grafw tis voithies 

    private void anadiataksh() {//voithia anadiatakshs
        ArrayList<Tile> temp = new ArrayList<>();//dhmiourgia pinaka temp wste na balw ola ta diathesima plakidia mesa tou
        for (Tile p : plakidia) {
            if (p.getDiathesimo()) {
                temp.add(p);
            }
        }
        Collections.shuffle(temp);//anakateuw ton neo pinaka
        for (int i = 0; i < plakidia.size(); i++) {
            //ekshgish
            if (plakidia.get(i).getDiathesimo()) {
                plakidia.set(i, temp.remove(0));
                kplak.get(i).setText(Integer.toString(plakidia.get(i).getArithmos()));
            }
        }

        anairesh.set(0, -1);
        anairesh.set(1, -1);

        check_eleuthero();//elegxw an einai eleuthero
        diorthosh_border();//diorthwnw border

        anadiataksh--;//afairw mia voithia anadiatakshs
        l1.setText(Integer.toString(anadiataksh));//enhmerwnw ta grafika
    }

    private ArrayList<Integer> voithia() {//voithia portashs gia epomenh epilogh
//psaxnei ena ena ola ta plakidia gia na brei dio idia,an einai eletheura kai an einai diathesima
        for (int i = 0; i < plakidia.size(); i++) {
            for (int j = i; j < plakidia.size(); j++) {
                if (check(i, j) && plakidia.get(i).getEleuthero() && plakidia.get(j).getEleuthero() && plakidia.get(i).getDiathesimo() && plakidia.get(j).getDiathesimo()) {
                    ArrayList<Integer> temp = new ArrayList<>();//ta vazoume se mia lista kai ta epistrefoume
                    temp.add(i);
                    temp.add(j);
                    return temp;//epistrefei ta diathesima

                }
            }
        }

        return null;
    }

    private boolean check(int thesi_plak1, int thesi_plak2) {//elegxei an einai idia
        Tile p1 = plakidia.get(thesi_plak1);
        Tile p2 = plakidia.get(thesi_plak2);

        if (thesi_plak1 == thesi_plak2) {//an exw epileksei to idio plakidio(px to 5 xarakthra me diplo click)
            return false;
        } //elegxw an einai diathesima,eleuthera,an exoun idio arithmo,an anoikoun sthn idia klash
        else if (p1.getDiathesimo() == p2.getDiathesimo() && p1.getEleuthero() == p2.getEleuthero() && p1.getArithmos() == p2.getArithmos() && p1.getClass().equals(p2.getClass())) {
            return true;
        } //elegxw an einai eleuthera kai an einai epoxh h louloudia
        else if (((p1 instanceof Season && p2 instanceof Season) || (p1 instanceof Loul && p2 instanceof Loul)) && p1.getEleuthero() == p2.getEleuthero()) {
            return true;
        } //an den einai tipota apo auta
        else {
            return false;
        }
    }

    private void diagrafh() {//voithia ekatharishs zeugous plakidiwn
        //1o zeugos diagrafh
        ArrayList<Integer> temp = voithia();//kaloume th sunarthsh voithias gia na broume idia plakidia
        diagrafh_plakidiwn(temp.get(0), temp.get(1));//kaloume diagrafh plakidiwn pou bghkame
        kplak.get(temp.get(0)).setVisible(false);//ta kanoume n mhn fenontai
        kplak.get(temp.get(1)).setVisible(false);//ta kanoume n mhn fenontai
        vathmologia(temp.get(0));//allazoume bathmologia

        //2o zeugos diagrafh omoia me to prwto
        if (voithia() != null) {
            temp = new ArrayList<>();
            temp = voithia();
            diagrafh_plakidiwn(temp.get(0), temp.get(1));
            kplak.get(temp.get(0)).setVisible(false);
            kplak.get(temp.get(1)).setVisible(false);
            vathmologia(temp.get(0));
        }

        diagrafh--;//afairw mia voithia diagrafhs
        anairesh.set(0, -1);
        anairesh.set(1, -1);
    }

    private boolean anairesh() {
        if (!(anairesh.get(0) == -1)) {//an mpei
            //kanoume pali diathesima kai visible ta plakidia pou kaname anairesh
            plakidia.get(anairesh.get(0)).setDiathesimo(true);
            plakidia.get(anairesh.get(1)).setDiathesimo(true);
            kplak.get(anairesh.get(0)).setVisible(true);
            kplak.get(anairesh.get(1)).setVisible(true);
            anairesh.set(0, -1);
            anairesh.set(1, -1);
            check_eleuthero();//elegxoume eleuthero
            diorthosh_border();//diorthwnoume ta border
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cant Undo before playing");
            return false;
        }
    }

    private void diorthosh_border() {//kaleitai kathe fora pou prepei n diorthwsoume ta border
        for (int i = 0; i < plakidia.size(); i++) {//gia ola ta plakidia
            kplak.get(i).setBorder(vasiko);
            if (plakidia.get(i).getEleuthero() && emf) {//elegxoume an einai eletheuro kai exoume pathsei voithia emfanishs diathesimwn plakidiwn
                kplak.get(i).setBorder(emfanish);//kaloume to xrwmatismo gia voithia emfanishs
            }
        }

        for (int i = 0; i < plakidia.size(); i++) {//gia ola ta plakidia
            if (plakidia.get(i) instanceof Character) {//an einai typou xarakthra ginetai kokkino border
                kplak.get(i).setBackground(Color.RED);
            } else if (plakidia.get(i) instanceof Circle) {//an einai typou kiklou ginetai cyan border
                kplak.get(i).setBackground(Color.CYAN);
            } else if (plakidia.get(i) instanceof Baboo) {//an einai typou baboo ginetai prasino border
                kplak.get(i).setBackground(Color.GREEN);
            } else if (plakidia.get(i) instanceof Season) {//an einai typou epoxh ginetai magenta border
                kplak.get(i).setBackground(Color.MAGENTA);
            } else if (plakidia.get(i) instanceof Loul) {//an einai typou louloudi ginetai kitrino border
                kplak.get(i).setBackground(Color.YELLOW);
            }
        }
    }

    private void check_eleuthero() {
        for (int i = 0; i < 124; i += 13) {//loop mexri ta 124 plakidia

            //me auth th for vriskoume to prwto diathesimo plakidio
            for (int j = i; j < (i + 13); j++) {
                if (j > 123) {//an perasoun ta 123 dn exoume alla plakidia
                    break;
                } else if (plakidia.get(j).getDiathesimo()) {
                    plakidia.get(j).setEleuthero(true);
                    //me auth th for vriskoume to prwto mh diathesimo plakidio
                    for (int k = j + 1; k < (i + 13); k++) {
                        if (k == 124) {//an einai to teleutaio plakidio to kanw eleuthero
                            plakidia.get(k - 1).setEleuthero(true);
                            break;
                        }
                        if (!plakidia.get(k).getDiathesimo()) {//an den einai diathesimo to plakidio tote kanoume to prohgoumeno tou eleuthero
                            plakidia.get(k - 1).setEleuthero(true);
                        } else if (k == (i + 12)) {//an einai to teleutaio plakidio ths grammhs to kanei eleuthero
                            plakidia.get(k).setEleuthero(true);
                        } else {
                            plakidia.get(k).setEleuthero(false);
                        }
                    }
                    break;
                }
            }
        }
    }
}

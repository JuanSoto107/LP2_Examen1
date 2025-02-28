import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.InputMismatchException;

public class FrmCajero extends JFrame {

    JComboBox cmbdenominador;
    String[] denominadores = new String[] {"100000", "50000", "20000", "10000", "5000", "2000", "1000", "500", "200", "100", "50"};
    String[] encabezados = new String[] {" Cantidad ", " Formato ", " Denominaciones "};
    String[] encabezados2 = new String[] {"Cantidades", "Denominaciones", "Total"};
    String[] formato = new String[] {"Billete", "Moneda"};
    DefaultTableModel dtm;
    JTextField txtcantidad, txtdevolver, txtresultado;
    JTable tbldevuelta, tblresultado;
    int[] numeradores = new int[] {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50};
    
    
    public FrmCajero() {
    
        setSize(700, 600);
        setTitle("Caja Registradora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
    
        JLabel lbldenominacion = new JLabel("Denominacion");
        lbldenominacion.setBounds(100, 80, 200, 10);
        getContentPane().add(lbldenominacion);
    
        JButton btncantidad = new JButton("Agregar cantidad");
        btncantidad.setBounds(60, 120, 150, 25);
        getContentPane().add(btncantidad);
    
        txtcantidad = new JTextField("");
        txtcantidad.setBounds(220, 120, 180, 25);
        getContentPane().add(txtcantidad);
    
        cmbdenominador = new JComboBox();
        cmbdenominador.setBounds(200, 75, 200, 20);
        DefaultComboBoxModel mdldenominadores = new DefaultComboBoxModel(denominadores);
        cmbdenominador.setModel(mdldenominadores);
        getContentPane().add(cmbdenominador);
    
        JLabel lbldevolver = new JLabel("Valor a devolver");
        lbldevolver.setBounds(70, 200, 200, 25);
        getContentPane().add(lbldevolver);
    
        txtdevolver = new JTextField("");
        txtdevolver.setBounds(200, 200, 200, 25);
        getContentPane().add(txtdevolver);
    
        JButton btndevolver = new JButton("Devolver");
        btndevolver.setBounds(410, 200, 150, 25);
        getContentPane().add(btndevolver);
    
        tbldevuelta = new JTable();
        JScrollPane sptbldevuelta = new JScrollPane(tbldevuelta);
        sptbldevuelta.setBounds(50, 250, 350, 200);
        getContentPane().add(sptbldevuelta);

        String[][] tabla = new String[denominadores.length][encabezados.length];
            
    
        DefaultTableModel dtm = new DefaultTableModel(tabla, encabezados);
        tbldevuelta.setModel(dtm);

        tblresultado = new JTable();
        JScrollPane spresultado = new JScrollPane(tblresultado);
        spresultado.setBounds(410, 250, 220, 200);
        getContentPane().add(spresultado);

        txtresultado = new JTextField("");
        txtresultado.setBounds(410, 470, 200, 25);
        getContentPane().add(txtresultado);
        txtresultado.setEnabled(false);

        String[][] tabla2 = new String[denominadores.length][encabezados2.length];

        DefaultTableModel dtm2 = new DefaultTableModel(tabla2, encabezados2);
        tblresultado.setModel(dtm2);
    
        btncantidad.addActionListener(new ActionListener() {
    
            public void actionPerformed(ActionEvent e) {
                agregarDato();
                    
            }
    
        });
    
        btndevolver.addActionListener(new ActionListener() {
    
            public void actionPerformed(ActionEvent e) {
                devueltaDato();
                    
            }
                
        });
    
    
    
    
    
    }

    private int[] cantidades = new int[100];
    private int totalcantidad = -1;
    private int[] denominador = new int[12];
    private int totaldenominador = -1;
    private String[] formatos = new String[12];
    private int posformato = -1;

    private void agregarDato() {
        
        int cantidad = Integer.parseInt(txtcantidad.getText());
        totalcantidad++;
        cantidades[totalcantidad] = cantidad;

        for(int i = 0; i < denominadores.length; i++) {
            String seleccion = denominadores[cmbdenominador.getSelectedIndex()];

            if(seleccion == denominadores[i]) {

                int seleccionado = Integer.parseInt(seleccion);
                totaldenominador++;
                denominador[totaldenominador] = seleccionado;
            }
        }


        insertarDato();
    }

    private void insertarDato() {

        String[][] strtabla = new String[denominadores.length][encabezados.length];
        int[][] inttabla = new int[denominadores.length][encabezados.length];
        String[][] strtabla2 = new String[denominadores.length][encabezados2.length];
        int[][] inttabla2 = new int[denominadores.length][encabezados2.length];

        for(int i = 0; i <= totalcantidad; i++) {

            inttabla[i][0] = cantidades[i];
            strtabla[i][0] = String.valueOf(inttabla[i][0]);

        }

        for(int j = 0; j <= totaldenominador; j++) {
            if(denominador[j] > numeradores[6]) {
        
            inttabla[j][2] = denominador[j];
            strtabla[j][2] = String.valueOf(inttabla[j][2]);
            strtabla[j][1] = formato[0];

            }

            if(denominador[j] <= numeradores[6]) {
        
                inttabla[j][2] = denominador[j];
                strtabla[j][2] = String.valueOf(inttabla[j][2]);
                strtabla[j][1] = formato[1];
    
                }
        }
        DefaultTableModel dtm = new DefaultTableModel(strtabla, encabezados);
        tbldevuelta.setModel(dtm);

    }


    private void devueltaDato() {

        int devuelta = Integer.parseInt(txtdevolver.getText());

        insertarResultado();
        

    }


    private void insertarResultado() {

        int devuelta = Integer.parseInt(txtdevolver.getText());

        String[][] strtabla2 = new String[denominadores.length][encabezados2.length];
        int[][] inttabla2 = new int[denominadores.length][encabezados2.length];
        int total = 0;
        int[] cantotal = new int[12];
        int sumatoria = -1;
        String xtotal = "";

        for(int i = 0; i < totalcantidad; i++) {

            inttabla2[i][0] = cantidades[i];
            strtabla2[i][0] = String.valueOf(inttabla2[i][0]);

        }

        for(int i = 0; i < totaldenominador; i++) {

            inttabla2[i][1] = denominador[i];
            strtabla2[i][1] = String.valueOf(inttabla2[i][1]);
            
        }

        for(int i = 0; i < totaldenominador; i++) {

            inttabla2[i][2] = inttabla2[i][0] * inttabla2[i][1];
            
        }



        for(int i = 0; i < denominadores.length; i++) {


            if(inttabla2[i][2] <= devuelta) {
                while(total <= devuelta) {
                    total += inttabla2[i][2];
                    break;
                }
                if(total > devuelta) {
                    while(total > devuelta) {
                        inttabla2[i][2] = inttabla2[i][2] - inttabla2[i][1];
                        inttabla2[i][0] -= 1;
                        total -= inttabla2[i][1];
                    }
                }

            }

            strtabla2[i][0] = String.valueOf(inttabla2[i][0]);
            strtabla2[i][2] = String.valueOf(inttabla2[i][2]);

        }


        xtotal = String.valueOf(total);
        txtresultado.setText(xtotal);


        DefaultTableModel dtm2 = new DefaultTableModel(strtabla2, encabezados2);
        tblresultado.setModel(dtm2);

        

    }
        
}
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class FrmCajero extends JFrame {

    JComboBox cmbdenominador;
    String[] denominadores = new String[] {"100000", "50000", "20000", "10000", "5000", "2000", "1000", "500", "200", "100", "50"};
    String[] encabezados = new String[] {" Cantidad ", " Formato ", " Denominaciones "};
    String[] encabezados2 = new String[] {"Cantidades", "Denominaciones"};
    String[] formato = new String[] {"Billete", "Moneda"};
    DefaultTableModel dtm;
    JTextField txtcantidad, txtdevolver;
    JTable tbldevuelta, tblresultado;
    int[] numeradores = new int[] {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50};
    
    
    public FrmCajero() {
    
        setSize(600, 600);
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
        spresultado.setBounds(410, 250, 160, 200);
        getContentPane().add(spresultado);

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
        int[] devueltacantidad = new int[12];
        int[] devueltadenominador = new int[12];
        int sumcantidad = -1;
        int sumdenom = -1;

        String[][] strtabla2 = new String[denominadores.length][encabezados2.length];
        int[][] inttabla2 = new int[denominadores.length][encabezados2.length];

        while(devuelta >= 0) {

            for(int i = 0; i < numeradores.length; i++) {
                if(devuelta >= numeradores[i]) {
                    if(devuelta >= 0) {
                        while(devuelta >= 0) {
                            if(cantidades[i] > 0) {
                                sumdenom++;
                                if(denominador[i] > devuelta) {
                                    
                                    denominador[i] *= cantidades[i - 1];
                                    devueltadenominador[sumdenom] = denominador[i];
                                    sumcantidad++;
                                    devueltacantidad[sumcantidad] = cantidades[i];
                                    cantidades[i]--;
                                    
                                    devuelta -= denominador[i];

                                }


                            }
                        }
                    }
                }
            }
            if(devuelta == 0) {
                break;
            }

        }

    }
    
}
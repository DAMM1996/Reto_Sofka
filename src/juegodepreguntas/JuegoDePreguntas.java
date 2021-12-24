/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodepreguntas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Albeiro
 */
public class JuegoDePreguntas extends javax.swing.JFrame {
    
    static String urlBD = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQOnc_H7Db5tkkBQ486G667hRhB-sjstb-6YTkYyMFwX9LMTG_ppGDeEv_-ukSpMhRMxtsfrvhA7cYo/pub?output=tsv";
    
    static String textoBaseDePreguntas = LeerArchivo_ASCII(urlBD);
    
    static String[] renglones = textoBaseDePreguntas.split("\n");
    static int cantidadDePreguntas = renglones.length;
    
    static String[][]baseDePreguntas = new String [cantidadDePreguntas][8];
    
    String[] preguntaEscogida;
    String pregunta;
    String respuesta;
    String categoria;
    String nivelDificultad;
    int valorPregunta=0;
    int acumulado=0;
    
    ArrayList<String> opciones = new ArrayList<>();
    static ArrayList<String[]> capitales = new ArrayList<>();
    static ArrayList<String[]> matematicas = new ArrayList<>();
    static ArrayList<String[]> tablaPeriodica = new ArrayList<>();
    static ArrayList<String[]> programacionSoftware = new ArrayList<>();
    static ArrayList<String[]> cuerpoHumano = new ArrayList<>();
    
    
    int n_pregunta = 1;
    
    public static void main(String args[]) {
        
        /*guardarEnExcel();*/
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        
        } catch (Exception ex) {
            
        }for(int i=0; i<renglones.length;i++ ){
            String renglon = renglones[i];
            baseDePreguntas[i] = renglon.split("\t"); 
            
            
        }
        cargarPreguntasPorCategoria(baseDePreguntas);
                new JuegoDePreguntas();
            }
    
    
    public void escogerPregunta(int n){
        
        if(n==1){
                preguntaEscogida = escogerRandom(capitales);
        }if(n==2){
                preguntaEscogida = escogerRandom(tablaPeriodica);
        }if(n==3){
                preguntaEscogida = escogerRandom(matematicas);
        }if(n==4){
                preguntaEscogida = escogerRandom(programacionSoftware);
        }if(n==5){
                preguntaEscogida = escogerRandom(cuerpoHumano);
        }if(n==6){
            
            JOptionPane.showMessageDialog(this, "Has ganado, felicitaciones");
                    this.dispose();
            FinDelJuego finDelJuegoForm = new FinDelJuego(acumulado);
            finDelJuegoForm.setVisible(true);
                    ;
            
        }else{
            
        
        
        
        pregunta = preguntaEscogida[0];
        opciones.clear();
        for (int i = 1; i < 5; i++) {
            opciones.add(preguntaEscogida[i]);
                    respuesta = preguntaEscogida[1];
        categoria = preguntaEscogida[5];
        nivelDificultad = preguntaEscogida[6];
        
        

            
        }
        
        for (int i = 0; i < 4; i++) {
            Collections.shuffle(opciones);
        }
     }
        
    }
    public void mostrarPregunta(){
        jLabel1.setText(pregunta);
        jButton1.setText(opciones.get(0));
        jButton2.setText(opciones.get(1));
        jButton3.setText(opciones.get(2));
        jButton4.setText(opciones.get(3));
    }
    
     void escogerRespuesta(int n){
         if(opciones.get(n).equals(respuesta)){
             JOptionPane.showMessageDialog(
                     this,
                     "Su respuesta es correcta",
                     "Muy bien :) ",
                     JOptionPane.INFORMATION_MESSAGE )
                     ;
             acumulado= acumulado + Integer.parseInt(preguntaEscogida[7]);
             
             jugar();
         }else{
             JOptionPane.showMessageDialog(
                     this,
                     "Su respuesta es incorrecta, la respuesta correcta es: " 
                             + respuesta ,
                     "Lamentablemente has fallado :( ",
                     JOptionPane.ERROR_MESSAGE 
                );
             this.dispose();
            FinDelJuego finDelJuegoForm = new FinDelJuego(0);
            finDelJuegoForm.setVisible(true);
         }
     }
    
    public void jugar(){
        if(n_pregunta==cantidadDePreguntas){
            JOptionPane.showMessageDialog(
                    this, 
                    "Juego terminado",
                    "Muy bien :)",
                    JOptionPane.PLAIN_MESSAGE
                 );
            
            
        }
        escogerPregunta(n_pregunta);
        
        mostrarPregunta();
        if(n_pregunta<6){
            n_pregunta ++;
        }
        
        
    }

  
    public JuegoDePreguntas() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setVisible(true);
         jugar();
    }
    
     public static String LeerArchivo_ASCII(String ruta)  {
         try {
             if (ruta == null) {
            throw new RuntimeException("Error, la URL de lectura no puede ser nula");
        }
        URL url = new URL(ruta);      
        URLConnection conexion = url.openConnection();
        InputStreamReader isr = new InputStreamReader(conexion.getInputStream());
        return LeerArchivo_ASCII(isr);
         } catch (Exception e) {
             System.out.println("No hay conexion a internet la base de datos no pudo ser cargada");
             System.exit(0);
         }
         return "";
    }

    

    public static String LeerArchivo_ASCII(Reader reader) throws Exception {
        BufferedReader br = new BufferedReader(reader);
        String texto = "";
        String linea;
        boolean primerRenglon = true;
        while ((linea = br.readLine()) != null) {
            if (primerRenglon) {
                primerRenglon = false;
            } else {
                texto += "\n";
            }
            texto += linea;
        }
        reader.close();
        br.close();
        return texto;
    }
    
    public static void  cargarPreguntasPorCategoria( String[][]baseDePreguntas){
        for(int i=0; i<renglones.length;i++){
            if(baseDePreguntas[i][6].equals("1")){
                capitales.add(baseDePreguntas[i]);
                
            }if(baseDePreguntas[i][6].equals("2")){
                tablaPeriodica.add(baseDePreguntas[i]);
                
            }if(baseDePreguntas[i][6].equals("3")){
                matematicas.add(baseDePreguntas[i]);
                
            }if(baseDePreguntas[i][6].equals("4")){
                programacionSoftware.add(baseDePreguntas[i]);
                
            }if(baseDePreguntas[i][6].equals("5")){
                cuerpoHumano.add(baseDePreguntas[i]);
            }
            
        }
        
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pregunta con imagen");

        jButton1.setText("Opcion 1");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Opcion 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Opcion 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Opcion 4");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Retirarse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(123, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addComponent(jButton5))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        escogerRespuesta(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        escogerRespuesta(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        escogerRespuesta(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        escogerRespuesta(3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
            FinDelJuego finDelJuegoForm = new FinDelJuego(acumulado);
            finDelJuegoForm.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
           
               
        
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private String[] escogerRandom(ArrayList<String[]> capitales) {
        int aleatorio=0;
        
        
        aleatorio = (int) (Math.random() * 4);
        return capitales.get(aleatorio);
    }
    
    public static void guardarEnExcel(){
        Workbook book = new HSSFWorkbook();
        Sheet sheet = (Sheet) book.createSheet("Datos jugador");
        
        try {
            FileOutputStream fileout = new FileOutputStream("Datos jugador.xls");
            try {
                book.write(fileout);
                fileout.close();
            } catch (IOException ex) {
                Logger.getLogger(JuegoDePreguntas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JuegoDePreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

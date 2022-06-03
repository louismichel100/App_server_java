/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_dav;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author NGONO
 */
public class App_dav extends Application {
    
    private String nom_dep;
    private Button p_1 = new Button("Ajouter une filière");
    private MenuButton p_2 = new MenuButton("Editer une filière");
    private GridPane grid1 = new GridPane();
   
     
     
     final int port = 8888;
    private Socket s;

    private OutputStream out;
    
    private InputStream in;

    private PrintWriter writer;
    
    private BufferedReader reader;
    
    @Override
    public void start(Stage primaryStage) {
       
        primaryStage.setTitle(nom_dep.toUpperCase());
         //primaryStage.initStyle(StageStyle.UTILITY);
         primaryStage.setResizable(false);
        
        
        ///////////////set font
        
        grid1.setTranslateX(10);
        grid1.setTranslateY(100);
        grid1.setHgap(10);
        grid1.setVgap(10);
        
        grid1.add(p_1, 0, 0);
        grid1.add(p_2, 1, 0);
        //grid1.add(p_3, 0, 3);
        
        
        
        
        
        
        
        
        p_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                String cde = JOptionPane.showInputDialog(null, "Entrer votre mot de passe");
                if(cde.equals("jackson")){
                String nom_f = JOptionPane.showInputDialog(null, "Entrer le nom de la filière à inserer");
                
                if(nom_f.length() != 0){
                
                try{
    
                        int num_serv = 202;
  

                        s = new Socket("localhost", port);
                        out = s.getOutputStream();
                        in = s.getInputStream();
                        
                        reader = new BufferedReader(new InputStreamReader(in));
                        writer = new PrintWriter(out);

                        writer.println(num_serv);
                        writer.flush();
                        writer.println(nom_f);
                        writer.flush();
                        writer.println(nom_dep);
                        writer.flush();
                        
                       
                        
                        JOptionPane.showMessageDialog(null, "Ajout de la filière réussie");
                        
                           App_dav pw = new App_dav();
                           pw.start(new Stage());
                           primaryStage.close(); 
                        
                        
                        
                        
                  s.close();
                    } catch (Exception p){
                        JOptionPane.showMessageDialog(null, "problème de connexion à la base de donnée");
                        
                          }
                
                } else JOptionPane.showMessageDialog(null, "Veuillez saisir un nom correct");
                
                } else JOptionPane.showMessageDialog(null, "Mot de passe érroné, action annulée");
            }
        });
        
        
        
        try{
    
                        int num_serv = 203;
  

                        s = new Socket("localhost", port);
                        out = s.getOutputStream();
                        in = s.getInputStream();
                        
                        reader = new BufferedReader(new InputStreamReader(in));
                        writer = new PrintWriter(out);

                        writer.println(num_serv);
                        writer.flush();
                        writer.println(nom_dep);
                        writer.flush();
                        
                        int t = Integer.parseInt(reader.readLine());
                        for(int w=0; w<t; w++){
                            String kh = reader.readLine();
                            MenuItem a = new MenuItem(kh.toUpperCase());
                            p_2.getItems().add(a);
                            
                            a.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //filiere2 gh = new filiere2(a.getText(), nom_dep);
                                    //gh.start(new Stage());
                                    //primaryStage.close();
                                    System.out.println("création de la table réussie, un pas vers la gloire");
                                }
                            });
                            
                        }
                            
                            
                               
                            
                            
                 s.close();
                    } catch (Exception p){
                          p.printStackTrace();
                          System.out.println("erreur erreur");
                          }
        
        
        
        
        
         Group root = new Group();
        root.getChildren().addAll(grid1);
        Scene scene = new Scene(root, 250, 300, Color.BROWN);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

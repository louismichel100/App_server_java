/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_dav;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.yield;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NGONO
 */
public class serveur {
    
 public static void main(String [] args) throws IOException
{
         
     
   
final int SQUARE_PORT = 8888;
Socket s;
  
ServerSocket server = new ServerSocket(SQUARE_PORT);


while(true)
{
s = server.accept();
Service_1 service = new Service_1(s);
service.start();
}
}

}
    




class Service_1 extends Thread
{
private Socket s;
private int ok;
private String ligne, ligne_1, code, nomd, nomc;
private String type_ec, nom_ec, logo_ec, pho_ec, type_pay;
private Connection con;
private SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
private SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
private java.sql.Date sqldate;
private String hp, hp1;
//private sender mailler = new sender();




private String ind_class, nom_class, ind_mat, nom_tranche, date_limit, montant;



private int okk;

private Connection con_miki() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url, user, motdepasse;
        url="jdbc:mysql://localhost:3306/";
        user="root";
        motdepasse="";
        Connection con = DriverManager.getConnection(url+"pro_m", user, motdepasse);
        con.setAutoCommit(false);
        return con;
    }

private String func_statut_ecole(){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `type` FROM `ecole_infos`");
            while(res.next()){
                String s = res.getString("type");
                return s;
                
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
}



private int getid_con(String y){
    
    int s = 3;
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `connexion`.`id_con` FROM `samac`.`connexion` WHERE `connexion`.`nom_user` = '"+y+"'");
            while(res.next()){
                s = res.getInt("id_con");
                return s;
                
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    System.out.println(s);
    return s;
}

private String func_test_admin(){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `mat_p` FROM `admin`");
            while(res.next()){
                String s = res.getString("mat_p");
                return s;
                
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
}

private String func_mdp_ou_user(String cni){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `nom_uti_p`FROM `personnel` WHERE personnel.n_cni_p = '"+cni+"'");
            while(res.next()){
                String s = res.getString("nom_uti_p");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String func_mdp_ou_mdp(String cni){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `mdp_p`FROM `personnel` WHERE personnel.n_cni_p = '"+cni+"'");
            while(res.next()){
                String s = res.getString("mdp_p");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String func_con_mdp(String user){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `mdp_user` FROM `connexion` WHERE connexion.nom_user = '"+user+"'");
            while(res.next()){
                String s = res.getString("mdp_user");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String func_p1_username(String user){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `prenom_p`FROM `personnel` WHERE personnel.nom_uti_p = '"+user+"'");
            while(res.next()){
                String s = res.getString("prenom_p");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private void func_add_depart(String nom_tab, String nom_auteur, String titre){
        
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (nom_dep, discipline) VALUES ('"+nom_auteur+"', '"+titre+"')");
            int low_2 = pstat.executeUpdate();
            if (low_2==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }    
    }

private String func_user_admin(){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `nom_uti_p` FROM `personnel`, `admin` WHERE personnel.mat_p = admin.mat_p");
            while(res.next()){
                String s = res.getString("nom_uti_p");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String func_user_with_statut(String qs){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `nom_uti_p` FROM `personnel`, `admin` WHERE personnel.statut = '"+qs+"'");
            while(res.next()){
                String s = res.getString("nom_uti_p");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String func_user_avc_mat(String mat){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `nom_uti_p` FROM `personnel` WHERE personnel.mat_p = '"+mat+"'");
            while(res.next()){
                String s = res.getString("nom_uti_p");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String func_code_ap_avc_mat(String mat){
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `code_ap` FROM `ap` WHERE ap.mat_p = '"+mat+"'");
            while(res.next()){
                String s = res.getString("code_ap");
                return s; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return "";
    
}

private String [] get_inf_id_med(int idx){
    String tp [] = new String[10];
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            
            
            ResultSet res = stmt.executeQuery("SELECT * FROM `insc_med` WHERE insc_med.id_insc_med = "+idx+" ");
            while(res.next()){
                String nom = res.getString("nom_med");
                String pnom = res.getString("prenom_med");
                String dat = res.getString("date_naiss");
                String spe = res.getString("specialite_ins");
                String grad = res.getString("grade_ins");
                String tel = res.getString("tel_ins");
                String cni = res.getString("cni_med");
                String mail = res.getString("mail_ins");
                String ville = res.getString("ville_quartier");
                String eval = res.getString("evaluation");
                
                tp[0] = nom;
                tp[1] = pnom;
                tp[2] = dat;
                tp[3] = cni;
                tp[4] = tel;
                tp[5] = mail;
                tp[6] = ville;
                tp[7] = spe;
                tp[8] = grad;
                tp[9] = eval;
                
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return tp;
    
}


private String [] get_inf_id_mem(int idx){
    String tp [] = new String[9];
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            
            
            ResultSet res = stmt.executeQuery("SELECT * FROM `insc_mem` WHERE insc_mem.id_ins = "+idx+" ");
            while(res.next()){
                String nom = res.getString("nom_ins");
                String pnom = res.getString("prenom_ins");
                String dat = res.getString("date_naiss_ins");
                String mal = res.getString("maladie");
                String tel = res.getString("tel_ins");
                String cni = res.getString("cni_mem");
                String mail = res.getString("mail_ins");
                String ville = res.getString("ville_quartier");
                String eval = res.getString("evaluation");
                
                tp[0] = nom;
                tp[1] = pnom;
                tp[2] = dat;
                tp[3] = cni;
                tp[4] = tel;
                tp[5] = mail;
                tp[6] = ville;
                tp[7] = mal;
                tp[8] = eval;
                
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return tp;
    
}


private String [] get_usermail_mem(){
    String tp [];
    int h = 1;
    int n = 0;
    int i = 0;
    
    
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            
            ResultSet res = stmt.executeQuery("SELECT * FROM `insc_mem` WHERE insc_mem.statut_ins = "+h+" ");
            while(res.next()){
                n++;   
            }
    } catch(Exception t){t.printStackTrace();}     
    
            tp = new String [n+n];   
            try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            
            
            ResultSet ress = stmt.executeQuery("SELECT * FROM `insc_mem` WHERE insc_mem.statut_ins = "+h+" ");
            while(ress.next()){
                String nom = ress.getString("login");
                String mail = ress.getString("mail_ins");
                tp[i]= nom;
                tp[i+1] = mail;
                i++;
                i++;    
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return tp;
    
}

private String [] get_usermail_med(){
    String [] tpp;
    int h = 1;
    int n = 0;
    int i = 0;
    
    
    try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            
            ResultSet res = stmt.executeQuery("SELECT * FROM medecin ");
            while(res.next()){
                n++;   
            }
            } catch(Exception eee){
            eee.printStackTrace();
        }             
            tpp = new String [n+n];  
            
            try{
                
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            
            ResultSet ress = stmt.executeQuery("SELECT * FROM medecin ");
            while(ress.next()){
                String nom = ress.getString("user_med");
                String mail = ress.getString("mail_med");
                System.out.println(nom);
                System.out.println(mail);
                
                tpp[i]= nom;
                tpp[i+1] = mail;
                i++;
                i++;    
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        }
    return tpp;
    
}


private int get_con(String qs){
    
    
   try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `id_con` FROM `connexion`WHERE connexion.nom_user = '"+qs+"'");
            while(res.next()){
                int ss = res.getInt("id_con");
                return ss; 
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
        } 
   
    
    return 0;
}

private void Insertligntable_22(String nom_tab, String nom_auteur){
        
    
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (nom_utilisateur) VALUES ('"+nom_auteur+"')");
            int low_2 = pstat.executeUpdate();
            if (low_2==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }

private void Delete_con(String titre){
        
    int uio = get_con(titre);
     try{
            Connection con = con_miki();
            PreparedStatement pstat = con.prepareStatement("DELETE FROM `rayappschool_test`.`connexion` WHERE `connexion`.`numero_con` = '"+uio+"' ");
            con.setAutoCommit(false); 
            
            
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("suppréssion des ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();           
     }catch(Exception e){
         e.printStackTrace();
     }
}

private String [] func_all_mat(){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT DISTINCT m.nom_mat FROM matiere m");
            while(res.next()){
                n++;
            }
            
            //if(n == 0) return null;     
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("Miki");
            
        }
        tabb = new String[n];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT DISTINCT m.nom_mat FROM matiere m ORDER BY `nom_mat`");
            while(res.next()){
                String s = res.getString("nom_mat");
                tabb[i] = s;
                i++;
            }            
        }catch(Exception e){
            //e.printStackTrace();
           System.out.println("leila");
        }
        
       if(n==0) return null;
       return tabb;
        
    }

private String [] func_mat_dep(){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.discipline FROM departements m");
            while(res.next()){
                n++;
            }
            
            //if(n == 0) return null;     
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("Miki");
            
        }
        tabb = new String[n];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.discipline FROM departements m");
            while(res.next()){
                String s = res.getString("discipline");
                tabb[i] = s;
                i++;
            }            
        }catch(Exception e){
            //e.printStackTrace();
           System.out.println("leila");
        }
        
       if(n==0) return null;
       
       return tabb;
        
    }


private String [] func_all_depart(){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT DISTINCT m.nom_dep FROM departements m ORDER BY `nom_dep`");
            while(res.next()){
                n++;
            }
            
            //if(n == 0) return null;     
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("Miki");
            
        }
        tabb = new String[n];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT DISTINCT m.nom_dep FROM departements m ORDER BY `nom_dep`");
            while(res.next()){
                String s = res.getString("nom_dep");
                tabb[i] = s;
                i++;
            }            
        }catch(Exception e){
            //e.printStackTrace();
           System.out.println("leila");
        }
        
       if(n==0) return null;
       return tabb;
        
    }


///////////

 public String set_mdp(){
    
    char [] tab_1 = new char [26];
    int [] tab_2 = new int [10];
    
    
   
    char k;
    
    
    
    int a,b,c,d, i=0, p=0, e, okk = 0;
    
    StringBuilder bu = new StringBuilder();

        Random random = ThreadLocalRandom.current();
    
        
        for (char q = 'A'; q <= 'Z'; q++) {
            tab_1[i] = q; 
            i++;    
        }
        
        for (int l = 0; l < 10; l++) {
            tab_2[l] = p;
            p++;
        }
        
        
     e = tab_2[random.nextInt(tab_2.length)];
     k = tab_1[random.nextInt(tab_1.length)];
     a = tab_2[random.nextInt(tab_2.length)];
     b = tab_2[random.nextInt(tab_2.length)];
     c = tab_2[random.nextInt(tab_2.length)];
     d = tab_2[random.nextInt(tab_2.length)];
     
     bu.append(e);
     bu.append(a);
     bu.append(k);
     bu.append(b);
     bu.append(c);
     bu.append(d);
     
     String mat = bu.toString();
     return mat;
     
     
    
}
 
 
 public String set_log(){
    
    char [] tab_1 = new char [26];
    int [] tab_2 = new int [10];
    
    
   
    char k;
    
    
    
    int a,b,c,d, i=0, p=0, e, okk = 0;
    
    StringBuilder bu = new StringBuilder();

        Random random = ThreadLocalRandom.current();
    
        
        for (char q = 'A'; q <= 'Z'; q++) {
            tab_1[i] = q; 
            i++;    
        }
        
        for (int l = 0; l < 10; l++) {
            tab_2[l] = p;
            p++;
        }
        
        
     e = tab_2[random.nextInt(tab_2.length)];
     k = tab_1[random.nextInt(tab_1.length)];
     a = tab_2[random.nextInt(tab_2.length)];
     b = tab_2[random.nextInt(tab_2.length)];
     c = tab_2[random.nextInt(tab_2.length)];
     d = tab_2[random.nextInt(tab_2.length)];
     
     bu.append(e);
     bu.append(a);
     bu.append(k);
     bu.append(b);
     bu.append(c);
     bu.append(d);
     
     String mat = bu.toString();
     return mat;
     
     
    
}


/// fonctions de func_ray


private void Createtable(String nom_table, String col1, String col2, String col3){
        
        try{
            con = con_miki();       
            PreparedStatement pstat = con.prepareStatement("CREATE TABLE "+nom_table+"('"+col1+"' VARCHAR(30) PRIMARY KEY, "+col2+" BIGINT, '"+col3+"' DATE)");
            int ok = pstat.executeUpdate();
            if (ok==0) {System.out.println("création de la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
        } catch (Exception e){
            e.printStackTrace();
            
            
        }
        
    }
    
    private void Createtable2(String nom_table, String col1, String col2, String col3){
        
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("CREATE TABLE "+nom_table+"("+col1+" VARCHAR(30) PRIMARY KEY, "+col2+" BIGINT, "+col3+" DATE)");
            int ok = pstat.executeUpdate();
            if (ok==0) {System.out.println("création de la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
        } catch (Exception e){
            e.printStackTrace();
            
            
        }
        
    }
    
    private void Create_dep_cons(String nom_table, String col1, String col2, String col3){
        
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("CREATE TABLE "+nom_table+"("+col1+" DATE PRIMARY KEY, "+col2+" VARCHAR(30) NOT NULL, "+col3+" VARCHAR(60) NOT NULL )");
            int okm = pstat.executeUpdate();
            if (okm==0) {System.out.println("création de la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
        } catch (Exception e){
            e.printStackTrace();
            
            
        }
        
    }
    
    private String selection3 (String a){
        
        
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.ind_mat FROM classe m WHERE m.nom_class = '"+a+"'");
            res.next();
            String as = res.getString("ind_mat");
            return as;
        }catch(Exception e){
            e.printStackTrace();
            
            return null;
        }
               
    }
    
    private int get_user_con (){
        
        
        int m = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM connexion m WHERE m.tempon = 1 ");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return m;
               
    }
    
    private String [] func_dis_one_dep (String a){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.discipline FROM departements m WHERE m.nom_dep = '"+a+"'");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.discipline FROM departements m WHERE m.nom_dep = '"+a+"'");
            while(res.next()){
               String as = res.getString("discipline");
               tap[v] = as;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tap;
               
    }  
    
    private String [] func_matr_one_dep (String a){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM depart_ens m WHERE m.nom_depart = '"+a+"'");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM depart_ens m WHERE m.nom_depart = '"+a+"'");
            while(res.next()){
               String as = res.getString("mat_p");
               tap[v] = as;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(m == 0) return null;
        else  return tap;
               
    }
    
    
    private String [] func_matr_all_ens (){
        
        String [] tap;
        int m = 0, v = 0;
        String pm = "ENSEIGNANT";
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM personnel m WHERE m.statut = '"+pm+"' ORDER BY `nom_p`");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM personnel m WHERE m.statut = '"+pm+"' ORDER BY `nom_p`");
            while(res.next()){
               String as = res.getString("mat_p");
               tap[v] = as;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(m == 0) return null;
        else  return tap;
               
    }
    
    private String [] func_matr_all_ap(){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT mat_p FROM ap");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT mat_p FROM ap");
            while(res.next()){
               String as = res.getString("mat_p");
               tap[v] = as;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(m == 0) return null;
        else  return tap;
               
    }
    
    private String [] func_matr_all_dep(){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT mat_p FROM depart_ens");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT mat_p FROM depart_ens");
            while(res.next()){
               String as = res.getString("mat_p");
               tap[v] = as;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(m == 0) return null;
        else  return tap;
               
    }
    
    private String  func_ap_one_dep (String a){
        
       
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM ap m WHERE m.nom_depart = '"+a+"'");
            while(res.next()){
            String as = res.getString("mat_p");
            return as;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
       return null;
               
    }
    
    
    private String [] func_cons_one_dep (String a){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.date_cons, m.ordre_jour, m.fich_cons FROM '"+a+"' m");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.date_cons, m.ordre_jour, m.fich_cons FROM '"+a+"' m");
            while(res.next()){
               String as = res.getString("date_cons");
               String ass = res.getString("ordre_jour");
               String asss = res.getString("fich_cons");
               tap[v] = as;
               tap[v+1] = ass;
               tap[v+2] = asss;
               v++;
               v++;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(m == 0) return null;
        else return tap;
               
    }
    
         private String [] func_one_cons (String a, Date b){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.date_cons, m.ordre_jour, m.fich_cons FROM '"+a+"' m WHERE m.date_cons = '"+b+"'");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tap = new String[3];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.date_cons, m.ordre_jour, m.fich_cons FROM '"+a+"' m WHERE m.date_cons = '"+b+"'");
            while(res.next()){
               String as = res.getString("date_cons");
               String ass = res.getString("ordre_jour");
               String asss = res.getString("fich_cons");
               tap[v] = as;
               tap[v+1] = ass;
               tap[v+2] = asss;
              
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(m == 0) return null;
        else return tap;
               
    }
     
    
    private String [] func_ens_one_dep (String a){
        
        String [] tap;
        int m = 0, v = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM depart_ens m WHERE m.nom_depart = '"+a+"'");
            while(res.next()){
                m++;
            //String as = res.getString("nom_mat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        tap = new String[m];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.mat_p FROM depart_ens m WHERE m.nom_depart = '"+a+"'");
            while(res.next()){
               String as = res.getString("mat_p");
               tap[v] = as;
               v++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if(m == 0) return null;
        else return tap;
               
    }
    
     private int selection4 (String b, String a){
        
 
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_mat FROM classe c, matiere m WHERE m.nom_mat = '"+a+"' AND m.ind_mat = c.ind_mat AND c.nom_class = '"+b+"' ");
            
            if(res.next()){
                return 1;
               
            }
            else{
                
                return 0;
                
                
            }
           
        }catch(Exception e){
            e.printStackTrace();
            
           
        }
        
        return 0;
               
    }
    
    private String [] selection1(String a, String b){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `"+a+"` FROM `"+b+"`");
            while(res.next()){
                String s = res.getString(a);
                n++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tabb = new String[n];
  
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT `"+a+"` FROM `"+b+"`");
            while(res.next()){
                String s = res.getString(a);
                tabb[i] = s;
                i++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
       return tabb;
        
    }
    
    private int [] get_id_ins_wait_med(){
        
        int [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.id_insc_med FROM insc_med m WHERE m.statut_ins = 0");
            while(res.next()){
                n++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        tabb = new int[n];
  
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.id_insc_med FROM insc_med m WHERE m.statut_ins = 0");
            while(res.next()){
                int s = res.getInt("id_insc_med");
                tabb[i] = s;
                i++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
       if(n == 0) return null;
        
       return tabb;
        
    }
    
    
    private String [] get_nd(){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_dep FROM departement m");
            while(res.next()){
                n++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        tabb = new String[n];
  
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_dep FROM departement m");
            while(res.next()){
                String s = res.getString("nom_dep");
                tabb[i] = s;
                i++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
       if(n == 0) return null;
        
       return tabb;
        
    }
    
    
    private String [] get_emploi(String a){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.code_m FROM emploi_temps m WHERE m.code_emp = '"+a+"' ");
            while(res.next()){
                n++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        tabb = new String[6*n];
  
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.code_m , m.elt_m , m.jour , m.heure , m.salle , m.ens FROM emploi_temps m WHERE m.code_emp = '"+a+"' ");
            while(res.next()){
                String c = res.getString("code_m");
                String e = res.getString("elt_m");
                String j = res.getString("jour");
                String h = res.getString("heure");
                String sa = res.getString("salle");
                String ens = res.getString("ens");
                
                
                tabb[i] = c;
                tabb[i+1] = e;
                tabb[i+2] = j;
                tabb[i+3] = h;
                tabb[i+4] = sa;
                tabb[i+5] = ens;
                i = i + 6;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
       if(n == 0) return null;
        
       return tabb;
        
    }
    
    private String [] get_test(){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.jour , m.heure , m.salle FROM emploi_temps m ");
            while(res.next()){
                n++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        tabb = new String[3*n];
  
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.jour , m.heure , m.salle FROM emploi_temps m ");
            while(res.next()){
                String c = res.getString("jour");
                String e = res.getString("heure");
                String j = res.getString("salle");
                
                
                
                tabb[i] = c;
                tabb[i+1] = e;
                tabb[i+2] = j;
                
                i = i + 3;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
       if(n == 0) return null;
        
       return tabb;
        
    }
    
    
    private int  get_id_mem(String a){
        
       
        int n = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.id_ins FROM insc_mem m WHERE m.statut_ins = 0 AND m.nom_ins = '"+a+"' ");
            while(res.next()){
              n = res.getInt("id_ins");  
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return n;
       
        
    }
    
    private String []  get_fi(String a){
        
       String [] tabb;
        int n = 0, i = 0;
        
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_f FROM filiere m WHERE m.nom_dep = '"+a+"' ");
            while(res.next()){
              n++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
         tabb = new String[n];
  
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_f FROM filiere m WHERE m.nom_dep = '"+a+"' ");
            while(res.next()){
                String s = res.getString("nom_f");
                tabb[i] = s;
                i++;
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
       if(n == 0) return null;
        
       return tabb;
       
        
    }
    
    private String [] func_nom_avc_matr (String a){
        
        
        
        try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_p, m.prenom_p FROM personnel m WHERE m.mat_p = '"+a+"'  ");
            res.next();
            String as = res.getString("nom_p");
            String ass = res.getString("prenom_p");
            String [] tab = new String [2];
            tab[0] = as;
            tab[1] = ass;
            
            return tab;
        }catch(Exception e){
            e.printStackTrace();
            
            return null;
        }
               
    }
    
    private String  func_nomdep_avc_matr_ap (String a){
        
        
        
        try{
            Connection con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_depart FROM depart_ens m WHERE m.mat_p = '"+a+"'  ");
            res.next();
            String as = res.getString("nom_depart");
            
            return as;
        }catch(Exception e){
            e.printStackTrace();
            
            return null;
        }
               
    }
    
   
    
    
    private String [] selection2(String a){
        
        String [] tabb;
        int n = 0, i = 0;
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_mat, m.coef_mat FROM matiere m, classe c WHERE c.nom_class = '"+a+"' AND c.ind_mat = m.ind_mat");
            while(res.next()){
                n++;
            }
            
            //if(n == 0) return null;
            
            
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("Miki");
            
        }
        
        
       
        tabb = new String[n+n];
        
        try{
            con = con_miki();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m.nom_mat, m.coef_mat FROM matiere m, classe c WHERE c.nom_class = '"+a+"' AND c.ind_mat = m.ind_mat");
            while(res.next()){
                String s = res.getString("nom_mat");
                String ss = res.getString("coef_mat");
                tabb[i] = s;
                tabb[i+1] = ss;
                i++;
                i++;
              
                
                
            }
            
            
        }catch(Exception e){
            //e.printStackTrace();
           System.out.println("leila");
        }
        
       if(n==0) return null;
       return tabb;
        
    }
    
    private int Insertligntable_1(String nom_tab, String code_num, String nom_auteur, String titre){
        
        try{
            con = con_miki();
            code_num = code_num.toUpperCase();
            nom_auteur = nom_auteur.toUpperCase();
            titre = titre.toUpperCase();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (type, nom_ecole, mode_payement) VALUES ('"+code_num+"', '"+nom_auteur+"', '"+titre+"')");
            int low = pstat.executeUpdate();
            if (low==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int Insertligntable_2(String nom_tab, String nom_auteur, String titre){
        
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (logo, photo) VALUES ('"+nom_auteur+"', '"+titre+"')");
            int low_2 = pstat.executeUpdate();
            if (low_2==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private  void Upd_emp(String nom_table, String ce, String cm, String att, String val){
        
     try{
    
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("UPDATE `"+nom_table+"` SET `"+att+"`=? where `code_emp`=? AND `code_m`=?");
            con.setAutoCommit(false);
            pstat.setString(1, val);
            pstat.setString(2, ce);
            pstat.setString(3, cm);
            
            //System.out.println(uuser);
            
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("modification de la ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
            
     }catch(Exception e){
         e.printStackTrace();
     }
}
    
    private void func_add_ap(String nom_tab, String nom_auteur, String titre, String po){
        
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (code_ap, nom_depart, mat_p) VALUES ('"+nom_auteur+"', '"+titre+"', '"+po+"')");
            int low_2 = pstat.executeUpdate();
            if (low_2==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    private void func_add_ens_depart(String nom_tab, String nom_auteur, String titre){
        
        try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (mat_p, nom_depart) VALUES ('"+nom_auteur+"', '"+titre+"')");
            int low_2 = pstat.executeUpdate();
            if (low_2==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    private int Insertligntable_3(String nom_tab, String code_num, String nom_auteur, String titre){
        
        try{
            
            code_num = code_num.toUpperCase();
            nom_auteur = nom_auteur.toUpperCase();
            titre = titre.toUpperCase();
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (ind_class, nom_class, ind_mat) VALUES ('"+code_num+"', '"+nom_auteur+"', '"+titre+"')");
            int low = pstat.executeUpdate();
            if (low==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int Insert_doss(String nom_tab, String pho){
        
        try{
            
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (doc_med) VALUES ('"+pho+"')");
            int low = pstat.executeUpdate();
            if (low==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int Insert_logmdp(String log, String mdp){
        
        try{
            
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO personnel (user, login) VALUES ('"+log+"', '"+mdp+"')");
            int low = pstat.executeUpdate();
            if (low==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int in_connexion(String log, String mdp){
        
        try{
            int y = 0;
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO connexion (nom_user, mdp_user, tempon) VALUES ('"+log+"', '"+mdp+"', "+y+")");
            int low = pstat.executeUpdate();
            if (low==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int in_personne(String log, String mdp){
        
        try{
            int y = 0;
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO personnel (user, login) VALUES ('"+log+"', '"+mdp+"', "+y+")");
            int low = pstat.executeUpdate();
            if (low==1)  con.commit();
            
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    
    
    private int Insertligntable4(String nom_tab, String code_num, String nom_auteur, Date titre){
        
        try{
            code_num = code_num.toUpperCase();
            
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (nom_tranche, montant, date_limite_payement) VALUES ('"+code_num+"', "+nom_auteur+", '"+titre+"')");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int Insertligntable5(String code_num, String titre, String nom_auteur){
        
        try{
            Connection con = con_miki();
            
            code_num = code_num.toUpperCase();
            titre = titre.toUpperCase();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO matiere (ind_mat, nom_mat, coef_mat) VALUES ('"+code_num+"', '"+titre+"', "+nom_auteur+")");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    } 
    
     private int In_consult(){
        
        try{
            Connection con = con_miki();
            int nom_auteur = 0;
            PreparedStatement pstat = con.prepareStatement("INSERT INTO consultation (tempon) VALUES ("+nom_auteur+")");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    } 
    
    
    private int In_membre(String no, String pre, Date dat, int cni, String mail, int tel, String ville, String mal, String doss, int st){
        
        try{
            Connection con = con_miki();
            
            no = no.toUpperCase();
            pre = pre.toUpperCase();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO insc_mem (nom_ins, prenom_ins, date_naiss_ins, cni_mem, mail_ins, tel_ins, ville_quartier, maladie, evaluation, statut_ins) VALUES ('"+no+"', '"+pre+"', '"+dat+"', "+cni+", '"+mail+"', "+tel+", '"+ville+"', '"+mal+"', '"+doss+"', "+st+")");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    } 
    
    private int In_medecin(String no, String pre, Date dat, int cni, String mail, int tel, String ville, String mal, String doss, String spe, int st){
        
        try{
            Connection con = con_miki();
            
            no = no.toUpperCase();
            pre = pre.toUpperCase();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO insc_med (nom_med, prenom_med, date_naiss, cni_med, mail_ins, tel_ins, ville_quartier, grade_ins, evaluation, specialite_ins, statut_ins) VALUES ('"+no+"', '"+pre+"', '"+dat+"', "+cni+", '"+mail+"', "+tel+", '"+ville+"', '"+mal+"', '"+doss+"', '"+spe+"', "+st+")");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    } 
    
    private int regis_medecin(String no, String pre, Date dat, int cni, String mail, int tel, String ville, String grad, String spe, String log, String mdp){
        
        try{
            Connection con = con_miki();
            
            no = no.toUpperCase();
            pre = pre.toUpperCase();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO medecin (nom_med, prenom_med, date_naiss, cni_med, mail_med, tel_med, ville_quartier, grade_med, specialite, user_med, mdp_med) VALUES ('"+no+"', '"+pre+"', '"+dat+"', "+cni+", '"+mail+"', "+tel+", '"+ville+"', '"+grad+"', '"+spe+"', '"+log+"', '"+mdp+"')");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    } 
    
    private int Insertligntable6(String mat, String nom, String prenom, Date date_naiss, String cni, String email, String tel, String user, String mdp, String parcours, String statutt){
        
        try{
            Connection con = con_miki();
            
            nom = nom.toUpperCase();
            prenom = prenom.toUpperCase();
            statutt = statutt.toUpperCase();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO personnel (mat_p, nom_p, prenom_p, date_naiss_p, n_cni_p, email_p, telephone_p, nom_uti_p, mdp_p, parcours_p, statut) VALUES ('"+mat+"', '"+nom+"', '"+prenom+"', '"+date_naiss+"', "+cni+", '"+email+"', "+tel+", '"+user+"', '"+mdp+"', '"+parcours+"', '"+statutt+"')");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("hum");
        }
        
        return 0;
        
    }
    
    private int Insdep(String nom_tab, String c, String nd, String nc){
        
        try{
            Connection con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (code_dep, nom_dep, nom_chef) VALUES ('"+c+"', '"+nd+"', '"+nc+"')");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int Insmat(String nom_tab, String ce, String cm, String j, String h, String sa, String ens, String el){
        
        try{
            Connection con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (code_emp, code_m, jour, heure, salle, ens, elt_m) VALUES ('"+ce+"', '"+cm+"', '"+j+"', '"+h+"', '"+sa+"', '"+ens+"', '"+el+"')");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private int Insfi(String nom_tab, String cf, String nd, String nf, String ce){
        
        try{
            Connection con = con_miki();
            PreparedStatement pstat = con.prepareStatement("INSERT INTO "+nom_tab+" (code_f, nom_dep, nom_f, code_emp) VALUES ('"+cf+"', '"+nd+"', '"+nf+"', '"+ce+"')");
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("insertion de ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
    private char [] test_ap(char [] cc){
        
        
        int ok = 0;
        int ww = 1;
        while(ww<cc.length){
                    if(cc[ww] == '\'' & cc[ww-1] != '\\'){
                        ok = 1;
                        char [] ccc = new char[cc.length+1];
                        for(int w=0; w<ww; w++){
                            ccc[w] = cc[w];
                        }
                        ccc[ww] = '\\';
                        for(int w=ww+1; w<ccc.length; w++){
                            ccc[w] = cc[ww];
                            ww++;
                        }
                        
                        return test_ap(ccc);
                    }
                    
                   
                    
              ww++;      
                    
                   }
        return cc;
        
    }

   
    
    private static void Updatetable(String nom_bd, String nom_table, int code_num, String nom_auteur, String titre){
        
     try{
     Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("le Driver a bien démarré");
            String url, user, motdepasse;
            url="jdbc:mysql://localhost:3306/"; 
            user="root";
            motdepasse="";
            Connection con = DriverManager.getConnection(url+nom_bd, user, motdepasse);
            PreparedStatement pstat = con.prepareStatement("UPDATE `"+nom_table+"` SET `auteur`=?, `titre`=? where `code`=?");
            con.setAutoCommit(false);
            pstat.setString(1, nom_auteur);
            pstat.setString(2, titre);
            pstat.setInt(3, code_num);
            
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("modification de la ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
            
     }catch(Exception e){
         e.printStackTrace();
     }
}
    
    
    private static void Updatetable_con(String nom_bd, String nom_table, int uuser, int val){
        
     try{
     Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("le Driver a bien démarré");
            String url, user, motdepasse;
            url="jdbc:mysql://localhost:3306/"; 
            user="root";
            motdepasse="";
            Connection con = DriverManager.getConnection(url+nom_bd, user, motdepasse);
            PreparedStatement pstat = con.prepareStatement("UPDATE `"+nom_table+"` SET `tempon`=? where `id_con`=?");
            con.setAutoCommit(false);
            pstat.setInt(1, val);
            pstat.setInt(2, uuser);
            
            System.out.println(uuser);
            
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("modification de la ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
            
     }catch(Exception e){
         e.printStackTrace();
     }
}
    
    private static void Updatetable_con3(String nom_bd, String nom_table, int uuser, int val, String iff, String log, String mdp){
        
     try{
     Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("le Driver a bien démarré");
            String url, user, motdepasse;
            url="jdbc:mysql://localhost:3306/"; 
            user="root";
            motdepasse="";
            Connection con = DriverManager.getConnection(url+nom_bd, user, motdepasse);
            PreparedStatement pstat = con.prepareStatement("UPDATE `"+nom_table+"` SET `statut_ins`=? , `login`=? , `mdp`=? where `"+iff+"`=?");
            con.setAutoCommit(false);
            pstat.setInt(1, val);
            pstat.setString(2, log);
            pstat.setString(3, mdp);
            pstat.setInt(4, uuser);
            
            System.out.println(uuser);
            
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("modification de la ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();
            
            
     }catch(Exception e){
         e.printStackTrace();
     }
}
    
    
     
    
    private static void Deletetable(String titre, String t){
        
     try{
     Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("le Driver a bien démarré");
            String url, user, motdepasse;
            url="jdbc:mysql://localhost:3306/"; 
            user="root";
            motdepasse="";
            Connection con = DriverManager.getConnection(url+"rayappschool_test", user, motdepasse);
            PreparedStatement pstat = con.prepareStatement("DELETE FROM `rayappschool_test`.`matiere` WHERE `matiere`.`ind_mat` = '"+titre+"' AND `matiere`.`nom_mat` = '"+t+"' ");
            con.setAutoCommit(false);            
            int low = pstat.executeUpdate();
            if (low==1) {System.out.println("suppréssion des ligne dans la table réussie, un pas vers la gloire");
            con.commit();
            }
            else con.rollback();           
     }catch(Exception e){
         e.printStackTrace();
     }
   }
    
    private void Delete_depart(String tt, String t, String a){
        
     try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("DELETE FROM `"+tt+"` WHERE "+t+" = '"+a+"'");
            con.setAutoCommit(false);            
            int low = pstat.executeUpdate();
            if (low==1) {
                System.out.println("suppréssion des ligne dans la table réussie, un pas vers la gloire");
                con.commit();
            }
            else {
                System.out.println("suppréssion impossibles");
                con.rollback();
            }           
     }catch(Exception e){
         e.printStackTrace();
     }
   }
    
    private void Delete_ap(String tt, String t, String a){
        
     try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("DELETE FROM `"+tt+"` WHERE `"+t+"` = '"+a+"'");
            con.setAutoCommit(false);            
            int low = pstat.executeUpdate();
            if (low==1) {
                System.out.println("suppréssion des ligne dans la table réussie, un pas vers la gloire");
                con.commit();
            }
            else {
                System.out.println("suppréssion impossibles");
                con.rollback();
            }           
     }catch(Exception e){
         e.printStackTrace();
     }
   }
    
    
    private void Delete_table(String tt){
        
     try{
            con = con_miki();
            PreparedStatement pstat = con.prepareStatement("DROP TABLE `"+tt+"`");
            con.setAutoCommit(false);            
            int low = pstat.executeUpdate();
            if (low==0) {
                System.out.println("suppréssion de la table réussie");
                //System.out.println(low);
                con.commit();
            }
            else {
                System.out.println("suppréssion de la table impossible");
                //System.out.println(low);
                con.rollback();
            }           
     }catch(Exception e){
         e.printStackTrace();
     }
   }











public Service_1(Socket aSocket)
{
s = aSocket;

}


public void run(){

    try{
        

BufferedReader in = new BufferedReader(
new InputStreamReader(s.getInputStream()));
PrintWriter out = new PrintWriter(
s.getOutputStream());


ligne = in.readLine();
int num_service = Integer.parseInt(ligne);



    
    if(num_service == 200){
        
    code = in.readLine();
    nomd = in.readLine();
    nomc = in.readLine();
    
        Insdep("departement", code, nomd, nomc);
        
    
    
    
    yield();

s.close();

    }else if(num_service == 201){
        
    String [] pk = get_nd();
    
        out.println(pk.length);
        out.flush();
        
        for(int i=0; i<pk.length; i++){
            
                out.println(pk[i]);
                out.flush();
            
            
        }
    
    yield();

s.close();

    }  else if(num_service == 202){
    
     String nf = in.readLine();
     String nd = in.readLine();
     Insfi("filiere", nf+1, nd, nf, nf+2);
     
     
    yield();

s.close();

    }else if(num_service == 203){
        
        String nd = in.readLine();
        
       String [] pk = get_fi(nd);
    
       if(pk == null ){
        out.println(0);
        out.flush();
        
       } else{
         out.println(pk.length);
        out.flush();
       for(int i=0; i<pk.length; i++){
            
                out.println(pk[i]);
                out.flush();
                
        }
       }
    yield();

s.close();

    } else if(num_service == 204){
        
    String nf = in.readLine();
    
    String [] emploi = get_emploi(nf+2);
    
    if(emploi == null){
      out.println(0);
        out.flush();  
    } else {
        
        out.println(emploi.length);
        out.flush();  
        
        for(int w=0; w<emploi.length; w++){
           out.println(emploi[w]);
           out.flush();      
        }
    }
    
    yield();

s.close();

    } else if(num_service == 205){
        
    String code = in.readLine();
    String elt = in.readLine();
    String jour = in.readLine();
    String heure = in.readLine();
    String salle = in.readLine();
    String ens = in.readLine();
    String nom_f = in.readLine();
    
    
        
        
          
           
       
               Insmat("emploi_temps", nom_f+2, code, jour, heure, salle, ens, elt);
           
        
    
    yield();

s.close();

    } else if(num_service == 206){
        
        String nom_f = in.readLine();
        String code_m = in.readLine();
        String att = in.readLine();
        String val = in.readLine();
        
        Upd_emp("emploi_temps", nom_f+2, code_m, att, val);
        
    
    yield();

s.close();

    } else if(num_service == 9){
        
    String tab_med [] = get_usermail_med();
    String tab_mem [] = get_usermail_mem();
    
    if(tab_med != null){
        
    out.println(tab_med.length);
    out.flush();
    
    }
    
    if(tab_mem != null){
        
    out.println(tab_mem.length);
    out.flush();
    
    }
    
    for(int i = 0; i<tab_med.length; i++){
        out.println(tab_med[i]);
        out.flush();
        
    }
    
    for(int i = 0; i<tab_mem.length; i++){
        out.println(tab_mem[i]);
        out.flush();
        
    }
    
    
    yield();

s.close();

    }  
    }catch(IOException e){
        e.printStackTrace();
        //System.out.println("erreur 2");
        
    }
    try{
    s.close();
    } catch (Exception kk){
        
    }
}

    

    
   
}
 




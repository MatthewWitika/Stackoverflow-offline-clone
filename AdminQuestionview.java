
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import keeptoo.KGradientPanel;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class AdminQuestionview extends JFrame implements Runnable {
    String username,sch;
    int useriden;
    int rowindex;
    int Qid;
    int userkey;
    
       ImageIcon image = new ImageIcon("C:/Users/DELL/Desktop/next.png");
    
     //QUESTION
        Connection in;
        String sql = "Select * from questions";
        PreparedStatement st;
        ResultSet rs;
        
        //ANSWER
         Connection opn;
        String req = "SELECT * FROM answers WHERE questionid='"+ Qid + "'";
        PreparedStatement pre;
        ResultSet cle;
    
    /**
     * Creates new form AdminQuestionview
     */
    public AdminQuestionview() {
        
             try {
           in = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
           st = in.prepareStatement(sql,  ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery();
           
        initComponents();
            new Thread(this).start();
        
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
     public void vef(){ v.setVisible(true);}
 public void getvotesa(){
                 
        try {
             Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
              
              String rd = "SELECT * FROM `nextgenlink`.`answers` WHERE (`questionid` = '" + Qid + "')";
             PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                ResultSet rus = stet.executeQuery();
                rus.next();
                int vox = rus.getInt("vote");
               
            vovv.setText("Votes:  "+ vox);
  
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
       public void getvotesq(){
                 
        try {
             Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
              
              String rd = "SELECT * FROM `nextgenlink`.`questions` WHERE (`idQuestions` = '" + Qid + "')";
             PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                ResultSet rus = stet.executeQuery();
                rus.next();
                int vox = rus.getInt("vote");
               
            Voten.setText("Votes:  "+ vox);
  
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
    public void cherche(String ch){
          sch = ch;
          if(!(sch.isEmpty())){
         try { //CHECK QUESTIONS
             Connection search;
             String open = "select * from questions where (description like  '%" + sch + "%') or (title like  '%" + sch + "%') ";
             PreparedStatement prst;
             ResultSet resultat;
             
             search = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
             prst = search.prepareStatement(open,  ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             resultat = prst.executeQuery();
             
            if(resultat.next()){
               int x = resultat.getRow();
                String a = resultat.getString("title");
                String b = "<html><br/><p><i>" + resultat.getString("description") + "</i></p></html>";
                String c = resultat.getString("tags");
                String d = resultat.getString("code");
               
                rowindex = x;
                
        boss.setText(a);
        desct.setText(b);
        tagy.setText(c);
        codey.setText(d);
            }
             
            else{
                JOptionPane.showMessageDialog(null,"No results found!");
            }
            search.close();
            
            /* CHECK ANSWERS
            String op = "select * from answers where description like '%" + sch + "%'";
            search = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
             prst = search.prepareStatement(op,  ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             resultat = prst.executeQuery();*/
             
             
         } catch (SQLException ex) {
             Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
         }
    } else{
              JOptionPane.showMessageDialog(null,"Search bar is Empty");
          }
    
    }
     public void setOffline(int x) throws SQLException{
          
            
               Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
                   String sqml = "UPDATE `nextgenlink`.`userlog` SET `Status` = 'offline' WHERE (`iduserlog` = '" + x + "')";
                    PreparedStatement pst = cn.prepareStatement(sqml);
                    pst.executeUpdate();
    }
     
     public void getdets(int key){
         try {
             Connection out = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
             String in = "SELECT * FROM userlog WHERE iduserlog='"+ key + "'";
            PreparedStatement stat= out.prepareStatement(in,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stat.executeQuery();
              result.next();
              String name = result.getString("Username");
          
              dude.setText(name);
              
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void getqdets(int key){
         try {
             Connection out = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
             String in = "SELECT * FROM userlog WHERE iduserlog='"+ key + "'";
            PreparedStatement stat= out.prepareStatement(in,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stat.executeQuery();
              result.next();
              String name = result.getString("Username");
              
              upblish.setText(name);
              
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getUser(String x,int y){
        username = x;
        useriden = y;
    }
    
    public void getrow(int x){
        rowindex = x;
    }
    
    
    public void answerinfo() throws SQLException{
      
        try {
             int count; 
       
         Connection connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
        
           String t = "SELECT COUNT(*) AS rowcount FROM answers WHERE questionid='" + Qid + "'";
         PreparedStatement ps = connec.prepareStatement(t,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
         
        ResultSet rst = ps.executeQuery();
        rst.next();
        count = rst.getInt("rowcount");
        ansnum.setText("Answers availables: " + count);
        connec.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuestionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                //GETS ANSWERS TO Q AND PUBLISHER INFO
        
          try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
             String trial = "SELECT * FROM answers WHERE questionid='"+ Qid + "'";
            PreparedStatement ps= con.prepareStatement(trial,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rst = ps.executeQuery();
            
              rst.next();
          
              
            String dec= rst.getString("description");
            String cd = rst.getString("code");
           userkey = rst.getInt("userid");
           
            getdets(userkey);
            
            
            answ.setText( dec + "<br/> <br/>" + cd);
            
            //opn.close();
              
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getvotesa();
        
        
    }
    public void openQuestion(){
                     int i=1;           
        try{
            
            
          if(rowindex == 1){
            
            rs.next();
             Qid = rs.getInt("idQuestions");
            
            String a = rs.getString("title");
            String b = "<html><br/><p><i>" + rs.getString("description") + "</i></p></html>";
            String c = rs.getString("tags");
            String d = rs.getString("code");
            int us = rs.getInt("iduserlog");
           
           getqdets(us);
            boss.setText(a);
            desct.setText(b);
            tagy.setText(c);
            codey.setText(d);
          }
          else { while(i<=rowindex){ rs.next(); i++;}
             Qid = rs.getInt("idQuestions");
             String a = rs.getString("title");
            String b = "<html><br/><p><i>" + rs.getString("description") + "</i></p></html>";
            String c = rs.getString("tags");
            String d = rs.getString("code");
            int us = rs.getInt("iduserlog");
            
            getqdets(us);
            
            boss.setText(a);
            desct.setText(b);
            tagy.setText(c);
            codey.setText(d);
            
            
          }
     
        }
       catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        getvotesq();
        hold.setVisible(false);

    }
    
    public void addPlaceholderStyle(JTextField txtfield){
        Font font = txtfield.getFont();
        font = font.deriveFont(Font.ITALIC);
        txtfield.setFont(font);
        txtfield.setForeground(Color.gray);
    }
    
    public void removePlaceholderStyle(JTextField txtfield){
        Font font = txtfield.getFont();
        font = font.deriveFont(Font.PLAIN|Font.BOLD);
        txtfield.setFont(font);
        txtfield.setForeground(Color.WHITE);  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel11 = new keeptoo.KGradientPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        srchtxt = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        kGradientPanel12 = new keeptoo.KGradientPanel();
        jLabel41 = new javax.swing.JLabel();
        Chatpanel = new javax.swing.JPanel();
        kGradientPanel13 = new keeptoo.KGradientPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel23 = new javax.swing.JPanel();
        Userlab = new javax.swing.JLabel();
        Userlab2 = new javax.swing.JLabel();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel32 = new javax.swing.JPanel();
        Userlab1 = new javax.swing.JLabel();
        Userlab4 = new javax.swing.JLabel();
        kGradientPanel15 = new keeptoo.KGradientPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jRadioButton16 = new javax.swing.JRadioButton();
        jRadioButton17 = new javax.swing.JRadioButton();
        jRadioButton18 = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pane = new javax.swing.JPanel();
        boss = new javax.swing.JLabel();
        desct = new javax.swing.JLabel();
        tagy = new javax.swing.JLabel();
        codey = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        answ = new javax.swing.JEditorPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        v = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dude = new javax.swing.JLabel();
        hold = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Com = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        Voten = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ansnum = new javax.swing.JLabel();
        upblish = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        comty = new javax.swing.JTextPane();
        jButton8 = new javax.swing.JButton();
        downvote = new javax.swing.JButton();
        upvote = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        vovv = new javax.swing.JLabel();
        NextQ = new javax.swing.JButton();
        Nom = new javax.swing.JLabel();
        Nom1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        kGradientPanel11.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel11.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        kGradientPanel11.setGradientFocus(600);
        kGradientPanel11.setStartColor(new java.awt.Color(153, 0, 153));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("<html><u>HOME</u></html>");
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });
        kGradientPanel11.add(jLabel37);
        jLabel37.setBounds(10, 10, 50, 16);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("<html><u>PRODUCTS</u></html>");
        kGradientPanel11.add(jLabel38);
        jLabel38.setBounds(60, 10, 63, 16);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("<html><u>CONTACT</u></html>");
        kGradientPanel11.add(jLabel39);
        jLabel39.setBounds(140, 10, 70, 16);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("<html><u>ABOUT</u></html>");
        kGradientPanel11.add(jLabel40);
        jLabel40.setBounds(220, 10, 50, 16);

        jButton14.setText("Sign out");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(jButton14);
        jButton14.setBounds(1220, 10, 73, 23);

        srchtxt.setBackground(new java.awt.Color(93, 78, 172));
        srchtxt.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        srchtxt.setForeground(new java.awt.Color(204, 204, 204));
        srchtxt.setText("Search");
        srchtxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(93, 78, 172), 7, true));
        srchtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                srchtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                srchtxtFocusLost(evt);
            }
        });
        kGradientPanel11.add(srchtxt);
        srchtxt.setBounds(300, 10, 700, 29);

        jButton17.setText("Search");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        kGradientPanel11.add(jButton17);
        jButton17.setBounds(1020, 10, 90, 23);

        kGradientPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kGradientPanel12.setEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel12.setStartColor(new java.awt.Color(153, 0, 204));

        jLabel41.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Questions");
        jLabel41.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        kGradientPanel12.add(jLabel41);
        jLabel41.setBounds(20, 10, 90, 30);

        kGradientPanel11.add(kGradientPanel12);
        kGradientPanel12.setBounds(0, 0, 0, 0);

        Chatpanel.setBackground(new java.awt.Color(255, 255, 255));
        Chatpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 12))); // NOI18N
        Chatpanel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N

        kGradientPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        kGradientPanel13.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel13.setStartColor(new java.awt.Color(0, 204, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 11)); // NOI18N
        jLabel8.setText("Online users");
        kGradientPanel13.add(jLabel8);
        jLabel8.setBounds(30, 0, 70, 15);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        Userlab.setText("img");

        Userlab2.setText("img");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Userlab, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Userlab2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(Userlab, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Userlab2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel23);

        kGradientPanel13.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 130, 150);

        kGradientPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        kGradientPanel14.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel14.setStartColor(new java.awt.Color(0, 153, 153));

        jLabel42.setFont(new java.awt.Font("Segoe UI Semibold", 1, 11)); // NOI18N
        jLabel42.setText("Online users");
        kGradientPanel14.add(jLabel42);
        jLabel42.setBounds(30, 0, 70, 15);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        Userlab1.setText("img");

        Userlab4.setText("img");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Userlab1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Userlab4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(Userlab1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Userlab4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel32);

        kGradientPanel14.add(jScrollPane3);
        jScrollPane3.setBounds(10, 20, 130, 250);

        javax.swing.GroupLayout ChatpanelLayout = new javax.swing.GroupLayout(Chatpanel);
        Chatpanel.setLayout(ChatpanelLayout);
        ChatpanelLayout.setHorizontalGroup(
            ChatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChatpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kGradientPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        ChatpanelLayout.setVerticalGroup(
            ChatpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChatpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kGradientPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        kGradientPanel11.add(Chatpanel);
        Chatpanel.setBounds(1080, 80, 186, 671);

        kGradientPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        kGradientPanel15.setEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel15.setGradientFocus(340);
        kGradientPanel15.setStartColor(new java.awt.Color(255, 255, 255));

        jTabbedPane7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jRadioButton16.setText("most recent questions");
        jRadioButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton16ActionPerformed(evt);
            }
        });

        jRadioButton17.setText("frequently asked questions");

        jRadioButton18.setText("answered questions");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton18)
                    .addComponent(jRadioButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jRadioButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton17)
                .addGap(3, 3, 3)
                .addComponent(jRadioButton18)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("Questions", jPanel31);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Tags", jPanel14);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        jTabbedPane7.addTab("Users", jPanel15);

        kGradientPanel15.add(jTabbedPane7);
        jTabbedPane7.setBounds(10, 10, 174, 188);

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setText("Teams");
        kGradientPanel15.add(jLabel6);
        jLabel6.setBounds(70, 230, 57, 20);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton4.setText("Join");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel7.setText("<html>Lorem Ipsum is simply dummy text of the printing and typesetting industry. <br/>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s<br/></html>");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 97, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
        );

        kGradientPanel15.add(jPanel16);
        jPanel16.setBounds(10, 270, 174, 178);

        pane.setBackground(new java.awt.Color(255, 255, 255));

        boss.setFont(new java.awt.Font("Segoe UI Semibold", 3, 24)); // NOI18N
        boss.setText("title here");
        boss.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        boss.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        desct.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        desct.setText("Little descritption");
        desct.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        desct.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tagy.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        tagy.setText("#TAG HERE");

        codey.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        codey.setText("CODE BLOCK");
        codey.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        codey.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        answ.setEditable(false);
        answ.setContentType("text/html"); // NOI18N
        answ.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        answ.setToolTipText("");
        jScrollPane4.setViewportView(answ);

        jTable1.setFont(new java.awt.Font("Open Sans", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Author", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setShowVerticalLines(false);
        jScrollPane6.setViewportView(jTable1);

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 204, 255));
        jLabel13.setText("<html><i><u>Show Comments</u></i></html>");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel14.setText("<html><u>Drop Comment</u></html>");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Answer Published by:");

        dude.setForeground(new java.awt.Color(255, 51, 51));

        Com.setFont(new java.awt.Font("Open Sans", 0, 11)); // NOI18N
        Com.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Author", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Com.setShowVerticalLines(false);
        jScrollPane10.setViewportView(Com);

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 3, 12)); // NOI18N
        jLabel11.setText("COMMENT SECTION");

        javax.swing.GroupLayout holdLayout = new javax.swing.GroupLayout(hold);
        hold.setLayout(holdLayout);
        holdLayout.setHorizontalGroup(
            holdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, holdLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
            .addGroup(holdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        holdLayout.setVerticalGroup(
            holdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(holdLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Voten.setText("Votes");

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 204, 255));
        jLabel17.setText("<html><i><u>Show Comments</u></i></html>");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        ansnum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        upblish.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        upblish.setForeground(new java.awt.Color(255, 51, 51));
        upblish.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        upblish.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Question published by:");

        jButton1.setText("Verify Answer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton15.setText("Open Q");
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Close Q");
        jButton16.setBorder(null);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        comty.setContentType("text/html"); // NOI18N
        comty.setEnabled(false);
        comty.setInheritsPopupMenu(true);
        comty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comtyKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(comty);

        jButton8.setBackground(new java.awt.Color(153, 204, 255));
        jButton8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton8.setText("Next Answer");
        jButton8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.white));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        downvote.setFont(new java.awt.Font("Comic Sans MS", 3, 11)); // NOI18N
        downvote.setText("DOWNVOTE");
        downvote.setBorder(null);
        downvote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downvoteActionPerformed(evt);
            }
        });

        upvote.setFont(new java.awt.Font("Comic Sans MS", 3, 12)); // NOI18N
        upvote.setText("UPVOTE");
        upvote.setBorder(null);
        upvote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upvoteActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Comic Sans MS", 3, 11)); // NOI18N
        jButton12.setText("UPVOTE");
        jButton12.setBorder(null);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Comic Sans MS", 3, 11)); // NOI18N
        jButton13.setText("DOWNVOTE");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        vovv.setText("Votes");

        NextQ.setBackground(new java.awt.Color(153, 204, 255));
        NextQ.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        NextQ.setText("Next Question");
        NextQ.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        NextQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextQActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codey, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addComponent(ansnum, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)
                                        .addComponent(Voten, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(35, 35, 35)
                                        .addComponent(dude, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(vovv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 21, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(24, 24, 24))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boss, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addComponent(desct, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addComponent(tagy, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(upblish, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel10))
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(261, 261, 261)
                                                .addComponent(downvote, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(269, 269, 269)
                                                .addComponent(upvote, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(paneLayout.createSequentialGroup()
                                                .addGap(261, 261, 261)
                                                .addComponent(NextQ, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(26, 26, 26)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(20, 20, 20))))
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(boss, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(desct, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NextQ, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(tagy, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(upblish, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(upvote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downvote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addComponent(hold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jButton15)
                                .addGap(18, 18, 18)
                                .addComponent(jButton16)
                                .addGap(158, 158, 158)
                                .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(codey, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ansnum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Voten))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(dude, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(vovv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );

        jScrollPane2.setViewportView(pane);

        kGradientPanel15.add(jScrollPane2);
        jScrollPane2.setBounds(200, 10, 825, 621);

        kGradientPanel11.add(kGradientPanel15);
        kGradientPanel15.setBounds(10, 80, 1060, 680);

        Nom.setFont(new java.awt.Font("Segoe UI Semibold", 3, 12)); // NOI18N
        Nom.setForeground(new java.awt.Color(0, 204, 255));
        kGradientPanel11.add(Nom);
        Nom.setBounds(1120, 10, 80, 20);

        Nom1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 12)); // NOI18N
        Nom1.setForeground(new java.awt.Color(0, 204, 255));
        kGradientPanel11.add(Nom1);
        Nom1.setBounds(930, 10, 80, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        try {
            UserHome frame =new UserHome();
            frame.setVisible(true);
            frame.getUser(username,useriden);
            
            this.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(AdminQuestionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jRadioButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton16ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            Statement ps = con.createStatement();

            ResultSet rst = ps.executeQuery("SELECT * FROM comments");

            Com.setModel(DbUtils.resultSetToTableModel(rst));

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        hold.setVisible(true);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
             Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
              
              String rd = "UPDATE answers SET `verified` = '1' WHERE (`idQuestions` = '" + Qid + "')";
             PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                int rus = stet.executeUpdate();
            
  
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        v.setVisible(true);
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
         try {
             Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
              
              String rd = "UPDATE `nextgenlink`.`questions` SET `Status` = 'open' WHERE (`idQuestions` = '" + Qid + "')";
             PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                int rus = stet.executeUpdate();
            
  
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
         try {
             Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
              
              String rd = "UPDATE `nextgenlink`.`questions` SET `Status` = 'closed' WHERE (`idQuestions` = '" + Qid + "')";
             PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
                int rus = stet.executeUpdate();
            
  
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void srchtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_srchtxtFocusGained
        if(srchtxt.getText().equals("Search")){
            srchtxt.setText(null);
            srchtxt.requestFocus();
            removePlaceholderStyle(srchtxt);
        }
    }//GEN-LAST:event_srchtxtFocusGained

    private void srchtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_srchtxtFocusLost
        if(srchtxt.getText().length()==0){
            addPlaceholderStyle(srchtxt);
            srchtxt.setText("Search");
        }
    }//GEN-LAST:event_srchtxtFocusLost

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String txt = srchtxt.getText();
        cherche(txt);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int userkey,i=1;
        int count=0;
        try {

            Connection connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            String t = "SELECT COUNT(*) AS rowcount FROM answers WHERE questionid='" + Qid + "'";
            PreparedStatement ps = connec.prepareStatement(t,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

            ResultSet rst = ps.executeQuery();
            rst.next();
            count = rst.getInt("rowcount");
            ansnum.setText("Answers availables: " + count);
            connec.close();
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
            String trial = "SELECT * FROM answers WHERE questionid='"+ Qid + "'";
            PreparedStatement ps= con.prepareStatement(trial,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

            ResultSet rst = ps.executeQuery();

            while(i<=count){ rst.next(); i++;}

            int ver = rst.getInt("verified");
            String dec= rst.getString("description");
            String cd = rst.getString("code");
            userkey = rst.getInt("userid");

            getdets(userkey);

            if(ver == 1){ vef();}

            answ.setText( dec + "<br/> <br/>" + cd);

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        comty.setVisible(true);
   
    }//GEN-LAST:event_jLabel14MouseClicked

    private void comtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comtyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_comtyKeyPressed

    private void downvoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downvoteActionPerformed
        try {
            Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            String rd = "UPDATE `nextgenlink`.`questions` SET vote = vote-1 WHERE (`idQuestions` = '" + Qid + "')";
            PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            int rus = stet.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        downvote.setEnabled(false);
        upvote.setEnabled(true);

    }//GEN-LAST:event_downvoteActionPerformed

    private void upvoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upvoteActionPerformed

        try {
            Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            String rd = "UPDATE `nextgenlink`.`questions` SET vote=vote+1 WHERE (`idQuestions` = '" + Qid + "')";
            PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            int rus = stet.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        upvote.setEnabled(false);
        downvote.setEnabled(true);

    }//GEN-LAST:event_upvoteActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        try {
            Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            String rd = "UPDATE `nextgenlink`.`answers` SET vote=vote+1 WHERE (`questionid` = '" + Qid + "')";
            PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            int rus = stet.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            Connection neu = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            String rd = "UPDATE `nextgenlink`.`answers` SET vote=vote-1 WHERE (`questionid` = '" + Qid + "')";
            PreparedStatement stet= neu.prepareStatement(rd,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            int rus = stet.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton13ActionPerformed

    private void NextQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextQActionPerformed

        try {

            String talk = "Select * from questions";
            Connection  n = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
            PreparedStatement t = n.prepareStatement(talk,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            ResultSet r = t.executeQuery();
            int i=1;
            rowindex++;

            while(i<=rowindex){ try {
                r.next(); i++;
            } catch (SQLException ex) {
                Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Qid = r.getInt("idQuestions");

        String a = r.getString("title");
        String b = "<html><br/><p><i>" + r.getString("description") + "</i></p></html>";
        String c = r.getString("tags");
        String d = r.getString("code");
        int us = r.getInt("iduserlog");

        getqdets(us);

        boss.setText(a);
        desct.setText(b);
        tagy.setText(c);
        codey.setText(d);
        } catch (SQLException e) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, e);
        }
        getvotesq();
        try {
            answerinfo();
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NextQActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminQuestionview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminQuestionview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chatpanel;
    private javax.swing.JTable Com;
    private javax.swing.JButton NextQ;
    private javax.swing.JLabel Nom;
    private javax.swing.JLabel Nom1;
    private javax.swing.JLabel Userlab;
    private javax.swing.JLabel Userlab1;
    private javax.swing.JLabel Userlab2;
    private javax.swing.JLabel Userlab4;
    private javax.swing.JLabel Voten;
    private javax.swing.JLabel ansnum;
    private javax.swing.JEditorPane answ;
    private javax.swing.JLabel boss;
    private javax.swing.JLabel codey;
    private javax.swing.JTextPane comty;
    private javax.swing.JLabel desct;
    private javax.swing.JButton downvote;
    private javax.swing.JLabel dude;
    private javax.swing.JPanel hold;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable1;
    private keeptoo.KGradientPanel kGradientPanel11;
    private keeptoo.KGradientPanel kGradientPanel12;
    private keeptoo.KGradientPanel kGradientPanel13;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel15;
    private javax.swing.JPanel pane;
    private javax.swing.JTextField srchtxt;
    private javax.swing.JLabel tagy;
    private javax.swing.JLabel upblish;
    private javax.swing.JButton upvote;
    private javax.swing.JLabel v;
    private javax.swing.JLabel vovv;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
                try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.openQuestion();
            this.answerinfo();
            
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
        
        
        
}

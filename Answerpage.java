
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.*;
import net.proteanit.sql.DbUtils;



/**
 *
 * @author DELL
 */
public class Answerpage extends JFrame implements Runnable {
   String username,stat,sch;
   int useriden;
    int rowindex,anscount = 0;;
    int Qid;
    private JLabel[] array = new JLabel[100];
    ImageIcon image = new ImageIcon("C:/Users/DELL/Desktop/next.png");
    
     
        Connection connec,con;
        String sql = "Select * from questions";
        PreparedStatement st,ps;
        ResultSet rs,rset;
    
        
    public Answerpage() {
        this.st = null;
        this.rs = null;
        this.connec = null;
        
         this.ps = null;
        this.rset = null;
        this.con = null;
        
        
             try { //TO DEAL W QS
           connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
           st = connec.prepareStatement(sql,  ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery();
            //PART2
             
            //TO MANAGE ANSWERS
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
             String trial = "SELECT * FROM answers WHERE questionid='"+ Qid + "'";
             ps= con.prepareStatement(trial,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            rset = ps.executeQuery();
            
            initComponents();
            
            addPlaceholderStyle(srchtxt);
           
            this.setTitle("nextgenlink");
            
            setIconImage(image.getImage());
            scaleimage();
            new Thread(this).start();
           
        } catch (SQLException ex) {
            Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    hold.setVisible(false);
     
    }
    
    
    public void vef(){ v.setVisible(true);}
    
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
    
    public void showusers(){
         int i=0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");

            Statement ps = con.createStatement();

            ResultSet rst = ps.executeQuery("SELECT * FROM userlog WHERE Status='online'");
                while(rst.next())
            {   
                String man = rst.getString("Username");
                String p = rst.getString("Email address");
                
               
                 array[i] = createLabel(man,p,10,i * 25);
               i++;
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
            }
        //hold.setVisible(true);
                                        

    }
   private JLabel createLabel(String user,String email, int x, int y) {
			JLabel label = new JLabel(user);
			up.add(label);
			label.setBounds(x, y, 375, 20);
                        
                        JLabel note = new JLabel(email);
			up.add(note);
			note.setBounds(x+10, y+10, 375, 20);
                        
			return label;
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
    public void getUser(String x,int y){
        username = x;
        useriden = y;
    }
    
    public void getrow(int x){
        rowindex = x;
    }
    
     public void scaleimage(){
        ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\Desktop\\PROJET QAplatform\\QAplatform\\Ved.png");
        Image img = icon.getImage();
        Image imgscale = img.getScaledInstance(v.getWidth(),v.getHeight(),Image.SCALE_SMOOTH);
           ImageIcon scaledicon = new ImageIcon(imgscale);
           v.setIcon(scaledicon);
           
           v.setVisible(false);
    }
     
     public void openAnswer(){
         
         //GET QUESTION
                          int i=1;           
        try{
          if(rowindex == 1){
            
            rs.next();
             Qid = rs.getInt("idQuestions");
            String a = rs.getString("title");
            String b = "<html><br/><p><i>" + rs.getString("description") + "</i></p></html>";
            String c = rs.getString("tags");
            String d = rs.getString("code");
            
            String y = rs.getString("Status");
            stat=y;
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
            String y = rs.getString("Status");
            stat=y;
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
            showusers();
     }
     
        public void setOffline(int x) throws SQLException{
          
            
               Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
                   String sqml = "UPDATE `nextgenlink`.`userlog` SET `Status` = 'offline' WHERE (`iduserlog` = '" + x + "')";
                    PreparedStatement pst = cn.prepareStatement(sqml);
                    pst.executeUpdate();
    }
     
     public void answerinfo() throws SQLException{
      
      int userkey; int max=0;
    int j=1;
        try {
            
       
         Connection connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
        
           String t = "SELECT COUNT(*) AS rowcount FROM answers WHERE questionid='" + Qid + "'";
         PreparedStatement ps = connec.prepareStatement(t,  ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
         
        ResultSet rst = ps.executeQuery();
        rst.next();
        anscount = rst.getInt("rowcount");
        ansnum.setText("Answers availables: " + anscount);
        connec.close();
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                //GETS ANSWERS TO Q AND PUBLISHER INFO
        
          try {
              if(anscount > 0){
                  int get,lin = 0;
                  try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System")) {
                      String trial = "SELECT * FROM answers WHERE questionid='"+ Qid + "'";
                      PreparedStatement ps= con.prepareStatement(trial,ResultSet.TYPE_SCROLL_SENSITIVE,
                              ResultSet.CONCUR_UPDATABLE);
                      
                      ResultSet rst = ps.executeQuery();
                      
                      while(j<=anscount) 
                      { rst.next(); get = rst.getInt("vote"); 
                          if(get>max){ max=get; lin = rst.getRow();}
                          j++;
                      }
                      
                      rst.absolute(lin);
                     
                      int ver =rst.getInt("verified");
                      String dec= rst.getString("description");
                      String cd = rst.getString("code");
                      userkey = rst.getInt("userid");
                      
                      getdets(userkey);
                      
                      if(ver == 1){ v.setVisible(true);}
                      
                      answ.setText( dec + "<br/> <br/>" + cd);
                  }
              
             }
             else{
                 answ.setText("No Answers Found!");
             }
              
              getvotesa();
        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
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

        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        srchtxt = new javax.swing.JTextField();
        Chatpanel = new javax.swing.JPanel();
        kGradientPanel13 = new keeptoo.KGradientPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        up = new javax.swing.JPanel();
        Userlab = new javax.swing.JLabel();
        Userlab2 = new javax.swing.JLabel();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel32 = new javax.swing.JPanel();
        Userlab1 = new javax.swing.JLabel();
        Userlab4 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pane = new javax.swing.JPanel();
        boss = new javax.swing.JLabel();
        desct = new javax.swing.JLabel();
        tagy = new javax.swing.JLabel();
        codey = new javax.swing.JLabel();
        ansnum = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        answ = new javax.swing.JEditorPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        codeinput = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        ansdesc = new javax.swing.JTextArea();
        v = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dude = new javax.swing.JLabel();
        hold = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Com = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        NextQ = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        downvote = new javax.swing.JButton();
        upvote = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        upblish = new javax.swing.JLabel();
        Voten = new javax.swing.JLabel();
        vovv = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        v1 = new javax.swing.JLabel();
        Nom = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        kGradientPanel2.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel2.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        kGradientPanel2.setGradientFocus(600);
        kGradientPanel2.setStartColor(new java.awt.Color(153, 0, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html><u>HOME</u></html>");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        kGradientPanel2.add(jLabel1);
        jLabel1.setBounds(10, 10, 50, 16);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html><u>PRODUCTS</u></html>");
        kGradientPanel2.add(jLabel2);
        jLabel2.setBounds(60, 10, 63, 16);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("<html><u>CONTACT</u></html>");
        kGradientPanel2.add(jLabel3);
        jLabel3.setBounds(140, 10, 70, 16);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("<html><u>ABOUT</u></html>");
        kGradientPanel2.add(jLabel4);
        jLabel4.setBounds(220, 10, 50, 16);

        jButton2.setText("Sign Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        kGradientPanel2.add(jButton2);
        jButton2.setBounds(1150, 10, 90, 23);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        kGradientPanel2.add(jButton1);
        jButton1.setBounds(860, 10, 90, 23);

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
        kGradientPanel2.add(srchtxt);
        srchtxt.setBounds(300, 10, 510, 29);

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

        up.setBackground(new java.awt.Color(255, 255, 255));

        Userlab.setText("img");

        Userlab2.setText("img");

        javax.swing.GroupLayout upLayout = new javax.swing.GroupLayout(up);
        up.setLayout(upLayout);
        upLayout.setHorizontalGroup(
            upLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(upLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Userlab, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Userlab2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        upLayout.setVerticalGroup(
            upLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(Userlab, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Userlab2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(up);

        kGradientPanel13.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 130, 150);

        kGradientPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        kGradientPanel14.setEndColor(new java.awt.Color(0, 204, 204));
        kGradientPanel14.setStartColor(new java.awt.Color(0, 153, 153));

        jLabel42.setFont(new java.awt.Font("Segoe UI Semibold", 1, 11)); // NOI18N
        jLabel42.setText("Online users");
        kGradientPanel14.add(jLabel42);
        jLabel42.setBounds(30, 0, 70, 15);

        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGap(109, 109, 109)
                .addComponent(Userlab1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Userlab4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel32);

        kGradientPanel14.add(jScrollPane11);
        jScrollPane11.setBounds(10, 20, 130, 250);

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
                .addContainerGap(77, Short.MAX_VALUE))
        );

        kGradientPanel2.add(Chatpanel);
        Chatpanel.setBounds(1010, 130, 186, 600);

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kGradientPanel1.setEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel1.setStartColor(new java.awt.Color(153, 0, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Questions");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        kGradientPanel1.add(jLabel5);
        jLabel5.setBounds(20, 10, 90, 30);

        kGradientPanel2.add(kGradientPanel1);
        kGradientPanel1.setBounds(450, 60, 124, 46);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jRadioButton1.setText("most recent questions");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("frequently asked questions");

        jRadioButton3.setText("answered questions");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(3, 3, 3)
                .addComponent(jRadioButton3)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Questions", jPanel13);

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

        jTabbedPane2.addTab("Tags", jPanel14);

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

        jTabbedPane2.addTab("Users", jPanel15);

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 153, 255));
        jLabel6.setText("Teams");

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

        ansnum.setBackground(new java.awt.Color(255, 255, 255));
        ansnum.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        ansnum.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        answ.setEditable(false);
        answ.setContentType("text/html"); // NOI18N
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

        codeinput.setColumns(20);
        codeinput.setRows(5);
        codeinput.setBorder(javax.swing.BorderFactory.createTitledBorder("CODE"));
        jScrollPane7.setViewportView(codeinput);

        jButton5.setBackground(new java.awt.Color(0, 153, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton5.setText("SUBMIT ANSWER");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        ansdesc.setColumns(20);
        ansdesc.setRows(5);
        ansdesc.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));
        jScrollPane8.setViewportView(ansdesc);

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
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        NextQ.setBackground(new java.awt.Color(153, 204, 255));
        NextQ.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        NextQ.setText("Next Question");
        NextQ.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        NextQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextQActionPerformed(evt);
            }
        });

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

        upvote.setFont(new java.awt.Font("Comic Sans MS", 3, 12)); // NOI18N
        upvote.setText("UPVOTE");
        upvote.setBorder(null);
        upvote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upvoteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Question published by:");

        upblish.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        upblish.setForeground(new java.awt.Color(255, 51, 51));
        upblish.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        upblish.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Voten.setText("Votes");

        vovv.setText("Votes");

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

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(tagy, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel10))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(upblish, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(boss, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addComponent(ansnum, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)
                                .addComponent(Voten, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codey, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel15)
                                .addGap(35, 35, 35)
                                .addComponent(dude, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(vovv, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(paneLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(v1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(desct, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NextQ, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upvote, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(downvote))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boss, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(desct, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(NextQ, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(upvote)
                        .addGap(8, 8, 8)
                        .addComponent(downvote, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(upblish, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tagy, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addComponent(hold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(v1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton12)
                                .addGap(18, 18, 18)
                                .addComponent(jButton13)
                                .addGap(56, 56, 56)
                                .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(codey, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ansnum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Voten))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(dude, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLayout.createSequentialGroup()
                                .addComponent(vovv)
                                .addGap(18, 18, 18)))))
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(pane);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        kGradientPanel2.add(jPanel12);
        jPanel12.setBounds(20, 130, 980, 600);

        Nom.setFont(new java.awt.Font("Segoe UI Semibold", 3, 12)); // NOI18N
        Nom.setForeground(new java.awt.Color(0, 204, 255));
        kGradientPanel2.add(Nom);
        Nom.setBounds(980, 10, 130, 20);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 999, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       try {
           UserHome frame =new UserHome();
           frame.setVisible(true);
           frame.getUser(username,useriden);
           
           this.dispose();
       } catch (InterruptedException ex) {
           Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String txt = srchtxt.getText();
        cherche(txt);
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //TO ADD ANSWERS
        if( stat.equals("open")){
            try {
                try {
                    Connection comm = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenlink","root","System");
                    String query ="INSERT INTO answers (`description`, `code`,`questionid`,`userid`) VALUES (?,?,?,?)";
                    PreparedStatement pst = comm.prepareStatement(query);
                    
                    pst.setString(1, ansdesc.getText());
                    pst.setString(2, codeinput.getText());
                    pst.setInt(3, Qid);
                    pst.setInt(4, useriden);
                    
                    pst.executeUpdate();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null,"Answer Submitted");
                new UserHome().setVisible(true);
                this.dispose();
            } catch (InterruptedException ex) {
                Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Question Closed");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            getvotesa();

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Questionview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Answerpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Answerpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Answerpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Answerpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(() -> {
           Answerpage An = new Answerpage();
            An.setVisible(true);
             
         });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chatpanel;
    private javax.swing.JTable Com;
    private javax.swing.JButton NextQ;
    private javax.swing.JLabel Nom;
    private javax.swing.JLabel Userlab;
    private javax.swing.JLabel Userlab1;
    private javax.swing.JLabel Userlab2;
    private javax.swing.JLabel Userlab4;
    private javax.swing.JLabel Voten;
    private javax.swing.JTextArea ansdesc;
    private javax.swing.JLabel ansnum;
    private javax.swing.JEditorPane answ;
    private javax.swing.JLabel boss;
    private javax.swing.JTextArea codeinput;
    private javax.swing.JLabel codey;
    private javax.swing.JLabel desct;
    private javax.swing.JButton downvote;
    private javax.swing.JLabel dude;
    private javax.swing.JPanel hold;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel13;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JPanel pane;
    private javax.swing.JTextField srchtxt;
    private javax.swing.JLabel tagy;
    private javax.swing.JPanel up;
    private javax.swing.JLabel upblish;
    private javax.swing.JButton upvote;
    private javax.swing.JLabel v;
    private javax.swing.JLabel v1;
    private javax.swing.JLabel vovv;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.openAnswer();
       try {
           this.answerinfo();
           //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       } catch (SQLException ex) {
           Logger.getLogger(Answerpage.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}

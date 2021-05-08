/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Formation;

import Entities.Formation;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.Date;
import services.ServiceFormation;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import static com.mycompany.ListSerie.MyApplication.theme;


/**
 *
 * @author User
 */
public class AjouterFormation extends Form {
//    
    String embededurl;
    ServiceFormation cs = ServiceFormation.getInstance();
    Form current;

    public AjouterFormation(Resources res)  {
        
       
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        
        


        Form AfficherFormation = null;
        this.getAllStyles().setBgImage(theme.getImage("backgroundForm.jpg"));
        this.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        getToolbar().addMaterialCommandToLeftBar(
                "",
                FontImage.MATERIAL_ARROW_BACK,
                (ev) -> AfficherFormation.show());
        
        
        Toolbar tb = new Toolbar(true);

        current = this;
        setToolbar(tb);
        
        tb.addSearchCommand(e -> {
            
        });
        
        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

        
        addTab(swipe,s1, res.getImage("logo.png"),",",res);
        
        //
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xcccccc);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xff2d55);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer,s1,s2);
       // add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Formations", barGroup);
        mesListes.setUIID("selectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        mesListes.setUIID("selectBar");
        RadioButton partage = RadioButton.createToggle("Ajout Formation", barGroup);
        mesListes.setUIID("selectBar");
        
        Label arrow = new Label(res.getImage("logo.png"),"Container");
        
        mesListes.addActionListener((e)->{
            
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInfiniteBlocking();
            refreshTheme();
            
        });
        
       // add(BorderLayout.CENTER,LayeredLayout.encloseIn(GridLayout.encloseIn(3, mesListes, liste, partage),FlowLayout.encloseBottom(arrow)));
        
        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
           
            arrow.setVisible(true);
            updatesArrowPosition(partage, arrow); 
        });
        
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        
        addOrientationListener(e ->{
           
            updatesArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()),arrow);
            
        });
        
        
        
        //
        
        
        Label FormTitle = new Label("Add New Video");
        FormTitle.getAllStyles().setAlignment(CENTER);
        
        TextField url = new TextField("", "URL", 15, TextField.EMAILADDR);
        url.setUIID("TextFieldBlack");
        addStringValue("url",url);
        
        
        TextField title = new TextField("", "Title", 15, TextField.EMAILADDR);
        title.setUIID("TextFieldBlack");
        addStringValue("title",title);
        
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());
        
        
        TextField description = new TextField("", "Description", 15, TextField.EMAILADDR);
        description.setUIID("TextFieldBlack");
        addStringValue("description",description);
        
        
        TextField domaine = new TextField("", "Domaine", 15, TextField.EMAILADDR);
        domaine.setUIID("TextFieldBlack");
        addStringValue("domaine",domaine);
        
        
        BrowserComponent browser = new BrowserComponent();
        browser.setVisible(false);
        url.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                if (!url.getText().isEmpty()) {
                    embededurl = "https://www.youtube.com/embed/";
                    String code = url.getText().substring(url.getText().length() - 11);
                    embededurl = embededurl + code;
                    browser.setURL(embededurl);
                    browser.setVisible(true);
                }
            }
        });
        Button Postvideo = new Button("Post Video");
        addStringValue("",Postvideo);
        
        Postvideo.getAllStyles().setAlignment(RIGHT);
        Postvideo.setUIID("FormationButton");
        Postvideo.addActionListener(e -> {
            try{
            if ((url.getText().length() == 0) || (title.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all fields", new Command("OK"));
            } else {
                
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                

                Formation f = new Formation(embededurl, String.valueOf(title.getText()).toString(), format.format(new Date()),String.valueOf(domaine.getText()).toString(),String.valueOf(description.getText()).toString());

                System.out.println("data formation =="+f);
                
                cs.AjouterFormation(f);
                
                iDialog.dispose();
                
                new AfficherFormation(res).show();
                
                refreshTheme();
            }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        
        Container labels = new Container(BoxLayout.yCenter()).addAll(title,
                url,domaine,description, dateLabel, Postvideo);
        Container by = new Container(new GridLayout(2, 1));
        by.addAll(
                labels,
                browser
        );
        add(BorderLayout.NORTH, FormTitle);
        add(BorderLayout.CENTER, by);
      //  add(BorderLayout.SOUTH, Postvideo);
    }
    
    private void addStringValue(String s , Component v){
        
        
        
//       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        //add(BorderLayout.CENTER,createLineSeparator(0xeeeeee));
        
    }
    
    
     private void addTab(Tabs swipe, Label spacer, Image image, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        if(image.getHeight()> Display.getInstance().getDisplayHeight()/2) {
            
            image = image.scaledHeight(Display.getInstance().getDisplayHeight()/2);
            
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
    
        imageScale.setUIID("container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overlay = new Label("","ImageOverlay");
        
        Container page1 = LayeredLayout.encloseIn(imageScale,overlay,BorderLayout.south(BoxLayout.encloseY(new SpanLabel(text,"LargeWhiteText"),spacer)));
        
        swipe.addTab("",res.getImage("logo.png"),page1);
 
    }
     
     
     
     public void bindButtonSelection(Button btn , Label l){
  
         btn.addActionListener(e -> {
         
         if(btn.isSelected()){
             updatesArrowPosition(btn,l);
         }
     });
     }

     
    private void updatesArrowPosition(Button btn, Label l) {
        
//        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
//        l.getParent().repaint();
    }

     


}

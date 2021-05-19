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
import com.mycompany.pidevapp.gui.BaseForm;


/**
 *
 * @author User
 */
public class AjouterFormation extends BaseForm {
//    
    String embededurl;
    ServiceFormation cs = ServiceFormation.getInstance();
    Form current;

    public AjouterFormation(Resources res)  {
        
       
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        
        super.installSidemenu(res);

        
        Label FormTitle = new Label("Ajouter Formation");
        FormTitle.getAllStyles().setAlignment(CENTER);
        
        TextField url = new TextField("", "URL", 15, TextField.EMAILADDR);
        addStringValue("url",url);
        
        
        TextField title = new TextField("", "Title", 15, TextField.EMAILADDR);
        addStringValue("title",title);
       
        
        
        Label dateLabel = new Label(new Date(System.currentTimeMillis()).toString());
        
        
        TextField description = new TextField("", "Description", 15, TextField.EMAILADDR);
        addStringValue("description",description);
        

        
        TextField domaine = new TextField("", "Domaine", 15, TextField.EMAILADDR);
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
        Button Postvideo = new Button("Ajouter");
        addStringValue("",Postvideo);
        
        Postvideo.getAllStyles().setAlignment(CENTER);

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
        

//        Container labels = new Container(BoxLayout.yCenter()).addAll(title,
//                url,domaine,description, dateLabel , new Label(" "));
//        labels.setUIID("SignInForm");
        Container by = new Container(new GridLayout(1, 1));
        by.addAll(
                
                browser
        );
        addAll(FormTitle,title,url,domaine,description, dateLabel ,Postvideo,by);
//        add(BorderLayout.NORTH,FormTitle);
//        add(BorderLayout.CENTER, by);

    }
    
    private void addStringValue(String s , Component v){

//       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        //add(BorderLayout.CENTER,createLineSeparator(0xeeeeee));
        
    }
    

}

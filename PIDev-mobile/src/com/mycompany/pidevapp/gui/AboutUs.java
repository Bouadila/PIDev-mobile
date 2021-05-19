/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pidevapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import static com.mycompany.ListSerie.MyApplication.theme;
import java.io.IOException;

/**
 *
 * @author Djo
 */
public class AboutUs extends BaseForm{
    
    public AboutUs(){
        installSidemenu(theme);
        setLayout(BoxLayout.y());
        final MapContainer cnt = new MapContainer("");
        cnt.setPreferredH(1000);
        cnt.setPreferredW(1000);
        cnt.setCameraPosition(new Coord(36.8993187, 10.1930525));//this breaks the code //because the Google map is not loaded yet
        Container container = new Container(new BorderLayout());
        Coord center = new Coord(36.8993187, 10.1930525);
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
                System.out.println("Map position updated: zoom="+zoom+", Center="+center);
            }
            
        });
        
        cnt.addLongPressListener(e->{
            System.out.println("Long press");
            ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
        cnt.addTapListener(e->{
            ToastBar.showMessage("Received tap at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
                Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
           cnt.setCameraPosition(new Coord(36.8993187, 10.1930525));
            cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCameraPosition(), "Hi marker", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Bounding box is "+cnt.getBoundingBox());
                    ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                }
            });
        cnt.zoom(center, 14);
        
        Font largeBoldMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        SpanLabel lb = createForFont(largeBoldMonospaceFont,"Recruitini est une, application  pour les chercheurs  d'emploi  et qui vise à rendre la vie  plus facile  pour les demandeurs  et surtout pour les jeunes chercheurs");
        Font font = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Label ll=createForFontl(largeBoldMonospaceFont,"Visitez notre bureau:");
        ll.setPreferredH(200);
        ll.getAllStyles().setFgColor(255);
        Container c = new Container(BoxLayout.y());
        ImageViewer Logo = null;
        try {
        Logo = new ImageViewer(Image.createImage("/recruitini-logo.png"));
        } catch (IOException ex) {
        }
        c.addAll(Logo ,lb, ll);
        container.add(BorderLayout.SOUTH, cnt).add(BorderLayout.NORTH, c);
        add(container);
    }
    
    private SpanLabel createForFont(Font fnt, String s) {
        SpanLabel l = new SpanLabel(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    private Label createForFontl(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    
}

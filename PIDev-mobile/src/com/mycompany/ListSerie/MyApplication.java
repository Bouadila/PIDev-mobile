package com.mycompany.ListSerie;

import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.pidevapp.gui.AboutUs;
import com.mycompany.pidevapp.gui.CalendarForm;
import com.mycompany.pidevapp.gui.FormAddOffre;
import com.mycompany.pidevapp.gui.QuizAddForm;
import com.mycompany.pidevapp.gui.SignInForm;
import com.mycompany.pidevapp.gui.TakeQuiz;
import java.util.Date;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    public static Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme_legacy");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
//        Form hi = new Form("Hi World", BoxLayout.y());
//        hi.add(new Label("Hi World"));
//        hi.show();
//            TextModeLayout tl = new TextModeLayout(3, 2);
//            Form f = new Form("Pixel Perfect", tl);
//
//            TextComponent title = new TextComponent().label("Title");
//            TextComponent price = new TextComponent().label("Price");
//            TextComponent location = new TextComponent().label("Location");
//            PickerComponent date = PickerComponent.createDate(new Date()).label("Date");
//            TextComponent description = new TextComponent().label("Description").multiline(true);
//
//            Validator val = new Validator();
//            val.addConstraint(title, new LengthConstraint(2));
//            val.addConstraint(price, new NumericConstraint(true));
//
//            f.add(tl.createConstraint().widthPercentage(60), title);
//            f.add(tl.createConstraint().widthPercentage(40), date);
//            f.add(location);
//            f.add(price);
//            f.add(tl.createConstraint().horizontalSpan(2), description);
//            f.setEditOnShow(title.getField());
//
//            f.show();
        AboutUs fo = new AboutUs();
        fo.show();

    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}
